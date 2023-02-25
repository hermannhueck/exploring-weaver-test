package _12configuing_parallelism

import weaver._

object MySuite extends SimpleIOSuite {

  override def maxParallelism = 1

  pureTest("test 1") {
    expect(2 > 1)
  }
}
