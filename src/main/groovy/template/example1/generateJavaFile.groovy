import groovy.text.SimpleTemplateEngine

// Define the values to be replaced in the template
def binding = [
        className: 'Person',
        fields: '''
    private String name;
    private int age;
    ''',
        constructorParams: 'String name, int age',
        constructorBody: '''
    this.name = name;
    this.age = age;
    ''',
        methods: '''
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    '''
]

// Read the template file
def templateFile = new File('template.groovy')
def templateContent = templateFile.text

// Create the template engine
def engine = new SimpleTemplateEngine()
def template = engine.createTemplate(templateContent).make(binding)

// Generate the Java file
def outputFile = new File('Person.java')
outputFile.text = template.toString()

println "Java file generated successfully: ${outputFile.absolutePath}"
