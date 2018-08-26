package hello_word_demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring配置文件，Spring的配置可以试Java配置，也可以是xml配置
 */
@Configuration
public class BeanConfig {

    @Bean
    public HelloWordService getHelloWordServiceBean() {
        return new HelloWordServiceImplA();
    }

}
