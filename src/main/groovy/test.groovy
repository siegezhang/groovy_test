
def c ={
    i ,j->
        println  "groovy $i $j"
}

println c.getMaximumNumberOfParameters()
println c.getParameterTypes()


class Test {
    def x = 30
    def y = 40

    def run() {
        def data = [x: 10, y: 20]
        def cl = { y = x + y }
        cl.delegate = data
        cl.resolveStrategy = Closure.DELEGATE_FIRST
        switch (cl.resolveStrategy) {
            case Closure.DELEGATE_FIRST:
                println "DELEGATE_FIRST"
                break;
            case Closure.DELEGATE_ONLY:
                println "DELEGATE_ONLY"
                break;
            case Closure.OWNER_ONLY:
                println "OWNER_ONLY"
                break;
            case Closure.TO_SELF:
                println "TO_SELF"
                break;
            case Closure.OWNER_FIRST:
                println "OWNER_FIRST"
                break;

        }
        println "cl=" + cl()
        println "x" + x
        println "y" + y
    }
}

new Test().run()
