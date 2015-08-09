package thangiee.riotapi.currentgame

import play.api.libs.json._

case class Observer(encryptionKey: String = "")

object Observer {
  implicit val observerFmt = Json.reads[Observer]
}
