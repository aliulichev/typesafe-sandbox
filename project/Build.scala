import sbt._
import play.Project._
import Keys._

object ApplicationBuild extends Build {

    val appName = "DelimetedViewer"
    val appVersion = "1.0-SNAPSHOT"

    val appDependencies = Seq(
        // Add your project dependencies here,
        jdbc,
        anorm,
        "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test",
        "org.webjars" %% "webjars-play" % "2.1.0-2",
        "org.webjars" % "bootstrap" % "2.1.1"
    )

    lazy val scct_settings = Defaults.defaultSettings ++ Seq(ScctPlugin.instrumentSettings: _*)

    val main = play.Project(appName, appVersion, appDependencies, settings = scct_settings).settings(
        testOptions in Test := Nil
    )


}
