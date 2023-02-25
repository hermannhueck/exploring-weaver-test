package _07sharing_resources_across_suites.somepackage

import cats.effect.{IO, Resource}
import weaver._

class MyOtherSuite(global: GlobalRead) extends IOSuite {

  import _07sharing_resources_across_suites.resource.MyResources._

  override type Res = String

  def sharedResource: Resource[IO, String] = sharedResourceOrFallback(global)

  test("oops, forgot something here") { sharedString =>
    IO(expect(sharedString == "hello world!"))
  }
}
