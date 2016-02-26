package org.denigma.preview

import org.denigma.codemirror.extensions.EditorConfig
import org.denigma.codemirror.{CodeMirror, EditorConfiguration}
import org.scalajs.dom
import org.scalajs.dom.raw.HTMLTextAreaElement

trait ExampleData extends ExampleKappaData {

  lazy val scalaCode = """
                         |  import org.denigma.org.denigma.codemirror.extensions.EditorConfig
                         |  import org.denigma.org.denigma.codemirror.{CodeMirror, EditorConfiguration}
                         |  import org.scalajs.dom
                         |  import org.scalajs.dom.raw.HTMLTextAreaElement
                         |
                         |  val id = "scala"
                         |  val code = println("hello Scala!") //code to add
                         |  val mode = "clike" //language mode, some modes have weird names in org.denigma.codemirror
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

trait ExampleKappaData{


  lazy val kappaCode =
    """
      | ####### TEMPLATE MODEL AS DESCRIBED IN THE MANUAL#############
      |
      |#### Signatures
      |%agent: A(x,c) # Declaration of agent A
      |%agent: B(x) # Declaration of B
      |%agent: C(x1~u~p,x2~u~p) # Declaration of C with 2 modifiable sites
      |
      |#### Rules
      |'a.b' A(x),B(x) <-> A(x!1),B(x!1) @ 'on_rate','off_rate' #A binds B
      |'ab.c' A(x!_,c),C(x1~u) ->A(x!_,c!2),C(x1~u!2) @ 'on_rate' #AB binds C
      |'mod x1' C(x1~u!1),A(c!1) ->C(x1~p),A(c) @ 'mod_rate' #AB modifies x1
      |'a.c' A(x,c),C(x1~p,x2~u) -> A(x,c!1),C(x1~p,x2~u!1) @ 'on_rate' #A binds C on x2
      |'mod x2' A(x,c!1),C(x1~p,x2~u!1) -> A(x,c),C(x1~p,x2~p) @ 'mod_rate' #A modifies x2
      |
      |#### Variables
      |%var: 'on_rate' 1.0E-4 # per molecule per second
      |%var: 'off_rate' 0.1 # per second
      |%var: 'mod_rate' 1 # per second
      |%obs: 'AB' A(x!x.B)
      |%obs: 'Cuu' C(x1~u?,x2~u?)
      |%obs: 'Cpu' C(x1~p?,x2~u?)
      |%obs: 'Cpp' C(x1~p?,x2~p?)
      |
      |
      |#### Initial conditions
      |%init: 1000 A(),B()
      |%init: 10000 C()
      |
      |%mod: [true] do $FLUX "flux.html" [true]
      |%mod: [T]>20 do $FLUX "flux.html" [false]
      |%def: "relativeFluxMaps" "true"
    """.stripMargin

}