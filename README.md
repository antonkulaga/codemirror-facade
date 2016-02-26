org.denigma.codemirror facade
=================

This if a facade of org.denigma.codemirror library. All the code is inside org.denigma.codemirror subproject.

Usage
=====

In order to resolve a lib you should add a resolver::
```scala
resolvers += sbt.Resolver.bintrayRepo("denigma", "denigma-releases") //add resolver
libraryDependencies += "org.denigma" %%% "codemirror-facade" % "5.11-0.7" //add dependency
```

In your code:
-------------

Add text area somewhere:

```html
<textarea id="scala"> </textarea>
```

Write some simple code 

```scala
  import org.denigma.org.denigma.codemirror.extensions.EditorConfig
  import org.denigma.org.denigma.codemirror.{CodeMirror, EditorConfiguration}
  import org.scalajs.dom
  import org.scalajs.dom.raw.HTMLTextAreaElement

  val id = "scala"
  val code = println("hello Scala!") //code to add
  val mode = "clike" //language mode, some modes have weird names in org.denigma.codemirror
  val params: EditorConfiguration = EditorConfig.mode(mode).lineNumbers(true) //config
  val editor = dom.document.getElementById(id) match {
    case el:HTMLTextAreaElement =>
      val m = CodeMirror.fromTextArea(el,params)
      m.getDoc().setValue(code) //add the code
    case _=> dom.console.error("cannot find text area for the code!")
  }
```


Preview
-------

Preview subprojects are required to see some examples of using the facade.

To run preview:
```sbt
    sbt //to opens sbt console
    re-start //Use this command **instead of** run to run the app
    Open localhost:5554 to see the result, it should reload whenever any sources are changed
```

Extensions
----------

In org.denigma.extensions package there are some methods that extend default codemirror functionality.
