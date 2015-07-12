object Versions extends WebJarsVersions with ScalaJSVersions with SharedVersions
{
	val scala = "2.11.7"

	val akkaHttp = "1.0-RC4"

	val akkaHttpExtensions = "0.0.5-1-RC4"

	val ammonite = "0.3.2"

}

trait ScalaJSVersions {

	val facade = "5.4-0.5"

	val jqueryFacade = "0.6"

	val dom = "0.8.1"

	val bindingControls = "0.0.5"

}

//versions for libs that are shared between client and server
trait SharedVersions
{
	val autowire = "0.2.5"

	val scalaRx = "0.2.8"

	val quicklens = "1.3.1"

	val scalaTags = "0.5.1"

	val scalaCSS = "0.3.0"

	val productCollections = "1.4.2"

	val scalaTest =  "3.0.0-M7"

}


trait WebJarsVersions{

	val jquery =  "2.1.3"

	val semanticUI = "2.0.3"

	val codemirror = "5.4"

	val selectize = "0.12.1"

}

