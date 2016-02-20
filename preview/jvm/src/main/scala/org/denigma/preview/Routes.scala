package org.denigma.preview

import akka.http.extensions.pjax.PJax
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.server.Directives._
import org.denigma.preview.templates.{Twirl, MyStyles}
import play.twirl.api.Html

import scalacss.Defaults._


/**
 * Trait that countains routes and handlers
 */
trait Routes extends Directives with PJax
{

  lazy val webjarsPrefix = "lib"

  lazy val resourcePrefix = "resources"

  def index =  pathSingleSlash{ctx=>
    ctx.complete {
      HttpResponse(  entity = HttpEntity(MediaTypes.`text/html`.withCharset(HttpCharsets.`UTF-8`), html.index(None).body  ))
    }
  }

  def mystyles =    path("styles" / "mystyles.css"){
    complete  {
      HttpResponse(  entity = HttpEntity(MediaTypes.`text/css`.withCharset(HttpCharsets.`UTF-8`),  MyStyles.render   ))   }
  }

  def loadResources = pathPrefix(resourcePrefix~Slash) {
    getFromResourceDirectory("")
  }

  def webjars =pathPrefix(webjarsPrefix ~ Slash)  {  getFromResourceDirectory(webjarsPrefix)  }


  def defaultPage: Option[Html] = None


  val loadPage:Html=>Html = h=> html.index(Some(h))

  def routes = index ~  webjars ~ mystyles ~ loadResources
}