import sbt._
import play.Project._

object ApplicationBuild extends Build {

    val appName = "DelimetedViewer"
    val appVersion = "1.0-SNAPSHOT"

    val appDependencies = Seq(
        // Add your project dependencies here,
        jdbc,
        anorm,
        "org.webjars" %% "webjars-play" % "2.1.0-2",
        "org.webjars" % "bootstrap" % "2.1.1"
    )


    val main = play.Project(appName, appVersion, appDependencies).settings(
    )

}
