package _05expectations

import weaver._

// Suites must be "objects" for them to be picked by the framework
object MySuite05 extends SimpleIOSuite {

  pureTest("expectations") {

    // Assert on boolean values using expect
    val myVar = 25
    val list  = List(1, 2, 3, 4)
    expect(myVar == 25 && list.size == 4)

    // Compose expectations using and/or
    (expect(1 == 1) and expect(2 > 1)) or expect(5 == 5)

    // Use varargs short form for asserting on all boolean values
    expect.all(1 == 1, 2 == 2, 3 > 2)

    // Use forEach to test every element of a collection (or anything that implements Foldable)
    forEach(List(1, 2, 3))(i => expect(i < 5))

    // Use exists to assert that at least one element of collection matches expectations:
    exists(Option(5))(n => expect(n > 3))

    // Use expect.eql for strict equality comparison (types that implement Eq typeclass) and string representation
    // diffing (using Show typeclass, fall back to toString if no instance found) in case of failure
    expect.eql(List(1, 2, 3), (1 to 3).toList)

    // Use expect.same for relaxed equality comparison (if no Eq instance is found,
    // fall back to universal equality) and relaxed string diffing (fall back to toString implementation)
    expect.same(List(1, 2, 3), (1 to 3).toList)

    // Use success or failure to create succeeding/failing expectations without conditions
    val result = if (5 == 5) success else failure("oh no")
    expect(result == success)

    // Use .failFast to evaluate the expectation eagerly and raise the assertion error in your effect type
    //
    // import cats.effect._
    // for {
    //   x <- IO("hello")
    //   _ <- expect(x.length == 4).failFast
    //   y  = x + "bla"
    //   _ <- expect(y.size > x.size).failFast
    // } yield expect(y.contains(x))

  }
}
