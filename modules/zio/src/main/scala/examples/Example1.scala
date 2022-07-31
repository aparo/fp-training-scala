package examples

import zio._
import zio.Clock
import zio.Console
import zio.Random._
import zio.Duration

object Example1 extends ZIOAppDefault {
  def run =
    allNrsParN
//    allNrs.exitCode
//    allNrsPar.exitCode

  val prg: ZIO[Any, Throwable, Long] = (for {
    n <- Random.nextLong // ZIO[Random, Nothing, Long]
    _ <- Console.printLine(s"Extracted $n ") // ZIO[Console, Nothing, Unit]
  } yield n)

  val allNrs: ZIO[Any, Throwable, List[Long]] = ZIO.collectAll(List.fill(100)(prg))

  // using greenthreads
  val allNrsPar: ZIO[Any, Throwable, List[Long]] = ZIO.collectAllPar(List.fill(100)(prg))

  val allNrsParN: ZIO[Any, Throwable, List[Long]] =
    ZIO.collectAllPar(List.fill(100)(prg)).withParallelism(5)

}
