package closure

void append(String text) {
    println text
}

Closure closure = {
    append("I'm inside a closure")
}
StringBuilder sb = new StringBuilder()
closure.delegate = sb
closure.resolveStrategy = Closure.DELEGATE_FIRST
closure.call()

assert sb.toString() == "I'm inside a closure"
