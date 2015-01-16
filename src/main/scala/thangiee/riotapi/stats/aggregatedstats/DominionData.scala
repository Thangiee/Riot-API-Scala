package thangiee.riotapi.stats.aggregatedstats

import play.api.libs.json._

case class DominionData
(averageAssists: Option[Int] = None,
 averageChampionsKilled: Option[Int] = None,
 averageCombatPlayerScore: Option[Int] = None,
 averageNodeCapture: Option[Int] = None,
 averageNodeCaptureAssist: Option[Int] = None,
 averageNodeNeutralize: Option[Int] = None,
 averageNodeNeutralizeAssist: Option[Int] = None,
 averageNumDeaths: Option[Int] = None,
 averageObjectivePlayerScore: Option[Int] = None,
 averageTeamObjective: Option[Int] = None,
 averageTotalPlayerScore: Option[Int] = None,
 maxAssists: Option[Int] = None,
 maxCombatPlayerScore: Option[Int] = None,
 maxNodeCapture: Option[Int] = None,
 maxNodeCaptureAssist: Option[Int] = None,
 maxNodeNeutralize: Option[Int] = None,
 maxNodeNeutralizeAssist: Option[Int] = None,
 maxObjectivePlayerScore: Option[Int] = None,
 maxTeamObjective: Option[Int] = None,
 maxTotalPlayerScore: Option[Int] = None,
 totalNodeCapture: Option[Int] = None,
 totalNodeNeutralize: Option[Int] = None
  )

object DominionData {
  implicit val data1Fmt = Json.reads[DominionData]
}
