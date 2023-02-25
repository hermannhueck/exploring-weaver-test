package _15successes

import weaver._

import cats.effect._

object MySuite extends SimpleIOSuite {

  val randomUUID = IO(java.util.UUID.randomUUID())

  test("hello side-effects") {
    for {
      x <- randomUUID
      y <- randomUUID
    } yield expect(x != y)
  }

}

object MyAnotherSuite extends SimpleIOSuite {
  import scala.util.Random.alphanumeric

  val randomString = IO(alphanumeric.take(10).mkString(""))

  test("double reversing is identity") {
    for {
      x <- randomString
    } yield expect(x == x.reverse.reverse)
  }
}
