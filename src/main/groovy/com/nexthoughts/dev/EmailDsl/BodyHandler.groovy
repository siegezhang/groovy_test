package com.nexthoughts.dev.EmailDsl

/**
 * Created by chetan on 26/5/16.
 */
class BodyHandler {

      def methodMissing(String name, def args) {
          attributes["body"][name] = args
      }
   }
