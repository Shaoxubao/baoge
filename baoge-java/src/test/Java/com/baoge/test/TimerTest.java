package com.baoge.test;

import base.SpringTestBase;
import com.baoge.timer.BusinessCount;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author shaoxubao
 * @Date 2019/12/26 16:50
 */
public class TimerTest extends SpringTestBase {

    @Autowired
    private BusinessCount count;

    @Test
    public void testTimer() throws Exception {

        Thread.sleep(100000);
    }

}
