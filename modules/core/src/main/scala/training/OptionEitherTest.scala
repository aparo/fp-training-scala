package training

import io.circe.generic.JsonCodec

import scala.util.Try

@JsonCodec
case class Person(name: String)

object OptionEitherTest extends App {

  val v1: Option[Int] = Some(1)
  val v2: Option[Int] = Some(2)
  val optionResult = for {
    i <- v1
    i2 <- v2
  } yield i + i2

  println(optionResult)

  def strToIntEither(str: String): Either[Throwable, Int] =
    Try(str.toInt).toEither

  val person = io.circe.parser
    .parse("""{"name":"Alberto"}""")
    .flatMap { json =>
      json.as[Person]
    }
    .flatMap { person =>
      Right(person.copy(name = "paolo"))
    }

  println(person)

  val person2 = for {
    json <- io.circe.parser.parse("""{"name":"Alberto"}""")
    person <- json.as[Person]
  } yield person.copy(name = "paolo")

  println(person2)

}
