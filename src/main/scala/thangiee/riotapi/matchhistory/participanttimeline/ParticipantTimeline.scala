package thangiee.riotapi.matchhistory.participanttimeline

import play.api.libs.json.{JsPath, Reads}

case class ParticipantTimeline(data1: Data1 = Data1(), data2: Data2 = Data2())

object ParticipantTimeline {

  import play.api.libs.functional.syntax._

  implicit val participantTimelineFmt: Reads[ParticipantTimeline] = (
    JsPath.read[Data1] and
    JsPath.read[Data2])(ParticipantTimeline.apply _)
}