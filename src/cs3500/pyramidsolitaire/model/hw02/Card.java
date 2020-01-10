package cs3500.pyramidsolitaire.model.hw02;

/**
 * To represent a card class in the game.
 */
public class Card {
  public CardAmount name; //King
  public CardType type; //suit
  public boolean exposed;

  /**
   * When creating a card, if you already have the name,
   * type and the exposed then you create a card with those fields.
   * @param name The name of the card
   * @param type The type of the card
   * @param exposed The exposed boolean of that card, whether its exposed or not
   */
  public Card(CardAmount name, CardType type, boolean exposed) {
    this.name = name;
    this.type = type;
    this.exposed = exposed;
  }

  /**
   * When creating a card if you don't have whether or not the card should be exposed.
   * @param name The name of the card
   * @param type The type of the card
   */
  public Card(CardAmount name, CardType type) {
    this.name = name;
    this.type = type;
    this.exposed = false;
  }

  public int getValue() {
    return name.getCardValue();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Card)) {
      return false;
    }
    else {
      Card that = (Card)obj;
      return this.name == that.name && this.type == that.type;
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    if (type == CardType.Spades) {
      if (name.getCardValue() == 13) {
        return "K" + '♠';
      }
      if (name.getCardValue() == 12) {
        return "Q" + '♠';
      }
      if (name.getCardValue() == 11) {
        return "J" + '♠';
      }
      if (name.getCardValue() == 1) {
        return "A" + '♠';
      }
      return "" + name.getCardValue() + '♠';
    }
    if (type == CardType.Clubs) {
      if (name.getCardValue() == 13) {
        return "K" + '♣';
      }
      if (name.getCardValue() == 12) {
        return "Q" + '♣';
      }
      if (name.getCardValue() == 11) {
        return "J" + '♣';
      }
      if (name.getCardValue() == 1) {
        return "A" + '♣';
      }
      return "" + name.getCardValue() + '♣';
    }
    if (type == CardType.Hearts) {
      if (name.getCardValue() == 13) {
        return "K" + '♥';
      }
      if (name.getCardValue() == 12) {
        return "Q" + '♥';
      }
      if (name.getCardValue() == 11) {
        return "J" + '♥';
      }
      if (name.getCardValue() == 1) {
        return "A" + '♥';
      }
      return "" + name.getCardValue() + '♥';
    }
    if (type == CardType.Diamonds) {
      if (name.getCardValue() == 13) {
        return "K" + '♦';
      }
      if (name.getCardValue() == 12) {
        return "Q" + '♦';
      }
      if (name.getCardValue() == 11) {
        return "J" + '♦';
      }
      if (name.getCardValue() == 1) {
        return "A" + '♦';
      }
      return "" + name.getCardValue() + '♦';
    }
    else {
      return " ";
    }
  }

}
