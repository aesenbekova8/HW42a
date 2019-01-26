package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // write your code here
        DatabaseConnector db = new DatabaseConnector();
        ArrayList<City> cities = db.getCities();
        cities.add(new City(1, "Bishkek"));
        cities.add(new City(2, "Osh"));
        cities.add(new City(3, "Talas"));

        ArrayList<City> cities2 = db.getCities();
        cities2.add(new City(1, "Bishkek"));
        cities2.add(new City(4, "Batken"));
        cities2.add(new City(5, "Naryn"));

        for (City c : cities) {
                System.out.println(db.insertCity(c));
      }

        for (int i = 0; i < cities2.size(); i++){
            for (City c : cities2){
                if (c.id != cities.get(i).id){
                    db.insertCity(c);
                }
               else if (c.id == cities.get(i).id){
                    c.id = 10;
                    db.insertCity(c);
                    break;
                }
               }
        }
    }
}

class City{
    int id;
    String name;
    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
