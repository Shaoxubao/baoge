此包下举例说明Spring中bean三种注入方式的区别和用法：

    一、定义bean的三种途径：
    
        首先编写Student和Teacher两个类
        
            public class Student {
             
                private String name;
             
                private Teacher teacher;
             
                public String getName() {
                    return name;
                }
             
                public void setName(String name) {
                    this.name = name;
                }
             
                public Teacher getTeacher() {
                    return teacher;
                }
             
                public void setTeacher(Teacher teacher) {
                    this.teacher = teacher;
                }
             
            }
            
            public class Teacher {
             
                private String name;
             
                public String getName() {
                    return name;
                }
             
                public void setName(String name) {
                    this.name = name;
                }
             
            }

        
        方法一：基于XML的bean定义（需要提供setter方法）
        
            <?xml version="1.0" encoding="UTF-8"?>
            <beans xmlns="http://www.springframework.org/schema/beans"
                   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                   xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
             
                <bean id="student" class="test.Student">
                    <property name="name" value="张三"/>
                    <property name="teacher" ref="teacher"/>
                </bean>
             
                <bean id="teacher" class="test.Teacher">
                    <property name="name" value="李四"/>
                </bean>
             
            </beans>

            public class Main {
             
                public static void main(String args[]){
                    FileSystemXmlApplicationContext context=new FileSystemXmlApplicationContext("applicationContext.xml的绝对路径");
                    Student student= (Student) context.getBean("student");
                    Teacher teacher= (Teacher) context.getBean("teacher");
                    System.out.println("学生的姓名："+student.getName()+"。老师是"+student.getTeacher().getName());
                    System.out.println("老师的姓名："+teacher.getName());
                }
             
            }
        
        方法二：基于注解的bean定义（不需要提供setter方法）
            Spring为此提供了四个注解，这些注解的作用与上面的XML定义bean效果一致，在于将组件交给Spring容器管理。组件的名称默认是类名（首字母变小写），也可以自己修改：
            @Component：当对组件的层次难以定位的时候使用这个注解
            @Controller：表示控制层的组件
            @Service：表示业务逻辑层的组件
            @Repository：表示数据访问层的组件
            使用这些注解的时候还有一个地方需要注意，就是需要在applicationContext.xml中声明<contex:component-scan...>一项，指明Spring容器扫描组件的包目录。
            
            @Component("teacher")
            public class Teacher {
             
                @Value("李四")
                private String name;
             
                public String getName() {
                    return name;
                }
             
            }

            @Component("student")
            public class Student {
             
                @Value("张三")
                private String name;
             
                @Resource
                private Teacher teacher;
             
                public String getName() {
                    return name;
                }
             
                public Teacher getTeacher() {
                    return teacher;
                }
             
            }

            <?xml version="1.0" encoding="UTF-8"?>
            <beans xmlns="http://www.springframework.org/schema/beans"
                   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                   xmlns:context="http://www.springframework.org/schema/context"
                   xsi:schemaLocation="http://www.springframework.org/schema/beans
                   http://www.springframework.org/schema/beans/spring-beans.xsd
                   http://www.springframework.org/schema/context
                   http://www.springframework.org/schema/context/spring-context.xsd">
             
                <!--扫描组件的包目录-->
                <context:component-scan base-package="test"/>
             
            </beans>

            public class Main {
             
                public static void main(String args[]){
                    FileSystemXmlApplicationContext context=new FileSystemXmlApplicationContext("applicationContext.xml的绝对路径");
                    Student student= (Student) context.getBean("student");
                    Teacher teacher= (Teacher) context.getBean("teacher");
                    System.out.println("学生的姓名："+student.getName()+"。老师是"+student.getTeacher().getName());
                    System.out.println("老师的姓名："+teacher.getName());
                }
             
            }
        
            
        方法三：基于Java类的bean定义（需要提供setter方法）
            @Configuration
            public class BeansConfiguration {
             
                @Bean
                public Student student(){
                    Student student=new Student();
                    student.setName("张三");
                    student.setTeacher(teacher());
                    return student;
                }
             
                @Bean
                public Teacher teacher(){
                    Teacher teacher=new Teacher();
                    teacher.setName("李四");
                    return teacher;
                }
             
            }
            public class Main {
             
                public static void main(String args[]){
                    AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(BeansConfiguration.class);
                    Student student= (Student) context.getBean("student");
                    Teacher teacher= (Teacher) context.getBean("teacher");
                    System.out.println("学生的姓名："+student.getName()+"。老师是"+student.getTeacher().getName());
                    System.out.println("老师的姓名："+teacher.getName());
                }
             
            }

    二、Spring的自动注入
    
        Spring提供了五种自动装配的类型
        
            no：顾名思义， 显式指明不使用Spring的自动装配功能
            byName：根据属性和组件的名称匹配关系来实现bean的自动装配
            byType：根据属性和组件的类型匹配关系来实现bean的自动装配，有多个适合类型的对象时装配失败
            constructor：与byType类似是根据类型进行自动装配，但是要求待装配的bean有相应的构造函数
            autodetect：利用Spring的自省机制判断使用byType或是constructor装配
        
        基于XML的自动装配
        
            <?xml version="1.0" encoding="UTF-8"?>
            <beans xmlns="http://www.springframework.org/schema/beans"
                   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                   xsi:schemaLocation="http://www.springframework.org/schema/beans
                   http://www.springframework.org/schema/beans/spring-beans.xsd">
             
                <bean id="student" class="test.Student" autowire="byName">
                    <property name="name" value="张三"/>
                </bean>
             
                <bean id="teacher" class="test.Teacher">
                    <property name="name" value="李四"/>
                </bean>
             
            </beans>
            这里我并没有显式为Student对象注入Teacher属性，而是使用autowired="byName"代替，这样一来Spring会帮我们处理这些细节，将名字是teacher的组件注入到Student对象中。
        
        基于注解的自动装配
            
            其实上面已经应用过了，这里再提一下@Resource和@Autowired的区别。@Resource默认是使用byName进行装配，@Autowired默认使用byType进行装配。
            
            @Component("teacher")
            public class Teacher {
             
                @Value("李四")
                private String name;
             
                public String getName() {
                    return name;
                }
             
                public void setName(String name) {
                    this.name = name;
                }
            }
            
            @Component("student")
            public class Student {
             
                @Value("张三")
                private String name;
             
                @Resource
                private Teacher teacher;
             
                public String getName() {
                    return name;
                }
             
                public void setName(String name) {
                    this.name = name;
                }
             
                public Teacher getTeacher() {
                    return teacher;
                }
             
                public void setTeacher(Teacher teacher) {
                    this.teacher = teacher;
                }
            }

三、如何进行选择？

    其实说了这么多，我们应该如何对这些不同方式进行权衡和选择呢？这个见仁见智，我说说我常用的方式。
    定义bean：一般我使用基于注解的bean定义。这样可以摆脱使用XML或是Java类对大量bean进行配置的噩梦，让程序变得简洁。注解还可以清楚地指明组件所在的层次。但是也有特殊的情况，比如说配置数据源，也许某个组件并不是你写的（来自于Spring或是第三方jar包里面的组件等），你没有办法在这些组件里面加上这些注解使之成为Spring容器管理的bean（别人也不会为你加上这些注解，因为他们不知道你会使用到哪些组件）。这种情况下就得使用XML或是Java类进行配置了，个人比较喜欢XML配置。如下例子：
    
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
            <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
            <property name="url" value="jdbc:mysql://localhost:3306/study"/>
            <property name="username" value="账号"/>
            <property name="password" value="密码"/>
    </bean>
        
    自动装配：一般我使用基于注解的自动装配。同样也是为了减少XML配置文件的“篇幅”。
    通过使用基于注解的bean定义和自动装配，大大减少了XML配置文件的长度，增加了程序的可读性。
        