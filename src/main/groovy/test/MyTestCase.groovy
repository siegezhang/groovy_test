package test

import groovy.test.GroovyTestCase
import groovy.test.NotYetImplemented

class MyTestCase extends GroovyTestCase {

    void testAssertions() {
        assertTrue(1 == 1)
        assertEquals("test", "test")

        def x = "42"
        assertNotNull "x must not be null", x
        assertNull null

        assertSame x, x
    }

    void testScriptAssertions() {
        assertScript '''
        def x = 1
        def y = 2

        assert x + y == 3
    '''
    }

    void testInvalidIndexAccess1() {
        def numbers = [1, 2, 3, 4]
        shouldFail {
            numbers.get(4)
        }
    }

    void testInvalidIndexAccess2() {
        def numbers = [1, 2, 3, 4]
        shouldFail IndexOutOfBoundsException, {
            numbers.get(4)
        }
    }

    void testInvalidIndexAccess3() {
        def numbers = [1, 2, 3, 4]
        def msg = shouldFail IndexOutOfBoundsException, {
            numbers.get(4)
        }
        assert msg.contains('Index: 4, Size: 4')
    }

    @NotYetImplemented
    void testNotYetImplemented2() {
        assert 1 == 2
    }

}