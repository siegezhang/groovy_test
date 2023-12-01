package ast

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/** * Annotation used to mark a class field as multi language. * It will aware the I18N AST Transformer to add an extra field to support multi language. * The new field will be an instance of {@link com.example.I18NMap} */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
@GroovyASTTransformationClass("com.example.I18NASTTransformation")
@interface I18N {

    /** * The name of the field to be added, otherwise the name will be the same * as the annotated field name prepending an i letter. i.e: iFirstName * @return */
    String fieldName() default ''
}