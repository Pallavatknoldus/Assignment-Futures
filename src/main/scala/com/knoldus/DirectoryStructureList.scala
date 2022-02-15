package com.knoldus

import java.nio.file.FileSystems
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class DirectoryStructureList {

  def getDirectoryStructure(path: String): Future[List[String]] = {
    def getFiles(path: String): List[String] = {
      val directory = FileSystems.getDefault.getPath(path)
      if (directory.toFile.isDirectory && directory.toFile.exists) {
        val subDirectory = directory.toFile.listFiles.toList
        subDirectory.flatMap { file =>
          if (file.isDirectory) file.toString +: getFiles(file.toString)
          else List(file.toString)
        }
      }
      else throw new Exception("Invalid path")
    }

    Future(getFiles(path))
  }
}