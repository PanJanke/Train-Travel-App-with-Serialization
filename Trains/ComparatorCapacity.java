package Trains;

import java.util.Comparator;

public class ComparatorCapacity implements Comparator<Train> {
    @Override
    public int compare(Train a, Train b){
        int result = a.capacity-b.capacity;
        return (-1)*result;
    }

}