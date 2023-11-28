import groovy.transform.TupleConstructor

//不好的写法
book = 'Fooled by Randomness'
book = "$book - Nassim Taleb"
book = "$book (2001)"
assert 'Fooled by Randomness - Nassim Taleb (2001)' == book

//好的写法
book = 'Fooled by Randomness'
bookWithAuthor = "$book - Nassim Taleb"
completeBook = "$bookWithAuthor (2001)"
assert 'Fooled by Randomness - Nassim Taleb (2001)' == completeBook

//不好的写法
years = [2001, 2002]
years << 2003
years += [2004, 2005]
assert [2001, 2002, 2003, 2004, 2005] == years

//好的写法
years = [2001, 2002]
allYears = years + 2003 + [2004, 2005]
assert [2001, 2002, 2003, 2004, 2005] == allYears


//不好的写法
def list = ['Gr', 'vy']
list.addAll 1, 'oo'
assert list == ['Gr', 'oo', 'vy']

//好的写法
def list1 = ['Gr', 'vy']
assert list1.plus(1, 'oo') == ['Gr', 'oo', 'vy']
assert list1 == ['Gr', 'vy']


//@Immutable
//class Coordinates {
//    Double latitude, longitude
//}
//
//def c1 = new Coordinates(latitude: 48.824068, longitude: 2.531733)
//def c2 = new Coordinates(48.824068, 2.531733)
//assert c1 == c2


def closure = { 'Hello world!' }
assert closure() == 'Hello world!'

def sum = { a, b -> a + b }
assert sum(2, 3) == 5

def square = { it * it }
assert square(9) == 81

final BASE = 1000
def salary = { variable -> BASE + variable }
assert salary(500) == 1500

//Turn Methods into Closures
def salary(variable) {
    final BASE = 1000
    BASE + variable
}

assert salary(500) == 1500
def salaryClosure = this.&salary
assert salaryClosure(500) == 1500


//Closures Composition
def minutesToSeconds = { it * 60 }
def hoursToMinutes = { it * 60 }
def daysToHours = { it * 24 }
def hoursToSeconds = minutesToSeconds << hoursToMinutes
def daysToSeconds = hoursToSeconds << daysToHours
assert daysToSeconds(1) == 86400


def upper = { it.toUpperCase() }
def firstLetter = {
    it.charAt(0)
}
def words = ["Don't", "Repeat", "Yourself"]
def acronym = words.collect(firstLetter >> upper).join()
assert acronym == 'DRY'


//不好的写法
def result = []
[1, 2, 3, 4].each {
    if (it > 2) {
        result << it
    }
}
assert result == [3, 4]
//好的写法
assert [1, 2, 3, 4].findAll {
    it > 2
} == [3, 4]

//不好的写法
def result1 = []
[1, 2, 3].each { result1 << it * 2 }
assert result1 == [2, 4, 6]

//好的写法
assert [1, 2, 3].collect {
    it * 2
} == [2, 4, 6]

//不好的写法
def total = 0
[1, 2, 3].each {
    total += it
}
assert total == 6


//好的写法
def total1 = [1, 2, 3].inject(0) { acc, n -> acc + n
}
assert total1 == 6

//不好的写法
def result2
try {
    [1, 2, 3].each {
        (it > 1) {
            if (result2 > 1) {
                result2 = it
                throw new Exception()
            }
        }
    }
} catch (exception) {

}
//assert result2 == 2

//好的写法
assert [1, 2, 3].find {
    it > 1
} == 2

//
//@TupleConstructor
//class Person {
//    String name
//    Integer age
//}
//
//def person1 = new Person('Arturo', 26)
//def person2 = new Person('Luis', 61)
//def person3 = new Person('Laura', 19)
//def family = [] << person1 << person2 << person3
//assert family.max { it.age }.age == 61
//assert family.collect { it.age }.max() == 61
//assert family*.age.max() == 61
//
//
//def exists = false
//family.each { person ->
//    if (person.age > 60) {
//        exists = true
//    }
//}
//assert exists == true
//
//def exists1 = family.inject(false) { found, person ->
//    if (person.age > 60) {
//        found = true
//        return found
//    }
//}
//assert exists1 == true
//
//assert family.any {
//    it.age > 60
//} == true


@TupleConstructor
// import groovy.transform.*
class Person {
    String name
    String lastname
    Integer age
}

def rafa = new Person('Rafael', 'Luque', 36)
def marcin = new Person('Marcin', 'Gryszko', 34)
def arturo = new Person('Arturo', 'Herrero', 26)
def osokers = [] << rafa << marcin << arturo << rafa
assert osokers.unique(false).findAll { it.age > 30 }.sort { it.lastname } == [marcin, rafa]
assert osokers == [rafa, marcin, arturo, rafa]


// Procedural style
def count = 0
for (i in (1..1000)) {
    if (i % 2) {
        count += ("$i".size())
    }
}
assert count == 1445
// Functional style
def count1 = (1..1000).findAll { it % 2 }.collect { "$it" }.inject(0) { sum1, num -> sum1 + num.size() }
assert count == 1445

//
//class Person1 {
//    @Lazy    String name = 'Arturo'
//}
//
//def person = new Person1()
//assert !(person.dump().contains('Arturo'))
//assert person.name.size() == 6
//assert person.dump().contains('Arturo')

