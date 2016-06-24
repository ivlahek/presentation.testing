package hr.ivlahek.presentation.testing.repository;

import hr.ivlahek.presentation.testing.entity.Country;

/**
 * Created by ivlahek on 23.6.2016..
 */
public class CountryRepository extends AbstractRepository<Country> {

    @Override
    protected Class<Country> returnEntityClass() {
        return Country.class;
    }
}
