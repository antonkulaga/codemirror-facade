package org.denigma.preview

import org.denigma.binding.binders.{NavigationBinder, GeneralBinder}
import org.denigma.binding.extensions.sq
import org.denigma.binding.views.BindableView
import org.denigma.controls.code.CodeBinder
import org.querki.jquery._
import org.scalajs.dom
import org.scalajs.dom.raw.HTMLElement
import org.denigma.binding.extensions._

import scala.collection.immutable.Map
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scala.util.Try

/**
 * Just a simple view for the whole app, if interested ( see https://github.com/antonkulaga/scala-js-binding )
 */
@JSExport("FrontEnd")
object FrontEnd extends BindableView with scalajs.js.JSApp
{

  lazy val elem: HTMLElement = dom.document.body

  val sidebarParams = js.Dynamic.literal(
    exclusive = false,
    dimPage = false,
    closable = false,
    useLegacy = false
  )
  /**
   * Register views
   */
  override lazy val injector = defaultInjector
    .register("sidebar")((el, params) => new SidebarView(el).withBinder(new GeneralBinder(_)))
    .register("intro")((el, params) => new IntroView(el).withBinder(new CodeBinder(_)))
    .register("features")((el, params) => new FeaturesView(el))


  @JSExport
  def main(): Unit = {
    this.bindView()
  }

  @JSExport
  def showLeftSidebar() = {
    $(".left.sidebar").dyn.sidebar(sidebarParams).sidebar("show")
  }

  @JSExport
  def load(content: String, into: String): Unit = {
    dom.document.getElementById(into).innerHTML = content
  }

  @JSExport
  def moveInto(from: String, into: String): Unit = {
    for {
      ins <- sq.byId(from)
      intoElement <- sq.byId(into)
    } {
      this.loadElementInto(intoElement, ins.innerHTML)
      ins.parentNode.removeChild(ins)
    }
  }


  withBinders(me => List(new CodeBinder(me), new NavigationBinder(me)))


}
