import org.scalatest.{AsyncFunSuite, FunSuite, Matchers}
import Monoids._
import cats.implicits._

import scala.concurrent.Future

class MonoidsTest extends AsyncFunSuite with Matchers {
  val s1 = Option("a")
  val s2 = Option("b")
  val f1 = Future{3}
  val f2 = Future{5}

  test("should add option texts") {
    val result1 = addByCatsMonoid(s1, s2)
    val result2 = addByUsingForComprehension(s1, s2)

    result1 shouldBe result2
    result1 shouldBe Some("ab")
  }

  test("should tuplify two option texts") {
    val result = tuplify(s1, s2)
    result shouldBe Some("a", "b")
  }

  test("should add future numbers") {
    val result1 = addByCatsMonoid(f1, f2)
    val result2 = addByUsingForComprehension(f1, f2)

    result1 map { number => assert(number == 8) }
    result2 map { number => assert(number == 8) }
  }

  test("should tuplify two future numbers") {
    val result = tuplify(f1, f2)
    result map { number => assert(number == (3, 5)) }
  }

}
