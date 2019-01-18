package pattern.proxy;

public class HelloServiceImpl implements HelloService {


    @Override
    public String sayHello(String str) {
        return "HelloServiceImpl:" + str;
    }
}
