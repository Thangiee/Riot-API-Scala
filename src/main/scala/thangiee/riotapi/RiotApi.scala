package thangiee.riotapi

import play.api.libs.json._
import thangiee.riotapi.summoner.{RunePages, MasteryPages, Summoner}
import thangiee.riotapi.utils.ApiCaller

object RiotApi {
  private var reg_ = "na"
  val summonerVer = "v1.4"

  def region(region: String) = reg_ = region.toLowerCase

  def baseUrl(region: String = reg_) = s"https://$region.api.pvp.net/api/lol/$region"

  def summonerByNames(names: List[String], reg: String = reg_)(implicit caller: ApiCaller): Map[String, Summoner] = {
    implicit val url = s"${baseUrl(reg)}/$summonerVer/summoner/by-name/${names.mkString(",")}?api_key="
    mapSummoners(names)
  }

  def summonerByIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Map[Long, Summoner] = {
    implicit val url = s"${baseUrl(reg)}/$summonerVer/summoner/${ids.mkString(",")}?api_key="
    mapSummoners(ids)
  }

  def masteryPagesByIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Map[Long, MasteryPages] = {
    val url = s"${baseUrl()}/$summonerVer/summoner/${ids.mkString(",")}/masteries?api_key="

    caller.call(url).map { response =>
      (for {
        id ← ids
        pages ← (Json.parse(response) \ id.toString).asOpt[MasteryPages]
      } yield id → pages).toMap
    }.getOrElse(Map())
  }

  def summonerNameById(id: Long, reg: String = reg_)(implicit caller: ApiCaller): Option[String] = {
    val url = s"${baseUrl()}/$summonerVer/summoner/$id/name?api_key="

    caller.call(url).map { response => (Json.parse(response) \ id.toString).asOpt[String]}.getOrElse(None)
  }

  def runePagesByIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Map[Long, RunePages] = {
    val url = s"${baseUrl()}/$summonerVer/summoner/${ids.mkString(",")}/runes?api_key="

    caller.call(url).map { response =>
      (for {
        id ← ids
        pages ← (Json.parse(response) \ id.toString).asOpt[RunePages]
      } yield id → pages).toMap
    }.getOrElse(Map())
  }

  private def mapSummoners[A](keys: List[A])(implicit caller: ApiCaller, url: String): Map[A, Summoner] = {
    caller.call(url).map { response =>
      (for {
        key ← keys
        summ ← (Json.parse(response) \ key.toString).asOpt[Summoner]
      } yield key → summ).toMap
    }.getOrElse(Map())
  }

}

