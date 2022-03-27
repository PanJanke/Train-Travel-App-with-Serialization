package Trains;

import java.io.Serializable;

public class Travel implements Comparable<Travel>, Serializable{

    Train train;
    String station1;
    String station2;
    int hour1;
    double price;

    public Travel(Train train, String station1, String station2, int hour1, double price){
        this.train=train;
        this.station1=station1;
        this.station2=station2;
        this.hour1=hour1;
        this.price=price;
    }


    public String getStation1() {
        return station1;
    }

    public Train getTrain() {
        return train;
    }

    public String getStation2() {
        return station2;
    }

    public int getHour1() {
        return hour1;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(Travel o) {
        int result = this.train.name.compareTo(o.train.name);
        return result;
    }
}
