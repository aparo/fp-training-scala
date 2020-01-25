package training


import scala.concurrent._
import scala.concurrent.duration.Duration
import scala.util.Try


object FutureEitherTesting extends App{
  implicit val ec=scala.concurrent.ExecutionContext.global

  def strToIntEither(str: String): Either[Throwable, Int] =
    Try(str.toInt).toEither

  val v1: Future[Either[Throwable, Int]] = Future.successful(Right(1))
  val v2: Future[Either[Throwable, Int]] = Future.successful(strToIntEither("30"))
  val optionResult = for {
    i <- v1
    i2 <- v2
  } yield {
    for{
      value1 <- i
      value2 <- i2
    } yield value1+value2
  }


  println(Await.result(optionResult, Duration.Inf) )

}