import sbt._

object Dependencies {

  object Versions {
    val betterfiles     = "3.9.1"
    val cats            = "2.8.0"
    val catsEffect      = "3.3.14"
    val catsMeowMtl     = "0.5.0"
    val catsRetry       = "3.1.0"
    val circe           = "0.14.2"
    val circeDerivation = "0.13.0-M5"
    val ciris           = "2.3.3"
    val javaxCrypto     = "1.0.1"
    val fs2             = "3.2.11"
    val http4s          = "1.0.0-M35"
    val http4sJwtAuth   = "1.0.0"
    val log4cats        = "1.1.1"
    val newtype         = "0.4.4"
    val refined         = "0.10.1"
    val redis4cats      = "1.2.0"
    val skunk           = "0.3.1"
    val squants         = "1.8.3"

    val betterMonadicFor = "0.3.1"
    val kindProjector    = "0.13.2"
    val logback          = "1.2.11"

    val scalaCheck    = "1.16.0"
    val scalaTest     = "3.2.13"
    val scalaTestPlus = "3.2.2.0"

    val zio        = "2.0.0"
    val zioConfig  = "3.0.1"
    val zioLogging = "2.0.1"
    val zioJson    = "0.3.0-RC9"
  }

  object Libraries {
    def circe(artifact: String): ModuleID  = "io.circe"   %% artifact % Versions.circe
    def ciris(artifact: String): ModuleID  = "is.cir"     %% artifact % Versions.ciris
    def http4s(artifact: String): ModuleID = "org.http4s" %% artifact % Versions.http4s

    val cats        = "org.typelevel"    %% "cats-core"     % Versions.cats
    val catsMeowMtl = "com.olegpy"       %% "meow-mtl-core" % Versions.catsMeowMtl
    val catsEffect  = "org.typelevel"    %% "cats-effect"   % Versions.catsEffect
    val catsRetry   = "com.github.cb372" %% "cats-retry"    % Versions.catsRetry
    val squants     = "org.typelevel"    %% "squants"       % Versions.squants
    val fs2         = "co.fs2"           %% "fs2-core"      % Versions.fs2

    lazy val betterFiles = "com.github.pathikrit" %% "better-files" % Versions.betterfiles
    val circeCore        = circe("circe-core")
    val circeDerivation  = "io.circe" %% "circe-derivation-annotations" % Versions.circeDerivation
    val circeGeneric     = circe("circe-generic")
    val circeParser      = circe("circe-parser")
    val circeRefined     = circe("circe-refined")

    val cirisCore    = ciris("ciris")
    val cirisEnum    = ciris("ciris-enumeratum")
    val cirisRefined = ciris("ciris-refined")

    val http4sDsl    = http4s("http4s-dsl")
    val http4sServer = http4s("http4s-blaze-server")
    val http4sClient = http4s("http4s-blaze-client")
    val http4sCirce  = http4s("http4s-circe")

    val http4sJwtAuth = "dev.profunktor" %% "http4s-jwt-auth" % Versions.http4sJwtAuth

    val refinedCore = "eu.timepit" %% "refined"      % Versions.refined
    val refinedCats = "eu.timepit" %% "refined-cats" % Versions.refined

    val log4cats = "io.chrisdavenport" %% "log4cats-slf4j" % Versions.log4cats
    val newtype  = "io.estatico"       %% "newtype"        % Versions.newtype

    val javaxCrypto = "javax.xml.crypto" % "jsr105-api" % Versions.javaxCrypto

    val redis4catsEffects  = "dev.profunktor" %% "redis4cats-effects"  % Versions.redis4cats
    val redis4catsLog4cats = "dev.profunktor" %% "redis4cats-log4cats" % Versions.redis4cats

    val skunkCore  = "org.tpolecat" %% "skunk-core"  % Versions.skunk
    val skunkCirce = "org.tpolecat" %% "skunk-circe" % Versions.skunk

    // Compiler plugins
    val betterMonadicFor = "com.olegpy"    %% "better-monadic-for" % Versions.betterMonadicFor
    val kindProjector    = "org.typelevel" % "kind-projector"      % Versions.kindProjector

    // Runtime
    val logback = "ch.qos.logback" % "logback-classic" % Versions.logback

    // Test
    val scalaCheck    = "org.scalacheck"    %% "scalacheck"      % Versions.scalaCheck
    val scalaTest     = "org.scalatest"     %% "scalatest"       % Versions.scalaTest
    val scalaTestPlus = "org.scalatestplus" %% "scalacheck-1-14" % Versions.scalaTestPlus

    // zio
    val zio            = "dev.zio" %% "zio"              % Versions.zio
    val zioStreams     = "dev.zio" %% "zio-streams"      % Versions.zio
    val zioInteropCats = "dev.zio" %% "zio-interop-cats" % "22.0.0.0"
    val zioTest        = "dev.zio" %% "zio-test"         % Versions.zio
    val zioTestSBT     = "dev.zio" %% "zio-test-sbt"     % Versions.zio

    val sttpClient = "com.softwaremill.sttp.client3" %% "async-http-client-backend-zio" % "3.7.2"

    val zioJSON = "dev.zio" %% "zio-json" % Versions.zioJson

    // zio-logging
    lazy val zioLogging      = "dev.zio" %% "zio-logging"       % Versions.zioLogging
    lazy val zioLoggingSlf4j = "dev.zio" %% "zio-logging-slf4j" % Versions.zioLogging
    // zio-config
    lazy val zioConfig         = "dev.zio" %% "zio-config"          % Versions.zioConfig
    lazy val zioConfigMagnolia = "dev.zio" %% "zio-config-magnolia" % Versions.zioConfig
    lazy val zioConfigTypesafe = "dev.zio" %% "zio-config-typesafe" % Versions.zioConfig

  }

}
