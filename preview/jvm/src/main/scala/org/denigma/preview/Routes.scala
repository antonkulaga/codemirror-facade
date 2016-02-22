package org.denigma.preview

import akka.http.extensions.pjax.PJax
import akka.http.extensions.resources.TextFilesDirectives
import akka.http.scaladsl.model._
import akka.http.scaladsl.server._
import akka.http.scaladsl.server.Directives._
import org.denigma.preview.templates.{Twirl, MyStyles}
import play.twirl.api.Html

import scalacss.Defaults._


/**
 * Trait that countains routes and handlers
 */
trait Routes extends Directives with PJax with TextFilesDirectives
{

  lazy val webjarsPrefix = "lib"

  lazy val resourcePrefix = "resources"

  lazy val sourcesPath = "js/src/main/scala/"

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

  lazy val loadPage: Html => Html = h => html.index( Some(h) )


  def page(html:Html): Route = pjax[Twirl](html, loadPage){h=>c=>
    val resp = HttpResponse(  entity = HttpEntity(MediaTypes.`text/html`.withCharset(HttpCharsets.`UTF-8`), h.body  ))
    c.complete(resp)
  }

  def menu = pathPrefix("pages" ~ Slash){ ctx =>
    ctx.unmatchedPath.toString() match {
      case "intro"=> page(html.intro())(ctx)
      case "features"=> page(html.features())(ctx)
      case other => ctx.complete(s"page $other not found!")
    }
  }

/*  def loadSources: Route = (pathPrefix("sources" ~ Slash) | pathPrefix("source" ~ Slash)){
    extractUnmatchedPath { place ⇒
      parameters("from", "to"){
        case (from, to) =>
          extractLog { case log=>
            filePath(sourcesPath,place,log,'/') match {
              case ""   ⇒
                reject()
              case resourceName ⇒
                this.linesFromResource(resourceName, from, to) { case lines =>
                  complete(HttpResponse(entity = HttpEntity(MediaTypes.`text/css`.withCharset(HttpCharsets.`UTF-8`),  lines.reduce(_+"\n"+_)  )  ))
                }
            }
          }
      }
    }
  }*/



  def routes = index ~  webjars ~ mystyles ~ loadResources ~ menu //~ loadSources
}