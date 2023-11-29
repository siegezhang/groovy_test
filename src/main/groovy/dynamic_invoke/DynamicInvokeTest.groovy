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




