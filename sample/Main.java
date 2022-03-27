package sample;

import Serializacja.Serializacja;
import Trains.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("PKP");
            primaryStage.setScene(new Scene(root, 600, 600));
            primaryStage.show();


    }



    public static void main(String[] args) throws IOException {



        Train tain1 = new Train(TrainInfo.OnTime,"Pendolino",200,false, Type.passenger);
        Train tain2 = new Train(TrainInfo.OnTime,"Stary Rzęch",41200,true,Type.cargo);
        Train tain3 = new Train(TrainInfo.OnTime,"Lux torpeda",10,false,Type.passenger);
        Train tain4 = new Train(TrainInfo.Delayed,"EIC-200",34,false,Type.passenger);


        Travel a =new Travel(tain4,"Warsaw","Moscow",13,235.4);
        Travel b =new Travel(tain2,"Warsaw","Chicago",7,235.4);
        Travel c = new Travel(tain3,"Moscow","Warsaw",11,135.4);
        Travel d = new Travel(tain2,"Moscow","Chicago",2,135.4);
        Travel e = new Travel(tain4,"Moscow","Warsaw",22,135.4);
        Travel f = new Travel(tain1,"Warsaw","Moscow",12,135.4);
        Travel g = new Travel(tain4,"Warsaw","Moscow",9,135.4);
        Travel h = new Travel(tain2,"Chicago","Moscow",12,135.4);
        Travel i = new Travel(tain1,"Chicago","Moscow",10,175.4);


        List<Travel> travels = new ArrayList<Travel>();


        travels.add(a);
        travels.add(b);
        travels.add(c);
        travels.add(d);
        travels.add(e);
        travels.add(f);
        travels.add(g);
        travels.add(h);
        travels.add(i);

        Serializacja test = new Serializacja();
        test.writeToFile(travels);


        launch(args);
    }



}
