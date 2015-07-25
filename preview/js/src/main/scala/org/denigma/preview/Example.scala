package org.denigma.preview

import org.denigma.codemirror.extensions.EditorConfig
import org.denigma.codemirror.{CodeMirror, EditorConfiguration}
import org.scalajs.dom
import org.scalajs.dom.raw.HTMLTextAreaElement

/**
 *  Just simple codemirror example
 */
object Example extends ExampleData{


  val name:String = "codemirror"

  def activate(): Unit = {
    activate("scala","text/x-scala",scalaCode)
    activate("html","htmlmixed",htmlCode)
    activate("sparql","sparql",sparqlCode)
    activate("turtle","turtle",turtleCode)
    activate("r","r",rCode)
  }


  def activate(id:String,mode:String,code:String): Unit = {
    val params:EditorConfiguration =EditorConfig.mode(mode).lineNumbers(true).value(code)//.noMargin()
    val editor = dom.document.getElementById(id) match {
      case el:HTMLTextAreaElement =>
        val m = CodeMirror.fromTextArea(el,params)
        m.getDoc().setValue(code)
      case _=> dom.console.error("cannot find text area for the code!")
    }

  }
}

trait ExampleData{

  lazy val scalaCode = """
                         |  import org.denigma.codemirror.extensions.EditorConfig
                         |  import org.denigma.codemirror.{CodeMirror, EditorConfiguration}
                         |  import org.scalajs.dom
                         |  import org.scalajs.dom.raw.HTMLTextAreaElement
                         |
                         |  val id = "scala"
                         |  val code = println("hello Scala!") //code to add
                         |  val mode = "clike" //language mode, some modes have weird names in codemirror
                         |  val params:EditorConfiguration =EditorConfig.mode(mode).lineNumbers(true) //config
                         |  val editor = dom.document.getElementById(id) match {
                         |    case el:HTMLTextAreaElement =>
                         |      val m = CodeMirror.fromTextArea(el,params)
                         |      m.getDoc().setValue(code) //add the code
                         |    case _=> dom.console.error("cannot find text area for the code!")
                         |  }""".stripMargin



  lazy val htmlCode ="""<textarea id="scala"> </textarea>""".stripMargin

  lazy val rCode =
    """
      |print("Hello R language!")
      |cat("Your height is ", X$height, " and your weight is ", X$weight, "\n")
    """.stripMargin

  lazy val turtleCode =
    """
      |@prefix foaf: <http://xmlns.com/foaf/0.1/> .
      |@prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#> .
      |@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
      |
      |<http://purl.org/net/bsletten>
      |    a foaf:Person;
      |    foaf:interest <http://www.w3.org/2000/01/sw/>;
      |    foaf:based_near [
      |        geo:lat "34.0736111" ;
      |        geo:lon "-118.3994444"
      |   ]
    """.stripMargin


  lazy val sparqlCode =
    """
      |PREFIX a: <http://www.w3.org/2000/10/annotation-ns#>
      |PREFIX dc: <http://purl.org/dc/elements/1.1/>
      |PREFIX foaf: <http://xmlns.com/foaf/0.1/>
      |PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
      |
      |# Comment!
      |
      |SELECT ?given ?family
      |WHERE {
      |  {
      |    ?annot a:annotates <http://www.w3.org/TR/rdf-sparql-query/> .
      |    ?annot dc:creator ?c .
      |    OPTIONAL {?c foaf:givenName ?given ;
      |                 foaf:familyName ?family }
      |  } UNION {
      |    ?c !foaf:knows/foaf:knows? ?thing.
      |    ?thing rdfs
      |  } MINUS {
      |    ?thing rdfs:label "剛柔流"@jp
      |  }
      |  FILTER isBlank(?c)
      |}
    """.stripMargin
}