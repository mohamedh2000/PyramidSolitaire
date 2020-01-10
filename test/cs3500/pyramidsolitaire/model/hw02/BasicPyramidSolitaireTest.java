package cs3500.pyramidsolitaire.model.hw02;

import org.junit.Test;

import java.util.ArrayList;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;

/**
 * To test the BasicPyramidSolitaire class.
 */
public class BasicPyramidSolitaireTest {
  private PyramidSolitaireModel<Card> game1 = new BasicPyramidSolitaire();

  @Test
  public void testGetDeck() {
    ArrayList<Card> deck = new ArrayList<>();
    deck.add(new Card(CardAmount.King, CardType.Spades));
    deck.add(new Card(CardAmount.Queen, CardType.Spades));
    deck.add(new Card(CardAmount.Ace, CardType.Spades));
    deck.add(new Card(CardAmount.Jack, CardType.Spades));
    deck.add(new Card(CardAmount.Two, CardType.Spades));
    deck.add(new Card(CardAmount.Three, CardType.Spades));
    deck.add(new Card(CardAmount.Four, CardType.Spades));
    deck.add(new Card(CardAmount.Five, CardType.Spades));
    deck.add(new Card(CardAmount.Six, CardType.Spades));
    deck.add(new Card(CardAmount.Seven, CardType.Spades));
    deck.add(new Card(CardAmount.Eight, CardType.Spades));
    deck.add(new Card(CardAmount.Nine, CardType.Spades));
    deck.add(new Card(CardAmount.Ten, CardType.Spades));
    deck.add(new Card(CardAmount.King, CardType.Clubs));
    deck.add(new Card(CardAmount.Queen, CardType.Clubs));
    deck.add(new Card(CardAmount.Ace, CardType.Clubs));
    deck.add(new Card(CardAmount.Jack, CardType.Clubs));
    deck.add(new Card(CardAmount.Two, CardType.Clubs));
    deck.add(new Card(CardAmount.Three, CardType.Clubs));
    deck.add(new Card(CardAmount.Four, CardType.Clubs));
    deck.add(new Card(CardAmount.Five, CardType.Clubs));
    deck.add(new Card(CardAmount.Six, CardType.Clubs));
    deck.add(new Card(CardAmount.Seven, CardType.Clubs));
    deck.add(new Card(CardAmount.Eight, CardType.Clubs));
    deck.add(new Card(CardAmount.Nine, CardType.Clubs));
    deck.add(new Card(CardAmount.Ten, CardType.Clubs));
    deck.add(new Card(CardAmount.King, CardType.Hearts));
    deck.add(new Card(CardAmount.Queen, CardType.Hearts));
    deck.add(new Card(CardAmount.Ace, CardType.Hearts));
    deck.add(new Card(CardAmount.Jack, CardType.Hearts));
    deck.add(new Card(CardAmount.Two, CardType.Hearts));
    deck.add(new Card(CardAmount.Three, CardType.Hearts));
    deck.add(new Card(CardAmount.Four, CardType.Hearts));
    deck.add(new Card(CardAmount.Five, CardType.Hearts));
    deck.add(new Card(CardAmount.Six, CardType.Hearts));
    deck.add(new Card(CardAmount.Seven, CardType.Hearts));
    deck.add(new Card(CardAmount.Eight, CardType.Hearts));
    deck.add(new Card(CardAmount.Nine, CardType.Hearts));
    deck.add(new Card(CardAmount.Ten, CardType.Hearts));
    deck.add(new Card(CardAmount.King, CardType.Diamonds));
    deck.add(new Card(CardAmount.Queen, CardType.Diamonds));
    deck.add(new Card(CardAmount.Ace, CardType.Diamonds));
    deck.add(new Card(CardAmount.Jack, CardType.Diamonds));
    deck.add(new Card(CardAmount.Two, CardType.Diamonds));
    deck.add(new Card(CardAmount.Three, CardType.Diamonds));
    deck.add(new Card(CardAmount.Four, CardType.Diamonds));
    deck.add(new Card(CardAmount.Five, CardType.Diamonds));
    deck.add(new Card(CardAmount.Six, CardType.Diamonds));
    deck.add(new Card(CardAmount.Seven, CardType.Diamonds));
    deck.add(new Card(CardAmount.Eight, CardType.Diamonds));
    deck.add(new Card(CardAmount.Nine, CardType.Diamonds));
    deck.add(new Card(CardAmount.Ten, CardType.Diamonds));

    ArrayList<Card> falseDeck = new ArrayList<>();
    falseDeck.add(new Card(CardAmount.Three, CardType.Hearts));
    falseDeck.add(new Card(CardAmount.Four, CardType.Hearts));
    falseDeck.add(new Card(CardAmount.Five, CardType.Hearts));
    falseDeck.add(new Card(CardAmount.Six, CardType.Hearts));
    falseDeck.add(new Card(CardAmount.Seven, CardType.Hearts));
    falseDeck.add(new Card(CardAmount.Eight, CardType.Hearts));
    falseDeck.add(new Card(CardAmount.Nine, CardType.Hearts));
    falseDeck.add(new Card(CardAmount.Ten, CardType.Hearts));
    falseDeck.add(new Card(CardAmount.King, CardType.Diamonds));
    falseDeck.add(new Card(CardAmount.Queen, CardType.Diamonds));
    falseDeck.add(new Card(CardAmount.Ace, CardType.Diamonds));
    falseDeck.add(new Card(CardAmount.Jack, CardType.Diamonds));
    falseDeck.add(new Card(CardAmount.Two, CardType.Diamonds));
    falseDeck.add(new Card(CardAmount.Three, CardType.Diamonds));
    falseDeck.add(new Card(CardAmount.Four, CardType.Diamonds));
    falseDeck.add(new Card(CardAmount.Five, CardType.Diamonds));
    falseDeck.add(new Card(CardAmount.Six, CardType.Diamonds));
    falseDeck.add(new Card(CardAmount.Seven, CardType.Diamonds));
    falseDeck.add(new Card(CardAmount.Eight, CardType.Diamonds));
    falseDeck.add(new Card(CardAmount.Nine, CardType.Diamonds));
    falseDeck.add(new Card(CardAmount.Ten, CardType.Diamonds));

    assert (new Card(CardAmount.King, CardType.Spades).equals(new Card(CardAmount.King,
            CardType.Spades)));
    assertEquals(game1.getDeck().size(), deck.size());
    assert (deck.size() == 52);
    assertEquals(game1.getDeck(), deck);
    assertNotEquals(game1.getDeck(), falseDeck);
    assertNotEquals(deck, falseDeck);
    game1.startGame(game1.getDeck(),false, 7, 3);
    assertEquals(deck, game1.getDeck());
    game1.startGame(game1.getDeck(), true, 7, 3);
    assertNotSame(deck, game1.getDeck());
  }

  @Test
  public void testRemove() {
    game1.startGame(game1.getDeck(), false, 7, 3);
    game1.remove(6,0,6,1);
    assertNull(game1.getCardAt(6, 0));
    assertNull(game1.getCardAt(6, 1));

    game1.startGame(game1.getDeck(),false, 7, 3);
    assertEquals(new Card(CardAmount.King, CardType.Spades), game1.getCardAt(0,0));
    game1.remove(6, 5);
    assertNull(game1.getCardAt(6, 5));

  }

  @Test
  public void testRemoveUsingDraw() {
    ArrayList<Card> draw = new ArrayList<Card>();

    draw.add(new Card(CardAmount.Ace, CardType.Hearts));
    draw.add(new Card(CardAmount.Jack, CardType.Hearts));
    draw.add(new Card(CardAmount.Two, CardType.Hearts));

    ArrayList<Card> draw2 = new ArrayList<Card>();
    draw2.add(new Card(CardAmount.Three, CardType.Hearts));
    draw2.add(new Card(CardAmount.Jack, CardType.Hearts));
    draw2.add(new Card(CardAmount.Two, CardType.Hearts));

    game1.startGame(game1.getDeck(), false, 7, 3);
    assertEquals(draw, game1.getDrawCards());
    game1.removeUsingDraw(0, 6,6);
    assertEquals(draw2, game1.getDrawCards());
  }

  @Test
  public void testDiscardDraw() {
    ArrayList<Card> draw = new ArrayList<Card>();

    draw.add(new Card(CardAmount.Ace, CardType.Hearts));
    draw.add(new Card(CardAmount.Jack, CardType.Hearts));
    draw.add(new Card(CardAmount.Two, CardType.Hearts));

    game1.startGame(game1.getDeck(), false, 7, 3);
    assertEquals(draw, game1.getDrawCards());
    game1.discardDraw(0);
    draw.remove(0);
    draw.add(0, new Card(CardAmount.Three, CardType.Hearts));
    assert (draw.equals(game1.getDrawCards()));
  }

  @Test
  public void testGetNumOfRows() {
    game1.startGame(game1.getDeck(),false,7, 3);
    assertEquals(7,game1.getNumRows());
    game1.startGame(game1.getDeck(),false, 5, 3);
    assertEquals(5, game1.getNumRows());
  }

  @Test
  public void testGetNumDraw() {
    game1.startGame(game1.getDeck(), false,7,3);
    assertEquals(3, game1.getNumDraw());
  }

  @Test
  public void testGetRowWidth() {
    game1.startGame(game1.getDeck(), false, 7,3);
    assertEquals(1, game1.getRowWidth(0));
    assertEquals(2, game1.getRowWidth(1));
    assertEquals(5, game1.getRowWidth(4));
  }

  @Test
  public void testIsGameOver() {
    //game1.startGame(game1.getDeck(), false, 1, 3);
    //assertFalse(game1.isGameOver());
    //game1.remove(0,0);
    //assertTrue(game1.isGameOver());

    game1.startGame(game1.getDeck(), false, 9, 3);
    assertFalse(game1.isGameOver());
    game1.remove(8,3);
    assertFalse(game1.isGameOver());
    game1.remove(8, 2,8,8);
    game1.discardDraw(0);
    game1.discardDraw(1);
    game1.discardDraw(2);
    assertFalse(game1.isGameOver());
    game1.discardDraw(0);
    game1.discardDraw(1);
    game1.discardDraw(2);
    assertFalse(game1.isGameOver());
    game1.discardDraw(0);
    assertFalse(game1.isGameOver());


  }

  @Test
  public void testGetScore() {
    game1.startGame(game1.getDeck(), false, 7, 3);
    assertEquals(207, game1.getScore());
    game1.remove(6,5);
    assertEquals(194 ,game1.getScore());
  }

  @Test
  public void testGetCardAt() {
    game1.startGame(game1.getDeck(), false, 7, 3);
    assertEquals(13, game1.getCardAt(0, 0).name.getCardValue());
    //means King card because its 13 points
    assertEquals(12, game1.getCardAt(1, 0).name.getCardValue());
    //means Ace card because its 1 point

  }

  @Test
  public void testGetDrawCards() {
    ArrayList<Card> deck = new ArrayList<Card>();

    deck.add(new Card(CardAmount.Ace, CardType.Hearts));
    deck.add(new Card(CardAmount.Jack, CardType.Hearts));
    deck.add(new Card(CardAmount.Two, CardType.Hearts));

    game1.startGame(game1.getDeck(), false, 7, 3);
    assertEquals(deck,game1.getDrawCards());

  }


}