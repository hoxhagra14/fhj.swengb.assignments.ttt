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

import scala.util.control.NonFatal

/**
  * Implement here your TicTacToe JavaFX App.
  */

object TicTacToeApp {
  def main (args: Array[String]){
    Application.launch(classOf[TicTacToeApp], args: _*)
    val map:Map[TMove, Player] = Map(TopLeft -> PlayerB, BottomLeft -> PlayerB, TopCenter -> PlayerB, TopRight -> PlayerB,
    MiddleLeft -> PlayerA, MiddleCenter -> PlayerB, /*MiddleRight -> PlayerA, BottomCenter -> PlayerB,*/ BottomRight -> PlayerA)

    val t = new TicTacToe(map, PlayerB)
    println(t.asString())
    println(" remainingMoves" +t.remainingMoves)
    println("winner " + t.winner)
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
  @FXML var highScore: TextArea = _
  @FXML var gameBoard: GridPane = _

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
    println(ticTacToeGame)
    if (ticTacToeGame.winner != None){
      informationArea.setText("PlayerB is winner")
    }
    if(ticTacToeGame.gameOver){
      highScore.setText("Game Over! Ein neues Spiel kann nun gestartet werden.")
    }
  }

  def newGame():Unit = {
    ticTacToeGame = TicTacToe(moveHistory,actualPlayer)
    informationArea.setText("Ein neues Spiel kann nun begonnen werden!")
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
  def buttonNewGame(): Unit = {newGame()}



}
