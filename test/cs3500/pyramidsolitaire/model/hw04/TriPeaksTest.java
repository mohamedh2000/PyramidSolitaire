package cs3500.pyramidsolitaire.model.hw04;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

import static junit.framework.TestCase.assertTrue;

/**
 * To Test the TriPeaks game mode.
 */
public class TriPeaksTest {
  PyramidSolitaireModel<Card> game1 = new TriPeaks();

  @Test
  public void testGetDeck() {
    List<Card> deck = game1.getDeck();
    assertTrue(deck.size() == 104);
  }




}