import cats._
import cats.implicits._

object Monoids {

  def addByCatsMonoid[F[_], A](x: F[A], y:F[A])(implicit m: Monoid[F[A]]): F[A] = x |+| y

  def addByUsingForComprehension[F[_], A](x: F[A], y: F[A])(implicit m: Monad[F], f: Monoid[A]): F[A] =
    for {
      xa <- x
      ya <- y
    } yield xa |+| ya

  def tuplify[F[_], A, B](x: F[A], y: F[B])(implicit m: Monad[F]): F[(A, B)] =
    for {
      xa <- x
      ya <- y
    } yield (xa, ya)
}
