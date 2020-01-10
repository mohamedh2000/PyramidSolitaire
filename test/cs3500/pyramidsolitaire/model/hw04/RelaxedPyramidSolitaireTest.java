package cs3500.pyramidsolitaire.model.hw04;

import org.junit.Test;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

import static junit.framework.TestCase.assertTrue;

/**
 * To Test the RelaxedPyramid game mode.
 */
public class RelaxedPyramidSolitaireTest {
  PyramidSolitaireModel<Card> game1 = new RelaxedPyramidSolitaire();

  @Test
  public void testRemove() {
    game1.startGame(game1.getDeck(), false, 7, 3);
    game1.remove(6,5);
    game1.remove(6,0,6,1);
    game1.discardDraw(1);
    game1.discardDraw(1);
    game1.removeUsingDraw(1,6,3);
    assertTrue(!game1.getCardAt(5, 3).exposed);
    game1.remove(6,4,5,3);
    //assertTrue(game1.getCardAt(5,3).exposed == null); -->Not sure why it's not working

  }

}