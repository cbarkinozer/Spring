package entity;

import java.math.BigDecimal;

public abstract class BaseHouse {
    private BigDecimal price;
    private int squareMeters;
    private int roomCount;
    private int livingRoomCount;


    public BaseHouse(BigDecimal price, int squareMeters, int roomCount, int livingRoomCount){
        this.price=price;
        this.squareMeters=squareMeters;
        this.roomCount=roomCount;
        this.livingRoomCount=livingRoomCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getSquareMeters() {
        return squareMeters;
    }

    public void setSquareMeters(int squareMeters) {
        this.squareMeters = squareMeters;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public int getLivingRoomCount() {
        return livingRoomCount;
    }

    public void setLivingRoomCount(int livingRoomCount) {
        this.livingRoomCount = livingRoomCount;
    }

    @Override
    public String toString() {
        return  "\nprice=" + price +
                ", squareMeters=" + squareMeters +
                ", roomCount=" + roomCount +
                ", livingRoomCount=" + livingRoomCount;
    }
}