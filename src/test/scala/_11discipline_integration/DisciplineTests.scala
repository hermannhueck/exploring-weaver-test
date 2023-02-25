package _11discipline_integration

import weaver._
import weaver.discipline._
import cats.kernel.laws.discipline.EqTests

object DisciplineTests extends FunSuite with Discipline {
  checkAll("Int", EqTests[Int].eqv)
  checkAll("Boolean", EqTests[Boolean].eqv)
}
