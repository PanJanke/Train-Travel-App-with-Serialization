package Trains;

import java.io.Serializable;

public class Train implements Comparable <Train>, Serializable {

    public transient TrainInfo info;
    public String name;
    public int capacity;
    public boolean damaged;
    public Type type;

    public Train(TrainInfo info, String name, int capacity, boolean damaged, Type type){
        this.info=info;
        this.name=name;
        this.capacity=capacity;
        this.damaged=damaged;
        this.type=type;
    }

    public TrainInfo getInfo() {
        return info;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }


    void show(){
        System.out.println("Name: " + this.name);
        System.out.print("Capacity: " + this.capacity);

        if(this.type == Type.cargo)
            System.out.println(" Tons");
        else
            System.out.println(" People");


        System.out.println("Damaged: " + this.damaged);
        System.out.println("Type: " + this.type +"\n");
    }

    public void changeTrainInfo(TrainInfo info){
        this.info=info;
    }

    public  void changeName(String name){
        this.name=name;
    }

    public void changeDamage(boolean damage){
        this.damaged=damage;
    }
    public void changeCapacity(int capacity){
        this.capacity=capacity;
    }
    public void changeType(Type type){
        this.type=type;
    }

    public void buyTicket(){
        this.capacity=this.capacity-1;
    }

    public void deleteTicket(){
        this.capacity=this.capacity+1;
    }

    public void setInfo(TrainInfo info) {
        this.info = info;
    }

    @Override
    public int compareTo(Train a){
        int result=name.compareTo(a.name);
        if(result == 0){
            return this.capacity-a.capacity;
        }
        return result;
    }



}