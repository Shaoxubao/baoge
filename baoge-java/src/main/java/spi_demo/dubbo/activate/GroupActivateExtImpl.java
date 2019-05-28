package spi_demo.dubbo.activate;

import com.alibaba.dubbo.common.extension.Activate;
import spi_demo.dubbo.ActivateExt;

/**
 * @Author shaoxubao
 * @Date 2019/5/28 17:59
 */

@Activate(group = {"group1", "group2"})
public class GroupActivateExtImpl implements ActivateExt {

    @Override
    public String echo(String msg) {
        return msg;
    }
}
