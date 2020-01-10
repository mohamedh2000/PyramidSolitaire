package cs3500.pyramidsolitaire.controller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import cs3500.pyramidsolitaire.view.PyramidSolitaireView;

/**
 * To Provide a way for a game to be played using both the model and the view.
 */
public class PyramidSolitaireTextualController implements PyramidSolitaireController {
  private Readable rd;
  private Appendable ap;

  /**
   * A constructor that takes in a readable and appendable.
   * @param rd Readable/input String we'll be adding
   * @param ap What the view will be showing
   * @throws IllegalArgumentException if either rd or ap are null
   */
  public PyramidSolitaireTextualController(Readable rd, Appendable ap)
          throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Either Readable or Appendable are Null!");
    }
    this.rd = rd;
    this.ap = ap;
  }

  /**
   * The primary method for beginning and playing a game.
   *
   * @param model   The game of solitaire to be played
   * @param deck    The deck of cards to be used
   * @param shuffle Whether to shuffle the deck or not
   * @param numRows How many rows should be in the pyramid
   * @param numDraw How many draw cards should be visible
   */
  @Override
  public <K> void playGame(PyramidSolitaireModel<K> model, List<K> deck,
                           boolean shuffle, int numRows, int numDraw) {
    PyramidSolitaireView view = new PyramidSolitaireTextualView(model,ap);

    if (model == null) {
      throw new IllegalArgumentException("Model is Null!");
    }
    else {
      try {
        Scanner sc = new Scanner(rd);
        try {
          model.startGame(deck, shuffle, numRows, numDraw);
          view.render();
          ap.append("\n");
          String nextCommand = sc.next();
          while (!nextCommand.isEmpty()) {

            if (nextCommand.equals("rm1")) {
              int row = 0;
              int card = 0;

              if (sc.hasNext()) {
                if (sc.hasNextInt()) {
                  row = sc.nextInt();
                  if (sc.hasNext()) {
                    if (sc.hasNextInt()) {
                      card = sc.nextInt();
                    }
                  }
                } else if (sc.hasNextLine()) {
                  String checkGang = sc.next();
                  if (checkGang.equals("q") || checkGang.equals("Q")) {
                    nextCommand = "q";
                    continue;
                  }
                }
              }

              try {
                model.remove(row - 1, card - 1);
                if (model.isGameOver()) {
                  doIfGameOver(model, ap);
                  sc.close();
                  break;
                } else {
                  view.render();
                  ap.append("\n" + "score: ").append(String.valueOf(model.getScore())).append("\n");
                  ap.append("\n");
                  if (sc.hasNext()) {
                    nextCommand = sc.next();
                  } else {
                    break;
                  }
                }
              } catch (IllegalArgumentException d) {
                ap.append("Invalid move. Play again. Card is not a king/coordinates aren't valid!");
                ap.append("\n");
                if (sc.hasNext()) {
                  nextCommand = sc.next();
                  continue;
                } else {
                  break;
                }
              } catch (IllegalStateException d) {
                ap.append("Invalid move. Play again. Game has not started yet!");
                break;
              }

            }

            if (nextCommand.equals("rm2")) {
              int row1 = 0;
              int card1 = 0;
              int row2 = 0;
              int card2 = 0;

              if (sc.hasNext()) {
                if (sc.hasNextInt()) {
                  row1 = sc.nextInt();
                  if (sc.hasNext()) {
                    if (sc.hasNextInt()) {
                      card1 = sc.nextInt();
                    }
                    if (sc.hasNext()) {
                      if (sc.hasNextInt()) {
                        row2 = sc.nextInt();
                      }
                      if (sc.hasNext()) {
                        if (sc.hasNextInt()) {
                          card2 = sc.nextInt();
                        }
                      }
                    }
                  }
                } else if (sc.hasNextLine()) {
                  String checkGang = sc.next();
                  if (checkGang.equals("q") || checkGang.equals("Q")) {
                    nextCommand = "q";
                    continue;
                  }
                }
              }

              try {
                model.remove(row1 - 1, card1 - 1, row2 - 1, card2 - 1);
                if (model.isGameOver()) {
                  doIfGameOver(model, ap);
                  sc.close();
                  break;
                } else {
                  view.render();
                  ap.append("\n" + "score: ").append(String.valueOf(model.getScore())).append("\n");
                  ap.append("\n");

                  if (sc.hasNext()) {
                    nextCommand = sc.next();
                    continue;
                  } else {
                    break;
                  }
                }
              } catch (IllegalArgumentException d) {
                ap.append("Invalid move. Play again. "
                        + "Cards don't add up to 13/Coordinates are Invalid");
                ap.append("\n");
                if (sc.hasNext()) {
                  nextCommand = sc.next();
                } else {
                  break;
                }
              } catch (IllegalStateException d) {
                ap.append("Invalid move. Play again. Game has not yet Started!");
                break;
              }

            }

            if (nextCommand.equals("rmwd")) {
              int drawIndex = 0;
              int row = 0;
              int card = 0;

              if (sc.hasNext()) {
                if (sc.hasNextInt()) {
                  drawIndex = sc.nextInt();
                  if (sc.hasNext()) {
                    if (sc.hasNextInt()) {
                      row = sc.nextInt();
                      if (sc.hasNext()) {
                        if (sc.hasNextInt()) {
                          card = sc.nextInt();
                        }
                      }
                    }
                  }
                } else if (sc.hasNextLine()) {
                  String checkGang = sc.next();
                  if (checkGang.equals("q") || checkGang.equals("Q")) {
                    nextCommand = "q";
                    continue;
                  }
                }
              }

              try {
                model.removeUsingDraw(drawIndex - 1, row - 1, card - 1);
                if (model.isGameOver()) {
                  doIfGameOver(model, ap);
                  sc.close();
                  break;
                } else {
                  view.render();
                  ap.append("\n" + "score: ").append(String.valueOf(model.getScore())).append("\n");
                  ap.append("\n");
                  if (sc.hasNext()) {
                    nextCommand = sc.next();
                    continue;
                  } else {
                    break;
                  }
                }
              } catch (IllegalArgumentException d) {
                ap.append("Invalid move. Play again. "
                        + "Cards don't add up to 13/Coordinates are Invalid");
                ap.append("\n");
                if (sc.hasNext()) {
                  nextCommand = sc.next();
                  continue;
                } else {
                  break;
                }
              } catch (IllegalStateException d) {
                ap.append("Invalid move. Play again. Game has not yet Started!");
                break;
              }

            }

            if (nextCommand.equals("dd")) {
              int drawIndex = 0;

              if (sc.hasNext()) {
                if (sc.hasNextInt()) {
                  drawIndex = sc.nextInt();
                } else if (sc.hasNextLine()) {
                  String checkGang = sc.next();
                  if (checkGang.equals("q") || checkGang.equals("Q")) {
                    nextCommand = "q";
                    continue;
                  }
                }
              }

              //can't do dd multiple times
              try {
                model.discardDraw(drawIndex - 1);
                if (model.isGameOver()) {
                  doIfGameOver(model, ap);
                  sc.close();
                  break;
                } else {
                  view.render();
                  ap.append("\n" + "score: ").append(String.valueOf(model.getScore())).append("\n");
                  ap.append("\n");
                  if (sc.hasNext()) {
                    nextCommand = sc.next();
                    continue;
                  } else {
                    break;
                  }
                }
              } catch (IllegalArgumentException d) {
                ap.append("Invalid move. Play again. "
                        + "Coordinates are Invalid");
                ap.append("\n");
                if (sc.hasNext()) {
                  nextCommand = sc.next();
                  continue;
                } else {
                  break;
                }
              } catch (IllegalStateException d) {
                ap.append("Invalid move. Play again. Game has not yet Started!");
                break;
              }
            }

            if (nextCommand.equals("q") || nextCommand.equals("Q")) {
              ap.append("Game quit!" + "\n");
              ap.append("State of game when quit:" + "\n");
              view.render();
              ap.append("\n");
              ap.append("Score: ").append(String.valueOf(model.getScore())).append("\n");
              break;
            } else {
              ap.append("Invalid move. Play again. "
                      + "Coordinates are Invalid");
              ap.append("\n");
              if (sc.hasNext()) {
                nextCommand = sc.next();
              } else {
                break;
              }
            }
          }
          sc.close();
        } catch (IOException e) {
          throw new IllegalStateException("Can't read!");
        }
        catch (IllegalArgumentException d) {
          throw new IllegalArgumentException("Inputs are incorrect!");
        }
      }
      catch (IllegalStateException e) {
        throw new IllegalStateException("Can't Read!");
      }
    }
  }

  private void doIfGameOver(PyramidSolitaireModel model, Appendable ap) throws IOException {
    if (model.getScore() == 0) {
      ap.append("\n" + "You win!");
    }
    else {
      ap.append("\n" + "Game over. Score: ").append(String.valueOf(model.getScore()));
    }
  }




}

