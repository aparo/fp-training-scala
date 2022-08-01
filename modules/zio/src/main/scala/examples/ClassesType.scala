package examples

trait MySUMTrait {
  def attr1: Int
  def attr2: String

  def sum(): String = s"$attr1 $attr2"
}

trait MySUMTrait2 {
  def attr1: Int
  def attr2: String

  def sum2(): String = s"22 $attr1 $attr2"
}

// la classe non viene mai usata in scala
class Classe(val attr1: Int, var attr2: String = "") extends MySUMTrait with MySUMTrait2 {
//  def sum(): String = s"$attr1 $attr2"
}

case class Classe2(attr1: Int, attr2: String = "") extends MySUMTrait with MySUMTrait2 {
  // di default immutabile: i valori all'interno non li posso cambiare
  // vengono aggiunte in automatico: hasCode, equals, estrattore, costruttori, ...

}

object ClassesType extends App {
  lazy val NOME = "Prova"

  val c1  = new Classe(5, "pippo")
  val c11 = new Classe(5, "pippo")
  println(s"ci=c11  ${c1 == c11}")
  println(Set(c1, c11))

  val c2  = Classe2(10, "pluto")
  var c22 = Classe2(10, "minni")
  println(s"c2=c22  ${c2 == c22}")
  val s1 = Set(c2, c22)

  c22 = c22.copy(attr2 = "pluto")

  val s2 = Set(c2, c22)

  println(s"s1= $s1")
  println(s"s2= $s2")

  println(c1.sum())
  println(c2.sum())

  println(c1.sum2())
  println(c2.sum2())

}
