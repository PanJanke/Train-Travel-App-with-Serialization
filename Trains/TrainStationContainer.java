package Trains;

import java.util.*;

public class TrainStationContainer {

    Map<String,TrainStation> stations;

    TrainStationContainer(){
        this.stations= new HashMap<String,TrainStation>();
    }

    void create(String name,int capacity){
        TrainStation object = new TrainStation(name,capacity);
        stations.put(name,object);
    }

    void add(TrainStation object){

        stations.put(object.name, object);
    }

    List <TrainStation> emptyStations(){

        List <TrainStation> list = new ArrayList<TrainStation>();
        for(Map.Entry<String,TrainStation> entry: stations.entrySet()){
            TrainStation object= entry.getValue();
            if(object.Trains.size()==0)
                list.add(object);
        }
        return list;
    }

    void show(){
        for(Map.Entry<String,TrainStation> entry: stations.entrySet()){
            TrainStation object= entry.getValue();
            System.out.println(object.name + " - " + object.Trains.size() + "/" + object.maxCapacity);
        }

    }


}