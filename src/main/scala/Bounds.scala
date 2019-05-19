/**
  * This project is referenced from 'Scala for the impatient' by Cay Horstmann
  */
object Bounds {

  val tony = new Student
  val anna = new Student
  val phil = new Person

  val p = new PairUpperBoundExample(tony, anna)
  val philAnna: PairUpperBoundExample[Person] = p.replaceFirst(phil)

  val pairViewBound = new PairViewBound(5, 10)
  val smallerNoV: Int = pairViewBound.smaller

  val pairContextBound = new PairContextBound(4, 10)
  val smallerNoC: Int = pairContextBound.smaller

  val pairTC = new PairTypeConstraint(4, 10)
//  val smallerNoTC: Int = pairTC.smaller

  class Person

  class Student extends Person

  class Pair[T <: Comparable[T]](val first: T, val second: T) {
    def smaller = if (first.compareTo(second) < 0) first else second
  }

  class PairUpperBoundExample[T](val first: T, val second: T) {
    def replaceFirst[R >: T](newFirst: R) = new PairUpperBoundExample[R](newFirst, second)
  }

  class PairViewBound[T <% Comparable[T]](val first: T, val second: T) {
    def smaller = if (first.compareTo(second) < 0) first else second
  }

  class PairContextBound[T: Ordering](val first: T, val second: T) {
    def smaller(implicit ord: Ordering[T]) = if (ord.compare(first, second) < 0) first else second
  }

  class PairTypeConstraint[T](val first: T, val second: T) {
    def smaller(implicit ev: T <:< Ordered[T]) = if (first < second) first else second
  }

}