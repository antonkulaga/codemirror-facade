import com.typesafe.sbt.SbtNativePackager.autoImport._
import com.typesafe.sbt.web.SbtWeb
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.Keys._
import sbt._
import bintray._
import BintrayPlugin.autoImport._
import spray.revolver.RevolverPlugin._
import play.twirl.sbt._
import play.twirl.sbt.SbtTwirl.autoImport._
import com.typesafe.sbt.web.SbtWeb.autoImport._


object Build extends PreviewBuild {

	lazy val root = Project("root",file("."),settings = commonSettings)
		.settings(
			mainClass in Compile := (mainClass in previewJVM in Compile).value,
			(managedClasspath in Runtime) += (packageBin in previewJVM in Assets).value
		) dependsOn previewJVM aggregate(previewJVM, previewJS)
}

class PreviewBuild extends FacadeBuild
{

	// some useful UI controls + shared code
	lazy val preview = crossProject
		.crossType(CrossType.Full)
		.in(file("preview"))
		.settings(commonSettings++publishSettings: _*)
		.settings(
			name := "preview"
		)
		.jsConfigure(p=>p.dependsOn(facade))
		.jvmConfigure(p=>p.enablePlugins(SbtTwirl,SbtWeb))
		.jvmSettings(Revolver.settings:_*)
		.jvmSettings(
			libraryDependencies ++= Dependencies.akka.value ++ Dependencies.webjars.value,
			mainClass in Compile :=Some("org.denigma.preview.Main"),
			mainClass in Revolver.reStart := Some("org.denigma.preview.Main"),
			(managedClasspath in Runtime) += (packageBin in Assets).value
		)
		.jsSettings(
			libraryDependencies ++= Dependencies.sjsLibs.value,
			persistLauncher in Compile := true,
			persistLauncher in Test := false,
			jsDependencies += RuntimeDOM % "test"
		)

	lazy val previewJS = preview.js
	lazy val previewJVM = preview.jvm settings (
		resourceGenerators in Compile <+=
			(fastOptJS in Compile in previewJS,	packageScalaJSLauncher in Compile in  previewJS) map(
				(f1, f2) => Seq(f1.data, f2.data)
				),
		watchSources <++= (watchSources in  previewJS)
		)
}

class FacadeBuild  extends sbt.Build{
	self=>

	protected lazy val bintrayPublishIvyStyle = settingKey[Boolean]("=== !publishMavenStyle") //workaround for sbt-bintray bug

	lazy val publishSettings = Seq(
		bintrayRepository := "denigma-releases",

		bintrayOrganization := Some("denigma"),

		licenses += ("MPL-2.0", url("http://opensource.org/licenses/MPL-2.0")),

		bintrayPublishIvyStyle := true
	)

	/**
	 * For parts of the project that we will not publish
	 */
	lazy val noPublishSettings = Seq(
		publish := (),
		publishLocal := (),
		publishArtifact := false
	)


	//settings for all the projects
	lazy val commonSettings = Seq(
		scalaVersion := Versions.scala,
		organization := "org.denigma",
		resolvers += sbt.Resolver.bintrayRepo("denigma", "denigma-releases"), //for scala-js-binding
		libraryDependencies ++= Dependencies.commonShared.value++Dependencies.testing.value,
		updateOptions := updateOptions.value.withCachedResolution(true) //to speed up dependency resolution
	)

	lazy val libName = "codemirror"

	lazy val facade = Project(libName, file("facade"))
		.settings(commonSettings++publishSettings: _*)
		.settings(
			name := s"$libName-facade",
			version := Versions.facade,
			libraryDependencies ++= Dependencies.facadeDependencies.value
		) enablePlugins ScalaJSPlugin

}
