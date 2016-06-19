object Versions extends WebJarsVersions with ScalaJSVersions with SharedVersions
{
	val scala = "2.11.8"

	val akkaHttpExtensions = "0.0.13"

}

trait ScalaJSVersions {

	val facade = "5.13.2-0.7"

	val jqueryFacade = "1.0-RC6"

	val dom = "0.9.1"

	val bindingControls = "0.0.16"

}

//versions for libs that are shared between client and server
trait SharedVersions
{
	val scalaTags = "0.5.1"

	val scalaCSS = "0.4.1"

	val scalaTest =  "3.0.0-RC2"

	val fastParse = "0.3.7"

}


trait WebJarsVersions{

	val jquery =  "3.0.0"

	val semanticUI = "2.1.8"

	val codemirror = "5.13.2"

}

