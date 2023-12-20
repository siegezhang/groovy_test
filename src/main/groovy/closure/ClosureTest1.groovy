package closure


Closure closure = {
    append("I'm inside a closure")
}
StringBuilder sb = new StringBuilder()
closure.delegate = sb
closure.call()

assert sb.toString() == "I'm inside a closure"
