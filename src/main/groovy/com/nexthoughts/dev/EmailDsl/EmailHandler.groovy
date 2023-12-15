package com.nexthoughts.dev.EmailDsl

/**
 * Created by chetan on 26/5/16.
 */
class EmailHandler {
       void from(String value) {
           attributes["from"] = value
       }
       void to(String value) {
           attributes["to"] = value
       }
       void subject(String value) {
           attributes["subject"] = value
       }
       void body(Closure body) {
           attributes["body"] = [:]

           def handler = new BodyHandler()
           def code = body.rehydrate(handler, null, null)
           code.resolveStrategy = Closure.DELEGATE_ONLY
           code.call()
       }
   }

