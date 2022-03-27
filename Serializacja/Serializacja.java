package Serializacja;

import Trains.TrainInfo;
import Trains.Travel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializacja {





    public static void writeToFile(List<Travel> t) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("BazaPrzejazdow.csv"));
        for (Travel travel : t)
            objectOutputStream.writeObject(travel);
    }

    public static void writeToFile2(List<Travel> t) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("BazaBiletow.csv"));
        for (Travel travel : t)
            objectOutputStream.writeObject(travel);
    }



    public ArrayList<Travel> readAllTravels() throws IOException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("BazaPrzejazdow.csv"));
        ArrayList<Travel> readedTravels = new ArrayList<Travel>();

        int counter=0;
        while (true) {
            try {
                Travel readObject = (Travel) objectInputStream.readObject();
                readedTravels.add(readObject);
                readObject.getTrain().setInfo(TrainInfo.OnTime);
                counter++;
            } catch (Exception e) {
                if (e instanceof java.io.EOFException)
                    System.out.println("Wczytano wszystkie mozliwe przejazdy:  "+counter);

                break;
            }

        }
        return readedTravels;
    }

    public ArrayList<Travel> readShoppingCart() throws IOException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("BazaBiletow.csv"));
        ArrayList<Travel> readedTravels = new ArrayList<Travel>();

        int counter=0;
        while (true) {
            try {
                Travel readObject = (Travel) objectInputStream.readObject();
                readedTravels.add(readObject);
                counter++;
            } catch (Exception e) {
                if (e instanceof java.io.EOFException)
                    System.out.println("Wczytano koszyk:  "+counter);

                break;
            }

        }
        return readedTravels;
    }


}
