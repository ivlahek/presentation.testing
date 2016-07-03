package hr.ivlahek.presentation.testing.repository;

import hr.ivlahek.presentation.testing.entity.Country;

import javax.ejb.Stateful;
import javax.transaction.Transactional;

/**
 * Created by ivlahek on 23.6.2016..
 */
@Transactional
@Stateful
public class CountryRepository extends AbstractRepository<Country> {

    @Override
    protected Class<Country> returnEntityClass() {
        return Country.class;
    }
}
