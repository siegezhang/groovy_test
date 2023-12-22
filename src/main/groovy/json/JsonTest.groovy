package json


import groovy.json.JsonBuilder
import groovy.json.StreamingJsonBuilder
import groovy.transform.Immutable

// Example class.
@Immutable
class Villain {
    String name
}


{

// A list of Villain objects that needs to transformed
// to a JSON array.
    def list = ['The Joker', 'Penguin', 'Catwoman', 'Harley Quinn'].collect { name -> new Villain(name) }

// We create a new JsonBuilder and
// use the list of Villain objects
// as argument for the constructor
// to create a root JSON array.
    def json1 = new JsonBuilder(list)

    assert json1.toString() == '[{"name":"The Joker"},{"name":"Penguin"},{"name":"Catwoman"},{"name":"Harley Quinn"}]'


// Here we use the no-argument constructor
// to create a JsonBuilder.
// Then we use the instance implicit
// method call with the list of Villain
// objects as arguments
    def json2 = new JsonBuilder()
    json2(list)

    assert json2.toString() == '[{"name":"The Joker"},{"name":"Penguin"},{"name":"Catwoman"},{"name":"Harley Quinn"}]'

}

{


// A list of Villain objects that needs to transformed
// to a JSON array.
    def list = ['The Joker', 'Penguin', 'Catwoman', 'Harley Quinn'].collect { name -> new Villain(name) }

// Here we use the no-argument constructor
// to create a JsonBuilder.
// Then we use the instance implicit
// method call with the list of Villain
// objects as arguments
    def json = new StringWriter()
    def jsonBuilder = new StreamingJsonBuilder(json)
    jsonBuilder(list)

    assert json.toString() == '[{"name":"The Joker"},{"name":"Penguin"},{"name":"Catwoman"},{"name":"Harley Quinn"}]'
}


{
    // A list of Villain objects that needs to transformed
// to a JSON array.
    def list = ['The Joker', 'Penguin', 'Catwoman', 'Harley Quinn'].collect { name -> new Villain(name) }

// We create a new JsonBuilder and
// then we use the instance implicit
// method call with the list of Villain
// objects as arguments and closure
// to transform each Villain object
// in the root JSON array.
    def json1 = new JsonBuilder()
    json1(list) { Villain villain ->
        name villain.name
        initials villain.name.split(' ').collect { it[0].toUpperCase() }.join()
    }

    assert json1.toString() == '[{"name":"The Joker","initials":"TJ"},{"name":"Penguin","initials":"P"},{"name":"Catwoman","initials":"C"},{"name":"Harley Quinn","initials":"HQ"}]'


// We can use the same implicit
// method call for a list and
// closure to transform each element
// to a root JSON array.
    def json = new StringWriter()
    def json2 = new StreamingJsonBuilder(json)
    json2(list) { Villain villain ->
        name villain.name
        initials villain.name.split(' ').collect { it[0].toUpperCase() }.join()
    }

    assert json.toString() == '[{"name":"The Joker","initials":"TJ"},{"name":"Penguin","initials":"P"},{"name":"Catwoman","initials":"C"},{"name":"Harley Quinn","initials":"HQ"}]'

}

{
    StringWriter writer = new StringWriter()
    StreamingJsonBuilder builder = new StreamingJsonBuilder(writer)
    builder.records {
        car {
            name 'HSV Maloo'
            make 'Holden'
            year 2006
            country 'Australia'
            record {
                type 'speed'
                description 'production pickup truck with speed of 271kph'
            }
        }
    }
    println writer.toString()
}

ObjectGraphBuilder a=new ObjectGraphBuilder()


