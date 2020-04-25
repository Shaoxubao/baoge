package callable;

import base.SpringTestBase;
import com.baoge.callable.CallableService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author shaoxubao
 * @Date 2019/10/17 17:26
 */

public class TestCallable extends SpringTestBase {

    @Autowired
    private CallableService callableService;

    @Test
    public void testCallable() {
        callableService.process();
    }


}
