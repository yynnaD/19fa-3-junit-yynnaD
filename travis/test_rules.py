# *****
# Test case template:
# 1. Define test_case object
# 2. Define do_test( test_data, test_case, skip_remaining_tests )
# 3. Call ( test_data, skip_remaining) = library.run_before_test( test_case )
# 4. Perform test
# 5. Calll library.run_after_test( test_case, skip_remaining_tests )

import library

# *****
# 1. Define test_case object
# - Required fields:
# - - name
# - - description
# - - points_possible
# - - points_earned (initialize to zero)
# - - test_ran (initialize to False)
# - - test_passed (initialize to False)
# *****

test_case = {
  'name' : 'BetterSlot Rules Testing',
  'description' : 'Your BetterSlot correctly implements the SSM rules.',
  'points_possible' : 50,
  'points_earned' : 0,
  'test_ran' : False,
  'test_passed' : False,
  'feedback' : '',
}

# *****
# 2. Define do_test
# Inputs:
# - test_case : Defined above; update fields within do_test
# - skip_remaining_tests : Update within do_test
# Outputs:
# - test_case : Updated test case data
# - skip_remaining_tests : Updated skip flag
# *****

def do_test( test_case, skip_remaining_tests ):
  
  # Short-circuit if skipping
  if ( skip_remaining_tests ):
    print('Skipping due to an earlier error...')
    return ( test_case, skip_remaining_tests )
  else:
    test_case['test_ran'] = True
  
  # Create a "wrapper" java program that will determine how many tests failed
  import test_create_betterslottest
  
  from subprocess import Popen, PIPE, STDOUT
  # Since JUnit can return many characters ( > 65536 ), we're using a
  # temporary file to catch the output instead.
  import tempfile
   
  test_timeout = False
  # $ java -Dsabotage=%s -cp .:junit.jar 
  #         org.junit.playform.console.ConsoleLauncher -c TestSlipperySlot
  
  print('Running your test case against a correct SlipperySlot implementation...')
  print('')
   
  junit_output = tempfile.NamedTemporaryFile()
    
  proc_junit = Popen( ["java",
                       "-cp", ".:junit.jar",
                       "RunBSTest"],
                       stdout=junit_output,
                       stderr=STDOUT,
                       text=True )
    
  try:
    proc_junit.wait(timeout=60) # Seconds
  except TimeoutExpired:
    proc_junit.kill()
    test_timeout = True
    
  # timeout error
  if ( test_timeout ):
    print('Timeout error: junit took longer than 60 seconds to run your tests')
    print('Your BetterSlot may be running VERY slow')
    print('')
    test_case['feedback'] = 'Test timed out; took longer than 60 seconds to run'
    # We'll try to run all of them even with failures
  # Test returned
  else:
    # Provide the junit output
    print('The following test failures were detected:')
    print('-' * 10)
    with open( junit_output.name, 'r') as output:
      for line in output:
        print(line.rstrip())
    
    print('-' * 10)
    
    proc_junit.poll()
    # JUnit should return 0 -- expecting no failures
    if ( proc_junit.returncode != 0 ):
      print('JUnit reports that one (or more) test failed')
      print('Your BetterSlot does not correctly implement all rules!')
      test_case['feedback'] = 'BetterSlot does not correctly implement all rules.'
      test_case['points_earned'] = max(
        test_case['points_possible'] - proc_junit.returncode, 0 )
    else:
      print('JUnit reports that all of your tests passed')
      print('Your BetterSlot correctly implements all rules!')
      test_case['points_earned'] = test_case['points_possible']
      test_case['test_passed'] = True
  #  
    
  return test_case, skip_remaining_tests 
   

# *****
# 3. Call run_before_test
# *****
( test_data, skip_remaining_tests ) = library.run_before_test( test_case )

# *****
# 4. Perform test
# *****

(test_case, skip_remaining_tests) = do_test( test_case, skip_remaining_tests )

# *****
# 5. Call run_after_test
# *****
library.run_after_test( test_data, test_case, skip_remaining_tests )