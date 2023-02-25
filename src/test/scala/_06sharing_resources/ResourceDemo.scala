package _06sharing_resources

import weaver._
import cats.effect._
import java.util.concurrent.ConcurrentLinkedQueue

object ResourceDemo extends IOSuite {

// We will store the messages in this queue
  val order = new ConcurrentLinkedQueue[String]()
// order: ConcurrentLinkedQueue[String] = [Acquiring resource, Test 1 is using resource 42, Test 2 is using resource 42, Releasing resource 42]

  def record(msg: String) = IO(order.add(msg)).void

  override type Res = Int

  override def sharedResource: Resource[IO, Int] = {
    val acquire = record("Acquiring resource") *> IO.pure(42)
    val release = (i: Int) => {
      record(s"Releasing resource $i")
      IO.println(order.toString)
    }
    Resource.make(acquire)(release)
  }

  test("Test 1") { res =>
    record(s"Test 1 is using resource $res").as(success)
  }

  test("Test 2") { res =>
    record(s"Test 2 is using resource $res").as(expect(res == 45))
  }
}
