package service;

import entity.BaseHouse;
import entity.House;
import entity.Summerhouse;
import entity.Villa;

import java.math.BigDecimal;
import java.util.ArrayList;

public class HouseService{

    public static BigDecimal sumHousePrices(){
        BigDecimal sumHousePrices= new BigDecimal(0);
        for(House house: House.getHouseList()){
            sumHousePrices = sumHousePrices.add(house.getPrice());
        }
        return sumHousePrices;
    }

    public static BigDecimal sumVillaPrices(){
        BigDecimal sumVillaPrices= new BigDecimal(0);
        for(Villa villa: Villa.getVillaList()){
            sumVillaPrices = sumVillaPrices.add(villa.getPrice());
        }
        return sumVillaPrices;
    }

    public static BigDecimal sumSummerhousePrices(){
        BigDecimal sumSummerhousePrices= new BigDecimal(0);
        for(Summerhouse summerhouse: Summerhouse.getSummerhouseList()){
            sumSummerhousePrices = sumSummerhousePrices.add(summerhouse.getPrice());
        }
        return sumSummerhousePrices;
    }

    public static BigDecimal sumAllPrices(){
        return sumHousePrices().add(sumVillaPrices()).add(sumSummerhousePrices());
    }

    public static double averageHouseSquareMeter(){
        double sumHouseSquareMeter= 0;
        for(House house: House.getHouseList()){
            sumHouseSquareMeter+=house.getSquareMeters();
        }
        double numberOfElements= House.getHouseList().size();
        return sumHouseSquareMeter/numberOfElements;
    }

    public static double averageVillaSquareMeter(){
        double sumVillaSquareMeter=0;
        for(Villa villa: Villa.getVillaList()){
            sumVillaSquareMeter+= villa.getSquareMeters();
        }
        double numberOfElements = Villa.getVillaList().size();
        return sumVillaSquareMeter/numberOfElements;
    }

    public static double averageSummerhouseSquareMeter(){
        double sumSummerhouseSquareMeter=0;
        for(Summerhouse summerhouse: Summerhouse.getSummerhouseList()){
            sumSummerhouseSquareMeter+= summerhouse.getSquareMeters();
        }
        double numberOfElements = Summerhouse.getSummerhouseList().size();
        return sumSummerhouseSquareMeter/numberOfElements;
    }

    public static double averageAllSquareMeter(){
        return (averageHouseSquareMeter()+averageVillaSquareMeter()+averageHouseSquareMeter())/3;
    }

    public static ArrayList<BaseHouse> filterAllByRoomCount(int roomCount,int livingRoomCount){
        ArrayList<BaseHouse> filteredHouses = new ArrayList<>();
        filteredHouses.addAll(
                House.getHouseList().stream()
                        .filter(x->x.getRoomCount()==roomCount && x.getLivingRoomCount()==livingRoomCount)
                        .toList()
        );
        filteredHouses.addAll(
                Villa.getVillaList().stream()
                        .filter(x->x.getRoomCount()==roomCount && x.getLivingRoomCount()==livingRoomCount)
                        .toList()
        );
        filteredHouses.addAll(
                Summerhouse.getSummerhouseList().stream()
                        .filter(x->x.getRoomCount()==roomCount && x.getLivingRoomCount()==livingRoomCount)
                        .toList()
        );
        return filteredHouses;
    }

}