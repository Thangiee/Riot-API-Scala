package thangiee.riotapi.utils

trait ApiCaller {
  def call(url: String)(implicit apiKey: ApiKey): Either[RiotException, String]
}
