package _16failures

import weaver._

import cats.effect._

object MySuite extends SimpleIOSuite {

  pureTest("failing test 1") {
    expect(1 >= 2)
  }
}

object MyAnotherSuite extends SimpleIOSuite {

  import scala.util.Random.alphanumeric

  val randomString = IO(alphanumeric.take(10).mkString(""))

  test("failing test 2") {
    for {
      x <- randomString
    } yield check(x).traced(here)
  }

  def check(x: String) = expect(x.length > 10)
}
