package com.nexthoughts.dev.DzoneMemoExample

/**
 * Created by chetan on 26/5/16.
 */
MemoDsl.make {
    to "Nirav Assar"
    from "Barack Obama"
    body "How are things? We are doing well. Take care"
    idea "The economy is key"
    request "Please vote for me"
    xml
}
//The last line in the DSL can also be changed to 'html' or 'text'. This affects the output format.