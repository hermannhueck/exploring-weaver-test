package _06sharing_resources

import weaver._
import cats.effect._

// Using http4s
import org.http4s.blaze.client._
import org.http4s.client._

object HttpSuite extends IOSuite {

  // Sharing a single http client across all tests
  // 'Res' is a Resource (defined in the base class IOSuite), so it will be automatically closed
  override type Res = Client[IO]
  override def sharedResource: Resource[IO, Res] =
    BlazeClientBuilder[IO].resource

  // The test receives the shared client as an argument
  test("Good requests lead to good results") { httpClient: Res =>
    for {
      statusCode <- httpClient.get("http://httpbin.org/get") { response =>
                      IO.pure(response.status.code)
                    }
    } yield expect(statusCode == 200)
  }

  test("Bad requests lead to bad results") { httpClient: Res =>
    for {
      statusCode <- httpClient.get("http://httpbin.org/oops") { response =>
                      IO.pure(response.status.code)
                    }
    } yield expect(statusCode == 404)
  }
}
