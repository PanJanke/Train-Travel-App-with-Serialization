
import Serializacja.Serializacja;
import Trains.*;
import Trains.Train;
import Trains.Travel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;



public class Controller implements Initializable,Serializable {

    private final ObservableList<Travel> dataList = FXCollections.observableArrayList();

    List<Travel> travels;
    ArrayList<Travel> loadedTravels = new ArrayList<Travel>();

    public ObservableList<Travel> ShoppingCart =FXCollections.observableArrayList();



  ObservableList <String> travelInfo = FXCollections.observableArrayList();


  ObservableList <String> prepare(ObservableList<Travel> dataList){
      ObservableList <String> travelInfo = FXCollections.observableArrayList();

      for(int i=0;i<dataList.size();i++){
          travelInfo.add("Train:"+ dataList.get(i).getTrain().getName()+"   From: "+dataList.get(i).getStation1()+"   To: "+dataList.get(i).getStation2()+ "   Hour: "+dataList.get(i).getHour1()+"   Price: " +dataList.get(i).getPrice());
      }
    return travelInfo;
  }

    ObservableList <Travel> stations(ObservableList<Travel> dataList,String from,String to) {
      
        ObservableList<Travel> travels = FXCollections.observableArrayList();
        for (int i = 0; i < dataList.size(); i++)
            if (from.equals(dataList.get(i).getStation1()) && to.equals(dataList.get(i).getStation2()))
                travels.add(dataList.get(i));
            return travels;
    }

    ObservableList <Travel> time(ObservableList<Travel> dataList, int a, int b) {
      
        ObservableList<Travel> travels = FXCollections.observableArrayList();
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).getHour1() >= a && dataList.get(i).getHour1() <= b)
                travels.add(dataList.get(i));


        }
        return travels;
    }

    
    
    @FXML
    private CheckBox checkboxtime;

    @FXML
    private Label TimeInfo;

    @FXML
    private Button SaveButton;

    @FXML
    private Label trainInfo;

    @FXML
    private TextField TimeTextFile;

    @FXML
    private ChoiceBox<String> FromCheckBox = new ChoiceBox<String>();

    @FXML
    private ChoiceBox<String> ToCheckBox = new ChoiceBox<String>();

    private String[] Stations = {"------","Warsaw","Chicago","Moscow"};

    @FXML
    private ListView<String> myListView;

    @FXML
    private Button ShowButton;




    @FXML
    private ListView<String> buyedTickets;

    @FXML
    private Button BuyButton;

    @FXML
    private Label Buyinfo;

    int hold;

    @FXML
    void BuyTicket(MouseEvent event) {
        int a = myListView.getSelectionModel().getSelectedIndex();
        hold=a;
        Travel ticket= travels.get(a);
        if(ticket.getTrain().getCapacity()>0){
            if(ticket.getTrain().damaged==false) {
                ShoppingCart.add(ticket);
                travels.get(a).getTrain().buyTicket();
                Buyinfo.setText("Zakupiono pomyslnie");
            }
            else
                Buyinfo.setText("Pociag uszkodzony");
        }
        else
            Buyinfo.setText("Brak Miejsc");

       travelInfo =prepare(ShoppingCart);
       buyedTickets.setItems(travelInfo);
    }


    @FXML
    void DisplayWindow(MouseEvent event) {
        int a =myListView.getSelectionModel().getSelectedIndex();
        Train current = travels.get(a).getTrain();
        trainInfo.setText("Name: "+ current.getName() +" Train Info: "+current.getInfo() +" Damage: "+current.damaged+" Type:"+current.getType()+" Capacity:" + current.getCapacity());

    }

    @FXML
    void showList(ActionEvent event) {

        ObservableList<Travel> stationList = dataList;

        String from = FromCheckBox.getValue();
        String to = ToCheckBox.getValue();

        boolean time=false;
        if(checkboxtime.isSelected())
            time=true;
        else
            TimeInfo.setText( 0 + "-" + 23);

        if(from.isEmpty() || to.isEmpty())
            return;
        if(!from.equals("------") && !to.equals("------")){
            stationList=stations(stationList,from,to);
        }

        if(time == true){
            String hour = TimeTextFile.getText();
            int intHour = setTime(hour);
            if(intHour != -1){
                int a,b;
                a=intHour-1;
                b=intHour+1;
                TimeInfo.setText(a + " - "+b);
                stationList= time(stationList,a,b);

            }

        }




        //SORTOWANIE
        if(time==false)
            Collections.sort(stationList);
        else
            Collections.sort(stationList,new TravelHourComparator());

        travels =stationList;
        travelInfo =prepare(stationList);
        myListView.setItems(travelInfo);
    }

    @FXML
    private Button Resignebutton;

    @FXML
    void ticketResigned(MouseEvent event) {

        int a = buyedTickets.getSelectionModel().getSelectedIndex();
        ShoppingCart.remove(a);
        Buyinfo.setText("Zrezygnowano z biletu");
        travels.get(hold).getTrain().deleteTicket();
        travelInfo =prepare(ShoppingCart);
        buyedTickets.setItems(travelInfo);
    }


    @FXML
    void SaveTickets(MouseEvent event) throws IOException {

        Serializacja test = new Serializacja();
        test.writeToFile2(ShoppingCart);
        Buyinfo.setText("Pomyślnie zapisano: "+ ShoppingCart.size()+" biletow");


    }

    public int setTime(String capacity){
        try {
            int n = Integer.parseInt(capacity);
            if (n >= 0 && n <= 23) {
                return n;
            }
        }
        catch(NumberFormatException e){
            TimeInfo.setText("Nie wczytano godziny");

        }
        TimeInfo.setText("Nie wczytano godziny");
        return -1;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            FromCheckBox.getItems().addAll(Stations);
            ToCheckBox.getItems().addAll(Stations);




     // Wczytywanie Wszystkich Mozliwych przejazdow
        Serializacja test = new Serializacja();

        try {
            loadedTravels=test.readAllTravels();

        }
        catch (IOException e) {
            System.out.println("Błąd przy otwieraniu pliku ");
        }



       for(int i=0;i< loadedTravels.size();i++)
           dataList.add(loadedTravels.get(i));

       //Wczytywanie Koszyka
        try {
            loadedTravels.clear();
            loadedTravels= test.readShoppingCart();


        } catch (IOException e) {
            System.out.println("Błąd przy otwieraniu pliku ");
        }

        ShoppingCart.clear();
        for(int i=0;i< loadedTravels.size();i++)
            ShoppingCart.add(loadedTravels.get(i));

        travelInfo =prepare(ShoppingCart);
        buyedTickets.setItems(travelInfo);

    }
}
