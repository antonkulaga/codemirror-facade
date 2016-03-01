package org.denigma.codemirror.extensions


import org.denigma.codemirror.{EditorChangeLike, Editor}

import scala.scalajs.js

class ExtendedChange(val change: EditorChangeLike) extends AnyVal {

  def mergeSpans(other: (Int, Int)): (Int, Int) = (changedSpan, other) match {
    case ( (min1, max1), (min2, max2) ) => (Math.min(min1, min2), Math.max(max1, max2))
  }

  def changedSpan = ( change.from.line,
    change.to.line -( if(change.removed.length > 1) change.removed.length -1 else 0 ) + ( if(change.text.length > 1) change.text.length -1 else 0 )
  ) //NOTE IS BUGGY

  def newLines: Map[Int, String] = change.text.zipWithIndex.map{
    case (s, i) => (i + change.from.line, s)
  } toMap

  //def changedLines: Seq[Int] = change.text.indices.map{  case i => change.from.line + i } ++ change.removed.indices.map{  case i => change.from.line -i }
}

class ExtendedEditor(val editor: Editor) extends AnyVal
{
  def addOnGutterClick(fun: (Editor, Int)=> Unit) = {
    val handler: js.Function2[Editor, _, Unit] = fun
    editor.on("gutterClick", handler)
  }

  /**
    * @param fun handler to react on change
    *
    * Fires every time the content of the editor is changed.
    * The changeObj is a {from, to, text, removed, origin} object
    * containing information about the changes that occurred as second argument.
    * from and to are the positions (in the pre-change coordinate system)
    * where the change started and ended
    * (for example, it might be {ch:0, line:18} if the position is at the beginning of line #19).
    * text is an array of strings representing the text that replaced the changed range (split by line).
    * removed is the text that used to be between from and to,
    * which is overwritten by this change.
    * This event is fired before the end of an operation, before the DOM updates happen.
    */
  def addOnChange(fun: (Editor, EditorChangeLike)=> Unit) = {
    val handler: js.Function2[Editor, _, Unit] = fun
    editor.on("change", handler)
  }

  def addOnChanges(fun: (Editor, js.Array[EditorChangeLike])=> Unit) = {
    val handler: js.Function2[Editor, _, Unit] = fun
    editor.on("changes", handler)
  }

  /**
    * @param fun handler to react BeforeChanges
    * This event is fired before a change is applied,
    * and its handler may choose to modify or cancel the change.
    * The changeObj object has from, to, and text properties,
    * as with the "change" event. It also has a cancel() method,
    * which can be called to cancel the change, and,
    * if the change isn't coming from an undo or redo event,
    * an update(from, to, text) method, which may be used to modify the change.
    */
  def addBeforeChange(fun: (Editor, EditorChangeLike)=> Unit) = {
    val handler: js.Function2[Editor, _, Unit] = fun
    editor.on("change", handler)
  }

  def lineText(line: Int): String = {
    editor.lineInfo(line).text
  }

  def linesText(lines: Seq[Int]): Seq[(Int, String)] = lines.map(num => num-> editor.lineText(num))

}

