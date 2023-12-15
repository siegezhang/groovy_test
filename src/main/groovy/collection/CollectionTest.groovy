package collection

def listOfMaps = [[k: 1, n: 'Name1', d1: 'a', d2: 'b'],
                  [k: 2, n: 'Name2', d1: 'c', d2: 'd'],
                  [k: 1, n: 'Name3', d1: 'e', d2: 'f'],
                  [k: 4, n: 'Name4', d1: 'g', d2: 'h']]


//find if there exist (or not) items, where k is equal, but n is not
listOfMaps.groupBy {
    it.k
}.values().findAll { l ->
    l.size() > 1 && (l.size() == l.unique { e -> e.n
    }.size())
}

var c = [1, 3, 4, 5].unique { it % 2 }
assert [1, 4] == [1, 3, 4, 5].unique { it % 2 }
assert [2, 3, 4] == [2, 3, 3, 4].unique { a, b -> a <=> b }

listOfMaps.groupBy {
    [it.k, it.n]
}.keySet().countBy {
    it[0]
}.any {
    it.value > 1
}

def r = listOfMaps.inject([:].withDefault {
    [].toSet()
}) { m, it -> m.get(it.k).add(it.n); m
}

println r.findAll {
    it.value.size() > 1
}

def find = {
    it.groupBy { it.k } // Fold one dimension
            .grep { it.value.size() > 1 } // filter results by that
            .grep { it.value*.n.unique().size() > 1 } // fold and repeat
            .collectEntries() // back to map
}

assert find(listOfMaps) == [1: [[k: 1, n: 'Name1', d1: 'a', d2: 'b'], [k: 1, n: 'Name3', d1: 'e', d2: 'f']]]

listOfMaps[2].n = "Name1" // Make them equals.
assert find(listOfMaps) == [:]

listOfMaps.groupBy([{ it.k }, { it.n }])
        .findAll { _, v -> v.size() > 1 }
        .collect { _, v ->
            v.values().collectMany {
                it
            }
        }


def sample = ['Groovy', 'Gradle', 'Grails', 'Spock'] as String[]

def result = sample.stream()  // Use stream() on array objects
        .filter { s -> s.startsWith('Gr') }
        .map { s -> s.toUpperCase() }
        .toList()  // toList() added to Stream by Groovy

assert result == ['GROOVY', 'GRADLE', 'GRAILS']


def numbers = [1, 2, 3, 1, 4, 2, 5, 6] as int[]

def even = numbers.stream()  // Use stream() on array objects
        .filter { n -> n % 2 == 0 }
        .toSet()  // toSet() added to Stream

assert even == [2, 4, 6] as Set

