package org.denigma
package codemirror

import org.scalajs._
import org.scalajs.dom.raw.{HTMLTextAreaElement, Event, HTMLElement}
import scala.scalajs.js
import scala.scalajs.js._
import scala.scalajs.js.annotation.JSName

@js.native
trait LineInfo extends js.Object {

  val line: Int = js.native
  val handle: LineHandle = js.native
  val text: String = js.native
  val gutterMarkers = js.native
  val textClass = js.native
  val bgClass = js.native
  val wrapClass = js.native
  val widgets = js.native

/*  return {line: n, handle: line, text: line.text, gutterMarkers: line.gutterMarkers,
    textClass: line.textClass, bgClass: line.bgClass, wrapClass: line.wrapClass,
    widgets: line.widgets};*/
}

@js.native
trait Editor extends js.Object {
  def hasFocus(): Boolean = js.native
  def findPosH(start: Position, amount: Double, unit: String, visually: Boolean): js.Any = js.native
  def findPosV(start: Position, amount: Double, unit: String): js.Any = js.native
  def setOption(option: String, value: js.Any): Unit = js.native
  def getOption(option: String): js.Dynamic = js.native
  def addKeyMap(map: js.Any, bottom: Boolean = js.native): Unit = js.native
  def removeKeyMap(map: js.Any): Unit = js.native
  def addOverlay(mode: js.Any, options: js.Any = js.native): Unit = js.native
  def removeOverlay(mode: js.Any): Unit = js.native
  def getDoc(): Doc = js.native
  def swapDoc(doc: Doc): Doc = js.native
  def setGutterMarker(line: js.Any, gutterID: String, value: HTMLElement): LineHandle = js.native
  def clearGutter(gutterID: String): Unit = js.native
  def addLineClass(line: js.Any, where: String, _clazz: String): LineHandle = js.native
  def removeLineClass(line: js.Any, where: String, clazz: String): LineHandle = js.native
  def lineInfo(line: Int): LineInfo = js.native

  def addWidget(pos: Position, node: HTMLElement, scrollIntoView: Boolean): Unit = js.native
  def addLineWidget(line: js.Any, node: HTMLElement, options: js.Any = js.native): LineWidget = js.native
  def setSize(width: js.Any, height: js.Any): Unit = js.native
  def scrollTo(x: Double, y: Double): Unit = js.native
  def getScrollInfo(): js.Any = js.native
  def scrollIntoView(pos: Position, margin: Double = js.native): Unit = js.native
  def cursorCoords(where: Boolean, mode: String): js.Any = js.native
  def charCoords(pos: Position, mode: String): js.Any = js.native
  def coordsChar(`object`: js.Any, mode: String = js.native): Position = js.native
  def defaultTextHeight(): Double = js.native
  def defaultCharWidth(): Double = js.native
  def getViewport(): js.Any = js.native
  def refresh(): Unit = js.native
  def getTokenAt(pos: Position): js.Any = js.native
  def getStateAfter(line: Double = js.native): js.Dynamic = js.native
  def operation[T](fn: js.Function0[T]): T = js.native
  def indentLine(line: Double, dir: String = js.native): Unit = js.native
  def focus(): Unit = js.native
  def getInputField(): HTMLTextAreaElement = js.native
  def getWrapperElement(): HTMLElement = js.native
  def getScrollerElement(): HTMLElement = js.native
  def getGutterElement(): HTMLElement = js.native
  def on(eventName: String, handler: js.Function1[Editor, Unit]): Unit = js.native
  def on(eventName: String, handler: js.Function2[Editor, _, Unit]): Unit = js.native //not sure if

  def off(eventName: String, handler: js.Function1[Editor, Unit]): Unit = js.native
  def off(eventName: String, handler: js.Function2[Editor, Any, Unit]): Unit = js.native

}


@js.native
@JSName("Doc")
class Doc protected () extends js.Object {
  def this(text: String, mode: js.Any = js.native, firstLineNumber: Double = js.native) = this()
  def getValue(seperator: String = js.native): String = js.native
  def setValue(content: String): Unit = js.native
  def getRange(from: Position, to: Position, seperator: String = js.native): String = js.native
  def replaceRange(replacement: String, from: Position, to: Position): Unit = js.native
  def getLine(n: Double): String = js.native
  def setLine(n: Double, text: String): Unit = js.native
  def removeLine(n: Double): Unit = js.native
  def lineCount(): Double = js.native
  def firstLine(): Double = js.native
  def lastLine(): Double = js.native
  def getLineHandle(num: Double): LineHandle = js.native
  def getLineNumber(handle: LineHandle): Double = js.native
  def eachLine(f: js.Function1[LineHandle, Unit]): Unit = js.native
  def eachLine(start: Double, end: Double, f: js.Function1[LineHandle, Unit]): Unit = js.native
  def markClean(): Unit = js.native
  def isClean(): Boolean = js.native
  def getSelection(): String = js.native
  def replaceSelection(replacement: String, collapse: String = js.native): Unit = js.native
  def getCursor(start: String = js.native): Position = js.native
  def somethingSelected(): Boolean = js.native
  def setCursor(pos: Position): Unit = js.native
  def setSelection(anchor: Position, head: Position): Unit = js.native
  def extendSelection(from: Position, to: Position = js.native): Unit = js.native
  def setExtending(value: Boolean): Unit = js.native
  def getEditor(): Editor = js.native
  def copy(copyHistory: Boolean): Doc = js.native
  def linkedDoc(options: js.Any): Doc = js.native
  def unlinkDoc(doc: Doc): Unit = js.native
  def iterLinkedDocs(fn: js.Function2[Doc, Boolean, Unit]): Unit = js.native
  def undo(): Unit = js.native
  def redo(): Unit = js.native
  def historySize(): js.Any = js.native
  def clearHistory(): Unit = js.native
  def getHistory(): js.Dynamic = js.native
  def setHistory(history: js.Any): Unit = js.native
  def markText(from: Position, to: Position, options: TextMarkerOptions = js.native): TextMarker = js.native
  def setBookmark(pos: Position, options: js.Any = js.native): TextMarker = js.native
  def findMarksAt(pos: Position): js.Array[TextMarker] = js.native
  def getAllMarks(): js.Array[TextMarker] = js.native
  def getMode(): js.Dynamic = js.native
  def posFromIndex(index: Double): Position = js.native
  def indexFromPos(`object`: Position): Double = js.native
}

@js.native
trait LineHandle extends js.Object {
  var text: String = js.native
}

@js.native
trait TextMarker extends js.Object {
  def clear(): Unit = js.native
  def find(): Position = js.native
  def getOptions(copyWidget: Boolean): TextMarkerOptions = js.native
}

@js.native
trait LineWidget extends js.Object {
  def clear(): Unit = js.native
  def changed(): Unit = js.native
}

@js.native
trait EditorChange extends js.Object {
  var from: Position = js.native
  var to: Position = js.native
  var text: js.Array[String] = js.native
  var removed: String = js.native
}

@js.native
trait EditorChangeLinkedList extends EditorChange {
  var next: EditorChangeLinkedList = js.native
}

@js.native
trait EditorChangeCancellable extends EditorChange {
  def update(from: Position = js.native, to: Position = js.native, text: String = js.native): Unit = js.native
  def cancel(): Unit = js.native
}

@js.native
trait Position extends js.Object {
  var ch: Double = js.native
  var line: Double = js.native
}

@js.native
trait EditorConfiguration extends js.Object {
  var value: js.Any = js.native
  var mode: js.Any = js.native
  var theme: String = js.native
  var indentUnit: Double = js.native
  var smartIndent: Boolean = js.native
  var tabSize: Double = js.native
  var indentWithTabs: Boolean = js.native
  var electricChars: Boolean = js.native
  var rtlMoveVisually: Boolean = js.native
  var keyMap: String = js.native
  var extraKeys: js.Any = js.native
  var lineWrapping: Boolean = js.native
  var lineNumbers: Boolean = js.native
  var firstLineNumber: Double = js.native
  var lineNumberFormatter: js.Function1[Double, String] = js.native
  var gutters: js.Array[String] = js.native
  var fixedGutter: Boolean = js.native
  var readOnly: js.Any = js.native
  var showCursorWhenSelecting: Boolean = js.native
  var undoDepth: Double = js.native
  var historyEventDelay: Double = js.native
  var tabindex: Double = js.native
  var autofocus: Boolean = js.native
  var dragDrop: Boolean = js.native
  var onDragEvent: js.Function2[Editor, Event, Boolean] = js.native
  var onKeyEvent: js.Function2[Editor, Event, Boolean] = js.native
  var cursorBlinkRate: Double = js.native
  var cursorHeight: Double = js.native
  var workTime: Double = js.native
  var workDelay: Double = js.native
  var pollInterval: Double = js.native
  var flattenSpans: Boolean = js.native
  var maxHighlightLength: Double = js.native
  var viewportMargin: Double = js.native
}

@js.native
trait TextMarkerOptions extends js.Object {
  var className: String = js.native
  var inclusiveLeft: Boolean = js.native
  var inclusiveRight: Boolean = js.native
  var atomic: Boolean = js.native
  var collapsed: Boolean = js.native
  var clearOnEnter: Boolean = js.native
  var replacedWith: HTMLElement = js.native
  var readOnly: Boolean = js.native
  var addToHistory: Boolean = js.native
  var startStyle: String = js.native
  var endStyle: String = js.native
  var shared: Boolean = js.native
}

@js.native
@JSName("CodeMirror")
object CodeMirror extends js.Object {
  var Pass: js.Any = js.native
  def fromTextArea(host: HTMLTextAreaElement, options: EditorConfiguration = js.native): Editor = js.native
  //def fromTextArea(host: HTMLTextAreaElement, options: js.Any): Editor = js.native

  var version: String = js.native
  def defineExtension(name: String, value: js.Any): Unit = js.native
  def defineDocExtension(name: String, value: js.Any): Unit = js.native
  def defineOption(name: String, default: js.Any, updateFunc: js.Function): Unit = js.native
  def defineInitHook(func: js.Function): Unit = js.native
  def on(element: js.Any, eventName: String, handler: js.Function): Unit = js.native
  def off(element: js.Any, eventName: String, handler: js.Function): Unit = js.native
}