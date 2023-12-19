package org.example;

public class Game {

    Hand player1;

    Hand player2;
    public Hand winner;

    public Game(Hand player1, Hand player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = null;
    }

    public void checkWinner() {
        Hand p1 = this.player1;
        Hand p2 = this.player2;

        if(p1.current_combinaison > p2.current_combinaison) {
            this.winner = p1;
            return;
        }
        if(p2.current_combinaison > p1.current_combinaison) {
            this.winner = p2;
            return;
        }

        if(p1.current_combinaison == p2.current_combinaison)
        {
            if(p1.combinaison_second_high_card_value == -1)
            {
                if(p1.combinaison_high_card_value > p2.combinaison_high_card_value) this.winner = p1;
                else if(p2.combinaison_high_card_value > p1.combinaison_high_card_value) this.winner = p2;
                else if(this.player1.high_card_value > this.player2.high_card_value) this.winner = p1;
                else if(this.player1.high_card_value < this.player2.high_card_value) this.winner = p2;
            }
            else { // si les deux ont double paire ou full on regarde les deux cartes composant leur combinaison
                if(p1.combinaison_high_card_value > p2.combinaison_high_card_value) this.winner = p1;
                else if(p2.combinaison_high_card_value > p1.combinaison_high_card_value) this.winner = p2;

                else {
                    if(p1.combinaison_second_high_card_value > p2.combinaison_second_high_card_value) this.winner = p1;
                    else if(p1.combinaison_second_high_card_value < p2.combinaison_second_high_card_value) this.winner = p2;
                    else if(this.player1.high_card_value > this.player2.high_card_value) this.winner = p1;
                    else if(this.player1.high_card_value < this.player2.high_card_value) this.winner = p2;
                }
            }
        }

    }

    /*
    public void checkWinner() {
        Hand p1 = this.player1;
        Hand p2 = this.player2;

        // Comparaison des combinaisons principales
        if(p1.current_combinaison > p2.current_combinaison) {
            this.winner = p1;
            return;
        }
        if(p2.current_combinaison > p1.current_combinaison) {
            this.winner = p2;
            return;
        }

        // Même combinaison, gestion des cartes secondaires
        if(p1.current_combinaison == p2.current_combinaison) {
            // Comparaison des cartes pour des combinaisons complexes (Full House, Double Paire, etc.)
            if(p1.combinaison_second_high_card_value != -1) {
                // Logique existante pour les combinaisons avec second_high_card_value
                // ...
            } else {
                // Comparaison des cartes une par une pour des combinaisons simples (Flush, Haute Carte, etc.)
                for (int i = p1.cards.size() - 1; i >= 0; i--) {
                    if (p1.cards.get(i).value > p2.cards.get(i).value) {
                        this.winner = p1;
                        return;
                    } else if (p2.cards.get(i).value > p1.cards.get(i).value) {
                        this.winner = p2;
                        return;
                    }
                }
                // Toutes les cartes sont égales, match nul
                this.winner = null; // ou toute autre représentation d'un match nul
            }
        }
    }*/


    public void displayWinner() {

        if(this.winner == null) {
            System.out.println("Match nul");
            return;
        }

        System.out.print(this.winner.owner + " gagne avec ");
        this.winner.displayCombinaison();
    }
}
