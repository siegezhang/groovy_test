package operator_overloading


class Bucket {
    int size

    Bucket(int size) { this.size = size }
//Bucket implements a special method called plus()
    Bucket plus(Bucket other) {
        return new Bucket(this.size + other.size)
    }

    Bucket plus(int capacity) {
        return new Bucket(this.size + capacity)
    }
}

def b1 = new Bucket(4)
def b2 = new Bucket(11)
assert (b1 + b2).size == 15
assert (b1 + 11).size == 15



