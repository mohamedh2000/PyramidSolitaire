package cs3500.pyramidsolitaire;


import java.io.InputStreamReader;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireController;
import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;
import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.TriPeaks;

/**
 * Use this class in order to play the game. It takes in an array of arguments and reads the inputs,
 * and from there it checks which game it should go towards. If the user inserts an array of length
 * 1+ then it will use the next two inputs as the rows and numDraw while creating the
 * specified game.
 */
public final class PyramidSolitaire {

  /**
   * It's the main method that takes in an array of arguments and makes sure the right game is
   * started with the correct amount of rows and such.
   * @param args an array of arguments passed in by the server or user
   */
  public static void main(String[] args) {
    for (String s : args) {
      if (s.equals("basic")) {
        if (args.length > 1) {
          int rows = Integer.parseInt(args[1]);
          int drawCards = Integer.parseInt(args[2]);
          new PyramidSolitaireCreator();
          PyramidSolitaireController controller =
                  new PyramidSolitaireTextualController(new InputStreamReader(System.in),
                            System.out);
          controller.playGame(PyramidSolitaireCreator.create(
                  PyramidSolitaireCreator.GameType.BASIC),
                  new BasicPyramidSolitaire().getDeck(),false, rows,drawCards);
        }
        else {
          new PyramidSolitaireCreator();
          PyramidSolitaireController controller =
                  new PyramidSolitaireTextualController(new InputStreamReader(System.in),
                          System.out);
          controller.playGame(PyramidSolitaireCreator.create(
                  PyramidSolitaireCreator.GameType.BASIC),
                  new BasicPyramidSolitaire().getDeck(),false, 7,3);
        }
      }
      if (s.equals("relaxed")) {
        if (args.length > 1) {
          int rows = Integer.parseInt(args[1]);
          int drawCards = Integer.parseInt(args[2]);
          new PyramidSolitaireCreator();
          PyramidSolitaireController controller =
                  new PyramidSolitaireTextualController(new InputStreamReader(System.in),
                          System.out);
          controller.playGame(PyramidSolitaireCreator.create(
                  PyramidSolitaireCreator.GameType.RELAXED),
                  new RelaxedPyramidSolitaire().getDeck(), false, rows, drawCards);
        } else {
          new PyramidSolitaireCreator();
          PyramidSolitaireController controller =
                  new PyramidSolitaireTextualController(new InputStreamReader(System.in),
                          System.out);
          controller.playGame(PyramidSolitaireCreator.create(
                  PyramidSolitaireCreator.GameType.RELAXED),
                  new RelaxedPyramidSolitaire().getDeck(), false, 7, 3);
        }
      }
      if (s.equals("tripeaks")) {
        if (args.length > 1) {
          int rows = Integer.parseInt(args[1]);
          int drawCards = Integer.parseInt(args[2]);
          new PyramidSolitaireCreator();
          PyramidSolitaireController controller =
                  new PyramidSolitaireTextualController(new InputStreamReader(System.in),
                          System.out);
          controller.playGame(PyramidSolitaireCreator.create(
                  PyramidSolitaireCreator.GameType.TRIPEAKS),
                  new TriPeaks().getDeck(), false, rows, drawCards);
        } else {
          new PyramidSolitaireCreator();
          PyramidSolitaireController controller =
                  new PyramidSolitaireTextualController(new InputStreamReader(System.in),
                          System.out);
          controller.playGame(PyramidSolitaireCreator.create(
                  PyramidSolitaireCreator.GameType.TRIPEAKS),
                  new TriPeaks().getDeck(), false, 7, 3);
        }
      }
    }
  }
}
