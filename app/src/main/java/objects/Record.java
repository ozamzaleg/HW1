package objects;

public class Record {
    private String name;
    private int score;
    private double latitude = 0;
    private double longitude = 0;

    public Record() {
    }

    public Record(String name, int score, double latitude, double longitude) {
        this.name = name;
        this.score = score;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public Record setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public Record setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }


    public String getName() {
        return name;
    }

    public Record setName(String name) {
        this.name = name;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Record setScore(int score) {
        this.score = score;
        return this;
    }

    @Override
    public String toString() {
        return "name:" + name +
                ",his score:" + score;
    }
}
