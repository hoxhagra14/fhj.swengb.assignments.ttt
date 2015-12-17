package fhj.swengb.assignments.ttt.ghoxha



import java.awt.event.ActionListener
import java.net.URL
import java.util.ResourceBundle
import javafx.application.Application
import javafx.fxml.{FXML, Initializable, FXMLLoader}
import javafx.scene.control.{TextArea, Button}
import javafx.scene.layout.GridPane
import javafx.scene.{Scene, Parent}
import javafx.stage.Stage

import fhj.swengb.assignments.ttt.ghoxha.PlayerA

import scala.collection.Set
import scala.util.control.NonFatal

/**
  * Implement here your TicTacToe JavaFX App.
  */

object TicTacToeApp {
  def main (args: Array[String]){
    Application.launch(classOf[TicTacToeApp], args: _*)
    val map:Map[TMove, Player] = Map(BottomLeft -> PlayerA, MiddleLeft -> PlayerA, MiddleRight -> PlayerB, BottomRight -> PlayerB, TopRight -> PlayerB, TopCenter -> PlayerA, TopLeft -> PlayerB, MiddleCenter -> PlayerA)

    val t = new TicTacToe(map, PlayerB)
    println(t.asString())
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

  var actualPlayer:Player = PlayerA
  var moveHistory:Map[TMove,Player] = Map()
  var ticTacToeGame = new TicTacToe(moveHistory, actualPlayer)

  @FXML var  b1: Button = _
  @FXML var  b2: Button = _
  @FXML var  b3: Button = _
  @FXML var  b4: Button = _
  @FXML var  b5: Button = _
  @FXML var  b6: Button = _
  @FXML var  b7: Button = _
  @FXML var  b8: Button = _
  @FXML var  b9: Button = _

  @FXML var informationArea: TextArea = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {

  }

  def doSomething(button: Button, move: TMove):Unit = {
    if (ticTacToeGame.nextPlayer == PlayerA){
      button.setText("X")
      button.setDisable(true)
    }
    else{
      button.setText("O")
      button.setDisable(true)
    }

    ticTacToeGame = ticTacToeGame.turn(move,ticTacToeGame.nextPlayer)
    if(ticTacToeGame.gameOver){
      val winner = ticTacToeGame.winner.getOrElse(PlayerA, Set(TopLeft, TopCenter, TopRight))
      highlightWinner(winner._2.toList, highlight = true)

      informationArea.setText( "Game Over! \n" + winner._1 + " ist der Sieger")
      disableButtons(true)
    }
  }

  def disableButtons(enable: Boolean):Unit = {
    b1.setDisable(enable); b2.setDisable(enable); b3.setDisable(enable); b4.setDisable(enable)
    b5.setDisable(enable); b6.setDisable(enable); b7.setDisable(enable); b8.setDisable(enable); b9.setDisable(enable)
  }

  def highlightWinner(list: List[TMove], highlight: Boolean):Unit = {
    val map = Map(b1 -> TopLeft, b2 -> TopCenter, b3 -> TopRight, b4 -> MiddleLeft, b5 -> MiddleCenter, b6 -> MiddleRight, b7 -> BottomLeft, b8 -> BottomCenter, b9 -> BottomRight)
    if (highlight) {
      for ((key, value) <- map) {
        if (list.contains(value))
          key.getStyleClass.add("winning-button")
      }
    }else{
      for ((key, value) <- map) {
          key.getStyleClass.remove("winning-button")
      }
    }
  }

  def setDefaultButtonSettings():Unit={
    b1.setText(""); b2.setText(""); b3.setText(""); b4.setText("")
    b5.setText(""); b6.setText(""); b7.setText(""); b8.setText(""); b9.setText("")
  }

  def buttonClick1(): Unit = doSomething(b1, TopLeft)
  def buttonClick2(): Unit = doSomething(b2, TopCenter)
  def buttonClick3(): Unit = doSomething(b3, TopRight)
  def buttonClick4(): Unit = doSomething(b4, MiddleLeft)
  def buttonClick5(): Unit = doSomething(b5, MiddleCenter)
  def buttonClick6(): Unit = doSomething(b6, MiddleRight)
  def buttonClick7(): Unit = doSomething(b7, BottomLeft)
  def buttonClick8(): Unit = doSomething(b8, BottomCenter)
  def buttonClick9(): Unit = doSomething(b9, BottomRight)
  def buttonNewGame(): Unit = {
    ticTacToeGame = TicTacToe(moveHistory,actualPlayer)
    informationArea.setText("Ein neues Spiel kann \nnun begonnen werden!")
    //Setze die Buttons wieder auf Default
    setDefaultButtonSettings()
    // Lösche highlighting falls ein neues Spiel begonnen wird
    highlightWinner(List(), highlight = false)
    // Buttons die disabled wurden wieder enablen
    disableButtons(false)
  }



}
