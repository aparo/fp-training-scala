package training

import scala.concurrent._
import scala.concurrent.duration.Duration

object FutureTesting extends App {
  implicit val ec = scala.concurrent.ExecutionContext.global

  val v1: Future[Int] = Future.successful({ println("a"); 1 })
  val v2: Future[Int] = Future.successful({ println("b"); 2 })
  val optionResult = for {
    i <- v1
    i2 <- v2
  } yield i + i2

  println(Await.result(optionResult, Duration.Inf))

}
