package thangiee.riotapi.staticdata

import play.api.libs.json._

case class SummonerSpell(
  name: String,
  description: String,
  summonerLevel: Int,
  id: Int ,
  key: String
  )

object SummonerSpell {
  implicit val summonerSpellFmt = Json.reads[SummonerSpell]
}
