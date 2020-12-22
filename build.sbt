import Dependencies._

ThisBuild / scalaVersion := "2.13.4"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.megl"
ThisBuild / organizationName := "Megl.io"

resolvers += Resolver.sonatypeRepo("snapshots")

lazy val root = (project in file("."))
  .settings(
    name := "fp-training"
  )
  .aggregate(core, tests)

lazy val tests = (project in file("modules/tests"))
  .configs(IntegrationTest)
  .settings(
    name := "fp-training-test-suite",
    scalacOptions += "-Ymacro-annotations",
    scalafmtOnCompile := true,
    testFrameworks ++= Seq(new TestFramework("zio.test.sbt.ZTestFramework")),
    Defaults.itSettings,
    libraryDependencies ++= Seq(
      compilerPlugin(Libraries.kindProjector cross CrossVersion.full),
      compilerPlugin(Libraries.betterMonadicFor),
      Libraries.scalaCheck,
      Libraries.scalaTest,
      Libraries.scalaTestPlus,
      Libraries.zioTest,
      Libraries.zioTestSBT
    )
  )
  .dependsOn(core, zio)

lazy val core = (project in file("modules/core"))
  .settings(
    name := "fp-training-core",
    scalacOptions += "-Ymacro-annotations",
    scalafmtOnCompile := true,
    resolvers += Resolver.sonatypeRepo("snapshots"),
    testFrameworks ++= Seq(new TestFramework("zio.test.sbt.ZTestFramework")),
    Defaults.itSettings,
    libraryDependencies ++= Seq(
      compilerPlugin(Libraries.kindProjector cross CrossVersion.full),
      compilerPlugin(Libraries.betterMonadicFor),
      Libraries.cats,
      Libraries.catsEffect,
      Libraries.catsMeowMtl,
      Libraries.catsRetry,
      Libraries.circeCore,
      Libraries.circeGeneric,
      Libraries.circeDerivation,
      Libraries.circeParser,
      Libraries.circeRefined,
      Libraries.cirisCore,
      Libraries.cirisEnum,
      Libraries.cirisRefined,
      Libraries.fs2,
      Libraries.http4sDsl,
      Libraries.http4sServer,
      Libraries.http4sClient,
      Libraries.http4sCirce,
      Libraries.http4sJwtAuth,
      Libraries.javaxCrypto,
      Libraries.log4cats,
      Libraries.logback % Runtime,
      Libraries.newtype,
      Libraries.redis4catsEffects,
      Libraries.redis4catsLog4cats,
      Libraries.refinedCore,
      Libraries.refinedCats,
      Libraries.skunkCore,
      Libraries.skunkCirce,
      Libraries.squants,
      Libraries.zio,
      Libraries.zioStreams,
      Libraries.zioInteropCats,
      //testing
      Libraries.zioTest    % Test,
      Libraries.zioTestSBT % Test
    )
  )

lazy val zio = (project in file("modules/zio"))
  .settings(
    name := "fp-training-zio",
    scalacOptions += "-Ymacro-annotations",
    scalafmtOnCompile := true,
    resolvers += Resolver.sonatypeRepo("snapshots"),
    testFrameworks ++= Seq(new TestFramework("zio.test.sbt.ZTestFramework")),
    Defaults.itSettings,
    libraryDependencies ++= Seq(
      compilerPlugin(Libraries.kindProjector cross CrossVersion.full),
      compilerPlugin(Libraries.betterMonadicFor),
      Libraries.circeCore,
      Libraries.circeDerivation,
      Libraries.circeParser,
      Libraries.circeRefined,
      Libraries.betterFiles,
      Libraries.logback % Runtime,
      // logging
      Libraries.zioLogging,
      Libraries.zioLoggingSlf4j,
      Libraries.zioConfig,
      Libraries.zioConfigMagnolia,
      Libraries.zioConfigTypesafe,
      Libraries.zio,
      Libraries.zioStreams,
      Libraries.zioInteropCats,
      // http
      Libraries.sttpClient,
      //testing
      Libraries.zioTest    % Test,
      Libraries.zioTestSBT % Test
    )
  )
