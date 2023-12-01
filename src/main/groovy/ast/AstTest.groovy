package ast

import org.codehaus.groovy.ast.ClassHelper
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.ast.expr.ArgumentListExpression
import org.codehaus.groovy.ast.expr.ConstructorCallExpression
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.stmt.ReturnStatement

def ast = new ReturnStatement(new ConstructorCallExpression(ClassHelper.make(Date),
        ArgumentListExpression.EMPTY_ARGUMENTS))

assert ast instanceof ReturnStatement


def ast1 = new AstBuilder().buildFromSpec {
    returnStatement {
        constructorCall(Date) { argumentList {} }
    }
}

assert ast1[0] instanceof ReturnStatement


def ast2 = new AstBuilder().buildFromString('new Date()')
assert ast2[0] instanceof BlockStatement
assert ast2[0].statements[0] instanceof ReturnStatement


@LogTime
def hello() {
    println "Hello world - start"
    // Do some stuff
    Object.sleep 1000
    println "Hello world - end"
}

hello()


