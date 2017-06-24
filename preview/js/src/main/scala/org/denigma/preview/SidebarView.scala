package org.denigma.preview

import org.denigma.binding.binders.{GeneralBinder, NavigationBinder}
import org.denigma.binding.extensions._
import org.denigma.binding.views.{BindableView, CollectionSeqView}
import org.querki.jquery._
import org.scalajs.dom.Element
import org.scalajs.dom.Element
import org.scalajs.dom.raw.Element
import rx._

import scala.collection.immutable.{Map, Seq}

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

  override lazy val injector = defaultInjector
    .register("menu"){
      case (el, args) => new MenuView(el)
        .withBinder(new GeneralBinder(_))
        .withBinder(new NavigationBinder(_))
    }
}




class MenuView(elem: Element) extends MapCollectionView(elem) {
  self =>

  override val items: Rx[Seq[Map[String, Any]]] = Var(
    Seq(
      Map("uri" -> "pages/intro", "label" -> "Getting started"),
      Map("uri" -> "pages/features", "label" -> "Features")
    )
  )
}

import org.denigma.binding.binders.{MapItemsBinder, NavigationBinder}
import org.scalajs.dom.Element
import rx.Var

import scala.collection.immutable._

object MapCollectionView {

    class JustMapView(val elem: Element, val params: Map[String, Any]) extends BindableView
    {
        val reactiveMap: Map[String, Var[String]] = params.map(kv => (kv._1, Var(kv._2.toString)))

        this.withBinders(m => new MapItemsBinder(m, reactiveMap)::new NavigationBinder(m)::Nil)
    }

}



abstract class MapCollectionView(val elem: Element) extends CollectionSeqView
{
    override type Item = Map[String, Any]

    override type ItemView = BindableView

    def newItemView(item: Item): ItemView = this.constructItemView(item){ (el, mp) => new MapCollectionView.JustMapView(el, item) }

}