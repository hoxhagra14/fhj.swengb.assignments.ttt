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
    println("Object:" + t)
    println("ObjectAdded"+t.turn(BottomCenter,PlayerB))
    println("ObjectAdded"+t.turn(MiddleRight,PlayerA))
    println("Object2:" + t)
    val tf = t.gameOver
    if (tf == true)
      println("true")
    else
      println("false")
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

  var actualPlayer:Player = PlayerA

  val moveHistory:Map[TMove,Player] = Map()

  val ticTacToeGame = new TicTacToe(moveHistory, actualPlayer)

  def doSomething(button: Button):Unit = {
    val position:Map[Button,TMove]= Map(b1 -> TopLeft,
                                    b2 -> TopCenter,
                                    b3 -> TopRight,
                                    b4 -> MiddleLeft,
                                    b5 -> MiddleCenter,
                                    b6 -> MiddleRight,
                                    b7 -> BottomLeft,
                                    b8 -> BottomCenter,
                                    b9 -> BottomRight)


    println("ttt" + ticTacToeGame)
    ticTacToeGame.turn(position(button),actualPlayer)
    println("ttt dannach" + ticTacToeGame)
    //var isFirstPlayer: Boolean = true
    if (actualPlayer == PlayerA) {
      button.setText("X")
      actualPlayer = PlayerB
    }
    else{
      button.setText("O")
      actualPlayer = PlayerA
    }

   // button.setText("x")
  }

  def buttonClick1(): Unit = doSomething(b1)
  def buttonClick2(): Unit = doSomething(b2)
  def buttonClick3(): Unit = doSomething(b3)
  def buttonClick4(): Unit = doSomething(b4)
  def buttonClick5(): Unit = doSomething(b5)
  def buttonClick6(): Unit = doSomething(b6)
  def buttonClick7(): Unit = doSomething(b7)
  def buttonClick8(): Unit = doSomething(b8)
  def buttonClick9(): Unit = doSomething(b9)


  def buttonClick(): Unit = {
    //println("Button: " + btn)
   /* var isFirstPlayer: Boolean = true


    if (isFirstPlayer == true) {

      b1.setText("X")
      b2.setText("O")
      isFirstPlayer = false
    }
    val map: Map[TMove, Player] = Map(TopLeft -> PlayerB, BottomLeft -> PlayerB, TopCenter -> PlayerB, TopRight -> PlayerB,
      MiddleLeft -> PlayerA, MiddleCenter -> PlayerB, /*MiddleRight -> PlayerA, BottomCenter -> PlayerB,*/ BottomRight -> PlayerA)
    val t = new TicTacToe(map, PlayerB)
    if (t.gameOver != true) {
      b3.setText("A")
    }*/

  }



}
