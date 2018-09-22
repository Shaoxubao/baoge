import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

/**
 * 需要用到spring类测试时继承此类，自动加载spring相关的配置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config-spring/spring-conf.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
                         DirtiesContextTestExecutionListener.class,
                         DbUnitTestExecutionListener.class})
public class SpringTestBase extends TestBase {
}
