package thangiee.riotapi.matchhistory.participantstats

import play.api.libs.json.{JsPath, Reads}

case class ParticipantStats(data1: Data1 = Data1(), data2: Data2 = Data2(), data3: Data3 = Data3())

object ParticipantStats {

  import play.api.libs.functional.syntax._

  implicit val participantStatsFmt: Reads[ParticipantStats] = (
    JsPath.read[Data1] and
    JsPath.read[Data2] and
    JsPath.read[Data3])(ParticipantStats.apply _)
}