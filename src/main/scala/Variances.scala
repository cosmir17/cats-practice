/**
  * This project is referenced from 'Scala for the impatient' by Cay Horstmann
  */
object Variances {

  class Person
  class Student extends Person

  class Pair[+T](val first: T, val second: T)

  def swapCoVariance(pair: Pair[Person]) = new Pair(pair.second, pair.first)

  val studentAlice = new Student
  val studentTom = new Student
  val studentPair = new Pair(studentAlice, studentTom)

  swapCoVariance(studentPair)

}
