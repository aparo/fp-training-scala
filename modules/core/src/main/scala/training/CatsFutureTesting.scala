package training

import cats.data.EitherT
import cats.implicits._

import scala.concurrent._
import scala.concurrent.duration.Duration
import scala.util.Try

object CatsFutureTesting extends App {
  implicit val ec = scala.concurrent.ExecutionContext.global

  def strToIntEither(str: String): Either[Throwable, Int] =
    Try(str.toInt).toEither

  val v1: EitherT[Future, Throwable, Int] = EitherT.fromEither(1.asRight)
  val v2: EitherT[Future, Throwable, Int]  = EitherT.fromEither(strToIntEither("30"))
  val optionResult = for {
    i <- v1
    i2 <- v2
  }  yield i+i2

  println(Await.result(optionResult.value, Duration.Inf))

}
