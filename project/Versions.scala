object Versions extends WebJarsVersions with ScalaJSVersions with SharedVersions
{
	val scala = "2.11.7"

	val akkaHttpExtensions = "0.0.10"

}

trait ScalaJSVersions {

	val facade = "5.11-0.6"

	val jqueryFacade = "0.11"

	val dom = "0.9.0"

	val bindingControls = "0.0.10"

}

//versions for libs that are shared between client and server
trait SharedVersions
{
	val scalaTags = "0.5.1"

	val scalaCSS = "0.4.0"

	val scalaTest =  "3.0.0-SNAP13"

}


trait WebJarsVersions{

	val jquery =  "2.2.0"

	val semanticUI = "2.1.8"

	val codemirror = "5.11"

}

