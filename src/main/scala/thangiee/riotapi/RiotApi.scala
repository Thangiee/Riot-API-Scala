package thangiee.riotapi

import org.scalactic.Or
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
import org.json4s._
import org.json4s.jackson.JsonMethods._

import scala.concurrent.duration._

/** Provide everything you need to consume the Riot API
  * <p>
  * See <a href="https://developer.riotgames.com/api/methods">https://developer.riotgames.com/api/methods</a> for
  * documentations of all api methods.
  */
object RiotApi {

  private implicit val jsonFormats = DefaultFormats

  private var _key = ""
  private var _reg = "na"

  val gameVer       = "v1.3"
  val summVer       = "v1.4"
  val teamVer       = "v2.4"
  val staticDataVer = "v1.2"
  val statsVer      = "v1.3"
  val leagueVer     = "v2.5"
  val matchVer      = "v2.2"

  /** Set the default region that will be used if no region is specified when using an api call
    * @param regionId abbreviation of a region's name
    */
  def regionId_=(regionId: String): Unit = _reg = regionId.toLowerCase

  /** Get the default region */
  def regionId: String = _reg

  /** Generate the common part of the URL in all api URLs */
  def baseUrl(region: String = _reg): String = s"https://${region.toLowerCase}.api.pvp.net/api/lol/${region.toLowerCase}"

  /** Set the api key to use for api calls */
  def key_=(key: String): Unit = _key = key

  /** Get the currently set api key */
  def key: String = _key

  // =====================
  //   Current games api call
  // =====================

  def currentGameInfoById(id: Long, reg: String = _reg, ttl: Duration = 20.minutes)(implicit c: ApiCaller): CurrentGameInfo Or RiotError = {
    val platformId = reg.toLowerCase match {
      case "na" => "NA1"
      case "euw" => "EUW1"
      case "eune" => "EUN1"
      case "kr" => "KR"
      case "oce" => "OC1"
      case "br" => "BR1"
      case "lan" => "LA1"
      case "las" => "LA2"
      case "ru" => "RU"
      case "tr" => "TR1"
      case _ => "PBE1"
    }
    implicit val url = s"https://$reg.api.pvp.net/observer-mode/rest/consumer/getSpectatorGameInfo/$platformId/$id?api_key=$key"
    jsonTo[CurrentGameInfo](ttl)
  }

  // =====================
  //    Games api call
  // =====================

  def recentGamesById(id: Long, reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): RecentGames Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$gameVer/game/by-summoner/$id/recent?api_key=$key"
    jsonTo[RecentGames](ttl)
  }

  // =====================
  //    league api calls
  // =====================

  def leagueByIds(ids: Seq[Long], reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): Map[Long, Seq[League]] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/by-summoner/${ids.mkString(",")}?api_key=$key"
    jsonToMap[Long, Seq[League]](ids, ttl)
  }

  def leagueEntryByIds(ids: Seq[Long], reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): Map[Long, Seq[League]] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/by-summoner/${ids.mkString(",")}/entry?api_key=$key"
    jsonToMap[Long, Seq[League]](ids, ttl)
  }

  def leagueEntryById(id: Long, reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): Seq[League] Or RiotError = {
    leagueEntryByIds(Seq(id), reg, ttl).map(_.getOrElse(id, Nil))
  }

  def leagueByTeamIds(ids: Seq[Long], reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): Map[Long, Seq[League]] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/by-team/${ids.mkString(",")}?api_key=$key"
    jsonToMap[Long, Seq[League]](ids, ttl)
  }

  def leagueEntryByTeamIds(ids: Seq[Long], reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): Map[Long, Seq[League]] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/by-team/${ids.mkString(",")}/entry?api_key=$key"
    jsonToMap[Long, Seq[League]](ids, ttl)
  }

  def challengerLeague(queue: QueueType, reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): League Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/challenger?type=${queue.`type`}&api_key=$key"
    jsonTo[League](ttl)
  }

  def masterLeague(queue: QueueType, reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): League Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$leagueVer/league/master?type=${queue.`type`}&api_key=$key"
    jsonTo[League](ttl)
  }

  // =====================
  //    Stats api calls
  // =====================

  def rankedStatsById(id: Long, season: Int, reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): RankedStats Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$statsVer/stats/by-summoner/$id/ranked?season=SEASON$season&api_key=$key"
    jsonTo[RankedStats](ttl)
  }

  def summaryStatsById(id: Long, season: Int, reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): PlayerStatsSummaryList Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$statsVer/stats/by-summoner/$id/summary?season=SEASON$season&api_key=$key"
    jsonTo[PlayerStatsSummaryList](ttl)
  }

  // =====================
  //  Summoner api calls
  // =====================

  def summonerByNames(names: Seq[String], reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): Map[String, Summoner] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/by-name/${names.mkString(",")}?api_key=$key"
    jsonToMap[String, Summoner](names.map(_.toLowerCase), ttl)
  }

  def summonerByName(name: String, reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): Summoner Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/by-name/$name?api_key=$key"
    jsonTo[String, Summoner](name.toLowerCase, ttl)
  }

  def summonerByIds(ids: Seq[Long], reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): Map[Long, Summoner] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}?api_key=$key"
    jsonToMap[Long, Summoner](ids, ttl)
  }

  def summonerById(id: Long, reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): Summoner Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/$id?api_key=$key"
    jsonTo[Long, Summoner](id, ttl)
  }

  def masteryPagesByIds(ids: Seq[Long], reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): Map[Long, MasteryPages] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}/masteries?api_key=$key"
    jsonToMap[Long, MasteryPages](ids, ttl)
  }

  def summonerNameById(id: Long, reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): String Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/$id/name?api_key=$key"
    jsonTo[Long, String](id, ttl)
  }

  def runePagesByIds(ids: Seq[Long], reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): Map[Long, RunePages] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$summVer/summoner/${ids.mkString(",")}/runes?api_key=$key"
    jsonToMap[Long, RunePages](ids, ttl)
  }

  // =====================
  //    Team api calls
  // =====================

  def teamBySummonerIds(ids: Seq[Long], reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): Map[Long, Seq[Team]] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$teamVer/team/by-summoner/${ids.mkString(",")}?api_key=$key"
    jsonToMap[Long, Seq[Team]](ids, ttl)
  }

  def teamByTeamIds(ids: Seq[String], reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): Map[String, Team] Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$teamVer/team/${ids.mkString(",")}?api_key=$key"
    jsonToMap[String, Team](ids, ttl)
  }

  // =====================
  // Static-data api calls
  // =====================

  def champStaticDataById(id: Int, reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): Champion Or RiotError = {
    implicit val url = s"https://global.api.pvp.net/api/lol/static-data/$reg/$staticDataVer/champion/$id?api_key=$key"
    jsonTo[Champion](ttl)
  }

  def spellStaticDataById(id: Int, reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): SummonerSpell Or RiotError = {
    implicit val url = s"https://global.api.pvp.net/api/lol/static-data/$reg/$staticDataVer/summoner-spell/$id?api_key=$key"
    jsonTo[SummonerSpell](ttl)
  }

  // =====================
  // Match api calls
  // =====================

  def matchListById(id: Int, reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): MatchList Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$matchVer/matchlist/by-summoner/$id?api_key=$key"
    jsonTo[MatchList](ttl)
  }

  def matchHistoryById(id: Int, reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): PlayerHistory Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$matchVer/matchhistory/$id?api_key=$key"
    jsonTo[PlayerHistory](ttl)
  }

  def matchByMatchId(id: Int, reg: String = _reg, ttl: Duration = 20.minutes)(implicit caller: ApiCaller): MatchDetail Or RiotError = {
    implicit val url = s"${baseUrl(reg)}/$matchVer/match/$id?api_key=$key"
    jsonTo[MatchDetail](ttl)
  }

  // =====================

  private def jsonToMap[A, B: Manifest](keys: Seq[A], ttl: Duration)(implicit caller: ApiCaller, url: String): Map[A, B] Or RiotError = {
    def asMap(jsonResponse: String) = {
      (for {
        key ← keys
        value ← (parse(jsonResponse) \ key.toString).extractOpt[B]
      } yield key → value).toMap
    }

    caller.call(url, ttl).map(asMap)
  }

  private def jsonTo[A, B: Manifest](key: A, ttl: Duration)(implicit caller: ApiCaller, url: String): B Or RiotError =
    caller.call(url, ttl).map(json => (parse(json) \ key.toString).extract[B])

  private def jsonTo[A: Manifest](ttl: Duration)(implicit caller: ApiCaller, url: String): A Or RiotError =
    caller.call(url, ttl).map(json => parse(json).extract[A])
}

