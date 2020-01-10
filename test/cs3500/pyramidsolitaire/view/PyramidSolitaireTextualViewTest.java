package cs3500.pyramidsolitaire.view;

import org.junit.Test;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.TriPeaks;

import static org.junit.Assert.assertEquals;

/**
 * Class to test the view.
 */
public class PyramidSolitaireTextualViewTest {
  private PyramidSolitaireModel<Card> game1 = new TriPeaks();
  private PyramidSolitaireView view1 = new PyramidSolitaireTextualView(game1);

  @Test
  public void testToString() {
    assertEquals("", view1.toString());
    game1.startGame(game1.getDeck(), false, 7, 3);
    System.out.println(view1.toString());

  }

}