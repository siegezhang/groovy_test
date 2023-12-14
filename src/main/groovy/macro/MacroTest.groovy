package macro

def languages = ["Java", "Groovy", "Clojure"]

// Use NV macro to create a NamedValue object
// with name and val properties containing the
// variable name and variable value.
def namedValue = NV(languages)

assert namedValue.name == "languages"
assert namedValue.val == ["Java", "Groovy", "Clojure"]
assert namedValue.class.name == "groovy.lang.NamedValue"


def alias = "mrhaki"
def name = "Hubert"
def age = 49

// We can pass multiple objects with the NVL macro
// and we get a List with multiple NamedValue objects.
def namedValues = NVL(alias, name, age)
assert namedValues.size() == 3
assert namedValues == [new NamedValue("alias", "mrhaki"), new NamedValue("name", "Hubert"), new NamedValue("age", 49)]

// We can use Groovy collection methods.
assert namedValues[0].name == "alias"
assert namedValues[0].val == "mrhaki"
assert namedValues.name == ["alias", "name", "age"]
assert namedValues.val == ["mrhaki", "Hubert", 49]
assert namedValues.find { nv -> nv.name == "age" }.val == 49