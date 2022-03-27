package Trains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainStation {
    public String name;
    public int maxCapacity;
    public List<Train> Trains;

    public  TrainStation(String name,int maxCapacity){
        this.name=name;
        this.maxCapacity=maxCapacity;
        this.Trains=new ArrayList<Train>();
    }

    void add(TrainInfo info, String name, int capacity, boolean damage,Type type){
        if(this.Trains.size()<this.maxCapacity){
            Train object = new Train(info,name,capacity,damage,type);
            this.Trains.add(object);}
        else
            System.err.println("Maximum capacity exceeded");
    }

    void show(){
        for(Train train: Trains){
            train.show();
        }
        System.out.println("##################################");
    }

    void sort(){
        Collections.sort(this.Trains);
    }

    public void sortCapacity(){
        Collections.sort(this.Trains,new ComparatorCapacity());
    }

    void changeTrainInfo(String name,TrainInfo info){
        for(Train train: Trains)
            if (train.name.equals(name)) {
                train.changeTrainInfo(info);
                return;
            }

        System.err.println("Nie znaleziono pociągu o podanej nazwie \n");

    }

    void changeDamage(String name,boolean damage){
        for(Train train: Trains)
            if (train.name.equals(name)) {
                train.changeDamage(damage);
                return;
            }

        System.err.println("Nie znaleziono pociągu o podanej nazwie \n");

    }

    List <Train> findByName(String name){
        List <Train> list = new ArrayList<>();
        for(Train train: Trains)
            if (train.name.equals(name)) {
                list.add(train);
            }
        if(list.size()==0)
            System.err.println("Nie znaleziono pociągu o podanej nazwie");
        return list;
    }

    List <Train> findByPartName(String name){
        List <Train> list = new ArrayList<>();
        for(Train train: Trains)
            if (train.name.contains(name)==true) {
                list.add(train);
            }
        if(list.size()==0)
            System.err.println("Nie znaleziono pociągu zawierajacaego podany ciag znakow");
        return list;
    }

    Train maxCapacity(){
        return Collections.max(this.Trains);
    }

    public void changeName(String name){
        this.name=name;
    }

    public void changeMaxCapacity(int maxCapacity){
        this.maxCapacity = maxCapacity;
    }

}