package thangiee.riotapi.stats.AggregatedStats

import play.api.libs.json._

case class AggregatedStats(data1: Data1, data2: Data2, dominionData: DominionData)

object AggregatedStats {
  import play.api.libs.functional.syntax._

  implicit val aggregatedStatsFmt: Reads[AggregatedStats] = (
    JsPath.read[Data1] and
    JsPath.read[Data2] and
    JsPath.read[DominionData])(AggregatedStats.apply _)
}
