package com.nexthoughts.dev.EmailDsl

/**
 * Created by chetan on 26/5/16.*/
class EmailSender {
    Map attributes = [:]
    //闭包中有delegate、owner、this三个成员变量，调用闭包没有的属性/方法时，会尝试在这三个变量上调用
    //this指向闭包外部的Object，指定义闭包的类。
    //owner指向闭包外部的Object/Closure，指直接包含闭包的类或闭包。
    //delegate默认和owner一致，指用于处理闭包属性/方法调用的第三方对象，可以修改。
    //在闭包构造时this和owner就已经确定并传入，是只读的。如果需要修改，可以用Closure.rehydrate()方法克隆新的闭包，同时设置其this和owner。
    //Closure还有一个resolveStrategy属性，有多种值（OWNER_FIRST、DELEGATE_FIRST、OWNER_ONLY、DELEGATE_ONLY、TO_SELF），
    // 默认为OWNER_FIRST，表示调用闭包没有定义的属性/方法时，先尝试从owner取，再尝试从delegate取。
    def send(Closure cls) {
        def handler = new EmailHandler()
        //1st argument is delegate
        //2nd argument is owner
        //3rd is this
        def code = cls.rehydrate(handler, null, null)
        code.resolveStrategy = Closure.DELEGATE_FIRST
        code.call()//call the new closure
        println handler.attributes
    }
}