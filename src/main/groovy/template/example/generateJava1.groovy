import groovy.text.SimpleTemplateEngine

// Define the binding with nested objects
def binding = [
        person: [
                firstName: 'John',
                lastName: 'Doe',
                address: [
                        street: '123 Main St',
                        city: 'GroovyLand',
                        zip: '12345'
                ]
        ]
]

def engine = new SimpleTemplateEngine()
def templateText = new File('template1.gtpl').text
def template = engine.createTemplate(templateText)

// Apply the variables to the template and get the result
def writable = template.make(binding)

// Render the template
def result = writable.toString()

// Access the variable from the binding after rendering the template
def fullNameValue = binding.fullName

println "Template Output:"
println result
println "Accessed Variable Value:"
println "Full Name: ${fullNameValue}"
