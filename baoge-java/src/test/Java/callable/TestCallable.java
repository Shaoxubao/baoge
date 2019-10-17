package callable;

import base.SpringTestBase;
import com.baoge.calable.CallableServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author shaoxubao
 * @Date 2019/10/17 17:26
 */

public class TestCallable extends SpringTestBase {

    @Autowired
    private CallableServiceImpl callableService;

    @Test
    public void testCallable() {
        callableService.process();
    }


}
