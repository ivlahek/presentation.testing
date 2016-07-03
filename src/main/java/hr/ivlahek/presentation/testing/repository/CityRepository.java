package hr.ivlahek.presentation.testing.repository;

import hr.ivlahek.presentation.testing.entity.City;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

/**
 * Created by ivlahek on 23.6.2016..
 */
@Transactional
@Stateful
public class CityRepository extends AbstractRepository<City> {

    @Override
    protected Class<City> returnEntityClass() {
        return City.class;
    }
}
