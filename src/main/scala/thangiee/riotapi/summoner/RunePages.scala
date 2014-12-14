package thangiee.riotapi.summoner

import play.api.libs.json._

case class RunePages(pages: Set[RunePage], summonerId: Long)

object RunePages {
  implicit val runePagesFmt = Json.format[RunePages]
}
