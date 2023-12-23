package subscript_operator

def letters = ['a', 'b', 'c', 'd']

assert letters[0] == 'a'
assert letters[1] == 'b'

assert letters[-1] == 'd'
assert letters[-2] == 'c'

letters[2] = 'C'
assert letters[2] == 'C'

letters << 'e'
assert letters[4] == 'e'
assert letters[-1] == 'e'

assert letters[1, 3] == ['b', 'd']
assert letters[2..4] == ['C', 'd', 'e']


class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name
        this.age = age
    }

    def getAt(int i) {
        switch (i) {
            case 0: return name
            case 1: return age
        }
    }

    void putAt(int i, def value) {
        switch (i) {
            case 0: name = value; break
            case 1: age = value; break
        }
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}

def p = new Person("Mike", 31);
println p[0] // reading
println p[1] // reading
p[0] = "Sara" // writing
p[1] = 25


// Create a Date instance.
def date = new Date().parse('yyyy/MM/dd', '1973/07/09')

// Groovy adds the subscript operator for multiple
// fields to the Date class.
def output = date[Calendar.DATE, Calendar.MONTH, Calendar.YEAR]
assert output == [9, 6, 1973]

// The result is a list and we can destructurize it
// to assign values to variables (also called multiple assignments).
def (day, month, year) = date[Calendar.DATE, Calendar.MONTH, Calendar.YEAR]

assert "$day-${month + 1}-$year" == "9-7-1973"


