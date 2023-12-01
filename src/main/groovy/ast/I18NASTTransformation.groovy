package ast

import org.codehaus.groovy.ast.*
import org.codehaus.groovy.control.CompilationFailedException
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

/** * AST Transforamtion class in charge of adding a new multi language field * on the fields that were marked with {@link com.example.I18N} */
@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
class I18NASTTransformation implements ASTTransformation {

    @Override
    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {
        if (safeToAddProperty(astNodes)) {
            def annotationNode = (AnnotationNode) astNodes[0]
            def fieldNode = (FieldNode) astNodes[1]
            addProperty(annotationNode, fieldNode)
        } else {
            throw new CompilationFailedException("@I18N annotation failed")
        }
    }

    private void addProperty(AnnotationNode annotationNode, FieldNode fieldNode) {
        def classNode = fieldNode.owner
        def propertyName = getFieldName(annotationNode, fieldNode)
        if (!containsField(classNode, propertyName)) {
            def field = new FieldNode(propertyName, FieldNode.ACC_PRIVATE, new ClassNode(LanguageMap), new ClassNode(classNode.class), null)
            classNode.addProperty(new PropertyNode(field, PropertyNode.ACC_PUBLIC, null, null))
        }
    }

    private String getFieldName(AnnotationNode annotationNode, FieldNode fieldNode) {
        def fieldName = annotationNode.getMember('fieldName')?.value
        return fieldName ?: "i${capitalizeFirst(fieldNode.name)}".toString()
    }

    private Boolean safeToAddProperty(ASTNode[] astNodes) {
        def invalid = (!astNodes || astNodes.length != 2 || !(astNodes[1] instanceof FieldNode) || ((FieldNode) astNodes[1]).type.name != String.name || !(astNodes[0] instanceof AnnotationNode) || ((AnnotationNode) astNodes[0]).classNode.name != I18N.name)
        return !invalid
    }

    private boolean containsField(ClassNode classNode, String propertyName) {
        classNode.fields.find { fieldNode -> fieldNode.name == propertyName
        } != null
    }

    private static String capitalizeFirst(str) {
        char[] array = str.toCharArray()
        array[0] = Character.toUpperCase(array[0])
        return new String(array)
    }
}