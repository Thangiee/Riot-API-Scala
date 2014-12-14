package thangiee.riotapi.utils

trait ApiCaller {
  def call(url: String): Option[String]
}
