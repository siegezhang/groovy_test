package extension

import org.junit.jupiter.api.Test

class StaticStringExtensionTest {


    @Test
    void test() {
        assert String.greeting() == 'Hello, world!'
    }
}
