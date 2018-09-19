package reflect;

public class Person {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void work() {
        System.out.println(text);
    }
}
