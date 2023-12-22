package dsl.game

//https://www.infoworld.com/article/2077904/scripting-jvm-languages-creating-dsls-in-java-part-4-where-metaprogramming-matters.html?page=4
class GameDSL {
    def playersAndScores = [:]

    def players(String[] names) {
        names.each { playersAndScores[it] = 0 }
    }

    def getResult() {
        def max = -1
        def winner = ''
        playersAndScores.each { name, score ->
            if (score > max) {
                max = score
                winner = name
            }
        }

        println "Winner is $winner with a score of $max"
    }

    def methodMissing(String name, args) {
        if (playersAndScores.containsKey(name) && args.length == 1 && args[0] instanceof Integer) {
            playersAndScores[name] = args[0]
        } else {
            throw new MissingMethodException(name, this.class, args)
        }
    }

    def propertyMissing(String name) { name }


    def static process(dsl) {
        def closure = (Closure) new GroovyShell().evaluate("{->" + dsl + "}")
        closure.delegate = new GameDSL()
        closure()
    }
}

GameDSL.process(new File('game.dsl').text)
