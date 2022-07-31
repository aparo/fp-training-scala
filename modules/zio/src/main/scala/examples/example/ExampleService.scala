package examples.example

import sttp.capabilities.WebSockets
import sttp.capabilities.zio.ZioStreams
import sttp.client3.{ SttpBackend, SttpBackendOptions }

import zio._

// generator: zio.environment: app.example.AppEnvironment

object ExampleService {

  type ExampleService = Service

  trait Service {

    /**
      * Multiply three times the value
      *
      * @param text the value to be multiplied
      * @return a list of values
      */
    def singleToList(text: String): ZIO[Any, Throwable, List[String]]

  }
  // services
  val live: ZLayer[SttpBackend[Task, ZioStreams with WebSockets] with ExampleConfig, Throwable, Service] =
    ZLayer {
      for {
        _ <- ZIO.logDebug("Init example service")
        httpClient <- ZIO.service[SttpBackend[Task, ZioStreams with WebSockets]]
        exampleConfig <- ZIO.service[ExampleConfig]

      } yield ExampleServiceImpl(httpclient = httpClient, exampleConfig = exampleConfig)
    }
}

private[example] case class ExampleServiceImpl(
    httpclient: SttpBackend[Task, ZioStreams with WebSockets],
    exampleConfig: ExampleConfig
) extends ExampleService.Service {

  /**
    * Multiply three times the value
    *
    * @param text        the value to be multiplied
    * @return a list of values
    */
  override def singleToList(text: String): ZIO[Any, Throwable, List[String]] =
    ZIO.attempt(List.fill(exampleConfig.repetition)(text))
}
