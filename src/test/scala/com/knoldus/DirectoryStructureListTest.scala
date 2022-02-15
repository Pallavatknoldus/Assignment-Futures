package com.knoldus

import org.scalatest.flatspec.AnyFlatSpec
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class DirectoryStructureListTest extends AnyFlatSpec {

  val dirList = new DirectoryStructureList

  "Condition" should "give exception for an invalid path" in {
    val errorMsg = "Invalid path"
    val error = intercept[Exception] {
      Await.result(dirList.getDirectoryStructure(".ideas"), 2 seconds)
    }
    assert(error.getMessage == errorMsg)
  }

  "Condition" should "give the list of directory struct for a valid path" in {
    val result: List[String] = Await.result(dirList.getDirectoryStructure("src"), 3 seconds)
    val finalResult = List("src/main", "src/main/scala", "src/main/scala/com", "src/main/scala/com/knoldus", "src/main/scala/com/knoldus/DirectoryStructureList.scala", "src/test", "src/test/scala", "src/test/scala/com", "src/test/scala/com/knoldus", "src/test/scala/com/knoldus/DirectoryStructureListTest.scala")
    assertResult(result)(finalResult)
  }
}