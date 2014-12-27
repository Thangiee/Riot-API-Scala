package thangiee.riotapi

import play.api.libs.json._
import thangiee.riotapi.game.RecentGames
import thangiee.riotapi.static_data.SummonerSpell
import thangiee.riotapi.summoner.{MasteryPages, RunePages, Summoner}
import thangiee.riotapi.team.Team
import thangiee.riotapi.utils._

object RiotApi {
  private implicit var key_ = ApiKey("")
  private var reg_ = "na"
  val gameVer = "v1.3"
  val summVer = "v1.4"
  val teamVer = "v2.4"
  val staticDataVer = "v1.2"

  def region(region: String) = reg_ = region.toLowerCase

  def baseUrl(region: String = reg_) = s"https://$region.api.pvp.net/api/lol/$region"

  def key(key: String) = key_ = ApiKey(key)

  def recentGamesBySummonerId(id: Long, reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, Option[RecentGames]] = {
    implicit val url = s"${baseUrl(reg)}/$gameVer/game/by-summoner/$id/recent?api_key="
    jsonToOption[Long, RecentGames](id)
  }

  def summonerByNames(names: List[String], reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, Map[String, Summoner]] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/by-name/${names.mkString(",")}?api_key="
    jsonToMap[String, Summoner](names)
  }

  def summonerByName(name: String, reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, Option[Summoner]] = {
    summonerByNames(List(name)).right.map(_.get(name))
  }

  def summonerByIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, Map[Long, Summoner]] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}?api_key="
    jsonToMap[Long, Summoner](ids)
  }

  def summonerById(id: Long, reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, Option[Summoner]] = {
    summonerByIds(List(id)).right.map(_.get(id))
  }

  def masteryPagesByIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, Map[Long, MasteryPages]] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}/masteries?api_key="
    jsonToMap[Long, MasteryPages](ids)
  }

  def summonerNameById(id: Long, reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, Option[String]] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/$id/name?api_key="
    jsonToOption[Long, String](id)
  }

  def runePagesByIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, Map[Long, RunePages]] = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}/runes?api_key="
    jsonToMap[Long, RunePages](ids)
  }

  def teamBySummonerIds(ids: List[Long], reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, Map[Long, List[Team]]] = {
    implicit val url = s"${baseUrl(reg)}/$teamVer/team/by-summoner/${ids.mkString(",")}?api_key="
    jsonToMap[Long, List[Team]](ids)
  }

  def teamByTeamIds(ids: List[String], reg: String = reg_)(implicit caller: ApiCaller): Either[RiotException, Map[String, Team]] = {
    implicit val url = s"${baseUrl(reg)}/$teamVer/team/${ids.mkString(",")}?api_key="
    jsonToMap[String, Team](ids)
  }

  // =====================
  // Static-data api calls
  // =====================

  def spellById(id: Int)(implicit caller: ApiCaller): Either[RiotException, Option[SummonerSpell]] = {
    implicit val url = s"https://na.api.pvp.net/api/lol/static-data/na/$staticDataVer/summoner-spell/$id?api_key="
    jsonToOption[SummonerSpell]
  }

  private def jsonToMap[A, B: Reads](keys: List[A])(implicit caller: ApiCaller, url: String): Either[RiotException, Map[A, B]] = {
    def asMap(jsonResponse: String) = {
      (for {
        key ← keys
        value ← (Json.parse(jsonResponse) \ key.toString).asOpt[B]
      } yield key → value).toMap
    }

    caller.call(url).right.map(asMap)
  }

  private def jsonToOption[A, B: Reads](key: A)(implicit caller: ApiCaller, url: String): Either[RiotException, Option[B]] = {
    caller.call(url).right.map(json => (Json.parse(json) \ key.toString).asOpt[B])
  }

  private def jsonToOption[A: Reads]()(implicit caller: ApiCaller, url: String): Either[RiotException, Option[A]] = {
    caller.call(url).right.map(json => Json.parse(json).asOpt[A])
  }
}

