package thangiee.riotapi

import play.api.libs.json._
import thangiee.riotapi.summoner.Summoner
import thangiee.riotapi.utils.ApiCaller

object RiotApi {
  private var reg_ = "na"
  val summonerVersion = "v1.4"

  def region(region: String) = reg_ = region.toLowerCase

  def baseUrl(region: String = reg_) = s"https://$region.api.pvp.net/api/lol/$region"

  def summonerByNames(names: List[String], reg: String = reg_)(implicit caller: ApiCaller): Map[String, Summoner] = {
    val url = s"${baseUrl(reg)}/$summonerVersion/summoner/by-name/${names.mkString(",")}?api_key="

    caller.call(url).map{ response =>
      (for {
        name ← names
        summ ← (Json.parse(response) \ name).asOpt[Summoner]
      } yield name → summ).toMap
    }.getOrElse(Map())
  }

  def summonerByIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Map[Long, Summoner] = {
    val url = s"${baseUrl(reg)}/$summonerVersion/summoner/${ids.mkString(",")}?api_key="

    caller.call(url).map { response =>
      (for {
        id ← ids
        summ ← (Json.parse(response) \ id.toString).asOpt[Summoner]
      } yield id → summ).toMap
    }.getOrElse(Map())
  }
}

