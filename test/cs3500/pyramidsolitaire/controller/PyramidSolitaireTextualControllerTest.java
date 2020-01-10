package cs3500.pyramidsolitaire.controller;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.TriPeaks;

import static org.junit.Assert.assertEquals;

/**
 * To have a method to test my Controller.
 */
public class PyramidSolitaireTextualControllerTest {
  private PyramidSolitaireModel model = new TriPeaks();

  @Test
  public void playGame() throws IOException {
    StringBuilder out = new StringBuilder();
    Reader in = new StringReader("rm1 6 2");
    PyramidSolitaireController controller = new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, model.getDeck(), false, 7, 3);
    assertEquals("7", out.toString());
  }


}