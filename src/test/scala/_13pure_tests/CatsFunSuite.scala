package _13pure_tests

object CatsFunSuite extends weaver.FunSuite {

  test("asserts") {
    expect(Some(5).contains(5))
  }

  test("fails") {
    expect(Some(25).contains(5))
  }

  test("throws") {
    throw new RuntimeException("oops")
  }
}
