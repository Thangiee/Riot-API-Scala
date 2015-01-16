package thangiee.riotapi.summoner

import play.api.libs.json._

case class RunePages(pages: Set[RunePage] = Set(), summonerId: Long = 0)

object RunePages {
  implicit val runePagesFmt = Json.reads[RunePages]
}
