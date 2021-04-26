package org.dedkot.chapter1.printableLibrary

trait Printable[A] {

  def format(value: A): String

}

object PrintableInstances {

  implicit val intPrintable: Printable[Int] = {
    new Printable[Int] {
      override def format(value: Int): String = value.toString
    }
  }

  implicit val stringPrintable: Printable[String] = {
    new Printable[String] {
      override def format(value: String): String = value
    }
  }

  implicit val catPrintable: Printable[Cat] = {
    new Printable[Cat] {
      override def format(value: Cat): String = {
        val name  = Printable.format(value.name)
        val age   = Printable.format(value.age)
        val color = Printable.format(value.color)

        s"$name is a $age year-old $color cat."
      }
    }
  }

}

object Printable {

  def format[A](aValue: A)(implicit p: Printable[A]): String = p.format(aValue)

  def print[A](aValue: A)(implicit p: Printable[A]): Unit = println(p.format(aValue))

}

object PrintableSyntax {

  implicit class PrintableOps[A](value: A) {

    def print(implicit p: Printable[A]): Unit = println(format)

    def format(implicit p: Printable[A]): String = p.format(value)

  }

}
