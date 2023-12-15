package dsl

{
// Create new FileTreeBuilder. Default the current
// directory is the base directory for creating new
// files and directories.
// We can pas another directory in the constructor as
// the base directory.
    final FileTreeBuilder treeBuilder = new FileTreeBuilder(new File('tree'))

// Add a file and set the contents using
// a closure. The delegate of the closure
// is the File object.
    treeBuilder.file('README.adoc') {
        write '''\
        = Groovy rocks!
 
        Hidden features in Groovy are also cool.
 
        '''.stripIndent()
    }

// Append to file contents with a String argument.
    treeBuilder.file('README.adoc', '== Extra heading')

    final File sample = new File('sample')
    sample.text = '''\
= Another sample
 
Testing the Groovy FileTreeBuilder.
'''

// Or we use another File's contents to append to a file.
    treeBuilder.file('README1.adoc', sample)

// Create a new directory.
    treeBuilder.dir('out')

// Create subdirectories and files
// using a closure. The delegate is
// is FileTreeBuilder again.
    treeBuilder.dir('src') {
        dir('docs') {
            file('manuscript.adoc') {
                // Another way to write file contents.
                withWriter('UTF-8') { writer -> writer.write '= Building Apps With Grails 3'
                }
            }
        }
    }

    assert new File('tree/README.adoc').exists()
    assert new File('tree/src/docs/manuscript.adoc').exists()
    assert new File('tree/src/docs/manuscript.adoc').text == '= Building Apps With Grails 3'

}

{

// We can even use a shorter syntax with the
// FileTreeBuilder where the node names are the name
// of a directory to be created (argument is a closure),
// or the name of a file and some contents.
// Notice that with the DSL all file contents is
// appended to existing file contents.
// We need to delete an existing file first if we
// don't want to append the contents.

    final File newDir = new File('dsl')

// Remove existing dir, so file contents is
// only set by the FileTreeBuilder DSL,
// otherwise content is added to the existing files.
    if (newDir.exists()) {
        newDir.deleteDir()
    }

    newDir.mkdirs()
    final FileTreeBuilder dir = new FileTreeBuilder(newDir)

    dir {
        // Node name is the file name, followed by the contents.
        'README.adoc'('''\
        = Groovy rocks!
 
        Hidden features in Groovy are also cool.
 
        '''.stripIndent())

        'README.adoc'('== Extra heading')

        // We cannot use a closure argument with this DSL,
        // like with the builder. The DSL assume that a node with a
        // closure is a directory.
        // But we can use the File object argument to set
        // the file contents.
        'README1.adoc'(sample)
    }

// If name is follwed by closure than a directory
// name is assumed and created.
    dir.out {}

// Created directory with subdirectories.
    dir.src {
        // The name of the node is the directory name.
        docs {
            // And create file in the src/docs directory.
            'manuscript.adoc'('= Building Apps With Grails 3')
        }
    }

    assert new File('dsl/README.adoc').exists()
    assert new File('dsl/src/docs/manuscript.adoc').exists()
    assert new File('dsl/src/docs/manuscript.adoc').text == '= Building Apps With Grails 3'
}