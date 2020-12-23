package objects;

import android.content.SharedPreferences;

import com.example.oz_tal_application_project.utils.Constans;

import java.util.ArrayList;

public class GameManger {
    private final int FOUR = 4;
    private final int ONE = 1;
    private int counterPlayer1 ;
    private int counterPlayer2 ;
    private ArrayList<Integer> idCards;


    public GameManger() {
       idCards = new ArrayList<Integer>();
        setCounterPlayer1(0);
        setCounterPlayer2(0);
    }

    public String compareValueOfCards(String card1,String card2){
    int valueCard1,valueCard2;
    valueCard1= (Integer.parseInt(card1.substring(card1.length() - 2)));
    valueCard2= (Integer.parseInt(card2.substring(card2.length() - 2)));
        if ((valueCard1 / FOUR + ONE) > (valueCard2 / FOUR + ONE)) {
            counterPlayer1++;
            return Constans.PLAYER1;
    }   else if ((valueCard1 / FOUR + ONE) < (valueCard2 / FOUR + ONE)) {
            counterPlayer2++;
            return Constans.PLAYER2;
        }
        return Constans.TIE;
    }

    public void addCards(int idCard) {
        idCards.add(idCard);
        }

        public void removeCards(int idCard) {
        idCards.remove(idCard);
        }

        public int getNumbersOfCards () {
            return idCards.size();
        }

        public int getCardById(int indexOfCards){
            return idCards.get(indexOfCards);
        }

        public ArrayList<Integer> getIdCards() {
            return idCards;
        }

        public void setIdCards(ArrayList<Integer> idCards) {
            this.idCards = idCards;
        }

        public int getCounterPlayer1() {
        return counterPlayer1;
       }

        public void setCounterPlayer1(int counterPlayer1) {
            this.counterPlayer1 = counterPlayer1;
       }

        public int getCounterPlayer2() {
            return counterPlayer2;
       }

        public void setCounterPlayer2(int counterPlayer2) {
            this.counterPlayer2 = counterPlayer2;
       }
}
