package closure

void append(String text) {
    println text
}

Closure closure = {
    append("I'm inside a closure")
}
StringBuilder sb = new StringBuilder()
closure.delegate = sb
closure.call()

assert sb.toString() == ""
