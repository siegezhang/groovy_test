package annotation

import groovy.transform.TupleConstructor
import org.junit.jupiter.api.Test

@TupleConstructor
class Person {
    String name
    Integer age
}

@TupleConstructor
class Person1 {
    String name
    String lastname
    Integer age
}

class AnnotationTest {
    @Test
    void test() {
        def person1 = new Person('Arturo', 26)
        def person2 = new Person('Luis', 61)
        def person3 = new Person('Laura', 19)
        def family = [] << person1 << person2 << person3

        assert family.max { it.age }.age == 61
        assert family.collect { it.age }.max() == 61
        assert family*.age.max() == 61
        def exists = false
        family.each { person ->
            if (person.age > 60) {
                exists = true
            }
        }
        assert exists == true

        def exists1 = family.inject(false) { found, person ->
            if (person.age > 60) {
                found = true
                return found
            }
        }
        assert exists1 == true

        assert family.any {
            it.age > 60
        }
    }

    @Test
    void test1() {
        def rafa = new Person1('Rafael', 'Luque', 36)
        def marcin = new Person1('Marcin', 'Gryszko', 34)
        def arturo = new Person1('Arturo', 'Herrero', 26)
        def osokers = [] << rafa << marcin << arturo << rafa
        assert osokers.unique(false).findAll { it.age > 30 }.sort { it.lastname } == [marcin, rafa]
        assert osokers == [rafa, marcin, arturo, rafa]
    }
}
