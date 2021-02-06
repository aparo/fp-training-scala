package examples

import zio._
import zio.clock.Clock
import zio.console._
import zio.random._
import zio.duration._

object Example1 extends App {
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    allNrsParN.exitCode
//    allNrs.exitCode
//    allNrsPar.exitCode

  val prg: ZIO[Console with Random with Clock, Throwable, Long] = (for {
    n <- random.nextLong // ZIO[Random, Nothing, Long]
    _ <- console.putStrLn(s"Extracted $n ") // ZIO[Console, Nothing, Unit]
  } yield n)

  val allNrs: ZIO[Console with Random with Clock, Throwable, List[Long]] = ZIO.collectAll(List.fill(100)(prg))

  // using greenthreads
  val allNrsPar: ZIO[Console with Random with Clock, Throwable, List[Long]] = ZIO.collectAllPar(List.fill(100)(prg))

  val allNrsParN: ZIO[Console with Random with Clock, Throwable, List[Long]] =
    ZIO.collectAllParN(10)(List.fill(100)(prg))

}
