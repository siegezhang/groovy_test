package closure

import org.junit.jupiter.api.Test

class ClosureTest {
    @Test
    void test() {

        def closure = { 'Hello world!' }
        assert closure() == 'Hello world!'

        def sum = { a, b -> a + b }
        assert sum(2, 3) == 5

        def square = { it * it }
        assert square(9) == 81

        final BASE = 1000
        def salary = { variable -> BASE + variable }
        assert salary(500) == 1500
    }
    //Turn Methods into Closures
    def salary(variable) {
        final BASE = 1000
        BASE + variable
    }

    @Test
    void test1() {
        assert salary(500) == 1500
        def salaryClosure = this.&salary
        assert salaryClosure(500) == 1500
    }

    @Test
    void test2() {
        //Closures Composition
        def minutesToSeconds = { it * 60 }
        def hoursToMinutes = { it * 60 }
        def daysToHours = { it * 24 }
        def hoursToSeconds = minutesToSeconds << hoursToMinutes
        def daysToSeconds = hoursToSeconds << daysToHours
        assert daysToSeconds(1) == 86400
    }

    @Test
    void test3() {
        def upper = { it.toUpperCase() }
        def firstLetter = {
            it.charAt(0)
        }
        def words = ["Don't", "repeat", "Yourself"]
        def acronym = words.collect(firstLetter >> upper).join()
        assert acronym == 'DRY'
    }
}
