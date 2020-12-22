import com.typesafe.config.Config
import examples.example.{ ExampleConfig, ExampleService }
import sttp.client3.asynchttpclient.zio._
import zio.ZLayer
import zio.config.typesafe.TypesafeConfig
import zio.logging.LogAnnotation.{ Cause, CorrelationId, Level, Name, Throwable }
import zio.logging.Logging
import zio.logging.slf4j.Slf4jLogger
import zio._
import zio.blocking.Blocking
import zio.clock.Clock
import zio.config._
import zio.config.magnolia.DeriveConfigDescriptor
import zio.config.syntax._
import zio.console.Console

package object examples {

  type AppEnvironment = Logging with Has[SttpClient.Service] with Has[ExampleService.Service] with ZEnv

  def buildApplicationLayer(config: Config): ZLayer[Any, Throwable, AppEnvironment] = {
    val logLayer: ZLayer[Any, Nothing, Logging] =
      Slf4jLogger.makeWithAnnotationsAsMdc(List(Cause, Throwable, Name, Level, CorrelationId))

    val configLayer = ZLayer.succeed(config)

    val environment = ZEnv.live

    val configDescription = DeriveConfigDescriptor.descriptor[RootConfig].mapKey(toKebabCase)
    val cfg               = TypesafeConfig.fromTypesafeConfig(config, configDescription)

    val httpLayer = AsyncHttpClientZioBackend.layer()

    val exampleLayer: ZLayer[Any, Throwable, ExampleService.ExampleService] =
      (logLayer ++ cfg.narrow(_.example) ++ httpLayer) >>> ExampleService.live

    environment ++ logLayer ++ exampleLayer ++ httpLayer
  }
}
