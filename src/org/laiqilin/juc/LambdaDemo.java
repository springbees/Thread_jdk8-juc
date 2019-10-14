package org.laiqilin.juc;

//函数式接口注解
@FunctionalInterface
interface Foo {
    //没有参数
    //public void sayHello();

    //带参数接口
    public int add(int x, int y);

    default int hello(int x, int y) {
        return x + y;
    }
}

public class LambdaDemo {

    public static void main(String[] args) {

        //new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();

       /* Foo foo = new Foo () {
            @Override
            public int add(int x, int y) {
                return x + y;
            }

            @Override
            public void sayHello() {
                System.out.println ("传统写法------>.sayHello");
            }
        };
        foo.sayHello ();
*/
        Foo lambdaFoo = (int x, int y) -> {
            System.out.println ("Lambda表达式写法----->.main");
            return x + y;
        };
        System.out.println (lambdaFoo.hello (8, 8));
        System.out.println (lambdaFoo.hello (8, 8));
    }
}
