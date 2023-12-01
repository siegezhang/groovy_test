package ast

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.stmt.ExpressionStatement
import org.codehaus.groovy.ast.stmt.Statement
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.syntax.Token
import org.codehaus.groovy.syntax.Types
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
class LogTimeASTTransformation extends AbstractASTTransformation {


    void visit(ASTNode[] nodes, SourceUnit sourceUnit) {
        println '================================================================'
        println sourceUnit
        List<MethodNode> methods = sourceUnit.getAST()?.getMethods()
        methods.findAll { MethodNode method -> method.getAnnotations(new ClassNode(LogTime))
        }.each { MethodNode method ->
            String startTimeVarName = randomVarName()
            String endTimeVarName = randomVarName()
            String totalTimeVarName = randomVarName()

            //Statement initialTime = createSystemNanoTimeAst(startTimeVarName)
            Statement initialTime = getInitialTimeFromString(startTimeVarName)
            Statement endTime = createSystemNanoTimeAst(endTimeVarName)
            Statement executionTime = createExecutionTimeAst(totalTimeVarName, startTimeVarName, endTimeVarName)
            Statement printTime = createPrintlnAst(totalTimeVarName)

            List existingStatements = method.getCode().getStatements()
            existingStatements.add(0, initialTime)
            existingStatements.add(endTime)
            existingStatements.add(executionTime)
            existingStatements.add(printTime)
        }
    }

    private static Statement createSystemNanoTimeAst(String var) {
        return new ExpressionStatement(new DeclarationExpression(new VariableExpression(var),
                Token.newSymbol(Types.EQUALS, 0, 0),
                new MethodCallExpression(new ClassExpression(new ClassNode(System)),
                        "nanoTime",
                        MethodCallExpression.NO_ARGUMENTS)))
    }

    private static ExpressionStatement getInitialTimeFromString(String varName) {
        List<ASTNode> nodes = new AstBuilder().buildFromString("def ${varName} = System.nanoTime()")

        BlockStatement block = (BlockStatement) nodes.get(0)
        List<Statement> statements = block.getStatements()
        if (statements != null && statements.size() > 0) {
            return new ExpressionStatement(statements.get(0).expression)
        }

        return null
    }

    private static Statement createExecutionTimeAst(String totalTimeVarName, String startTimeVarName, String endTimeVarName) {
        return new ExpressionStatement(new DeclarationExpression(new VariableExpression(totalTimeVarName),
                Token.newSymbol(Types.EQUALS, 0, 0),
                new BinaryExpression(new BinaryExpression(new VariableExpression(endTimeVarName),
                        Token.newSymbol(Types.MINUS, 0, 0),
                        new VariableExpression(startTimeVarName),),
                        Token.newSymbol(Types.DIVIDE, 0, 0),
                        new ConstantExpression(new Integer(1000000)))))
    }

    private static Statement createPrintlnAst(String message) {
        return new ExpressionStatement(new MethodCallExpression(new VariableExpression("this"),
                new ConstantExpression("println"),
                new ArgumentListExpression(new VariableExpression(message))))
    }

    private static String randomVarName() {
        def pool = ['a'..'z', 'A'..'Z'].flatten()
        Random rand = new Random(System.currentTimeMillis())

        def chars = (0..10).collect { pool[rand.nextInt(pool.size())] }
        def rndString = chars.join()

        return "${rndString}_${new Date().time}"
    }

}
