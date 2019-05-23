package anonymous;

/**
 * @Author shaoxubao
 * @Date 2019/5/23 9:15
 */
public class AnonymousTest {

    public static void main(String[] args) {
        String word = "hello";

        ISayHelloService service = new ISayHelloService() {
            @Override
            public String sayHello(String word) {
                return word;
            }
        };


        System.out.println(service.sayHello(word));
    }

}
