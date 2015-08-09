package thangiee.riotapi.`match`

import play.api.libs.json.Json

case class Timeline(frameInterval: Long = 0, frames: List[Frame] = Nil)

object Timeline {
  implicit val timelineFmt = Json.reads[Timeline]
}
