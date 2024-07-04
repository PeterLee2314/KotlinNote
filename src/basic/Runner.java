package basic;

import basic.inheritance.Com;

public class Runner {
    public static void main(String[] args) {
        // originally MainKt now its First
        System.out.println(MainKt.max(5,8));

        Com.show(); // its from companion object @JvmStatic
    }
}
