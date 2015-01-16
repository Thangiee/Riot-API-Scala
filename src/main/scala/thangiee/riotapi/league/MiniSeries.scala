package thangiee.riotapi.league

import play.api.libs.json._

case class MiniSeries(losses: Int = 0, progress: String = "", target: Int = 0, wins: Int = 0)

object MiniSeries {
  implicit val miniSeriesFmt = Json.reads[MiniSeries]
}
