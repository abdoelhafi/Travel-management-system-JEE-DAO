package dao;

import models.Place;

public interface IPlaceDao {
    Long createPlace(Place p);
    Place findPlaceById(Long id);
    boolean updatePlace(Place p);
    boolean removePlace(Place p);

}
