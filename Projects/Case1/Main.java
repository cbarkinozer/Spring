import entity.*;
import service.HouseService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args){
        System.out.println("Welcome to the real estate program...");
        System.out.println("To exit, enter q");
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Select the real estate you want to produce by typing it: (q to exit) ");
            System.out.println("House, Villa, Summerhouse");
            String decision = scanner.nextLine();
            if(decision.equals("q")){
                System.out.println("Exiting the program...");
                break;
            }else{
                createRealEstate(decision);
            }
        }

        printCalculations();


    }

    public static void createRealEstate(String decision){
        System.out.println("Would you like to use default values?: y/n ");
        Scanner scanner = new Scanner(System.in);
        String isDefault = scanner.nextLine();
        if(isDefault.equalsIgnoreCase("y")){
            //Creating real estates with default values
            if(decision.equalsIgnoreCase("House")){
                new House();
            }else if(decision.equalsIgnoreCase("Villa")){
                new Villa();
            } else if(decision.equalsIgnoreCase("Summerhouse")){
                new Summerhouse();
            }
        }else {
            //Creating real estates by user given inputs
            HouseDTO.houseType= decision;
            System.out.println("Please enter following properties in following order: ");
            System.out.println("price,square meters, room count, living room count");
            try{
                HouseDTO.price= scanner.nextBigDecimal();
                HouseDTO.squareMeters=scanner.nextInt();
                HouseDTO.roomCount= scanner.nextInt();
                HouseDTO.livingRoomCount= scanner.nextInt();
            }catch(InputMismatchException ex){
                System.out.println("Invalid input!");
            }
            HouseFactory houseFactory = new HouseFactory();
            houseFactory.produceHouse();

        }
    }

    public static void printCalculations(){
        System.out.println("Sum of all houses prices: " + HouseService.sumHousePrices()+" tl");
        System.out.println("Sum of all villas prices: " + HouseService.sumVillaPrices()+" tl");
        System.out.println("Sum of all summerhouses prices: " + HouseService.sumSummerhousePrices()+" tl");

        System.out.println("Sum of  all real estates prices: " + HouseService.sumAllPrices()+" tl");

        System.out.println("Average of all houses square meters: " + HouseService.averageHouseSquareMeter()+" m^2");
        System.out.println("Average of all villas square meters: " + HouseService.averageVillaSquareMeter()+" m^2");
        System.out.println("Average of all summerhouses square meters: " + HouseService.averageSummerhouseSquareMeter()+" m^2");

        System.out.println("Average of  all real estates square meters: " + HouseService.averageAllSquareMeter()+" m^2");

        System.out.println("Enter room and living room count respectively for filtering real estates");
        Scanner scanner = new Scanner(System.in);
        int roomCount= scanner.nextInt();
        int livingRoomCount= scanner.nextInt();

        System.out.println("Real estates that are "+roomCount+"+"+livingRoomCount+" "
                +HouseService.filterAllByRoomCount(roomCount,livingRoomCount).toString());

        /*  Example output:
            Sum of all houses prices: 1492500 tl
            Sum of all villas prices: 2550000 tl
            Sum of all summerhouses prices: 9600000 tl
            Sum of  all real estates prices: 13642500 tl
            Average of all houses square meters: 145.0 m^2
            Average of all villas square meters: 185.0 m^2
            Average of all summerhouses square meters: 220.0 m^2
            Average of  all real estates square meters: 158.33333333333334 m^2
            Real estates that are 2+1: [
            price=497500, squareMeters=145, roomCount=2, livingRoomCount=1,
            price=497500, squareMeters=145, roomCount=2, livingRoomCount=1,
            price=497500, squareMeters=145, roomCount=2, livingRoomCount=1]
        */
    }

}
