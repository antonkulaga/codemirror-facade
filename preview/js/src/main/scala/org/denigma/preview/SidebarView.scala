package org.denigma.preview

import org.denigma.binding.extensions._
import org.denigma.binding.views.BindableView
import org.querki.jquery._
import org.scalajs.dom.Element
import rx._

/**
 * Just a simple view for the sidebar, if interested ( see https://github.com/antonkulaga/scala-js-binding )
 */
class SidebarView (val elem: Element) extends BindableView {


  val title = Var("CodeMirror facade")

  val logo = Var("/resources/logo.png")

  /*override def bindElement(el:HTMLElement) = {
    super.bindElement(el)
    $(".ui.accordion").dyn.accordion()
  }*/

  override def bindView()  = {
    super.bindView()
    $(".ui.accordion").dyn.accordion()
  }

}
