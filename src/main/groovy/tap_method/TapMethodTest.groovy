package tap_method;

import org.junit.jupiter.api.Test;

/**
 * Sample class with some properties
 * and a method.*/
class Sample {

    String username, email

    List<String> labels = []

    void addLabel(value) {
        labels << value
    }

}

class TapMethodTest {
    @Test
    void test() {
        // Use tap method to create instance of
// Sample and set properties and invoke methods.
        def sample =
                new Sample().tap {
                    assert delegate.class.name == 'static_method.Sample'

                    username = 'mrhaki'
                    email = 'email@host.com'
                    addLabel 'Groovy'
                    addLabel 'Gradle'

                    // We use tap, an alias for with(true),
                    // so the delegate of the closure,
                    // the Sample object, is returned.
                }

        assert sample.labels == ['Groovy', 'Gradle']
        assert sample.username == 'mrhaki'
        assert sample.email == 'email@host.com'
    }

    @Test
    void test1() {
// Use with method to create instance of
// Sample and set properties and invoke methods.
        def sample1 =
                new Sample().with {
                    assert delegate.class.name == 'static_method.Sample'

                    username = 'mrhaki'
                    email = 'email@host.com'
                    addLabel 'Groovy'
                    addLabel 'Gradle'

                }
        // With method returns the result
        // from the closure. In the previous
        // case the return result is null,
        // because the last statement addLabel
        // is used as return value. addLabel has
        // return type void.
        assert !sample1
    }

    @Test
    void test2() {
        // Use with method to create instance of
// Sample and set properties and invoke methods.
        def sample2 =
                new Sample().with {
                    assert delegate.class.name == 'static_method.Sample'

                    username = 'mrhaki'
                    email = 'email@host.com'
                    addLabel 'Groovy'
                    addLabel 'Gradle'

                    // Explicitly return delegate of
                    // closure, which is the Sample object.
                    return delegate
                }

        assert sample2.labels == ['Groovy', 'Gradle']
        assert sample2.username == 'mrhaki'
        assert sample2.email == 'email@host.com'

    }

    @Test
    void test3() {
// Use with method to create instance of
// Sample and set properties and invoke methods.
        def sample3 =
                new Sample().with(true) {
                    assert delegate.class.name == 'static_method.Sample'

                    username = 'mrhaki'
                    email = 'email@host.com'
                    addLabel 'Groovy'
                    addLabel 'Gradle'

                    // We use with(true), so the
                    // delegate of the closure, the Sample
                    // object, is returned.
                }

        assert sample3.labels == ['Groovy', 'Gradle']
        assert sample3.username == 'mrhaki'
        assert sample3.email == 'email@host.com'
    }

    @Test
    void test4() {
        def sample =
                new Sample().tap {
                    username = 'mrhaki'
                    email = 'email@host.com'
                    addLabel 'Groovy'
                    addLabel 'Gradle'
                }

// The with method can be very useful to
// transform object to another type using
// values from the object.
        def user = sample.with { "$username likes ${labels.join(', ')}." }

        assert user == 'mrhaki likes Groovy, Gradle.'
    }
}
