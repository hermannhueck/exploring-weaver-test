package _07sharing_resources_across_suites

import weaver._

import cats.effect.IO
import cats.effect.Resource

// note how this is a static object
object SharedResources extends GlobalResource {
  def sharedResources(global: GlobalWrite): Resource[IO, Unit] =
    for {
      foo <- Resource.pure[IO, String]("hello world!")
      _   <- global.putR(foo)
    } yield ()
}

class SharingSuite(global: GlobalRead) extends IOSuite {
  type Res = String
  def sharedResource: Resource[IO, String] =
    global.getOrFailR[String]()

  test("a stranger, from the outside ! ooooh") { sharedString =>
    IO(expect(sharedString == "hello world!"))
  }
}

class OtherSharingSuite(global: GlobalRead) extends IOSuite {
  type Res = Option[Int]

  // We didn't store an `Int` value in our `GlobalResourcesInit` impl
  def sharedResource: Resource[IO, Option[Int]] =
    global.getR[Int]()

  test("oops, forgot something here") { sharedInt =>
    IO(expect(sharedInt.isEmpty))
  }
}
