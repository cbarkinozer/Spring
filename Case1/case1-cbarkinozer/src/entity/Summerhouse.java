package entity;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Summerhouse extends BaseHouse {


    private static ArrayList<Summerhouse> summerhouseList= new ArrayList<>();

    //Default constructor
    public Summerhouse() {
        super(BigDecimal.valueOf(3200000),220,4,1);
        addToSummerhouseList(this);
    }

    public Summerhouse(BigDecimal price, int squareMeters, int roomCount, int livingRoomCount) {
        super(price, squareMeters, roomCount, livingRoomCount);
        addToSummerhouseList(this);
    }
    public static ArrayList<Summerhouse> getSummerhouseList(){
        return summerhouseList;
    }
    public static void addToSummerhouseList(Summerhouse summerhouse){
        summerhouseList.add(summerhouse);
    }
}