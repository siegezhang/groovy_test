package static_method;

import org.junit.jupiter.api.Test
import groovy.time.TimeCategory

class User {
    String username, email

    String toString() { "$username, $email" }
}


class StaticMethodTest {
    @Test
    void test() {
        User.metaClass.static.new = { u, e ->
            new User(username: u, email: e)
        }

        def u = User.new('mrhaki', 'mail@host.com')
        assert 'mrhaki' == u.username
        assert 'mail@host.com' == u.email
    }

    @Test
    void test1() {
        use(TimeCategory) {
            println 1.minute.from.now
            println 10.hours.ago

            def someDate = new Date()
            println someDate - 3.months
        }
    }
}
