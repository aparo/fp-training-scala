package training

//import zio.App
import zio.console._
import zio.duration._
import zio.random._
import zio.{DefaultRuntime, Schedule, ZIO}

import scala.concurrent.ExecutionContext

object ZioEffectTest extends App {

//  def run(args: List[String]) =
//    myAppLogic.fold(_ => 1, _ => 0)

  val v1 = ZIO.effect({ println("a"); 1 })
  val v2 = ZIO.effect({ println("b"); 2 })
  val v3 = ZIO.succeed(5)

  def func(d:Double):Double=d*4
  def funcError(d:Double):Double={
    println("000")
    throw new RuntimeException("")
  }

  val myAppLogic =
    for {
      i <- nextDouble
      _ <- putStrLn(s"double: $i")
      i1 <- ZIO.effect(func(i)).retry(Schedule.recurs(10)).timeout(10.second).fork
      i3 <- ZIO.effect(funcError(i)).retry(Schedule.recurs(10)).fold(_ => 0, identity).fork
      i2 <- v2
      i4 <- i1.join
      i5 <- i3.join
      _ <- ZIO.when(i>0.5)(putStrLn(""))
      _ <- ZIO.foreachParN(4)(List("a1111", "b1111"))(a => putStrLn(s"$a"))
      _ <- putStrLn(s"$i1 $i2 $i3 $i4 $i5")
    } yield ()

  val myAppLogic2 =
    for {
      _ <- putStrLn("Hello! What is your name?")
      name <- getStrLn
      _ <- putStrLn(s"Hello, ${name}, welcome to ZIO!")
    } yield ()

  val runtime = new DefaultRuntime {}
  runtime.unsafeRun(myAppLogic)


  val person2 = for {
    json <- ZIO.fromEither(io.circe.parser.parse("""{"name":"Alberto"}"""))
    person <- ZIO.fromEither(json.as[Person])
  } yield person.copy(name = "paolo")

  println(runtime.unsafeRun(person2))
}
