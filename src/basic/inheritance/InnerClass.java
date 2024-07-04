package basic.inheritance;

interface Human3 {
    void think();
}

public class InnerClass {
    public static void main(String[] args) {
        Human3 programmer = new Human3() {
            @Override
            public void think() {
                System.out.println("Hello");
            }
        };
    }
}
