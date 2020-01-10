package cs3500.pyramidsolitaire.view;

import java.io.IOException;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * To make everything visible in a game matter.
 */
public class PyramidSolitaireTextualView implements PyramidSolitaireView {

  private final PyramidSolitaireModel<?> model;
  Appendable out;

  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model) {
    this.model = model;
  }

  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model, Appendable out) {
    this.model = model;
    this.out = out;
  }

  @Override
  public String toString() {
    if (model.getNumRows() == -1) { //how to know if game hasn't started
      return "";
    }
    if (model.getScore() == 0 && model.isGameOver()) {
      return "You Win!";
    }
    if (model.getScore() != 0 && model.isGameOver()) { //if there are no moves left
      return "Game Over. Score: " + model.getScore();
    }
    else {
      String return2 = "";
      String gamePyramid2 = "";
      for (int i = 0; i < model.getNumRows(); i++) {
        String temp = "";
        for (int l = model.getNumRows() - i; l > 1; l--) {
          temp = temp + "  ";
        }
        for (int j = 0; j < model.getRowWidth(i); j++) {
          if (model.getCardAt(i,j) == null) {
            temp = temp + "    ";
          }
          else {
            String card2 = model.getCardAt(i, j).toString();
            if (model.getCardAt(i, j).toString().equals("10♠") ||
                    model.getCardAt(i, j).toString().equals("10♣") ||
                    model.getCardAt(i, j).toString().equals("10♥") ||
                    model.getCardAt(i, j).toString().equals("10♦")) {
              temp = temp + card2 + " ";
            }
            else {
              if (j == model.getRowWidth(i) - 1) {
                temp = temp + card2;
              }
              else {
                temp = temp + card2 + "  ";
              }
            }
          }
        }
        gamePyramid2 = temp + "\n";
        return2 = return2 + gamePyramid2;
      }
      if (model.getDrawCards().size() == 0) {
        return return2;
      }
      else {
        return2 = return2 + "Draw:";
        String return3 = "";
        for (int i = 0; i < model.getNumDraw(); i++) {
          if (i == model.getNumDraw() - 1) {
            if (model.getDrawCards().get(i) == null) {
              return3 = return3 + "    ";
            }
            else {
              return3 = return3 + " " + model.getDrawCards().get(i);
            }
          }
          else {
            return3 = return3 + " " + model.getDrawCards().get(i) + ",";
          }
        }
        return2 += return3;
      }
      return return2;
    }
  }

  @Override
  public void render() throws IOException {
    this.out.append(toString());
  }

}


