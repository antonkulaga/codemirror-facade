object Versions extends WebJarsVersions with ScalaJSVersions with SharedVersions
{
	val scala = "2.11.7"

	val akkaHttpExtensions = "0.0.10"

	val ammonite = "0.4.3"

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
	val autowire = "0.2.5"

	val scalaRx = "0.2.8"

	val quicklens = "1.3.1"

	val scalaTags = "0.5.1"

	val scalaCSS = "0.4.0"

	val productCollections = "1.4.2"

	val scalaTest =  "3.0.0-SNAP13"

}


trait WebJarsVersions{

	val jquery =  "2.2.3"

	val semanticUI = "2.1.8"

	val codemirror = "5.11"

}

