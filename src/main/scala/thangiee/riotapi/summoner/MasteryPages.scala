package thangiee.riotapi.summoner

import play.api.libs.json._

case class MasteryPages(summonerId: Long = 0, pages: Set[MasteryPage] = Set())

object MasteryPages {
  implicit val masteryPagesFmt = Json.reads[MasteryPages]
}
