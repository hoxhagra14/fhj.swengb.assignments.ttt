package fhj.swengb.assignments.ttt.ghoxha

import scala.collection.Set

/**
  * models the different moves the game allows
  *
  * each move is made by either player a or player b.
  */
sealed trait TMove {
  def idx: Int
}

case object TopLeft extends TMove {
  override def idx: Int = 0
}

case object TopCenter extends TMove {
  override def idx: Int = 1
}

case object TopRight extends TMove {
  override def idx: Int = 2
}

case object MiddleLeft extends TMove {
  override def idx: Int = 3
}

case object MiddleCenter extends TMove {
  override def idx: Int = 4
}

case object MiddleRight extends TMove {
  override def idx: Int = 5
}

case object BottomLeft extends TMove {
  override def idx: Int = 6
}

case object BottomCenter extends TMove {
  override def idx: Int = 7
}

case object BottomRight extends TMove {
  override def idx: Int = 8
}


/**
  * for a tic tac toe game, there are two players, player A and player B
  */
sealed trait Player

case object PlayerA extends Player

case object PlayerB extends Player

object TicTacToe {

  /**
    * creates an empty tic tac toe game
    * @return
    */
  def apply(): TicTacToe ={
    TicTacToe(null)
  }


  /**
    * For a given tic tac toe game, this function applies all moves to the game.
    *
    * @param t
    * @param moves
    * @return
    */
  //def play(t: TicTacToe, moves: Seq[TMove]): TicTacToe = ???

  /**
    * creates all possible games.
    * @return
    */
  //def mkGames(): Map[Seq[TMove], TicTacToe] = ???

}

/**
  * Models the well known tic tac toe game.
  *
  * The map holds the information which player controls which field.
  *
  * The nextplayer parameter defines which player makes the next move.
  */
case class TicTacToe(moveHistory: Map[TMove, Player],
                     nextPlayer: Player = PlayerA) {

  /**
    * outputs a representation of the tic tac toe like this:
    *
    * |---|---|---|
    * | x | o | x |
    * |---|---|---|
    * | x | o | x |
    * |---|---|---|
    * | x | o | x |
    * |---|---|---|
    *
    *
    * @return
    */
  def asString(): String = {
    println(moveHistory)

      var fieldTTT: String  = "|---|---|---| \n" +
      "|   |   |   | \n" +
      "|---|---|---| \n" +
      "|   |   |   | \n" +
      "|---|---|---| \n" +
      "|   |   |   | \n" +
      "|---|---|---| \n"


    val stringPosition = Map(0 -> 17,
                             1 -> 21,
                             2 -> 25,
                             3 -> 47,
                             4 -> 51,
                             5 -> 55,
                             6 -> 77,
                             7 -> 81,
                             8 -> 85)

    for((key,value)<- moveHistory){
      if(value == PlayerA) {
        fieldTTT = fieldTTT.updated(stringPosition(key.idx), "X").mkString
      }else{
        fieldTTT = fieldTTT.updated(stringPosition(key.idx), "O").mkString
      }
    }
    fieldTTT
  }

  /**
    * is true if the game is over.
    *
    * The game is over if either of a player wins or there is a draw.
    */
  val gameOver: Boolean = {
    if(winner != None)
      true
    else
      false
  }


  /**
    * the moves which are still to be played on this tic tac toe.
    */
  val remainingMoves: Set[TMove] = {
    var moves:Set[TMove] = Set(TopLeft,TopCenter,TopRight,MiddleLeft, MiddleCenter, MiddleRight, BottomLeft, BottomCenter, BottomRight)
    for((key,value)<- moveHistory){
      moves -= key
    }
    moves
  }


  /**
    * given a tic tac toe game, this function returns all
    * games which can be derived by making the next turn. that means one of the
    * possible turns is taken and added to the set.
    */
  lazy val nextGames: Set[TicTacToe] = {
    var ticTacToe: Set[TicTacToe] = Set()
    var nextP = nextPlayer
    if (nextPlayer == PlayerA)
      nextP = PlayerB
    else
      nextP = PlayerA

    var freeField:Set[TMove] = Set(TopLeft,TopCenter,TopRight,MiddleLeft, MiddleCenter, MiddleRight, BottomLeft, BottomCenter, BottomRight)
    for((key,value)<- moveHistory){
      freeField -= key
    }
    for(el <- freeField){
        val addTicTacToe = Set(TicTacToe(Map(el->nextPlayer),nextP ))
        ticTacToe = ticTacToe ++ addTicTacToe
    }
    ticTacToe
  }

  /**
  * Either there is no winner, or PlayerA or PlayerB won the game.
  *
  * The set of moves contains all moves which contributed to the result.
  */
  def winner: Option[(Player, Set[TMove])] = {

    var winner: Player = PlayerA
    //Row 1
    if (moveHistory.contains(TopLeft) && moveHistory.contains(TopCenter) && moveHistory.contains(TopRight) ) {
      if(moveHistory(TopLeft) == moveHistory(TopCenter) && moveHistory(TopCenter) == moveHistory(TopRight) ) {
        winner = moveHistory(TopLeft)
      }else
      return None
    }
    //Row 2
    else if (moveHistory.contains(MiddleLeft) && moveHistory.contains(MiddleCenter) && moveHistory.contains(MiddleRight) ) {
      if(moveHistory(MiddleLeft) == moveHistory(MiddleCenter) && moveHistory(MiddleCenter) == moveHistory(MiddleRight) ) {
        winner = moveHistory(MiddleLeft)
      }else
      return None
    }
    //Row 3
    else if (moveHistory.contains(BottomLeft) && moveHistory.contains(BottomCenter) && moveHistory.contains(BottomRight) ) {
      if(moveHistory(BottomLeft) == moveHistory(BottomCenter) && moveHistory(BottomCenter) == moveHistory(BottomRight) ) {
        winner = moveHistory(BottomLeft)
      }else
      return None
    }
    //Column 1
    else if (moveHistory.contains(TopLeft) && moveHistory.contains(MiddleLeft) && moveHistory.contains(BottomLeft) ) {
      if(moveHistory(TopLeft) == moveHistory(MiddleLeft) && moveHistory(MiddleLeft) == moveHistory(BottomLeft) ) {
        winner = moveHistory(TopLeft)
      }else
      return None
    }
    //column 2
    else if (moveHistory.contains(TopCenter) && moveHistory.contains(MiddleCenter) && moveHistory.contains(BottomCenter) ) {
      if(moveHistory(TopCenter) == moveHistory(MiddleCenter) && moveHistory(MiddleCenter) == moveHistory(BottomCenter) ) {
        winner = moveHistory(TopCenter)
      }else
      return None
    }
    //column 3
    else if (moveHistory.contains(TopRight) && moveHistory.contains(MiddleRight) && moveHistory.contains(BottomRight) ) {
      if(moveHistory(TopRight) == moveHistory(MiddleRight) && moveHistory(MiddleRight) == moveHistory(BottomRight) ) {
        winner = moveHistory(TopRight)
      }else
      return None
    }
    // diagonal left to right
    else if (moveHistory.contains(TopLeft) && moveHistory.contains(MiddleCenter) && moveHistory.contains(BottomRight) ) {
      if(moveHistory(TopLeft) == moveHistory(MiddleCenter) && moveHistory(MiddleCenter) == moveHistory(BottomRight) ) {
        winner = moveHistory(TopLeft)
      }else
      return None
    }
    // diagonal right to left
    else if (moveHistory.contains(TopRight) && moveHistory.contains(MiddleCenter) && moveHistory.contains(BottomLeft) ) {
      if(moveHistory(TopRight) == moveHistory(MiddleCenter) && moveHistory(MiddleCenter) == moveHistory(BottomLeft) ) {
        winner = moveHistory(TopRight)
      }else
      return None
    }
    else{
    return None
    }

    var move:Set[TMove] = Set()
    for((key,value)<- moveHistory){
    val addToMove = Set(key)
    move = move ++ addToMove
    }
    Some((winner, move))

  }
  /**
  * returns a copy of the current game, but with the move applied to the tic tac toe game.
  *
  * @param p to be played
  * @param player the player
  * @return
  */
  def turn(p: TMove, player: Player): TicTacToe = {
    val map= Map(p -> player)
    println("Turn" + moveHistory)
    val addedMove = moveHistory ++ map
    println("Turn" +addedMove)
    if (player == PlayerA){
    TicTacToe(addedMove, PlayerB)
    }else{
    TicTacToe(addedMove, PlayerA)
    }

  }



}


