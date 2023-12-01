package ast

/** * Class which purpose is to store multi language strings * key: iso6391Code * value: text in native language */
class I18NMap extends HashMap<String, String> {

    private static final String SEPARATOR = ":"

    public I18NMap() {
        super()
    }

    public I18NMap(List values) {
        init(values)
    }

    public I18NMap(String[] values) {
        init(values)
    }

    private init(values) {
        values.each { value ->
            def lang = decodeValue(value)
            if (lang.size() == 2) this.put(lang[0], lang[1])
        }
    }

    private String[] decodeValue(String value) {
        if (value == "") return []
        def pair = value.split(SEPARATOR, 2)
        if (pair.size() != 2) {
            throw new RuntimeException("invalid language map entry: $value")
        }
        return pair
    }
}