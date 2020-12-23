package objects;

import android.util.Log;

import java.util.ArrayList;

public class TopTen {
    private ArrayList<Record> topTenPlayer;


    public TopTen() {
        topTenPlayer = new ArrayList<Record>();
    }

    public TopTen(ArrayList<Record> ranking) {
        this.topTenPlayer = ranking;
    }


    public ArrayList<Record> getTopTenPlayer() {
        return topTenPlayer;
    }

    public TopTen setTopTenPlayer(ArrayList<Record> topTenPlayer) {
        this.topTenPlayer = topTenPlayer;
        return this;
    }

    public void topTenRecords(Record record) {
        boolean inTopTen=false;
        if (topTenPlayer.isEmpty()) {
            topTenPlayer.add(record);
        }
        else {
            for (int i = 0;!inTopTen&&i < topTenPlayer.size(); i++) {
                if (topTenPlayer.get(i).getScore() < record.getScore()) {
                    topTenPlayer.add(i, record);
                    inTopTen=true;
                }
            }
            if (topTenPlayer.size() > 10) {
                topTenPlayer.remove(topTenPlayer.size()-1);
            } else if(!inTopTen) {
                topTenPlayer.add(record);
            }
        }
    }
}

