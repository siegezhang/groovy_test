package spread_dot

class Language {
    String lang

    def speak() { "$lang speaks." }
}

// Create a list with 3 objects. Each object has a lang
// property and a speak() method.
def list = [
        new Language(lang: 'Groovy'),
        new Language(lang: 'Java'),
        new Language(lang: 'Scala')
]

// Use the spread-dot operator to invoke the speak() method.
assert ['Groovy speaks.', 'Java speaks.', 'Scala speaks.'] == list*.speak()
assert ['Groovy speaks.', 'Java speaks.', 'Scala speaks.'] == list.collect { it.speak() }

// We can also use the spread-dot operator to access
// properties, but we don't need to, because Groovy allows
// direct property access on list members.
assert ['Groovy', 'Java', 'Scala'] == list*.lang
assert ['Groovy', 'Java', 'Scala'] == list.lang


def map = [:]

map."an identifier with a space and double quotes" = "ALLOWED"
map.'with-dash-signs-and-single-quotes' = "ALLOWED"

assert map."an identifier with a space and double quotes" == "ALLOWED"
assert map.'with-dash-signs-and-single-quotes' == "ALLOWED"

map.'single quote'
map."double quote"
map.'''triple single quote'''
map."""triple double quote"""
map./slashy string/
map.$/dollar slashy string/$

def firstname = "Homer"
map."Simpson-${firstname}" = "Homer Simpson"

assert map.'Simpson-Homer' == "Homer Simpson"


//The closure is a parameterless closure which doesn’t take arguments.
def sParameterLessClosure = "1 + 2 == ${-> 3}"
assert sParameterLessClosure == '1 + 2 == 3'

//Here, the closure takes a single java.io.StringWriter argument, to which you can append content with the << leftShift operator.
// In either case, both placeholders are embedded closures.
def sOneParamClosure = "1 + 2 == ${w -> w << 3}"
assert sOneParamClosure == '1 + 2 == 3'


//We define a number variable containing 1 that we then interpolate within two GStrings, as an expression in eagerGString and as a closure in lazyGString.
def number = 1
def eagerGString = "value == ${number}"
def lazyGString = "value == ${-> number}"

assert eagerGString == "value == 1"
assert lazyGString == "value == 1"

number = 2
assert eagerGString == "value == 1"
assert lazyGString == "value == 2"

def fooPattern = /.*foo.*/
assert fooPattern == '.*foo.*'


def escapeSlash = /The character \/ is a forward slash/
assert escapeSlash == 'The character / is a forward slash'