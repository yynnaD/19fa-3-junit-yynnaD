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
  'name' : 'Test Suite Submission Test',
  'description' : 'Your repository contains "TestSlipperySlot.java" ',
  'points_possible' : 5,
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
    return ( test_case, skip_remaining_tests )
  else:
    test_case['test_ran'] = True
  
  
  from subprocess import Popen, PIPE, STDOUT
  
  print('Looking for "TestSlipperySlot.java" within your repository...')
  test_timeout = False
  # $ test -e Hello.java
  proc_exists = Popen( ["test", "-e", "TestSlipperySlot.java"] )
  
  try:
    proc_exists.wait( timeout=60 ) # Seconds
  except TimeoutError:
    proc_exists.kill()
    test_timeout = True
  
  # Timeout failure
  if ( test_timeout ):
    print('Timeout error: test took longer than 60 seconds to complete')
    print('Do you have a LOT of files in your repository?')
    print('')
    test_case['feedback'] = 'Test timed out; took longer than 60 seconds to run'
    skip_remaining_tests = True
  # test returned
  else:
    proc_exists.poll()
    # No file "Hello.java"
    if ( proc_exists.returncode != 0 ):
      print('No file named "TestSlipperySlot.java" exists in your repository.')
      print('Check that:')
      print(' - The file was added to git tracking and committed')
      print(' - The file is in your repository root directory (and not, e.g., ./src/Hello.java')
      print(' - The file has the exact name "TestSlipperySlot.java"')
      
      test_case['feedback'] = 'File "TestSlipperySlot.java" not found in your repository'
      skip_remaining_tests = True
      
    # File exists
    else:
      print('A file named "TestSlipperySlot.java" was found in your repository.')
      
      test_case['points_earned'] = test_case['points_possible']
      test_case['test_passed'] = True
    
    if ( skip_remaining_tests ):
      print('All JUnit tests will fail without this file present; skipping remaining tests...')
     
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