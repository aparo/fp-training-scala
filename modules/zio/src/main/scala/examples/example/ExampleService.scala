package examples.example

import sttp.client3.asynchttpclient.zio.SttpClient
import zio._
import zio.logging.{ Logger, Logging }

// generator: zio.environment: app.example.AppEnvironment

object ExampleService {

  type ExampleService = Has[Service]

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
  val live: ZLayer[Logging with Has[SttpClient.Service] with Has[ExampleConfig], Throwable, Has[Service]] =
    ZLayer.fromServicesM[Logger[String], SttpClient.Service, ExampleConfig, Any, Throwable, Service] {
      (logger, httpClient, exampleConfig) =>
        for {
          _ <- logger.debug("Init example service")
        } yield ExampleServiceImpl(logger = logger, httpclient = httpClient, exampleConfig = exampleConfig)
    }
}

private[example] case class ExampleServiceImpl(
    logger: Logger[String],
    httpclient: SttpClient.Service,
    exampleConfig: ExampleConfig
) extends ExampleService.Service {

  /**
    * Multiply three times the value
    *
    * @param text        the value to be multiplied
    * @return a list of values
    */
  override def singleToList(text: String): ZIO[Any, Throwable, List[String]] =
    ZIO.effect(List.fill(exampleConfig.repetition)(text))
}
