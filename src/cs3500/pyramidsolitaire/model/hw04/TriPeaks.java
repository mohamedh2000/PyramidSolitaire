package cs3500.pyramidsolitaire.model.hw04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.CardAmount;
import cs3500.pyramidsolitaire.model.hw02.CardType;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * Create another version of the game where there are three pyramids instead of just one.
 */
public class TriPeaks extends BasicPyramidSolitaire implements PyramidSolitaireModel<Card> {

  /**
   * If there are no inputs to the game they should initialize everything
   * like BasicSolitaire would.
   */
  public TriPeaks() {
    super();
  }

  /**
   * If you're given a deck and you have to build a game with it.
   *
   * @param deck the deck we'll be playing with
   */
  public TriPeaks(ArrayList<Card> deck) {
    super(deck);
  }

  /**
   * If we're given only the amount of rows and amount of draw Cards we'll be playing with.
   *
   * @param numRows the number of rows the game will be playing with
   * @param numDraw the number of drawCards the game will be playing with
   */
  public TriPeaks(int numRows, int numDraw) {
    super(numRows, numDraw);
  }

  /**
   * If we're given only the deck, amount of rows and amount of draw cards we'll be playing with.
   * @param deck the deck we'll be playing with
   * @param numRows the number of rows we'll be playing with
   * @param numDraw the number of draw cards we'll be playing with
   */
  public TriPeaks(ArrayList<Card> deck, int numRows, int numDraw) {
    super(deck, numRows, numDraw);
  }


  /**
   * Return a valid and complete deck of cards for a game of Pyramid Solitaire. There is no
   * restriction imposed on the ordering of these cards in the deck. The validity of the deck is
   * determined by the rules of the game.
   *
   * @return the deck of cards as a list
   */
  @Override
  public List<Card> getDeck() {
    ArrayList<Card> temp = new ArrayList<>();
    CardAmount[] typeDeck = {CardAmount.King, CardAmount.Queen, CardAmount.Ace, CardAmount.Jack,
        CardAmount.Two, CardAmount.Three, CardAmount.Four, CardAmount.Five, CardAmount.Six,
        CardAmount.Seven, CardAmount.Eight, CardAmount.Nine, CardAmount.Ten};
    int numOfTimesForSuiteSpades = 0;
    int numOfTimesForSuiteHearts = 0;
    int numOfTimesForSuiteClubs = 0;
    int numOfTimesForSuiteDiamonds = 0;

    for (int i = 0; i < 104; i++) {
      if (!(checkIfEqual(temp, numOfTimesForSuiteSpades, CardType.Spades))) {
        addCard(temp, typeDeck, numOfTimesForSuiteSpades, CardType.Spades);
      }
      if (!(checkIfEqual(temp, numOfTimesForSuiteClubs, CardType.Clubs))) {
        addCard(temp, typeDeck, numOfTimesForSuiteClubs, CardType.Clubs);
      }
      if (!(checkIfEqual(temp, numOfTimesForSuiteHearts, CardType.Hearts))) {
        addCard(temp, typeDeck, numOfTimesForSuiteHearts, CardType.Hearts);
      }
      if (!(checkIfEqual(temp, numOfTimesForSuiteDiamonds, CardType.Diamonds))) {
        addCard(temp, typeDeck, numOfTimesForSuiteDiamonds, CardType.Diamonds);
      }
    }
    return temp;
  }


  /**
   * This method checks if there are 13 cards in the deck with the type of suit.
   * @param cards That is the current list we have for our deck.
   * @param numOfTimes this is an accumulator to make sure that we have 13 cards of that type.
   * @param whichSuit this is the type of suit we will be checking for.
   * @return a boolean if its reached 13.
   */
  protected boolean checkIfEqual(ArrayList<Card> cards, int numOfTimes, CardType whichSuit) {
    //checks that there are 13 of the suite in the deck
    boolean check = false;
    for (int i = 0; i < cards.size(); i++) {
      if (cards.get(i).type == whichSuit) {
        numOfTimes = numOfTimes + 1;
      }
      if (numOfTimes == 26) {
        check = true;
      }
    }
    return check;
  }

  /**
   *If there are not 13 cards for the suit then we will add cards with that type of suit to the
   * deck.
   *
   * @param temp This is the deck we currently have
   * @param typeDeck this is the type of cards we have, such as Queen, King, etc.
   * @param numOfTimes this is an accumulator so we can stop when we have added 13 cards of that
   *                   type of suit
   * @param cardType That is the type of suit we will be adding
   */
  protected void addCard(ArrayList<Card> temp, CardAmount[] typeDeck, int numOfTimes,
                       CardType cardType) {
    if (!(checkIfEqual(temp, numOfTimes, cardType))) {
      for (int j = 0; j < typeDeck.length; j++) {
        if (j == 26) {
          break;
        } else {
          temp.add(new Card(typeDeck[j], cardType));
          temp.add(new Card(typeDeck[j], cardType));
          numOfTimes = numOfTimes + 2;
        }
      }
    }
  }

  /**
   * Checks when the rows should start Overlapping.
   * @param numRows The amount of rows in the game
   * @return the rows that should be overlapping in the game
   */
  private int numStartOverlapping(int numRows) {
    if ((numRows % 2) != 0) {
      return (int) Math.ceil(numRows / 2.0);
    }
    else {
      return numRows / 2;
    }
  }

  /**
   * <p>Deal a new game of Pyramid Solitaire.  The cards to be used and their order
   * are specified by the the given deck, unless the {@code shuffle} parameter indicates the order
   * should be ignored.</p>
   *
   * <p>This method first verifies that the deck is valid. It deals cards into the characteristic
   * pyramid shape having the specified number of complete rows, followed by the specified number of
   * draw cards. The 0th card in {@code deck} is the top of the pyramid.</p>
   *
   * <p>This method should have no other side effects, and should work for any valid
   * arguments.</p>
   *
   * @param deck          the deck to be dealt
   * @param shouldShuffle if {@code false}, use the order as given by {@code deck}, otherwise
   *                      shuffle the cards
   * @param numRows       number of rows in the pyramid
   * @param numDraw       number of open piles
   * @throws IllegalArgumentException if the deck is null or invalid, or another input is invalid
   */
  @Override
  public void startGame(List<Card> deck, boolean shouldShuffle, int numRows, int numDraw) {
    if (deck == null) {
      throw new IllegalStateException("Deck is Null!");
    }

    ArrayList<Card> realDeck = new ArrayList<>();
    for (int i = 0; i < deck.size(); i++) {
      realDeck.add(deck.get(i));
    }
    this.deck = realDeck;

    if (this.deck.isEmpty()) {
      List<Card> realDeck2 = getDeck();
      for (int i = 0; i < deck.size(); i++) {
        this.deck.add(realDeck2.get(i));
      }
    }

    if (this.deck.size() != 104) {
      throw new IllegalArgumentException("Deck is null or doesn't have 52 Cards!");
    }
    if (numRows > 8) {
      throw new IllegalArgumentException("number of Rows is too big!");
    }
    if (numRows < 1) {
      throw new IllegalArgumentException("Game needs at least 1 row!");
    }
    if (numDraw < 0) {
      throw new IllegalArgumentException("numDraw cant be less than 0!");
    } else {
      this.numRows = numRows;
      this.numDraw = numDraw;
      ArrayList<ArrayList<Card>> pyramid = new ArrayList<>();

      if (!shouldShuffle) {
        for (int i = 0; i < numRows; i++) {
          pyramid.add(new ArrayList<>());
        }

        //You take the number of rows and subtract the amount that need to overlap in the pyramid
        // and that's when the pyramid needs to start overlapping
        int rowTillOverLap = numRows - (numStartOverlapping(numRows));
        int cardNumToAdd = 6;
        int sizeOfRow = numRows;
        int numOfNull = (sizeOfRow - 3) / 2;
        int whenToPutNulls = 1; //want to round down

        for (int i = 0; i < rowTillOverLap; i++) {
          ArrayList<Card> currentRow = pyramid.get(i);

          while (currentRow.size() < sizeOfRow) {
            int accumulator = 0;
            for (int j = 0; j < sizeOfRow ; j++) {
              if (accumulator == whenToPutNulls) {
                if (currentRow.size() == sizeOfRow) {
                  break;
                }
                for (int l = 0; l < numOfNull; l++) {
                  currentRow.add(null);
                }
                accumulator = 0;
                j++;
              }
              else {
                currentRow.add(deck.get(0));
                deck.remove(0);
                accumulator = accumulator + 1;
              }
            }
          }
          numOfNull -= 1;
          whenToPutNulls += 1;
          sizeOfRow += 1;
        }

        for (int i = rowTillOverLap; i < pyramid.size(); i ++) {
          ArrayList<Card> currentRow = pyramid.get(i);
          while (currentRow.size() < sizeOfRow) {
            currentRow.add(deck.get(0));
            deck.remove(0);
          }
          sizeOfRow += 1;
        }

        gamePyramid = pyramid;
        exposed1 = getExposedList(gamePyramid);
        System.out.println(gamePyramid);


        //handles piles of the game
        ArrayList<Card> piles = new ArrayList<>();
        int l = 0;
        while (l < this.numDraw) {
          if (this.deck.size() == 0) {
            piles.add(null);
            l++;
          } else {
            piles.add(this.deck.get(0));
            this.deck.remove(0);
            l++;
          }
        }
        stock = this.deck;
        drawCards = piles;
        score = getScore();
      }
      else {
        Collections.shuffle(this.deck);
        startGame(this.deck, false, numRows, numDraw);
      }
    }
  }

  protected ArrayList<ArrayList<Boolean>> getExposedList(ArrayList<ArrayList<Card>> gp) {
    ArrayList<ArrayList<Boolean>> temp = new ArrayList<ArrayList<Boolean>>();
    for (int j = 0; j < gp.size(); j ++) {
      ArrayList<Card> currentColumn = gp.get(j);
      ArrayList<Boolean> temp2 = new ArrayList<>();
      for (int i = 0; i < currentColumn.size(); i++) {
        if (currentColumn.get(i) == null) {
          temp2.add(null);
        }
        else {
          temp2.add(currentColumn.get(i).exposed);
        }
      }
      temp.add(temp2);
    }
    for (int u = 0; u < gp.get(gp.size() - 1).size(); u++) {
      temp.get(temp.size() - 1).set(u, true);
    }
    return temp;
  }
}


