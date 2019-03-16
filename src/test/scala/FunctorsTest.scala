import Functors.{FutureHandler, OptionHandler}
import org.scalatest._
import cats.implicits._

class FunctorsTest extends AsyncFunSuite with Matchers {

  test("OptionHander should be able to convert 300 string to int and pass") {
    val result = OptionHandler.checkThenAddIt("300")
    result shouldBe Some(300)
  }

  test("OptionHander should be able to convert 98 string to int but does not pass") {
    val result = OptionHandler.checkThenAddIt("98")
    result shouldBe None
  }

  test("FutureHandler should convert 300 string to int and pass") {
    val result = FutureHandler.checkThenAddIt("300")
    result map { number => assert(number == 300) }
  }

  test("FutureHandler should convert 98 string to int but does not pass") {
    recoverToSucceededIf[IllegalArgumentException]{FutureHandler.checkThenAddIt("98")}
  }

  test("FutureHandler should not convert 98er string to int but does not pass") {
    recoverToSucceededIf[IllegalArgumentException](FutureHandler.checkThenAddIt("98er"))
  }
}
