# Create BetterSlotTest

with open( 'RunBSTest.java', 'wt' ) as testFile:
  testFile.write("""  
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.launcher.listeners.TestExecutionSummary.Failure;

import java.io.PrintWriter;

public class RunBSTest {
  
  public static void main(String[] args) {
  
    // Build the request
    LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
      .selectors(
        selectClass(BetterSlotTest.class)
      )
      .build();

    // Create a launcher  
    Launcher launcher = LauncherFactory.create();

    // Enable test result listener
    SummaryGeneratingListener listener = new SummaryGeneratingListener();
    launcher.registerTestExecutionListeners(listener);

    // Run the tests
    launcher.execute(request);

    // Get a summary of results
    TestExecutionSummary summary = listener.getSummary();
    
    // Iterate through the test failures
    int pointsDeducted = 0;
    for ( Failure failure : summary.getFailures() ) {
      System.out.println("Failed JUnit test: " 
        + failure.getTestIdentifier().getDisplayName() );
      pointsDeducted += 5;
    }
    System.exit(pointsDeducted);
  }
}

""")

from subprocess import Popen, PIPE, STDOUT

test_timeout = False
# $ javac <fileName>
proc_compile = Popen( ["javac",
                       "-cp",
                       ".:junit.jar",
                       "RunBSTest.java"],
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
    print('Compilation error: javac return code non-zero')
    print('Compiler output:')
    print('-' * 10)
    print( compile_output )
    print('-' * 10)
    print('Review the javac output above to diagnose compiler error')