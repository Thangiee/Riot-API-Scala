package thangiee.riotapi.staticdata

import play.api.libs.json._

case class Champion(
  id: Int = 0,
  title: String = "",
  name: String = "",
  key: String = ""
  )

object Champion {
  implicit val championFmt = Json.reads[Champion]
}

