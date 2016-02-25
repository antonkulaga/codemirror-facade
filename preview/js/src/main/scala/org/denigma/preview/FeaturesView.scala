package org.denigma.preview

import editors.CodeMirrorEditor
import org.denigma.binding.binders.ReactiveBinder
import org.denigma.binding.views.BindableView
import org.denigma.codemirror.{Editor, CodeMirror, EditorConfiguration}
import org.denigma.codemirror.extensions.EditorConfig
import org.scalajs.dom
import org.scalajs.dom.raw.{Element, HTMLTextAreaElement}
import rx.Var
import scala.scalajs.js.JSConverters._
import scala.scalajs.js
import scala.util.Try

/**
  * Created by antonkulaga on 2/24/16.
  */
class FeaturesView(val elem: Element) extends BindableView with WithMirrors with EditorMaker{

  val editors: Var[Map[String, Editor]] = Var(Map.empty)

  protected def onGutterClick(ed: Editor, num: Int): Unit = {
    val lm = ed.lineInfo(num)
    ed.setGutterMarker()

  }


  override def makeEditor(name: String, element: ViewElement, codeMode: String): Editor = element match {
    case area: HTMLTextAreaElement =>

      val editor = this.createEditor(area, area.value, codeMode)
      val handler: js.Function1[Editor, Unit] = onGutterClick _
      editor.on("gutterClick", handler)
      /*
      editor.on("gutterClick", function(cm, n) {
        var info = cm.lineInfo(n);
        cm.setGutterMarker(n, "breakpoints", info.gutterMarkers ? null : makeMarker());
      });

      function makeMarker() {
        var marker = document.createElement("div");
        marker.style.color = "#822";
        marker.innerHTML = "●";
        return marker;
      }
      ed
*/
      editor

    case _ =>
      val message = "cannot find text area for the code!"
      throw new Exception(message)
  }

  override def bindView() = {
    super.bindView()
  }

}


trait WithMirrors extends BindableView {

  def editors: Var[Map[String, Editor]]

  def makeEditor(name: String, element: Element, codeMode: String): Editor

}


trait EditorMaker {

  def createEditor(area: HTMLTextAreaElement, textValue: String, codeMode: String, readOnly: Boolean = false): Editor = {
    val params = EditorConfig
      .mode(codeMode)
      .lineNumbers(true)
      .value(textValue)
      .readOnly(readOnly)
      .viewportMargin(Integer.MAX_VALUE)
        .gutters(js.Array("CodeMirror-linenumbers", "breakpoints"))
    //  gutters: ["CodeMirror-linenumbers", "breakpoints"]

    CodeMirror.fromTextArea(area, params)
  }

}

class EditorsBinder(view: WithMirrors, defaultMode: String = "htmlmixed") extends ReactiveBinder
{
  override def elementPartial(el: Element, ats: Map[String, String]): PartialFunction[(String, String), Unit] = {
    case (name, value) =>
      val eds = view.editors.now
      val mode = ats.getOrElse("mode", defaultMode)
      if (!eds.contains(name)) view.editors() = eds.updated(value, view.makeEditor(value, el, mode))//else - nothing
  }
}