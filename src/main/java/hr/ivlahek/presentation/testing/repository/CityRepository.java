package hr.ivlahek.presentation.testing.repository;

import hr.ivlahek.presentation.testing.entity.City;

/**
 * Created by ivlahek on 23.6.2016..
 */
public class CityRepository extends AbstractRepository<City> {

    @Override
    protected Class<City> returnEntityClass() {
        return City.class;
    }
}
