package yaml


import groovy.yaml.YamlBuilder
import groovy.yaml.YamlSlurper

// Sample class and object to transform in YAML.
class User {
    String firstName, lastName, alias, website
}

def userObj = new User(firstName: 'Hubert', lastName: 'Klein Ikkink', alias: 'mrhaki', website: 'https://www.mrhaki.com/')


// Create YamlBuilder.
def config = new YamlBuilder()

config {
    application 'Sample App'
    version '1.0.1'
    autoStart true

    // We can nest YAML content.
    database {
        url 'jdbc:db//localhost'
    }

    // We can use varargs arguments that will
    // turn into a list.
    // We could also use a Collection argument.
    services 'ws1', 'ws2'

    // We can even apply a closure to each
    // collection element.
    environments(['dev', 'acc']) { env ->
        name env.toUpperCase()
        active true
    }

    // Objects with their properties can be converted.
    user(userObj)
}

assert config.toString() == '''\
---
application: "Sample App"
version: "1.0.1"
autoStart: true
database:
  url: "jdbc:db//localhost"
services:
- "ws1"
- "ws2"
environments:
- name: "DEV"
  active: true
- name: "ACC"
  active: true
user:
  firstName: "Hubert"
  lastName: "Klein Ikkink"
  alias: "mrhaki"
  website: "https://www.mrhaki.com/"
'''


def configYaml = '''\
---
application: "Sample App"
users:
- name: "mrhaki"
  likes:
  - Groovy
  - Clojure
  - Java
- name: "Hubert"
  likes:
  - Apples
  - Bananas
connections:
- "WS1"
- "WS2"
'''

// Parse the YAML.
def config1 = new YamlSlurper().parseText(configYaml)

assert config1.application == 'Sample App'

assert config1.users.size() == 2
assert config1.users[0] == [name: 'mrhaki', likes: ['Groovy', 'Clojure', 'Java']]
assert config1.users[1] == [name: 'Hubert', likes: ['Apples', 'Bananas']]

assert config1.connections == ['WS1', 'WS2']


// Create YAML file.
def yamlFile = new File("sample.yml")
// with YAML contents.
yamlFile.write('''\
---
sample: true
Groovy: "Rocks!"
''')

// Using File.withReader,
// so reader is closed by Groovy automatically.
yamlFile.withReader { reader ->
    // Use parse method of YamlSlurper.
    def yaml = new YamlSlurper().parse(reader)

    assert yaml.sample
    assert yaml.Groovy == 'Rocks!'
}


def multiDocYaml = '''\
---
version: 1
---
loadAtStartup: true
'''

// For YAML with multiple documents separated by ---
// we first need to remove the separators, otherwise
// only the first document is parsed.
def multiDoc = new YamlSlurper().parseText(multiDocYaml.replaceAll('---', ''))

assert multiDoc.version == 1
assert multiDoc.loadAtStartup
