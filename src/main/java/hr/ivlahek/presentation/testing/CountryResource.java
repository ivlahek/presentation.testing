package hr.ivlahek.presentation.testing;

import hr.ivlahek.presentation.testing.entity.Country;
import hr.ivlahek.presentation.testing.repository.CountryRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by ivlahek on 3.7.2016..
 */
@Path("/api/country")
public class CountryResource {
    @Inject
    private CountryRepository countryRepository;

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/")
    public Country create(Country country) {
        List<Country> countryList = countryRepository.findAll();

        country.setCode("HRV");
        country.setName("Croatia");
        countryRepository.persist(country);
        return country;
    }
}
