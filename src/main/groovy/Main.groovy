import groovy.transform.Immutable

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


@Immutable
class Coordinates {
    Double latitude, longitude
}

def c1 = new Coordinates(latitude: 48.824068, longitude: 2.531733)
def c2 = new Coordinates(48.824068, 2.531733)
assert c1 == c2


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

// Procedural style
def count = 0
for (i in (1..1000)) {
    if (i % 2) {
        count += ("$i".size())
    }
}
assert count == 1445
// Functional style
def count1 = (1..1000).findAll { it % 2 }
        .collect { "$it" }
        .inject(0) { sum1, num -> sum1 + num.size() }
assert count == 1445


//class Person1 {
//    @Lazy
//    String name = 'Arturo'
//}
//
//def person = new Person1()
//assert !(person.dump().contains('Arturo'))
//assert person.name.size() == 6
//assert person.dump().contains('Arturo')

