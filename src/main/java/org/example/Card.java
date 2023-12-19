package org.example;

public class Card {
    public int colour;
    public int value;

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
