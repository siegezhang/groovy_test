// Import necessary libraries
import groovy.text.SimpleTemplateEngine

def templateFile = new File("template.gtpl") // Replace with your template path
def templateText = templateFile.text

def binding = [
        className: "MyClass", // Replace with desired class name
        name: "defaultName" // Replace with desired default name
]

def engine = new SimpleTemplateEngine()
def template = engine.createTemplate(templateText)

def output = template.make(binding).toString()

def javaFile = new File("MyClass.java") // Replace with desired output filename
javaFile.write(output)

println "Java class generated: MyClass.java"
