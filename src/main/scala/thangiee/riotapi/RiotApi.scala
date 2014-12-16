package thangiee.riotapi

import play.api.libs.json._
import thangiee.riotapi.summoner.{MasteryPages, RunePages, Summoner}
import thangiee.riotapi.team.Team
import thangiee.riotapi.utils.ApiCaller

object RiotApi {
  private var reg_ = "na"
  val summVer = "v1.4"
  val teamVer = "v2.4"

  def region(region: String) = reg_ = region.toLowerCase

  def baseUrl(region: String = reg_) = s"https://$region.api.pvp.net/api/lol/$region"

  def summonerByNames(names: List[String], reg: String = reg_)(implicit caller: ApiCaller): Map[String, Summoner] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/by-name/${names.mkString(",")}?api_key="
    jsonToMap[String, Summoner](names)
  }

  def summonerByIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Map[Long, Summoner] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}?api_key="
    jsonToMap[Long, Summoner](ids)
  }

  def masteryPagesByIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Map[Long, MasteryPages] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}/masteries?api_key="
    jsonToMap[Long, MasteryPages](ids)
  }

  def summonerNameById(id: Long, reg: String = reg_)(implicit caller: ApiCaller): Option[String] = {
    val url = s"${baseUrl(reg)}/$summVer/summoner/$id/name?api_key="
    caller.call(url).map { response => (Json.parse(response) \ id.toString).asOpt[String]}.getOrElse(None)
  }

  def runePagesByIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Map[Long, RunePages] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}/runes?api_key="
    jsonToMap[Long, RunePages](ids)
  }

  def teamBySummonerIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Map[Long, List[Team]] = {
    implicit val url = s"${baseUrl(reg)}/$teamVer/team/by-summoner/${ids.mkString(",")}?api_key="
    jsonToMap[Long, List[Team]](ids)
  }

  def teamByTeamIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Map[Long, Team] = {
    implicit val url = s"${baseUrl(reg)}/$teamVer/team/${ids.mkString(",")}?api_key="
    jsonToMap[Long, Team](ids)
  }

  private def jsonToMap[A, B: Reads](keys: List[A])(implicit caller: ApiCaller, url: String): Map[A, B] = {
    caller.call(url).map { jsonResponse =>
      (for {
        key ← keys
        value ← (Json.parse(jsonResponse) \ key.toString).asOpt[B]
      } yield key → value).toMap
    }.getOrElse(Map())
  }
}

