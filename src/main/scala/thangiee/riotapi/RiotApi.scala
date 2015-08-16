package thangiee.riotapi

import org.scalactic.Or
import play.api.libs.json._
import thangiee.riotapi.`match`.MatchDetail
import thangiee.riotapi.currentgame.CurrentGameInfo
import thangiee.riotapi.game.RecentGames
import thangiee.riotapi.league.League
import thangiee.riotapi.matchhistory.PlayerHistory
import thangiee.riotapi.matchlist.MatchList
import thangiee.riotapi.staticdata.{Champion, SummonerSpell}
import thangiee.riotapi.stats.{PlayerStatsSummaryList, RankedStats}
import thangiee.riotapi.summoner.{MasteryPages, RunePages, Summoner}
import thangiee.riotapi.team.Team

import scala.concurrent.duration._

object RiotApi {
  private var _key = ""
  private var _reg = "na"

  val gameVer       = "v1.3"
  val summVer       = "v1.4"
  val teamVer       = "v2.4"
  val staticDataVer = "v1.2"
  val statsVer      = "v1.3"
  val leagueVer     = "v2.5"
  val matchVer      = "v2.2"

  def regionId_=(regionId: String) = _reg = regionId.toLowerCase

  def regionId = _reg

  def baseUrl(region: String = _reg) = s"https://${region.toLowerCase}.api.pvp.net/api/lol/${region.toLowerCase}"

  def key_=(key: String) = _key = key

  def key = _key

  // =====================
  //   Current games api call
  // =====================

  def currentGameInfoById(id: Long, ttl: Duration = 20.minutes, reg: String = _reg)(implicit c: ApiCaller): CurrentGameInfo Or RiotError = {
    val platformId = reg.toLowerCase match {
      case "na"   => "NA1"
      case "euw"  => "EUW1"
      case "eune" => "EUN1"
      case "kr"   => "KR"
      case "oce"  => "OC1"
      case "br"   => "BR1"
      case "lan"  => "LA1"
      case "las"  => "LA2"
      case "ru"   => "RU"
      case "tr"   => "TR1"
      case _      => "PBE1"
    }
    implicit val url = s"https://$reg.api.pvp.net/observer-mode/rest/consumer/getSpectatorGameInfo/$platformId/$id?api_key=$key"
    jsonTo[CurrentGameInfo](ttl)
  }

  // =====================
  //    Games api call
  // =====================

  def recentGamesById(id: Long, ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): RecentGames Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$gameVer/game/by-summoner/$id/recent?api_key=$key"
    jsonTo[RecentGames](ttl)
  }

  // =====================
  //    league api calls
  // =====================

  def leagueByIds(ids: List[Long], ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): Map[Long, List[League]] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/by-summoner/${ids.mkString(",")}?api_key=$key"
    jsonToMap[Long, List[League]](ids, ttl)
  }

  def leagueEntryByIds(ids: List[Long], ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): Map[Long, List[League]] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/by-summoner/${ids.mkString(",")}/entry?api_key=$key"
    jsonToMap[Long, List[League]](ids, ttl)
  }

  def leagueEntryById(id: Long, ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): List[League] Or RiotError = {
    leagueEntryByIds(List(id), ttl, reg).map(_.getOrElse(id, Nil))
  }

  def leagueByTeamIds(ids: List[Long],ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): Map[Long, List[League]] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/by-team/${ids.mkString(",")}?api_key=$key"
    jsonToMap[Long, List[League]](ids, ttl)
  }

  def leagueEntryByTeamIds(ids: List[Long], ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): Map[Long, List[League]] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/by-team/${ids.mkString(",")}/entry?api_key=$key"
    jsonToMap[Long, List[League]](ids, ttl)
  }

  def challengerLeague(queue: QueueType, ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): League Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/challenger?type=${queue.`type`}&api_key=$key"
    jsonTo[League](ttl)
  }

  def masterLeague(queue: QueueType, ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): League Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/master?type=${queue.`type`}&api_key=$key"
    jsonTo[League](ttl)
  }

  // =====================
  //    Stats api calls
  // =====================

  def rankedStatsById(id: Long, season: Int, ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): RankedStats Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$statsVer/stats/by-summoner/$id/ranked?season=SEASON$season&api_key=$key"
    jsonTo[RankedStats](ttl)
  }

  def summaryStatsById(id: Long, season: Int, ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): PlayerStatsSummaryList Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$statsVer/stats/by-summoner/$id/summary?season=SEASON$season&api_key=$key"
    jsonTo[PlayerStatsSummaryList](ttl)
  }

  // =====================
  //  Summoner api calls
  // =====================

  def summonerByNames(names: List[String], ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): Map[String, Summoner] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/by-name/${names.mkString(",")}?api_key=$key"
    jsonToMap[String, Summoner](names.map(_.toLowerCase), ttl)
  }

  def summonerByName(name: String, ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): Summoner Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/by-name/$name?api_key=$key"
    jsonTo[String, Summoner](name.toLowerCase, ttl)
  }

  def summonerByIds(ids: List[Long], ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): Map[Long, Summoner] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}?api_key=$key"
    jsonToMap[Long, Summoner](ids, ttl)
  }

  def summonerById(id: Long, ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): Summoner Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/$id?api_key="
    jsonTo[Long, Summoner](id, ttl)
  }

  def masteryPagesByIds(ids: List[Long], ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): Map[Long, MasteryPages] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}/masteries?api_key=$key"
    jsonToMap[Long, MasteryPages](ids, ttl)
  }

  def summonerNameById(id: Long, ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): String Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/$id/name?api_key=$key"
    jsonTo[Long, String](id, ttl)
  }

  def runePagesByIds(ids: List[Long], ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): Map[Long, RunePages] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}/runes?api_key=$key"
    jsonToMap[Long, RunePages](ids, ttl)
  }

  // =====================
  //    Team api calls
  // =====================

  def teamBySummonerIds(ids: List[Long], ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): Map[Long, List[Team]] Or RiotError= {
    implicit val url = s"${baseUrl(reg)}/$teamVer/team/by-summoner/${ids.mkString(",")}?api_key=$key"
    jsonToMap[Long, List[Team]](ids, ttl)
  }

  def teamByTeamIds(ids: List[String], ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): Map[String, Team] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$teamVer/team/${ids.mkString(",")}?api_key=$key"
    jsonToMap[String, Team](ids, ttl)
  }

  // =====================
  // Static-data api calls
  // =====================

  def champStaticDataById(id: Int, ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): Champion Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$staticDataVer/champion/$id?&api_key=$key"
    jsonTo[Champion](ttl)
  }

  def spellStaticDataById(id: Int, ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): SummonerSpell Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$staticDataVer/summoner-spell/$id?api_key=$key"
    jsonTo[SummonerSpell](ttl)
  }

  // =====================
  // Match api calls
  // =====================

  def matchListById(id: Int, ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): MatchList Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$matchVer/matchlist/by-summoner/$id?api_key=$key"
    jsonTo[MatchList](ttl)
  }

  def matchHistoryById(id: Int, ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): PlayerHistory Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$matchVer/matchhistory/$id?api_key=$key"
    jsonTo[PlayerHistory](ttl)
  }

  def matchByMatchId(id: Int, ttl: Duration = 20.minutes, reg: String = _reg)(implicit caller: ApiCaller): MatchDetail Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$matchVer/match/$id?api_key=$key"
    jsonTo[MatchDetail](ttl)
  }

  // =====================

  private def jsonToMap[A, B: Reads](keys: List[A], ttl: Duration)(implicit caller: ApiCaller, url: String): Map[A, B] Or RiotError = {
    def asMap(jsonResponse: String) = {
      (for {
        key ← keys
        value ← (Json.parse(jsonResponse) \ key.toString).asOpt[B]
      } yield key → value).toMap
    }

    caller.call(url, ttl).map(asMap)
  }

  private def jsonTo[A, B: Reads](key: A, ttl: Duration)(implicit caller: ApiCaller, url: String): B Or RiotError =
    caller.call(url, ttl).map(json => (Json.parse(json) \ key.toString).as[B])

  private def jsonTo[A: Reads](ttl: Duration)(implicit caller: ApiCaller, url: String): A Or RiotError =
    caller.call(url, ttl).map(json => Json.parse(json).as[A])
}

