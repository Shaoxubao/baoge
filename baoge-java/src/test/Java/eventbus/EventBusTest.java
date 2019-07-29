package eventbus;

import base.SpringTestBase;
import com.baoge.eventbus.UpdateOrderEvent;
import com.google.common.eventbus.EventBus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author shaoxubao
 * @Date 2019/7/29 12:05
 */
public class EventBusTest extends SpringTestBase {

    @Autowired
    private EventBus eventBus;

    @Test
    public void testEventBus() {
        UpdateOrderEvent event = new UpdateOrderEvent(432321L);
        eventBus.post(event);
    }
}
