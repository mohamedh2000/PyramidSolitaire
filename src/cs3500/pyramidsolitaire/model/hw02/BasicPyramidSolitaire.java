package cs3500.pyramidsolitaire.model.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * To provide the basic implementations behind the game.
 */
public class BasicPyramidSolitaire implements PyramidSolitaireModel<Card> {
  protected List<Card> deck;
  protected int numRows;
  protected int numDraw;
  protected ArrayList<ArrayList<Card>> gamePyramid;
  protected List<Card> stock;
  protected int score;
  protected List<Card> drawCards;
  protected ArrayList<ArrayList<Boolean>> exposed1;

  /**
   * If there are outside inputs for the game.
   */
  public BasicPyramidSolitaire() {
    deck = new ArrayList<>();
    numRows = -1;
    numDraw = -1;
    gamePyramid = new ArrayList<ArrayList<Card>>();
    stock = new ArrayList<>();
    score = 0;
    drawCards = new ArrayList<>();
    exposed1 = new ArrayList<ArrayList<Boolean>>();

  }

  /**
   * If you're given a deck and you have to build a game with it.
   * @param deck the deck we'll be playing with
   */
  public BasicPyramidSolitaire(ArrayList<Card> deck) {
    if (deck == null) {
      throw new IllegalStateException("Deck Cannot be Null");
    }
    else {
      ArrayList<Card> cardDeck = new ArrayList<>();
      for (int i = 0; i < deck.size(); i++) {
        cardDeck.add(deck.get(i));
      }
      this.deck = cardDeck; //copy of the deck
      numRows = -1;
      numDraw = -1;
      gamePyramid = new ArrayList<ArrayList<Card>>();
      stock = new ArrayList<>();
      score = 0;
      drawCards = new ArrayList<>();
      exposed1 = new ArrayList<ArrayList<Boolean>>();
    }
  }

  /**
   * If we're given only the amount of rows and amount of draw Cards we'll be playing with.
   * @param numRows the number of rows the game will be playing with
   * @param numDraw the number of drawCards the game will be playing with
   */
  public BasicPyramidSolitaire(int numRows, int numDraw) {
    this.deck = new ArrayList<>();
    this.numRows = numRows;
    this.numDraw = numDraw;
    gamePyramid = new ArrayList<ArrayList<Card>>();
    stock = new ArrayList<>();
    score = 0;
    drawCards = new ArrayList<>();
    exposed1 = new ArrayList<ArrayList<Boolean>>();
  }

  /**
   * If we're given only the deck, amount of rows and amount of draw cards we'll be playing with.
   * @param deck the deck we'll be playing with
   * @param numRows the number of rows we'll be playing with
   * @param numDraw the number of draw cards we'll be playing with
   */
  public BasicPyramidSolitaire(ArrayList<Card> deck, int numRows, int numDraw) {
    if (deck == null) {
      throw new IllegalStateException("Deck Cannot be Null");
    } else {
      ArrayList<Card> cardDeck = new ArrayList<>();
      for (int i = 0; i < deck.size(); i++) {
        cardDeck.add(deck.get(i));
      }
      this.deck = cardDeck;
      this.numRows = numRows;
      this.numDraw = numDraw;
      gamePyramid = new ArrayList<ArrayList<Card>>();
      stock = new ArrayList<>();
      score = 0;
      drawCards = new ArrayList<>();
      exposed1 = new ArrayList<ArrayList<Boolean>>();
    }
  }

  /**
   * Make an arrayList of booleans of all the exposed and non-exposed cards in the game.
   * @param gp the GamePyramid we'll be feeding to get all of the exposed and non exposed cards
   * @return A pyramid of Booleans
   */
  protected ArrayList<ArrayList<Boolean>> getExposedList(ArrayList<ArrayList<Card>> gp) {
    ArrayList<ArrayList<Boolean>> temp = new ArrayList<ArrayList<Boolean>>();
    for (int j = 0; j < gp.size(); j ++) {
      ArrayList<Card> currentColumn = gp.get(j);
      ArrayList<Boolean> temp2 = new ArrayList<>();
      for (int i = 0; i <= j; i++) {
        temp2.add(currentColumn.get(i).exposed);
      }
      temp.add(temp2);
    }
    return temp;
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

    for (int i = 0; i < 52; i++) {
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
        if (j == 13) {
          break;
        } else {
          temp.add(new Card(typeDeck[j], cardType));
          numOfTimes = numOfTimes + 1;
        }
      }
    }
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
      if (numOfTimes == 13) {
        check = true;
      }
    }
    return check;
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

    if (this.deck.size() != 52) {
      throw new IllegalArgumentException("Deck is null or doesn't have 52 Cards!");
    }
    if (numRows > 9) {
      throw new IllegalArgumentException("number of Rows is too big!");
    }
    if (numRows < 1) {
      throw new IllegalArgumentException("Game needs at least 1 row!");
    }
    if (numDraw < 0) {
      throw new IllegalArgumentException("numDraw cant be less than 0!");
    }
    if (duplicates(this.deck)) {
      throw new IllegalArgumentException("There are duplicates in the deck!");
    }
    else {
      this.numRows = numRows;
      this.numDraw = numDraw;
      if (!shouldShuffle) {
        ArrayList<ArrayList<Card>> pyramid = new ArrayList<>();

        //handle rows(how many arrays the inner will have)
        for (int i = 0; i < numRows; i++) {
          ArrayList<Card> temp = new ArrayList<>();
          ArrayList<Card> nested = new ArrayList<>();

          if (i == numRows - 1) {
            for (int j = 0; j <= i; j++) {

              while (nested.size() <= j) {
                nested.add(new Card(this.deck.get(0).name, this.deck.get(0).type, true));
                this.deck.remove(0);
              }
              temp = nested;
            }
            pyramid.add(temp);
          }

          else {
            //handle the amount of cards per row
            for (int j = 0; j <= i; j++) {

              //then you keep adding cards to the row until the row width is equal to the cards
              // that should be in the row
              while (nested.size() <= j) {
                nested.add(this.deck.get(0));
                this.deck.remove(0);
              }
              temp = nested;
            }
            pyramid.add(temp);
          }
        }

        gamePyramid = pyramid;
        exposed1 = getExposedList(gamePyramid);

        //handles piles of the game
        ArrayList<Card> piles = new ArrayList<>();
        int l = 0;
        while (l < this.numDraw) {
          if (this.deck.size() == 0) {
            piles.add(null);
            l++;
          }
          else {
            piles.add(this.deck.get(0));
            this.deck.remove(0);
            l++;
          }
        }
        stock = this.deck;
        drawCards = piles;
        score = getScore();
      } else {
        Collections.shuffle(this.deck);
        startGame(this.deck, false, numRows, numDraw);
      }
    }
  }

  /**
   * Checks if deck has any Duplicate cards in it.
   * @param deck the deck we're going to start the game with.
   * @return boolean checking if the deck has duplicates in it.
   */
  protected boolean duplicates(List<Card> deck) {
    boolean check = false;
    for (int i = 0; i < deck.size(); i++) {
      if (checkForDuplicates(deck.get(i), deck, i)) {
        check = true;
        break;
      }
    }
    return check;
  }

  /**
   * Checks if that card has duplicates in it.
   * @param card The Card we're checking if the rest of the deck has another copy of
   * @param deck The Deck we're checking has duplicates.
   * @param number The index number of the card we're checking that way it can just skip it.
   * @return A boolean checking if it found another copy of the card.
   */
  private boolean checkForDuplicates(Card card, List<Card> deck, int number) {
    boolean check = false;
    for (int i = 0; i < deck.size(); i++) {
      if (i == number) {
        continue;
      }
      if (card.equals(deck.get(i))) {
        check = true;
        break;
      }
    }
    return check;
  }

  /**
   * Gets the card coordinates positions.
   * @param row The row index of the pyramid we want
   * @param card the card index of the pyramid we want
   * @return a boolean
   */
  protected Boolean getCardAtExposed1(int row, int card) {
    updateExposed();
    if (toString().equals(" ")) {
      throw new IllegalStateException("The game hasn't started yet!");
    }
    if (row > getNumRows()) {
      throw new IllegalStateException("The coordinates are invalid!");
    }
    if (card > getRowWidth(row)) {
      throw new IllegalArgumentException("The Card Number is invalid!");
    }
    else {
      ArrayList<Boolean> currentColumn = exposed1.get(row);
      if (exposed1.get(row) == null) {
        return null;
      }
      else {
        return currentColumn.get(card);
      }
    }
  }

  protected void abstractTheRemovedExposedCards(int row1, int card1, int row2, int card2) {
    exposed1.get(row1).set(card1, true);
    exposed1.get(row2).set(card2, true);
    ArrayList<Card> rowOfCard1 = gamePyramid.get(row1);
    ArrayList<Card> rowOfCard2 = gamePyramid.get(row2);

    Card replaceFirst = getCardAt(row1, card1);
    Card replaceSecond = getCardAt(row2, card2);
    score = score - replaceFirst.getValue();
    score = score - replaceSecond.getValue();
    rowOfCard1.add(card1, null);
    rowOfCard2.add(card2, null);
    rowOfCard1.remove(replaceFirst);
    rowOfCard2.remove(replaceSecond);

    ArrayList<Boolean> rowOfExposed1 = exposed1.get(row1);
    ArrayList<Boolean> rowOfExposed2 = exposed1.get(row2);
    rowOfExposed1.remove(card1);
    rowOfExposed1.add(card1, null);
    rowOfExposed2.remove(card2);
    rowOfExposed2.add(card2, null);
    updateExposed();
  }

  /**
   * Execute a pairwise move on the pyramid, using the two specified card positions.
   *
   * @param row1  row of first card position, numbered from 0 from the top of the pyramid
   * @param card1 card of first card position, numbered from 0 from left
   * @param row2  row of second card position
   * @param card2 card of second card position
   * @throws IllegalArgumentException if the move is invalid
   * @throws IllegalStateException    if the game has not yet been started
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
      throw new IllegalArgumentException("This move is illegal. Cards aren't Exposed!");
    }
    else {
      abstractTheRemovedExposedCards(row1, card1, row2, card2);
    }
  }

  /**
   * Execute a single-card move on the pyramid, using the specified card position.
   *
   * @param row  row of the desired card position, numbered from 0 from the top of the pyramid
   * @param card card of the desired card position, numbered from 0 from left
   * @throws IllegalArgumentException if the move is invalid
   * @throws IllegalStateException    if the game has not yet been started
   */
  @Override
  public void remove(int row, int card) throws IllegalStateException, IllegalArgumentException {
    updateExposed();
    if (row < 0 || card < 0 || row >= getNumRows() || card >= getRowWidth(row)) {
      throw new IllegalArgumentException("These Coordinates are invalid!");
    }
    if (getCardAt(row, card) == null) {
      throw new IllegalArgumentException("The card is null/Already Removed!");
    }
    if (getCardAt(row, card).getValue() != 13) {
      throw new IllegalArgumentException("This move is invalid");
    }
    if (toString().equals(" ")) {
      throw new IllegalStateException("The game has not yet been started!");
    }
    if (getCardAtExposed1(row, card) == null || !(getCardAtExposed1(row, card))) {
      System.out.println(stock.size());
      System.out.println(gamePyramid);
      System.out.println(exposed1);
      throw new IllegalArgumentException("This move is illegal. Card isn't Exposed!");
    }
    else {
      Card replace = getCardAt(row, card);
      Boolean replace2 = getCardAtExposed1(row, card);
      ArrayList<Card> cardRow = gamePyramid.get(row);
      ArrayList<Boolean> exposedRow = exposed1.get(row);
      score = score - replace.getValue();
      cardRow.add(card, null);
      exposedRow.remove(replace2);
      exposedRow.add(card, null);
      cardRow.remove(replace);
      updateExposed();
    }
  }

  /**
   * It'll update the exposed pyramid.
   */
  protected void updateExposed() {
    for (int i = 0; i < exposed1.size() - 1; i ++) {
      ArrayList<Boolean> currentRow = exposed1.get(i);
      for (int j = 0; j <= i; j ++) {
        Boolean currentBooleanCard = currentRow.get(j);
        if (currentBooleanCard == null) {
          continue;
        }
        if (!currentBooleanCard) {
          if ((exposed1.get(i + 1).get(j) == null) && (exposed1.get(i + 1).get(j + 1) == null)) {
            currentRow.set(j, true);
          }
        }
      }
    }
  }

  /**
   * Execute a pairwise move, using the specified card from the draw pile and the specified card
   * from the pyramid.
   *
   * @param drawIndex the index of the drawcard/stock
   * @param row       row of the desired card position, numbered from 0 from the top of the pyramid
   * @param card      card of the desired card position, numbered from 0 from left
   * @throws IllegalArgumentException if the move is invalid
   * @throws IllegalStateException    if the game has not yet been started
   */
  @Override
  public void removeUsingDraw(int drawIndex, int row, int card) throws IllegalStateException {
    updateExposed();
    if (toString().equals(" ")) {
      throw new IllegalStateException("Game hasn't started yet!");
    }
    if (drawIndex >= getNumDraw() || row >= getNumRows() || card >= getRowWidth(row)
            || drawIndex < 0 || row < 0 || card < 0) {
      throw new IllegalArgumentException("Invalid Draw Index!");
    }
    if (row > numRows - 1) {
      throw new IllegalArgumentException("Invalid Row Coordinate!");
    }
    if (card > getRowWidth(row) - 1) {
      throw new IllegalArgumentException("Invalid Card Coordinate!");
    }
    if (getCardAtExposed1(row, card) == null || !getCardAtExposed1(row, card)) {
      throw new IllegalArgumentException("This move is illegal. Card isn't Exposed!");
    }
    if (drawCards.get(drawIndex).getValue() + getCardAt(row, card).getValue() != 13) {
      throw new IllegalArgumentException("Cards do not Equal 13! Move is invalid!");
    } else {
      score = score - getCardAt(row, card).getValue();
      ArrayList<Card> cardRow = gamePyramid.get(row);
      ArrayList<Boolean> exposedRow = exposed1.get(row);
      exposedRow.remove(card);
      exposedRow.add(card, null);
      cardRow.add(card, null);
      cardRow.remove(cardRow.get(card + 1));
      discardDraw(drawIndex);
      updateExposed();
    }
  }

  /**
   * Discards an individual card from the draw pile.
   *
   * @param drawIndex the card to be discarded
   * @throws IllegalArgumentException if the index is invalid or no card is present there.
   * @throws IllegalStateException    if the game has not yet been started
   */
  @Override
  public void discardDraw(int drawIndex) throws IllegalStateException, IllegalArgumentException {
    if (toString().equals(" ")) {
      throw new IllegalStateException("Game hasn't started yet!");
    }
    if (drawIndex < 0 || drawIndex >= getNumDraw() || drawCards.get(drawIndex) == null) {
      throw new IllegalArgumentException("The Index is invalid or no card is present there");
    }
    else if (stock.size() == 0) {
      drawCards.remove(drawIndex);
      drawCards.add(drawIndex,null);
    }
    else {
      drawCards.remove(drawIndex);
      drawCards.add(drawIndex, stock.get(0));
      stock.remove(0);
    }
  }


  /**
   * Returns the number of rows originally in the pyramid, or -1 if the game hasn't been started.
   *
   * @return the height of the pyramid, or -1
   */
  @Override
  public final int getNumRows() {
    return numRows;
  }

  /**
   * Returns the maximum number of visible cards in the draw pile, or -1 if the game hasn't been
   * started.
   *
   * @return the number of visible cards in the draw pile, or -1
   */
  @Override
  public int getNumDraw() {
    if (toString().equals(" ")) {
      return -1;
    }
    else {
      return numDraw;
    }
  }

  /**
   * Returns the number of cards dealt into the given row.
   *
   * @param row the desired row (zero-indexed)
   * @return the width of that row
   * @throws IllegalArgumentException if the row is invalid
   * @throws IllegalStateException    if the game has not yet been started
   */
  @Override
  public int getRowWidth(int row) {
    if (toString().equals(" ")) {
      throw new IllegalStateException("The Game has not yet been started!");
    }
    if (row > getNumRows()) {
      throw new IllegalArgumentException("The Row is Invalid: Row field > Number of Rows");
    } else {
      ArrayList<Card> rows = gamePyramid.get(row);
      return rows.size();
    }
  }

  /**
   * Checks from all the exposed cards if there are still pairs available as moves to be played.
   * @param gp Game Pyramid
   * @return A Boolean checking if there are still moves left to be made or not
   */
  private boolean containsPairs(ArrayList<ArrayList<Card>> gp) {
    updateExposed();
    boolean bol = false;
    ArrayList<Card> allExposedCards = new ArrayList<>();

    for (int i = 0; i < gp.size(); i++) {
      ArrayList<Card> currentColumn = gp.get(i);
      for (int j = 0; j < currentColumn.size(); j++) {
        if (getCardAt(i, j) == null) {
          continue;
        }
        if (getCardAtExposed1(i , j) == null) {
          continue;
        }
        if (getCardAtExposed1(i, j)) {
          allExposedCards.add(getCardAt(i, j));
        }
      }
    }

    for (int i = 0; i < allExposedCards.size(); i++) {
      for (int j = 0; j <= i; j++) {
        if (allExposedCards.get(i).getValue() + allExposedCards.get(j).getValue() == 13) {
          bol = true;
          break;
        }
      }
    }
    return bol;
  }

  /**
   * Signal if the game is over or not.
   *
   * @return true if game is over, false otherwise
   * @throws IllegalStateException if the game hasn't been started yet
   */
  @Override
  public boolean isGameOver() throws IllegalStateException {
    if (toString().equals(" ")) {
      throw new IllegalStateException("Game Hasn't Started yet!");
    }
    if (getScore() == 0 && checkSize(drawCards)) {
      return true;
    }
    if (getScore() != 0 && !checkSize(drawCards)) {
      return false;
    }
    if (checkSize(drawCards)) {
      return !containsPairs(gamePyramid); //this is where the problem is
    }
    else {
      return getScore() == 0;
    }
  }

  /**
   * Checks if all of the drawCards are null.
   * @param d The Draw Card deck
   * @return A Boolean
   */
  private boolean checkSize(List<Card> d) {
    int size = 0;
    for (int i = 0; i < d.size(); i++) {
      if (d.get(i) == null) {
        continue;
      }
      else {
        size = size + d.get(i).getValue();
      }
    }
    return size == 0;
  }

  /**
   * Checks how many of the Rows are not Zero. If all are Zero then the game is over.
   * @return An Integer of how many rows are not empty/null.
   */
  private int getContents() {
    int numOfEmptyRows = 0;
    for (int i = 0; i < gamePyramid.size(); i++ ) {
      if (gamePyramid.get(i).size() != 0) {
        numOfEmptyRows = numOfEmptyRows + 1;
        if (isAllNull(gamePyramid.get(i))) {
          numOfEmptyRows = numOfEmptyRows - 1;
        }
      }
    }
    return numOfEmptyRows;
  }

  /**
   * Are all of the row null.
   * @param gamePyramid2 The Game Pyramid we're going to be feeding it
   * @return a boolean checking whether or not the whole row is null or not
   */
  private boolean isAllNull(ArrayList<Card> gamePyramid2) {
    boolean check = true;
    for (int i = 0; i < gamePyramid2.size(); i++) {
      if (gamePyramid2.get(i) != null) {
        check = false;
        break;
      }
    }
    return check;
  }

  /**
   * Return the current score, which is the sum of the values of the cards remaining in the
   * pyramid.
   *
   * @return the score
   * @throws IllegalStateException if the game hasn't been started yet
   */
  @Override
  public int getScore() throws IllegalStateException {
    if (toString().equals(" ")) {
      throw new IllegalStateException("Game hasn't been started yet!");
    }
    else {
      int beginningScore = 0;
      for (int i = 0; i < gamePyramid.size(); i++) {
        for (int j = 0; j < gamePyramid.get(i).size(); j++) {
          ArrayList<Card> currentRow = gamePyramid.get(i);
          if (currentRow.get(j) == null) {
            continue;
          }
          else {
            beginningScore = beginningScore + currentRow.get(j).getValue();
          }
        }
      }
      return beginningScore;
    }
  }

  /**
   * Returns the card at the specified coordinates.
   *
   * @param row  row of the desired card
   * @param card column of the desired card
   * @return the card at the given position, or <code>null</code> if no card is there
   * @throws IllegalArgumentException if the coordinates are invalid
   * @throws IllegalStateException    if the game hasn't been started yet
   */
  @Override
  public Card getCardAt(int row, int card) throws IllegalStateException {
    if (toString().equals(" ")) {
      throw new IllegalStateException("The game hasn't started yet!");
    }
    if (row > getNumRows()) {
      throw new IllegalStateException("The coordinates are invalid!");
    }
    if (card > getRowWidth(row)) {
      throw new IllegalArgumentException("The Card Number is invalid!");
    }
    else {
      ArrayList<Card> currentColumn = gamePyramid.get(row);
      if (currentColumn.get(card) == null) {
        return null;
      }
      else {
        return currentColumn.get(card);
      }
    }
  }

  /**
   * Returns the currently available draw cards. There should be at most {@link
   * PyramidSolitaireModel#getNumDraw} cards (the number specified when the game started) -- there
   * may be fewer, if cards have been removed.
   *
   * @return the ordered list of available draw cards
   * @throws IllegalStateException if the game hasn't been started yet
   */
  @Override
  public List<Card> getDrawCards() throws IllegalStateException {
    if (toString().equals(" ")) {
      throw new IllegalStateException("Game Hasn't been Started!");
    } else {
      ArrayList<Card> copyOfDrawCards = new ArrayList<Card>();
      for (int i = 0; i < drawCards.size(); i++) {
        copyOfDrawCards.add(drawCards.get(i));
      }
      return copyOfDrawCards;
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof BasicPyramidSolitaire)) {
      return false;
    } else {
      BasicPyramidSolitaire that = (BasicPyramidSolitaire) obj;
      boolean check = false;
      if (that.deck.size() == this.deck.size()) {
        for (int i = 0; i < deck.size(); i++) {
          if (this.deck.contains(that.deck)) {
            check = true;
          }
          if (this.deck.get(i) != that.deck.get(i) && !(that.deck.contains(this.deck.get(i)))) {
            check = false;
          }
        }
      }
      else {
        check = false;
      }
      return check;
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

}


