package currying

import org.junit.jupiter.api.Test

class CurryingTest {
    @Test
    void test() {
        //Currying
        def modulus = { mod, num -> num % mod
        }
        assert modulus(2, 5) == 1
        assert modulus(3, 5) == 2
        def mod2 = modulus.curry(2)
        assert mod2(5) == 1
        def mod3 = modulus.curry(3)
        assert mod3(5) == 2


        def bill = { amount, currency -> "$amount $currency" }
        assert bill(1000, '$') == '1000 $'
        def billInDollars = bill.rcurry('$')
        assert billInDollars(1000) == '1000 $'

        var add = Integer::sum
        assert add(10, 1) == 11
        var add2 = add.curry(2)
        assert add2(10) == 12


        //Partial Application
        def joinWithSeparator = { one, sep, two -> one + sep + two }
        def joinWithAmpersand = joinWithSeparator.ncurry(1, '&')
        assert joinWithAmpersand('a', 'b') == 'a&b'
    }
}
