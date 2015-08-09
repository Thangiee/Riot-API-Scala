package thangiee.riotapi.stats.aggregatedstats

import play.api.libs.json._

case class DominionData(
  averageAssists: Int = 0,
  averageChampionsKilled: Int = 0,
  averageCombatPlayerScore: Int = 0,
  averageNodeCapture: Int = 0,
  averageNodeCaptureAssist: Int = 0,
  averageNodeNeutralize: Int = 0,
  averageNodeNeutralizeAssist: Int = 0,
  averageNumDeaths: Int = 0,
  averageObjectivePlayerScore: Int = 0,
  averageTeamObjective: Int = 0,
  averageTotalPlayerScore: Int = 0,
  maxAssists: Int = 0,
  maxCombatPlayerScore: Int = 0,
  maxNodeCapture: Int = 0,
  maxNodeCaptureAssist: Int = 0,
  maxNodeNeutralize: Int = 0,
  maxNodeNeutralizeAssist: Int = 0,
  maxObjectivePlayerScore: Int = 0,
  maxTeamObjective: Int = 0,
  maxTotalPlayerScore: Int = 0,
  totalNodeCapture: Int = 0,
  totalNodeNeutralize: Int = 0
  )

object DominionData {
  implicit val data1Fmt = Json.reads[DominionData]
}
