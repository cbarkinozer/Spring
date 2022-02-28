package entity;

import java.math.BigDecimal;
import java.util.ArrayList;

public class House extends BaseHouse {

    private static ArrayList<House> houseList= new ArrayList<>();

    //Default constructor
    public House(){
        super(BigDecimal.valueOf(497500),145,2,1);
        addToHouseList(this);
    }

    public House(BigDecimal price, int squareMeters, int roomCount, int livingRoomCount){
        super(price, squareMeters, roomCount, livingRoomCount);
        addToHouseList(this);
    }

    public static ArrayList<House> getHouseList(){
        return houseList;
    }
    public static void addToHouseList(House house){
        houseList.add(house);
    }
}