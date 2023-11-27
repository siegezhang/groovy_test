package spaceship

import org.junit.jupiter.api.Test

class SpaceshipTest {
    @Test
    void test() {
        println "apple" <=> "orange" // -1
        println 5 <=> 4 // 1
         [1, 2, 3] <=> [1, 2, 4] // -1
    }
}
