# Create TestShapes.java
# Compile TestShapes.java

from subprocess import Popen, PIPE, STDOUT

test_timeout = False
# $ tar -xzf travis.tar.gz
proc_compile = Popen( ["tar", 
                       "-xzf",
                       "./travis/travis.tar.gz"],
                          stdout=PIPE,
                          stderr=STDOUT,
                          text=True )
    
try:
  proc_compile.wait( timeout=60 ) # Seconds
except TimeoutError:
  proc_compile.kill()
  test_timeout = True
    
compile_output = proc_compile.stdout.read()
    
# timeout error
if ( test_timeout ):
  print('Timeout error: javac took longer than 60 seconds to complete.')
# test returned
else:
  proc_compile.poll()
  # compiler error
  if ( proc_compile.returncode != 0 ):
    print('Grading script error: tar failed to unpack properly')
    print('Console output:')
    print('-' * 10)
    print( compile_output )
    print('-' * 10)
    print('Review the output above to diagnose the error')