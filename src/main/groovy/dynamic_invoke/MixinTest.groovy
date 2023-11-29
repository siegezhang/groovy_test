package dynamic_invoke

class Parrot {
    static String speak(String text) {
        /"$text" Polly wants a cracker!/
    }
}

// Runtime mixin on String object instead of class.
String s = 'Groovy is Gr8'
s.metaClass.mixin Parrot

assert s.speak() == '"Groovy is Gr8" Polly wants a cracker!'


String other = 'Groovy and Grails'
try {
    println other.speak()
} catch (MissingMethodException e) {
    assert e.message.startsWith('No signature of method: java.lang.String.speak() is applicable for argument types: () values: []')
}

String.metaClass.mixin Parrot

println other.speak()


class A {
    String methodFromA() { 'A' }
}

class B {
    String methodFromB() { 'B' }
}

A.metaClass.mixin B
def o = new A()
assert o.methodFromA() == 'A'
assert o.methodFromB() == 'B'
assert o instanceof A
assert !(o instanceof B)

george = new B()
george.mixin("say") { something -> println something }
george.say("Hello, world!")
//george = new B().mixin(FighterType)
//william = new B()
//georage.getFighter()  // returns
//william.getFighter()   //error
