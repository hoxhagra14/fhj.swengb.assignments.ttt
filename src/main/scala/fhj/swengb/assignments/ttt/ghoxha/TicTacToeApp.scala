package fhj.swengb.assignments.ttt.ghoxha

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.{Scene, Parent}
import javafx.stage.Stage

import scala.util.control.NonFatal

/**
  * Implement here your TicTacToe JavaFX App.
  */

object TicTacToeApp {
  def main (args: Array[String]){
    Application.launch(classOf[TicTacToeApp], args: _*)
  }
}

class TicTacToeApp extends javafx.application.Application {

  val Fxml = "/fhj/swengb/assignments/ttt/TicTacToeApp.fxml"

  val loader = new FXMLLoader(getClass.getResource(Fxml))

  override def start(stage: Stage): Unit = try {
    stage.setTitle("TicTacToe")
    loader.load[Parent]() // side effect
    val scene = new Scene(loader.getRoot[Parent])
    stage.setScene(scene)
    stage.show()

  } catch {
    case NonFatal(e) => {
      e.printStackTrace()
    }
  }

}