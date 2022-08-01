package examples

object TestTypes extends App {
  val a: Int     = 1
  val d: Double  = 1.0
  val f: Float   = 1.0f
  val b: Boolean = true

  // mutabile & immutabile
  var i = 0 // Mutabile
  i = 4
  val tipo = 4 // immutabile

  lazy val p = {
    println(5)
    9
  }

  def add(a: Int, b: Int = 0): Int =
    if (a > 0)
      a + b
    else
      -a + b

//  println("Pippo")
//  println(p)
  //curried
//  println(add(1, 3))
//  println(add(1))
//  println(add(a = 1, b = 4))
//  val curried: Int => Int =add(5, _)
//  println(curried(10))

  val lower: String => String = _.toLowerCase()
//  val addHello: String => String = { st: String =>
//    s"Hello $st"
//  }

  def addHello(st: String): String = s"Hello $st"

  //  println(lower("GIGI"))

  val list = List(lower, addHello(_))

  var text = "Alberto"
  // esecuzione etl su text
  list.foreach(step => text = step(text))
  println(text)

}
