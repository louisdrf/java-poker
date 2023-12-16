package org.example;

enum Colour {
    SPADES,
    CLUBS,
    HEARTS,
    DIAMONDS,
}

enum Value {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13),
    ACE(14);

    private final int value;

    private Value(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

public class Card {
    int colour;
    int value;

    public Card(int colour, int value) {
        this.colour = colour;
        this.value = value;
    }

    public void display() {
        printValue();
        printColor();
    }

    public void printColor() {
        switch(this.colour) {
            case 0:
                System.out.println(" de pic"); break;
            case 1:
                System.out.println(" de trèfle"); break;
            case 2:
                System.out.println(" de coeur"); break;
            case 3:
                System.out.println(" de carreau"); break;
        }
    }

    public void printValue() {
        switch(this.value) {
            case 11:
                System.out.print("Valet"); break;
            case 12:
                System.out.print("Dame"); break;
            case 13:
                System.out.print("Roi"); break;
            case 14:
                System.out.print("As"); break;
            default:
                System.out.print(this.value); break;
        }
    }

    public int getValue() {
        return value;
    }
}
