package com.nexthoughts.dev.dslFanMovesdemo

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer
import org.codehaus.groovy.control.customizers.SecureASTCustomizer


def binding = new Binding([
        fan: new Fan(),
         * : FanMoves.values().collectEntries { [(it.name()): it] }
//        one: FanMoves.one,
//        two: FanMoves.two,
//        three: FanMoves.three,
//        four: FanMoves.four
])

CompilerConfiguration conf = new CompilerConfiguration();
SecureASTCustomizer customizer = new SecureASTCustomizer();

conf.addCompilationCustomizers(customizer);
customizer.closuresAllowed=false
customizer.setReceiversBlackList(Arrays.asList(System.class.getName()));
def importCustomizer = new ImportCustomizer()
//importCustomizer.addStaticStars FanMoves.class.name

conf.addCompilationCustomizers importCustomizer
//scripts evaluated with this configuration will inherit from that class
conf.scriptBaseClass = RobotBaseScriptClass.class.name
def shell = new GroovyShell(this.class.classLoader, binding,conf)
shell.evaluate(new File("test.groovy"))



//Using a base script class with a move method delegating to fan instance
abstract class RobotBaseScriptClass extends Script {

//    void move(FanMoves speed) {
//        //access the fan at through the scripts binding
    @Delegate @Lazy Fan fan = this.binding.fan
//        //The move method is at script level
//       fan.move speed
    }
//}



//Groovy supports the @Delegate annotation. With this annotation we can import all the methods of the class
// the annotation is used for. For example if we use the delegate annotation for the Date class
// we get all the methods of the Date class in our custom class.