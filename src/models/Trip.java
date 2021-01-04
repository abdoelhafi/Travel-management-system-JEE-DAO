package models;

public class Trip {
    Long id;
    Place departure;
    Place destination;
    double price;

    public Trip() {
    }

    public Trip(Place departure, Place destination, double price) {
        this.id = id;
        this.departure = departure;
        this.destination = destination;
        this.price = price;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Place getDeparture() {
        return departure;
    }

    public void setDeparture(Place departure) {
        this.departure = departure;
    }

    public Place getDestination() {
        return destination;
    }

    public void setDestination(Place destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
