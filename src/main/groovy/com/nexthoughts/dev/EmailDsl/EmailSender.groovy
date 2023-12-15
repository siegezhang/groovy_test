package com.nexthoughts.dev.EmailDsl

/**
 * Created by chetan on 26/5/16.
 */
class EmailSender {
    Map attributes = [:]

    def send(Closure cls) {
        def handler = new EmailHandler()
        //1st argument is delegate
        //2nd argument is owner
        //3rd is this
        def code = cls.rehydrate(handler, null, null)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code.call()//call the new closure
        println handler.attributes
    }
}