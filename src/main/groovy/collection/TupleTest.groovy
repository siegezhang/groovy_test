package collection

{
// We can define the types of the elements when we
// construct a tuple.
    def tuple3 = new Tuple3<String, Integer, BigDecimal>('add', 2, 40.0)

// We can use first, second, third properties
// to get values from the tuple.
    assert tuple3.v1 == 'add'
    assert tuple3.v2 == 2
    assert tuple3.v3 == 40.0

// We can use the [index] syntax to get element.
    assert tuple3[0] == 'add'

// Fully typed tuple.
    Tuple4<String, Integer, BigDecimal, Integer> tuple4 =
            new Tuple4<>('subtract', 100, 55.0, 3)

    assert tuple4.v1 == 'subtract'
    assert tuple4.v2 == 100
    assert tuple4.v3 == 55.0
    assert tuple4.v4 == 3
    assert tuple4[-1] == 3

// With subTuple we can get subsequent
// values from the tuple as a new tuple.
    assert tuple4.subTuple(2, tuple4.size()) == new Tuple2<BigDecimal, Integer>(55.0, 3)

// We can imagine how to work with Tuple4..Tuple8 :-)
// ...

// Finally a tuple with 9 items.
    def tuple9 = new Tuple9('Groovy', 'rocks', 'and', 'is', 'fun', 'to', 'use', 'as', 'language')

    assert tuple9.v5 == 'fun'
    assert tuple9.v6 == 'to'
    assert tuple9.v7 == 'use'
    assert tuple9.v8 == 'as'
    assert tuple9.v9 == 'language'

// Tuple extends AbstractList, so we can
// use all methods from List as well.
    assert tuple9.join(' ') == 'Groovy rocks and is fun to use as language'

}

{
    // Using the constructor to create a Tuple.
    def tuple2 = new Tuple2("Groovy", "Goodness")

// We can also use the static tuple method.
// Maximum number of elements is 16.
    def tuple3 = Tuple.tuple("Groovy", "is", "great")

    assert tuple3 instanceof Tuple3


// We can mix types as each elements can
// have it's own type.
    def mixed = Tuple.tuple(30, "minutes")

// We can use the subscript operator ([])
// to get a value.
    assert mixed[0] == 30
    assert mixed[1] == "minutes"

// Or use the get() method.
    assert mixed.get(0) instanceof Integer
    assert mixed.get(1) instanceof String

// Or use the getter/property V1/V2.
// For each element in a Tuple we can use that.
// Notice that the first element starts with v1.
    assert mixed.v1 == 30
    assert mixed.getV2() == "minutes"

// Or use multiple assignments.
    def (int minutes, String period) = mixed
    assert minutes == 30
    assert period == "minutes"


// We can get the size.
    assert mixed.size() == 2


// Or transform the elements to an array
// and type information is saved.
    assert mixed.toArray() == [30, "minutes"]

    assert mixed.toArray()[0].class.name == "java.lang.Integer"
    assert mixed.toArray()[1].class.name == "java.lang.String"


// Sample tuple with 4 elements.
    Tuple4 tuple4 = Tuple.tuple("Groovy", "rocks", "as", "always")

// We can use subList or subTuple to create a new Tuple
// with elements from the original Tuple.
// We need to specify the "from" and "to" index.
// The "to" index is exclusive.
    assert tuple4.subList(0, 2) == Tuple.tuple("Groovy", "rocks")
    assert tuple4.subTuple(0, 2) == Tuple.tuple("Groovy", "rocks")

// As Tuple extends from List we can use all
// Groovy collection extensions.
    assert tuple4.findAll { e -> e.startsWith("a") } == ["as", "always"]
    assert tuple4.collect { e -> e.toUpperCase() } == ["GROOVY", "ROCKS", "AS", "ALWAYS"]


// We can even create an empty Tuple.
    assert Tuple.tuple() instanceof Tuple0
}

{
    def tuple = new Tuple('one', 1, new Expando(number: 1))

    assert tuple.size() == 3

// To get the value of an element
// at a certain position we use
// the get(index) method.
    assert tuple.get(0) == 'one'

// We can use the [] syntax to
// get elements from the tuple.
    assert tuple[1] == 1

// We can use methods added to the
// Collection API by Groovy.
    assert tuple.last().number == 1

// We cannot change the tuple.
    try {
        tuple.add('extra')
        assert false
    } catch (UnsupportedOperationException e) {
        assert e
    }

    try {
        tuple.remove('one')
        assert false
    } catch (UnsupportedOperationException e) {
        assert e
    }

    try {
        tuple[0] = 'new value'
        assert false
    } catch (UnsupportedOperationException e) {
        assert e
    }


// Create a Tuple with fixed size
// of 2 elements, a pair.
    def pair = new Tuple2('two', 2)

// The Tuple2 class has extra methods
// getFirst() and getSecond() to
// access the values.
    assert pair.first == 'two'
    assert pair.second == 2


}

def calculate(String key, Integer... values) {
    // Method return a Tuple2 instance.
    new Tuple2(key, values.sum())
}

// Use multiple assignment to
// extract the values from the tuple.
// Tuple2 has typed objects.
def (String a, Integer b) = calculate('sum', 1, 2, 3)

assert a == 'sum'
assert b == 6