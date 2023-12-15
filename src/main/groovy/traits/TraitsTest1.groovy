package traits

trait Name {
    String salutation, firstName, lastName

    String getFullName() {
        [salutation, firstName, lastName].join(' ')
    }
}

class User {
    String username, password
}

def user = new User() as Name
user.with {
    salutation = 'Mr.'
    firstName = 'Hubert'
    lastName = 'Klein Ikkink'
    username = 'mrhaki'
    password = '*****'
}

assert user.fullName == 'Mr. Hubert Klein Ikkink'
assert user.username == 'mrhaki'


trait Id {
    Long id
}

trait Version {
    Long version = 0
}

trait Active {
    Date from = new Date()
    Date to = null

    boolean isActive() {
        final Date now = new Date()
        from < now && (!to || to > now)
    }
}

class Person {
    String username
}

def person = new Person(username: 'mrhaki')
def domainPerson = person.withTraits Id, Version, Active

domainPerson.id = 1

assert domainPerson.username == 'mrhaki'
assert domainPerson.id == 1
assert domainPerson.version == 0
assert domainPerson.active


/**
 * Simple interface with one method to
 * transform a String value.
 */
interface Transformer {
    String transform(String input)
}

/** Default trait will return the input value unchanged. */
trait DefaultTransformer implements Transformer {
    String transform(String input) {
        input
    }
}

/** Transform the String value to upper case */
trait Upper implements Transformer {
    String transform(String input) {
        super.transform(input.toUpperCase())
    }
}

/** Remove 'mr' from input String value. */
trait Filter implements Transformer {
    String transform(String input) {
        super.transform(input - 'mr')
    }
}

/**
 * Simple class uses three traits. The value property get method
 * returns the transformed value.
 */
class StringTransformer implements DefaultTransformer, Upper, Filter {
    String value

    String getValue() { transform(value) }
}

// Create StringTransformer instance.
def transformer = new StringTransformer(value: 'mrhaki')

assert transformer.value == 'HAKI'


// Use same traits, but in different order.
class OtherStringTransformer implements DefaultTransformer, Filter, Upper {
    String value

    String getValue() { transform(value) }
}

// Create OtherStringTransformer instance.
def otherTransformer = new OtherStringTransformer(value: 'mrhaki')

// The Filter trait cannot find 'mr',
// because the String value is already in
// upper case after the Upper trait.
assert otherTransformer.value == 'MRHAKI'


/** Only chain input values smaller than 5 characters. */
trait SmallFilter implements Transformer {
    String transform(String input) {
        if (input.size() < 5) {
            super.transform(input)
        } else {
            ''
        }
    }
}

class SmallStringTransformer implements DefaultTransformer, Upper, SmallFilter {
    String value

    String getValue() { transform(value) }
}

def smallTransformer = new SmallStringTransformer(value: 'mrhaki')
assert smallTransformer.value == ''

smallTransformer.value = 'haki'
assert smallTransformer.value == 'HAKI'

