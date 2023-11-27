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
            delegate.toLowerCase().tokenize('_').each { e ->
                res += res ? e.capitalize() : e
            }
            res
        }
        println "SAMPLE_VAR".cons2var()
    }
}
