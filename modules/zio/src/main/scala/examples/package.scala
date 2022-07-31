import com.typesafe.config.Config
import examples.example.{ ExampleConfig, ExampleService }
import sttp.client3.asynchttpclient.zio._
import sttp.client3.{ SttpBackend, SttpBackendOptions }
import sttp.capabilities.WebSockets
import sttp.capabilities.zio.ZioStreams
import zio.config.typesafe._
import zio.logging.LogAnnotation._
import zio._
import zio.logging.backend.SLF4J
import zio.config._
import zio.config.magnolia._
import zio.config.syntax._
import zio.logging.{ LogAnnotation, LogFormat }
package object examples {

  type AppEnvironment = SttpBackend[Task, ZioStreams with WebSockets] with ExampleService.Service

  def buildApplicationLayer(config: Config): ZLayer[Any, Throwable, AppEnvironment] = {
    val logger =
      Runtime.removeDefaultLoggers >>> SLF4J.slf4j(
            LogLevel.Info,
            LogFormat.annotation(LogAnnotation.TraceId) |-| LogFormat.annotation(
                  "user"
                ) |-| LogFormat.line |-| LogFormat.cause
          )

    val configLayer = ZLayer.succeed(config)

    val configDescription = descriptor[RootConfig].mapKey(toKebabCase)
    val cfg               = TypesafeConfig.fromTypesafeConfig(ZIO.succeed(config), configDescription)

    val httpLayer = AsyncHttpClientZioBackend.layer()

    val exampleLayer: ZLayer[Any, Throwable, ExampleService.ExampleService] =
      (logger ++ cfg.narrow(_.example) ++ httpLayer) >>> ExampleService.live

    exampleLayer ++ httpLayer
  }
}
