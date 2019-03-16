import cats._
import cats.implicits._

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object Functors {

  trait Base[F[_]] {
    protected def parse(input: String): F[Int]
    protected def shouldBeBiggerThan100(number: Int): F[Int]
  }

  abstract class MyClass[F[_]] extends Base[F] {
    def checkThenAddIt(input: String)(implicit monad: Monad[F]): F[Int] =
      parse(input).flatMap(shouldBeBiggerThan100)
  }

  object OptionHandler extends MyClass[Option] {
    protected override def parse(input: String): Option[Int] = Try(input.trim.toInt) match {
      case Success(v) => Some(v)
      case Failure(_) => None
    }

    protected override def shouldBeBiggerThan100(number: Int): Option[Int] = number match {
      case n if n > 100 => Some(n)
      case _ => None
    }
  }

  object FutureHandler extends MyClass[Future] {
    implicit val ec = scala.concurrent.ExecutionContext.global

    /**
      * assume that parsing takes a long time so that it returns future
      * @param input
      * @return
      */
    protected override def parse(input: String): Future[Int] = {
      Future {Try(input.trim.toInt)}.map{
        case Success(v) => v
        case Failure(_) => throw new IllegalArgumentException("string can not be parsed")
      }
    }

    /**
      * assume that comparison takes a long time so that it returns future
      * @param input
      * @return
      */
    protected override def shouldBeBiggerThan100(number: Int): Future[Int] = number match {
      case n if n > 100 => Future{n}
      case _ => Future {throw new IllegalArgumentException("Number is not bigger than 100")}
    }
  }
}