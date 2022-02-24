import org.junit.Test;
import problem1.Driver;

import static org.junit.Assert.assertTrue;

public class TestProblem1 {
    @Test(timeout=5000)
    public void test(){
        for(int i = 0; i < 10000; i++){
            assertTrue(Driver.driver());
        }
    }
}
