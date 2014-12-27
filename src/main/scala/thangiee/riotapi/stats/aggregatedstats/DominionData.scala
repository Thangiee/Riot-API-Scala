package thangiee.riotapi.stats.aggregatedstats

import play.api.libs.json._

case class DominionData
(averageAssists: Option[Int],
 averageChampionsKilled: Option[Int],
 averageCombatPlayerScore: Option[Int],
 averageNodeCapture: Option[Int],
 averageNodeCaptureAssist: Option[Int],
 averageNodeNeutralize: Option[Int],
 averageNodeNeutralizeAssist: Option[Int],
 averageNumDeaths: Option[Int],
 averageObjectivePlayerScore: Option[Int],
 averageTeamObjective: Option[Int],
 averageTotalPlayerScore: Option[Int],
 maxAssists: Option[Int],
 maxCombatPlayerScore: Option[Int],
 maxNodeCapture: Option[Int],
 maxNodeCaptureAssist: Option[Int],
 maxNodeNeutralize: Option[Int],
 maxNodeNeutralizeAssist: Option[Int],
 maxObjectivePlayerScore: Option[Int],
 maxTeamObjective: Option[Int],
 maxTotalPlayerScore: Option[Int],
 totalNodeCapture: Option[Int],
 totalNodeNeutralize: Option[Int]
  )

object DominionData {
  implicit val data1Fmt = Json.reads[DominionData]
}
