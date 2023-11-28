package spaceship

import org.junit.jupiter.api.Test

class SpaceshipTest {
    @Test
    void test() {
        println "apple" <=> "orange" // -1
        println 5 <=> 4 // 1
        def list1 = [1, 2, 3]
        def list2 = [4, 5, 6]

        def result = []

        for (i in 0..list1.size() - 1) {
            result << list1[i] <=> list2[i]
        }

        println result
    }
}
