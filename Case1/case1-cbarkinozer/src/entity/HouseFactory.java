package entity;

public class HouseFactory {
    public void  produceHouse(){
        if(HouseDTO.houseType.equalsIgnoreCase("House")){
            new House(HouseDTO.price, HouseDTO.squareMeters, HouseDTO.roomCount, HouseDTO.livingRoomCount);
        }else if(HouseDTO.houseType.equalsIgnoreCase("Villa")){
            new Villa(HouseDTO.price, HouseDTO.squareMeters, HouseDTO.roomCount, HouseDTO.livingRoomCount);
        }else if(HouseDTO.houseType.equalsIgnoreCase("Summerhouse")){
            new Summerhouse(HouseDTO.price, HouseDTO.squareMeters, HouseDTO.roomCount, HouseDTO.livingRoomCount);
        }else{
            System.out.println("Please enter a valid house type");
        }
    }
}
