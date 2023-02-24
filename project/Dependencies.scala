import sbt._

object Dependencies {

  import Versions._

  lazy val weaverCats       = "com.disneystreaming" %% "weaver-cats"       % weaverVersion
  lazy val weaverScalacheck = "com.disneystreaming" %% "weaver-scalacheck" % weaverVersion
  lazy val weaverDiscipline = "com.disneystreaming" %% "weaver-discipline" % weaverVersion
  // lazy val circeGeneric = "io.circe"      %% "circe-generic" % circeVersion
  // lazy val circeCore   = "io.circe"                      %% "circe-core"     % circeVersion
  // lazy val circeParser = "io.circe"                      %% "circe-parser"   % circeVersion
  lazy val slf4jApi         = "org.slf4j"            % "slf4j-api"         % slf4jVersion
  lazy val slf4jSimple      = "org.slf4j"            % "slf4j-simple"      % slf4jVersion
  lazy val munit            = "org.scalameta"       %% "munit"             % munitVersion
  // lazy val newtype     = "io.estatico"                   %% "newtype"        % newTypeVersion

  // https://github.com/typelevel/kind-projector
  lazy val kindProjectorPlugin    = compilerPlugin(
    compilerPlugin("org.typelevel" % "kind-projector" % kindProjectorVersion cross CrossVersion.full)
  )
  // https://github.com/oleg-py/better-monadic-for
  lazy val betterMonadicForPlugin = compilerPlugin(
    compilerPlugin("com.olegpy" %% "better-monadic-for" % betterMonadicForVersion)
  )

  val compilerDependencies = Seq(
    weaverCats,
    weaverScalacheck,
    weaverDiscipline,
    // circeGeneric,
    // circeCore,
    // circeParser,
    // newtype,
    slf4jApi,
    slf4jSimple,
    munit
  )

  val testDependencies = Seq.empty

  val pluginDependencies = Seq(kindProjectorPlugin, betterMonadicForPlugin)

  val allDependencies = compilerDependencies ++ testDependencies ++ pluginDependencies
}
