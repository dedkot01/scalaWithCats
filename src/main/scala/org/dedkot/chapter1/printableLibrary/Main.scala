package org.dedkot.chapter1.printableLibrary

object Main extends App {

  import org.dedkot.chapter1.printableLibrary.PrintableInstances._

  val cat = Cat("Tom", 5, "Gray")
  Printable.print(cat)

  import org.dedkot.chapter1.printableLibrary.PrintableSyntax._

  cat.print
}
