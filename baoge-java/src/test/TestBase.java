public class TestBase {

    private static final String ENV = "dev";

    {
        // 设置测试环境变量
        System.setProperty("env", ENV);
        System.out.println("set env to " + ENV);
    }
}
