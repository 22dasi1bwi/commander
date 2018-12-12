package model;

public enum Rank {

    ASSO(12, 11),
    DUE(2, 0),
    TRE(11, 10),
    QUATTRO(4, 0),
    CINQUE(5, 0),
    SEI(6, 0),
    SETTE(7, 0),
    DONNA(8, 2),
    CAVALLO(9, 3),
    RE(10, 4);

    private int value;

    private int points;

    Rank (int value, int points) {
        this.value = value;
        this.points = points;
    }

    public int getValue(){
        return value;
    }

    public int getPoints(){
        return points;
    }
}
