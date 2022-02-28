package entity;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Villa extends BaseHouse {

    private static ArrayList<Villa> villaList= new ArrayList<>();

    //Default constructor
    public Villa() {
        super(BigDecimal.valueOf(850000),185,3,1);
        addToVillaList(this);
    }

    public Villa(BigDecimal price, int squareMeters, int roomCount, int livingRoomCount) {
        super(price, squareMeters, roomCount, livingRoomCount);
        addToVillaList(this);
    }
    public static ArrayList<Villa> getVillaList(){
        return villaList;
    }
    public static void addToVillaList(Villa villa){
        villaList.add(villa);
    }
}