package org.denigma.preview

import org.denigma.binding.views.BindableView
import org.denigma.codemirror.{CodeMirror, EditorConfiguration}
import org.denigma.codemirror.extensions.EditorConfig
import org.scalajs.dom
import org.scalajs.dom.raw.Element
import org.scalajs.dom.raw.HTMLTextAreaElement

class IntroView(val elem: Element) extends BindableView with ExampleData{

  def activate(): Unit = {
    activate("scala", "text/x-scala",scalaCode)
    activate("html", "htmlmixed",htmlCode)
    activate("sparql", "sparql",sparqlCode)
    activate("turtle", "turtle",turtleCode)
    activate("r","r", rCode)
  }


  def activate(id: String, mode: String, code: String): Unit = {
    val params: EditorConfiguration = EditorConfig.mode(mode).lineNumbers(true).value(code)//.noMargin()
    val editor = dom.document.getElementById(id) match {
        case el:HTMLTextAreaElement =>
          val m = CodeMirror.fromTextArea(el, params)
          m.getDoc().setValue(code)
        case _=> dom.console.error("cannot find text area for the code!")
      }
  }

  override def bindView(): Unit ={
    super.bindView()
    activate()
  }

}
