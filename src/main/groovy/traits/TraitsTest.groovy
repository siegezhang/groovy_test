package traits

import org.junit.jupiter.api.Test

trait Extra {
    String extra() { "I'm an extra method" }
}

class Something {
    String doSomething() { 'Something' }
}

trait A {
    String methodFromA() {
        'methodFromA'
    }
}

trait B {
    String methodFromB() {
        'methodFromB'
    }
}

class C {}

class TraitsTest {
    @Test
    void test() {
        //use of the as keyword to coerce an object to a trait at runtime
        def s = new Something() as Extra
        println s.extra()
        println s.doSomething()
    }

    @Test
    void test1() {
        def c = new C()
        def d = c.withTraits A, B
        println d.methodFromA()
        println d.methodFromB()
    }
}
