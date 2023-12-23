package destructuring

import groovy.transform.Immutable

@Immutable
class Coordinates {
    double latitude
    double longitude

    double getAt(int idx) {
        if (idx == 0) latitude
        else if (idx == 1) longitude
        else throw new Exception("Wrong coordinate index, use 0 or 1")
    }
}


def coordinates = new Coordinates(latitude: 43.23, longitude: 3.67)

def (la, lo) = coordinates

assert la == 43.23
assert lo == 3.67
