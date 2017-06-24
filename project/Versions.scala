object Versions extends WebJarsVersions with ScalaJSVersions with SharedVersions
{
	val scala = "2.12.2"

	val akkaHttpExtensions = "0.0.15"

}

trait ScalaJSVersions {

	val facade = "5.13.2-0.7"

	val jqueryFacade = "1.0"

	val dom = "0.9.2"

	val bindingControls = "0.0.25"

}

//versions for libs that are shared between client and server
trait SharedVersions
{
	val scalaTags = "0.5.4"

	val scalaCSS = "0.5.3"

	val scalaTest =  "3.0.3"

	val fastParse = "0.4.3"

}


trait WebJarsVersions{

	val jquery =  "3.2.1"

	val semanticUI = "2.2.10"

	val codemirror = "5.24.2"

}

