package training

import io.chrisdavenport.log4cats.Logger
import io.chrisdavenport.log4cats.slf4j.Slf4jLogger
import cats.effect.Sync
import cats.implicits._
import cats.effect._
import cats.implicits._

object LogForCats extends IOApp {
  implicit def unsafeLogger[F[_]: Sync] = Slf4jLogger.getLogger[F]

  // Arbitrary Local Function Declaration
  def doSomething[F[_]: Sync]: F[Unit] =
    Logger[F].info("Logging Start Something") *>
      Sync[F].delay(println("I could be doing anything"))
        .attempt.flatMap{
        case Left(e) => Logger[F].error(e)("Something Went Wrong")
        case Right(_) => Sync[F].pure(())
      }

  override def run(args: List[String]): IO[ExitCode] =
    for {
      _      <- doSomething[IO]
    } yield ExitCode.Success


}
