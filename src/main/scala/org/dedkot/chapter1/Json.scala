package org.dedkot.chapter1

sealed trait Json

final case class JsObject(get: Map[String, Json]) extends Json

final case class JsString(get: String) extends Json

final case class JsNumber(get: Double) extends Json

final case object JsNull extends Json

trait JsonWriter[A] {
  def write(value: A): Json
}

object Json {

  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json = w.write(value)

}

object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {

    def toJson(implicit w: JsonWriter[A]): Json = w.write(value)

  }
}

object JsonWriterInstances {

  implicit val stringWriter: JsonWriter[String] =
    new JsonWriter[String] {
      override def write(value: String): Json = JsString(value)
    }

  implicit val personWritter: JsonWriter[Person] =
    new JsonWriter[Person] {
      override def write(value: Person): Json = JsObject(
        Map("name" -> JsString(value.name), "email" -> JsString(value.email))
      )
    }

  implicit def optionWriter[A](implicit writer: JsonWriter[A]): JsonWriter[Option[A]] = {
    case Some(aValue) => writer.write(aValue)
    case None         => JsNull
  }

}
