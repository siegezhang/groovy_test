package com.nexthoughts.dev.EmailDsl

def email = new EmailSender()
email.send {
   from    'dsl@groovy.com'
   to      'greach@greachconf.com'
   subject 'Easy DSL'

   body {
       p 'Welcome DSL'
   }
}