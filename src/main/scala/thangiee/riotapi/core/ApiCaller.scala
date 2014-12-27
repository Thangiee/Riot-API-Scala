package thangiee.riotapi.core

trait ApiCaller {
  def call(url: String)(implicit apiKey: ApiKey): Either[RiotException, String]
}
