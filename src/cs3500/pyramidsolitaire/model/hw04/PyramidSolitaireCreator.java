package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.TriPeaks;

/**
 * A "Builder Class" which basically creates an instance of the type of class you need for the game
 * type. It contains an enum with each gameType, and creates the appropriate
 * class for each gameType.
 */
public class PyramidSolitaireCreator {

  /**
   * An Enum containing all Game Types.
   */
  public enum GameType { BASIC, RELAXED, TRIPEAKS } ;

  GameType type;

  /**
   * Creates an instance of the gameType based on what gameType is passed in.
   * @param type The type of enum that is passed in
   * @return An instance of the game class that you need.
   */
  public static PyramidSolitaireModel<Card> create(GameType type) {
    if (type.equals(GameType.BASIC)) {
      return new BasicPyramidSolitaire();
    } else if (type.equals(GameType.RELAXED)) {
      return new RelaxedPyramidSolitaire();
    } else if (type.equals(GameType.TRIPEAKS)) {
      return new TriPeaks();
    }
    return null;
  }
  
  
}



