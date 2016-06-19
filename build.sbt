import com.typesafe.sbt.gzip.Import.gzip
import com.typesafe.sbt.web._
import com.typesafe.sbt.web.pipeline.Pipeline
import playscalajs.PlayScalaJS.autoImport._
import playscalajs.ScalaJSPlay.autoImport._
import playscalajs.{PlayScalaJS, ScalaJSPlay}
import sbt.Keys._
import sbt._
import spray.revolver.RevolverPlugin.autoImport._

lazy val bintrayPublishIvyStyle = settingKey[Boolean]("=== !publishMavenStyle") //workaround for sbt-bintray bug

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
  ) enablePlugins ScalaJSPlugin disablePlugins RevolverPlugin


lazy val scalaJSDevStage  = Def.taskKey[Pipeline.Stage]("Apply fastOptJS on all Scala.js projects")

def scalaJSDevTaskStage: Def.Initialize[Task[Pipeline.Stage]] = Def.task { mappings: Seq[PathMapping] =>
  mappings ++ PlayScalaJS.devFiles(Compile).value ++ PlayScalaJS.sourcemapScalaFiles(fastOptJS).value
}


// some useful UI controls + shared code
lazy val preview = crossProject
  .crossType(CrossType.Full)
  .in(file("preview"))
  .settings(commonSettings++publishSettings: _*)
  .settings(
    name := "preview"
  ).disablePlugins(RevolverPlugin)
  .jsConfigure(p=>p.dependsOn(facade).enablePlugins(ScalaJSPlay))
  .jsSettings(
    libraryDependencies ++= Dependencies.sjsLibs.value,
    persistLauncher in Compile := true,
    persistLauncher in Test := false,
    jsDependencies += RuntimeDOM % Test
  )
  .jvmConfigure(p=>p.enablePlugins(SbtTwirl, SbtWeb).enablePlugins(PlayScalaJS)) //despite "Play" in name it is actually sbtweb-related plugin
  .jvmSettings(
  libraryDependencies ++= Dependencies.akka.value ++ Dependencies.webjars.value,
  mainClass in Compile :=Some("org.denigma.preview.Main"),
  scalaJSDevStage := scalaJSDevTaskStage.value,
  //pipelineStages := Seq(scalaJSProd, gzip),
  (emitSourceMaps in fullOptJS) := true,
  pipelineStages in Assets := Seq(scalaJSDevStage, gzip), //for run configuration
  (fullClasspath in Runtime) += (packageBin in Assets).value //to package production deps
)
lazy val previewJS = preview.js
lazy val previewJVM = preview.jvm settings( scalaJSProjects := Seq(previewJS) )

lazy val root = Project("root",file("."),settings = commonSettings)
  .settings(
    mainClass in Compile := (mainClass in previewJVM in Compile).value,
    (fullClasspath in Runtime) += (packageBin in previewJVM in Assets).value
  ) dependsOn previewJVM aggregate(previewJVM, previewJS)
