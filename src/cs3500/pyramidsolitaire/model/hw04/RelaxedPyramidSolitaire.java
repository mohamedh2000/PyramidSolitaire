package cs3500.pyramidsolitaire.model.hw04;

import java.util.ArrayList;
import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * A class for a version of the game where the rules are bit more
 * lax and you can remove cards that shouldn't be exposed.
 */
public class RelaxedPyramidSolitaire extends BasicPyramidSolitaire
        implements PyramidSolitaireModel<Card> {

  /**
   * If there are outside inputs for the game.
   */
  public RelaxedPyramidSolitaire() {
    super();
  }

  /**
   * If you're given a deck and you have to build a game with it.
   *
   * @param deck the deck we'll be playing with
   */
  public RelaxedPyramidSolitaire(ArrayList<Card> deck) {
    super(deck);
  }

  /**
   * If we're given only the amount of rows and amount of draw Cards we'll be playing with.
   *
   * @param numRows the number of rows the game will be playing with
   * @param numDraw the number of drawCards the game will be playing with
   */
  public RelaxedPyramidSolitaire(int numRows, int numDraw) {
    super(numRows, numDraw);
  }

  /**
   * Checks to see if the first card is one row away from the second card.
   * @param row1 the row of the inner card
   * @param card1 the index number of the inner card
   * @param row2 the row of the outter card that we're checking is on the next row
   * @param card2 the index number of the outter card
   * @return a boolean checking if the location is correct
   */
  private boolean getLocationOfCards(int row1, int card1, int row2, int card2) {
    if (row1 == getNumRows() - 1) {
      return false;
    }
    if ((row1 + 1) == row2) {
      return (card1 == card2) || (card1 + 1 == card2);
    }
    else {
      return false;
    }
  }


  /**
   * It's the exact same as BasicPyramidSolitare's RemovePairs except that it's able to remove a
   * pair if the card is only blocked by one.
   * @param row1  row of first card position, numbered from 0 from the top of the pyramid
   * @param card1 card of first card position, numbered from 0 from left
   * @param row2  row of second card position
   * @param card2 card of second card position
   * @throws IllegalStateException If the game hasn't started yet, or if the state has been altered
   * @throws IllegalArgumentException If the arguments are invalid
   */
  @Override
  public void remove(int row1, int card1, int row2, int card2)
          throws IllegalStateException, IllegalArgumentException {
    updateExposed();
    if (row1 < 0 || card1 < 0 || row2 < 0 || card2 < 0 ) {
      throw new IllegalStateException("Game Hasn't started Yet!");
    }
    if (row1 >= getNumRows()
            || card1 >= getRowWidth(row1) || row2 >= getNumRows() || card2 >= getRowWidth(row2)) {
      throw new IllegalStateException("These coordinates are invalid!");
    }
    if (toString().equals(" ")) {
      throw new IllegalStateException("The game has not yet been started!");
    }
    if (getCardAt(row1, card1) == null || getCardAt(row2, card2) == null) {
      throw new IllegalArgumentException("The Card is Null/Already Removed!");
    }
    if (getCardAt(row1, card1).getValue() + getCardAt(row2, card2).getValue() != 13) {
      throw new IllegalArgumentException("This move is invalid! Cards Don't add up to 13!");
    }
    if (getCardAtExposed1(row1, card1) == null || getCardAtExposed1(row2, card2) == null
            || !(getCardAtExposed1(row1, card1)) || !(getCardAtExposed1(row2, card2))) {
      if (!getCardAtExposed1(row1, card1) && !getCardAtExposed1(row2, card2)) {
        throw new IllegalArgumentException("This move is illegal. Cards aren't Exposed!");
      }
      if (getLocationOfCards(row1, card1, row2, card2)) {
        if (card2 == card1) {
          if (getCardAt(row2, card1 + 1) == null) {
            abstractTheRemovedExposedCards(row1, card1, row2, card2);
            return;
          }
          else {
            throw new IllegalArgumentException("This move is illegal. Cards aren't Exposed!");
          }
        }
        if (card2 == card1 + 1) {
          if (getCardAt(row2, card1) == null) {
            abstractTheRemovedExposedCards(row1, card1, row2, card2);
            return;
          }
          else {
            throw new IllegalArgumentException("This move is illegal. Cards aren't Exposed!");
          }
        }
      }

      if (getLocationOfCards(row2, card2, row1, card1)) {

        if (card2 == card1) {
          if (getCardAt(row1, card1 + 1) == null) {
            abstractTheRemovedExposedCards(row1, card1, row2, card2);
            return;
          }
          else {
            throw new IllegalArgumentException("This move is illegal. Cards aren't Exposed!");
          }
        }
        if (card1 == card2 + 1) {
          if (getCardAt(row1, card2) == null) {
            abstractTheRemovedExposedCards(row1, card1, row2, card2);
            return;
          }
          else {
            throw new IllegalArgumentException("This move is illegal. Cards aren't Exposed!");
          }
        }
      }
      else {
        throw new IllegalArgumentException("This move is illegal. Cards aren't Exposed!");
      }
    }
    else {
      abstractTheRemovedExposedCards(row1, card1, row2, card2);
    }
  }
}
