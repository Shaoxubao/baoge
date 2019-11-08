package com.baoge.distribute_id.zk;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author shaoxubao
 * @Date 2019/11/8 15:51
 */

@Slf4j
public class ZKIDMakerTester {

    @Test
    public void testMakeId() {
        ZKIDMaker idMaker = new ZKIDMaker();
        idMaker.init();
        String nodeName = "/test/IDMaker/ID-";

        for (int i = 0; i < 10; i++) {
            String id = idMaker.makeId(nodeName);
            log.info("第" + i + "个创建的id为:" + id);
        }
        idMaker.destroy();

    }

}
