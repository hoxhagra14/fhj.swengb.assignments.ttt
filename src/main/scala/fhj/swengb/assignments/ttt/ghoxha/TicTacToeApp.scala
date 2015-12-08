package fhj.swengb.assignments.ttt.ghoxha


import java.awt.event.ActionListener
import java.net.URL
import java.util.ResourceBundle
import javafx.application.Application
import javafx.fxml.{FXML, Initializable, FXMLLoader}
import javafx.scene.control.Button
import javafx.scene.layout.GridPane
import javafx.scene.{Scene, Parent}
import javafx.stage.Stage

import scala.util.control.NonFatal

/**
  * Implement here your TicTacToe JavaFX App.
  */

object TicTacToeApp {
  def main (args: Array[String]){
    Application.launch(classOf[TicTacToeApp], args: _*)
    val map:Map[TMove, Player] = Map(/*TopLeft -> PlayerB, BottomLeft -> PlayerB, TopCenter -> PlayerB,*/ TopRight -> PlayerB,
    MiddleLeft -> PlayerA, MiddleCenter -> PlayerB, /*MiddleRight -> PlayerA, BottomCenter -> PlayerB,*/ BottomRight -> PlayerA)

    val t = new TicTacToe(map, PlayerB)
    val n = t.turn(MiddleRight, PlayerA)
    println(n.asString())
    println(t.asString())
    println(t.remainingMoves)
    println(t.winner)
    println(t.nextGames)
  }
}

class TicTacToeApp extends javafx.application.Application {

  val Fxml = "/fhj/swengb/assignments/ttt/TicTacToeApp.fxml"
  val Css = "fhj/swengb/assignments/ttt/TicTacToeApp.css"

  //val loader = new FXMLLoader(getClass.getResource(Fxml))

  def mkFxmlLoader(fxml: String): FXMLLoader = {
    new FXMLLoader(getClass.getResource(fxml))
  }

  override def start(stage: Stage): Unit =
    try {
      stage.setTitle("TicTacToe")
      setSkin(stage, Fxml, Css)
      stage.show()
      stage.setMinWidth(stage.getWidth)
      stage.setMinHeight(stage.getHeight)
    } catch {
      case NonFatal(e) => e.printStackTrace()
    }

  def setSkin(stage: Stage, fxml: String, css: String): Boolean = {
    val scene = new Scene(mkFxmlLoader(fxml).load[Parent]())
    stage.setScene(scene)
    stage.getScene.getStylesheets.clear()
    stage.getScene.getStylesheets.add(css)
  }

}
class TicTacToeAppController extends Initializable {
  @FXML var  b1: Button = _
  @FXML var  b2: Button = _
  @FXML var  b3: Button = _
  @FXML var  b4: Button = _
  @FXML var  b5: Button = _
  @FXML var  b6: Button = _
  @FXML var  b7: Button = _
  @FXML var  b8: Button = _
  @FXML var  b9: Button = _

  @FXML var gameBoard: GridPane =_

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
  }

  def buttonClick(): Unit = {
    b1.setText("X")

  }


}
