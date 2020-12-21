package training

import io.chrisdavenport.log4cats.Logger
import io.chrisdavenport.log4cats.slf4j.Slf4jLogger
import cats.effect.Sync
import cats.implicits._
import cats.implicits._
import zio._
import zio.interop.catz._

object Log4CatsZIO extends App {
  implicit def unsafeLogger[F[_]: Sync] = Slf4jLogger.getLogger[F]

  // Arbitrary Local Function Declaration
  def doSomething[F[_]: Sync]: F[Unit] =
    Logger[F].info("Logging Start Something") *>
        Sync[F].delay(println("I could be doing anything")).attempt.flatMap {
          case Left(e)  => Logger[F].error(e)("Something Went Wrong")
          case Right(_) => Sync[F].pure(())
        }

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, Int] =
    doSomething[zio.Task]
      .fold(_ => 1, _ => 0)
}
