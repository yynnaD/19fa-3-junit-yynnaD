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
  'name' : 'Test Suite Application (broken implementations)',
  'description' : 'An incorrect SlipperySlot fails at least one of your tests (x7).',
  'points_possible' : 70,
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
  
  from subprocess import Popen, PIPE, STDOUT
  # Since JUnit can return many characters ( > 65536 ), we're using a
  # temporary file to catch the output instead.
  import tempfile
  
  badSSList = [ '4', '5', 'F', 'B', '1', 'S', '2' ]
  badSSCount = 1
  
  for badSS in badSSList:
    
    test_timeout = False
    # $ java -Dsabotage=%s -cp .:junit.jar 
    #         org.junit.playform.console.ConsoleLauncher -c TestSlipperySlot
    
    print('Running your test case against (broken) SlipperySlot implementation #%i...' % badSSCount)
    print('')
    
    junit_output = tempfile.NamedTemporaryFile()
    
    proc_junit = Popen( ["java",
                         "-Dsabotage=%s" % badSS,
                         "-cp", ".:junit.jar",
                         "org.junit.platform.console.ConsoleLauncher",
                         "-c", "TestSlipperySlot"],
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
      print('Your test suite may be performing WAY too many computations.')
      print('')
      test_case['feedback'] += 'Test timed out; took longer than 60 seconds to run SS %s\n' % badSS
      # We'll try to run all of them even with failures
      continue # To next broken SS
    # Test returned
    else:
      # Provide the junit output
      print('JUnit provided the following output:')
      print('-' * 10)
      with open( junit_output.name, 'r') as output:
        for line in output:
          print(line.rstrip())
      
      print('-' * 10)
      
      proc_junit.poll()
      # JUnit should return 1 -- expecting a failure here
      if ( proc_junit.returncode != 1 ):
        print('JUnit reports that all of your tests passed')
        print('A broken SlipperySlot slipped through your test cases!')
        test_case['feedback'] += 'SS %i passed all of your tests!\n' % badSSCount
        continue # To next broken SS
      # JUnit returned 1 -- task failed successfully
      else:
        print('JUnit reports that one (or more) of your tests failed')
        print('This is expected -- a successful failure!')
        print('')
        test_case['points_earned'] += test_case['points_possible'] / len(badSSList)
    # end else (no timeout)
    
    badSSCount += 1
    
  # end for badSS in badSSList
  
  ## Check if all three passed
  if ( test_case['points_earned'] == test_case['points_possible'] ):
    print('Your tests correctly found failures in all broken SlipperySlot implementations!')
    test_case['test_passed'] = True
  else:
    numSS = len(badSSList)
    numSSPassed = test_case['points_earned'] * numSS / test_case['points_possible']
    print('Your tests found failures in %i of %i broken SlipperySlot implementations.' % (numSSPassed, numSS))
  
  # Fix the float type
  test_case['points_earned'] = int( test_case['points_earned'] )
    
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