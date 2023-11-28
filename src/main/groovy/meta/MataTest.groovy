package meta

import org.junit.jupiter.api.Test

class MataTest {
    @Test
    void test() {
        def s = 'Hello Groovy'
        String.metaClass {
            toMixedCase { delegate.toUpperCase() }
            toUpperCase { delegate.toLowerCase() }
            multiply { i -> delegate * i }
        }
        println s.toMixedCase()
        println s.toUpperCase()
        println s.multiply(3)

        String.metaClass.cons2var = { ->
            String res = ''
            delegate.toLowerCase().tokenize('_').each { e -> res += res ? e.capitalize() : e
            }
            res
        }
        println "SAMPLE_VAR".cons2var()
    }

    @Test
    void test1() {
        def someGroovyClass = new SomeGroovyClass()

        assert someGroovyClass.test() == 'method exists'
        assert someGroovyClass.someMethod() == 'called methodMissing someMethod []'
    }

    @Test
    void test2() {

        def pogo = new POGO()
        pogo.property = 'a'

        assert pogo.property == 'overridden'
        pogo.@property = 'a'
        assert pogo.property == 'a'
    }


    class SomeGroovyClass {

        def invokeMethod(String name, Object args) {
            return "called invokeMethod $name $args"
        }

        def methodMissing(String name, def args) {
            return "called methodMissing $name $args"
        }

        def test() {
            return 'method exists'
        }
    }

    class POGO {

        String property

        void setProperty(String name, Object value) {
            this.@"$name" = 'overridden'
        }
    }

}
