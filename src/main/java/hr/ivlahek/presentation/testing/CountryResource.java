package hr.ivlahek.presentation.testing;

import hr.ivlahek.presentation.testing.dto.CountryDTO;
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
    public CountryDTO create(CountryDTO countryDTO) {
        List<Country> countryList = countryRepository.findAll();

        for (Country country1 : countryList) {
            if (country1.getCode().equalsIgnoreCase(countryDTO.getCode())) {
                throw new RuntimeException();
            }
        }
        Country newCountry = new Country();
        newCountry.setId(null);
        newCountry.setCode("HRV");
        newCountry.setName("Croatia");
        countryRepository.persist(newCountry);

        countryDTO.setId(newCountry.getId());
        return countryDTO;
    }
}
