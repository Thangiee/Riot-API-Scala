package thangiee.riotapi.static_data

import play.api.libs.json._

case class SummonerSpell(name: String = "", description: String = "", summonerLevel: Int = 0, id: Int = 0, key: String = "")

object SummonerSpell {
  implicit val summonerSpellFmt = Json.reads[SummonerSpell]
}
