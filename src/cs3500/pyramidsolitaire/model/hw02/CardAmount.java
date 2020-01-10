package cs3500.pyramidsolitaire.model.hw02;

/**
 * An Enum representing all cards and their values.
 */
public enum CardAmount {
  King(13), Queen(12), Jack(11), Ace(1), Two(2),
  Three(3), Four(4), Five(5), Six(6),
  Seven(7), Eight(8), Nine(9), Ten(10);

  private final int value;

  /**
   * The Value of the card.
   * @param value the value of the card
   */
  CardAmount(int value) {
    this.value = value;
  }

  /**
   * It returns back the value of that card.
   * @return An Int with the value of the card
   */
  public int getCardValue() {
    return this.value;
  }

}
