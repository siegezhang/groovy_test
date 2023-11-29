package dynamic_invoke


class Dog {
    def bark() { println "woof!" }

    def sit() { println "(sitting)" }

    def jump() { println "boing!" }
}

def doAction(animal, action) {
    animal."$action"() //action name is passed at invocation
}

def rex = new Dog()

doAction(rex, "bark")
doAction(rex, "jump")


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


