package dao;

import models.Place;
import models.Trip;

import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        boolean quit=false;
        PlaceDao placeDao= new PlaceDao();
        TripDao tripDao= new TripDao();
        System.out.println("Welcome Abroad !!!!");
        System.out.println("1-Add A place");
        System.out.println("2-Find a place");
        System.out.println("3-Edit a place");
        System.out.println("4-Remove a place");
        System.out.println("5-Add a trip");
        System.out.println("6-Find a trip");
        System.out.println("7-Remove a trip");
        System.out.println("8-Quit");
        while (scanner.hasNextInt()&&!quit) {
            int choice =scanner.nextInt();
            long pId;
            String pName;
            switch (choice) {
                case 1:
                    System.out.printf("Add place's name :");
                    pName=new Scanner(System.in).nextLine();
                    pId=placeDao.createPlace(new Place(pName));
                    System.out.println("Place added with the Id :"+pId);
                    break;

                case 2:
                    System.out.printf("Add place's Id:");
                    pId=new Scanner(System.in).nextInt();
                    Place place=placeDao.findPlaceById(pId);
                    System.out.println("The Place with the Id given is :"+place.getName());
                    break;

                case 3:
                    System.out.printf("Add Place's Id to edit:");
                    pId=new Scanner(System.in).nextInt();
                    System.out.printf("Add a new place name :");

                    pName=new Scanner(System.in).nextLine();
                    Place p = new Place();
                    p.setId(pId);
                    p.setName(pName);
                    boolean bool=placeDao.updatePlace(p);
                    if(bool)
                        System.out.println("Done : Place updated");
                    else
                        System.out.println("Failed to update place :");
                    break;
                case 4:
                    System.out.print("Id: ");
                    long placeId=scanner.nextLong();
                    Place fplace=placeDao.findPlaceById(placeId);
                    if(fplace!=null) {
                        boolean rm=placeDao.removePlace(fplace);
                        if(rm) {
                            System.out.println("Done : Place removed ");
                        }else {
                            System.out.println("Error");
                        }
                    }else {
                        System.out.println("place not found");
                    }
                    break;
                case 5:
                    System.out.print("Add the Departure:");
                    Place depart=new Place(scanner.next());
                    placeDao.createPlace(depart);
                    System.out.print("Add the Destination:");
                    Place destination=new Place(scanner.next());
                    placeDao.createPlace(destination);
                    System.out.print("Add the Price:");
                    double price=scanner.nextDouble();
                    long id1 =tripDao.creatTrip(new Trip(depart,destination,price));
                    System.out.println("Place added with the ID ="+id1);
                    break;


                case 6:
                    System.out.print("Add the Id: ");
                    long tripId=scanner.nextLong();
                    Trip trip=tripDao.findTripById(tripId);
                    if(trip!=null) {
                        System.out.println("The Trip founded with the given Id is:");
                        System.out.println("Departure:"+trip.getDeparture().getName());
                        System.out.println("Destination:"+trip.getDestination().getName());

                    }else {
                        System.out.println("Trip not find");
                    }
                    break;
                case 7:
                    System.out.print("Add the Id: ");
                    long ftripId=scanner.nextLong();
                    Trip ftrip=tripDao.findTripById(ftripId);
                    if(ftrip!=null) {
                        boolean rm=tripDao.removeTrip(ftrip);
                        if(rm) {
                            System.out.println("The Trip with the given Id was removed ");
                        }else {
                            System.out.println("Error");
                        }
                    }else {
                        System.out.println("Trip not found");
                    }
                    break;

                case 8:

                    quit=true;
                    scanner.close();
                    break;

                default:

                    break;
            }

        }


    }

}
