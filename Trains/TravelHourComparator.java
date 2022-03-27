package Trains;

import java.util.Comparator;

public class TravelHourComparator implements Comparator<Travel> {
    @Override
    public int compare(Travel o1, Travel o2) {
        return o1.getHour1()-o2.getHour1();
    }
}
