package org.denigma.codemirror

/**
  * Created by antonkulaga on 25/02/16.
  */
package object extensions {

  implicit def editorExtended(editor: Editor): ExtendedEditor = new ExtendedEditor(editor)

  //implicit def editorExtended(editor: Editor): ExtendedEditor = new ExtendedEditor(editor)

  implicit def editorExtended(change: EditorChangeLike): ExtendedChange = new ExtendedChange(change)

}
