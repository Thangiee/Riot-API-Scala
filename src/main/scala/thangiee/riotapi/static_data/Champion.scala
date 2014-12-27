package thangiee.riotapi.static_data

import play.api.libs.json._

case class Champion
(id: Int,
 title: String,
 name: String,
 key: String)

object Champion {
  implicit val championFmt = Json.reads[Champion]
}
