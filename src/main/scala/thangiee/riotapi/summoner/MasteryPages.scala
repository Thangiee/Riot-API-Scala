package thangiee.riotapi.summoner

import play.api.libs.json._

case class MasteryPages(summonerId: Long, pages: Set[MasteryPage])

object MasteryPages {
  implicit val masteryPagesFmt = Json.reads[MasteryPages]
}
