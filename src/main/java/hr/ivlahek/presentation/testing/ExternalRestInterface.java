package hr.ivlahek.presentation.testing;

import hr.ivlahek.presentation.testing.entity.City;
import hr.ivlahek.presentation.testing.entity.Country;
import hr.ivlahek.presentation.testing.repository.CityRepository;
import hr.ivlahek.presentation.testing.repository.CountryRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by ivlahek on 29.6.2016..
 */
@Path("/api")
public class ExternalRestInterface {

    @Inject
    private ComplexClass complexClass;
    @Inject
    private CityRepository cityRepository;
    @Inject
    private CountryRepository countryRepository;

    @GET
    @Produces("text/plain")
    @Path("/testComplexClass")
    public void get() {
        Country country = new Country();
        country.setCode("HRV");
        country.setName("Croatia");
        countryRepository.persist(country);

        City city = new City();
        city.setName("Zagreb");
        city.setPostalCode(10000);
        city.setCountry(country);
        cityRepository.persist(city);

        ComplexRequest request = new ComplexRequest();
        request.address = "Ulica grada Vukovara";
        request.houseNumber = 269;
        request.postalCode = 10000;
        request.surname = "Doe";
        request.name = "John";
        request.cityId = city.getId().intValue();
        request.language = "hr";
        complexClass.doSomething(request);
    }
}
