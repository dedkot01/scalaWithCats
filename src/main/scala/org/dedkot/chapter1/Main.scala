package org.dedkot.chapter1

object Main extends App {
  import JsonSyntax._
  import JsonWriterInstances._

  println(Json.toJson(Person("dedkot", "dedkot01@gmail.com")))
  println(Person("another dedkot", "dedkot01@gmail.com").toJson)
  println(Json.toJson(Option("Test")))
}
