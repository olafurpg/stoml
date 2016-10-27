lazy val stoml = project.in(file("."))

name := "stoml"

organization := "me.vican.jorge"

scalaVersion := "2.11.7"

libraryDependencies ++= Vector(
  "com.lihaoyi" %% "fastparse" % "0.3.7",
  "org.scalacheck" %% "scalacheck" % "1.12.5" % "test",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
)

homepage := Some(url("https://github.com/jvican/stoml"))
licenses := Seq("MPL-2.0 License" -> url("https://opensource.org/licenses/MPL-2.0"))

pomExtra in Global := {
  <scm>
    <developerConnection>scm:git:git@github.com:jvican</developerConnection>
    <url>https://github.com/jvican/stoml.git</url>
    <connection>scm:git:git@github.com:jvican/stoml.git</connection>
  </scm>
  <developers>
    <developer>
      <id>jvican</id>
      <name>Jorge Vicente Cantero</name>
      <url>https://github.com/jvican</url>
    </developer>
  </developers>
}

// Bintray
publishTo := (publishTo in bintray).value
bintrayOrganization := None
bintrayRepository := "releases"

// Release
import ReleaseTransformations._
releasePublishArtifactsAction := PgpKeys.publishSigned.value
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  publishArtifacts,
  releaseStepTask(bintrayRelease in stoml),
  setNextVersion,
  commitNextVersion,
  pushChanges
)
