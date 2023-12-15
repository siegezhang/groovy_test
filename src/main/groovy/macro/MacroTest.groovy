package macro

{
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
}
{
    def languages = ["Java", "Groovy", "Clojure"]

// With SV the toString method for the object is used.
// The name of the variable is also in our output.
    assert SV(languages) == "languages=[Java, Groovy, Clojure]"
    assert SV(languages).class.name == "org.codehaus.groovy.runtime.GStringImpl"

// With SVI the inspect method for the object is used.
    assert SVI(languages) == "languages=['Java', 'Groovy', 'Clojure']"
    assert SVI(languages).class.name == "org.codehaus.groovy.runtime.GStringImpl"

// We cannot assert here as the output contains the object instance representation
// and that changes with each run.
    SVD(languages)
    // Possible output: languages=<java.util.ArrayList@8f636a77 elementData[Java, Groov, Clojure] size=3 modCount=3>
    assert SVI(languages).class.name == "org.codehaus.groovy.runtime.GStringImpl"


// We can pass multiple objects to the SV, SVI and SVD macros.
    def alias = "mrhaki"
    def name = "Hubert"
    def age = 49

    assert SV(alias, name, age) == "alias=mrhaki, name=Hubert, age=49"
    assert SVI(alias, name, age) == "alias='mrhaki', name='Hubert', age=49"
    SVD(alias, name, age)
    // Possible output: alias=<java.lang.String@c077733c value=[109, 114, 104, 97, 107, 105] coder=0 hash=-1065913540 hashIsZero=false>, name=<java.lang.String@817bc072 value=[72, 117, 98, 101, 114, 116] coder=0 hash=-2122596238 hashIsZero=false>, age=<java.lang.Integer@31 value=49>

}