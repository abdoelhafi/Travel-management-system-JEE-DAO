package dao;

import models.Trip;

public interface ITripDao {
    Long creatTrip(Trip t);
    Trip findTripById(Long id);
    boolean removeTrip(Trip t);

}
