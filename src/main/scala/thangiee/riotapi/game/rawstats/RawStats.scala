package thangiee.riotapi.game.rawstats

import play.api.libs.json._

case class RawStats(data1: Data1, data2: Data2, data3: Data3, data4: Data4) // split fields into groups since play-json
                                                                            // can't handle more than 22 fields
object RawStats {
 import play.api.libs.functional.syntax._

  implicit val rawStatsReads: Reads[RawStats] = (
    JsPath.read[Data1] and
      JsPath.read[Data2] and
      JsPath.read[Data3] and
      JsPath.read[Data4])(RawStats.apply _)
}

