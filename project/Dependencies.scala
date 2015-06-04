import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt._


object Dependencies {

	//libs for testing
  lazy val testing = Def.setting(Seq(
    "com.lihaoyi" %%% "utest" % Versions.utest % "test"
  ))

	//akka-related libs
	lazy val akka = Def.setting(Seq(

		"com.typesafe.akka" %% "akka-stream-experimental" % Versions.akkaHttp,

		"com.typesafe.akka" %% "akka-http-core-experimental" % Versions.akkaHttp,

		"com.typesafe.akka" %% "akka-http-experimental" % Versions.akkaHttp,

		"com.typesafe.akka" %% "akka-http-testkit-experimental" % Versions.akkaHttp
	))

	lazy val templates = Def.setting(Seq(
		"com.github.japgolly.scalacss" %%% "core" % Versions.scalaCSS,

		"com.github.japgolly.scalacss" %%% "ext-scalatags" %  Versions.scalaCSS
	))

	lazy val facadeDependencies = Def.setting(Seq(
		"org.scala-js" %%% "scalajs-dom" % Versions.dom,

		"org.querki" %%% "querki-jsext" % Versions.jsext //useful sclalajs extensions
	))

	//scalajs libs
	lazy val sjsLibs= Def.setting(Seq(
		"org.scala-js" %%% "scalajs-dom" % Versions.dom,

		"org.querki" %%% "jquery-facade" % Versions.jqueryFacade, //scalajs facade for jQuery + jQuery extensions

		"org.querki" %%% "querki-jsext" % Versions.jsext, //useful sclalajs extensions

		"org.denigma" %%% "binding" % Versions.binding
	))

	//dependencies on javascript libs
	lazy val webjars= Def.setting(Seq(

		"org.webjars" % "Semantic-UI" % Versions.semanticUI, //css theme, similar to bootstrap

		"org.webjars" % "selectize.js" % Versions.selectize, //select control

		"org.webjars" % "codemirror" % "5.3"
	))

	//common purpose libs
	lazy val commonShared: Def.Initialize[Seq[ModuleID]] = Def.setting(Seq(
		//"com.softwaremill.quicklens" %%% "quicklens" % Versions.quicklens//, //nice lenses for case classes
	))
}
