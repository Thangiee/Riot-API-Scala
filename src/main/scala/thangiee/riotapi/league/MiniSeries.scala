package thangiee.riotapi.league

import play.api.libs.json._

case class MiniSeries(losses: Int, progress: String, target: Int, wins: Int)

object MiniSeries {
  implicit val miniSeriesFmt = Json.reads[MiniSeries]
}
