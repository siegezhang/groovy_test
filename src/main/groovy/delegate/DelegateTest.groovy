package delegate


def x = 30
def y = 40

def run1() {
    def data = [x: 10, y: 20]
    def cl = { y = x + y }
    cl.delegate = data
    cl.resolveStrategy = Closure.DELEGATE_FIRST
    switch (cl.resolveStrategy) {
        case Closure.DELEGATE_FIRST:
            println "DELEGATE_FIRST"
            break
        case Closure.DELEGATE_ONLY:
            println "DELEGATE_ONLY"
            break
        case Closure.OWNER_ONLY:
            println "OWNER_ONLY"
            break
        case Closure.TO_SELF:
            println "TO_SELF"
            break
        case Closure.OWNER_FIRST:
            println "OWNER_FIRST"
            break

    }
    println "cl=" + cl()
    println "x" + x
    println "y" + y
}


class Delegate {

    def fun() {
        println "delegate"
    }
}

def fun() {
    println "owner"
}

def cl = {
    def fun = {
        println "self"
    }
    fun()
}

cl.delegate = new Delegate()
cl.resolveStrategy = Closure.DELEGATE_ONLY
switch (cl.resolveStrategy) {
    case Closure.DELEGATE_FIRST:
        println "DELEGATE_FIRST"
        break
    case Closure.DELEGATE_ONLY:
        println "DELEGATE_ONLY"
        break
    case Closure.OWNER_ONLY:
        println "OWNER_ONLY"
        break
    case Closure.TO_SELF:
        println "TO_SELF"
        break
    case Closure.OWNER_FIRST:
        println "OWNER_FIRST"
        break
}
cl()

def fun(Closure c) {
    def b=c.rehydrate('123','234','456')
    b.call()
}


fun {
    println this
    println delegate
    println owner
}





