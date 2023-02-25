package _07sharing_resources_across_suites.resource

import cats.effect.{IO, Resource}
import weaver._

object MyResources extends GlobalResource {
  override def sharedResources(global: GlobalWrite): Resource[IO, Unit] =
    baseResources.flatMap(global.putR(_))

  def baseResources: Resource[IO, String] = Resource.pure[IO, String]("hello world!")

  // Provides a fallback to support running individual tests via testOnly
  def sharedResourceOrFallback(read: GlobalRead): Resource[IO, String] =
    read.getR[String]().flatMap {
      case Some(value) => Resource.eval(IO(value))
      case None        => baseResources
    }
}
