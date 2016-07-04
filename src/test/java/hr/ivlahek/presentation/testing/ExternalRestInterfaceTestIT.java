package hr.ivlahek.presentation.testing;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import hr.ivlahek.presentation.testing.dto.UserDTO;
import hr.ivlahek.presentation.testing.entity.City;
import hr.ivlahek.presentation.testing.entity.Country;
import hr.ivlahek.presentation.testing.util.CityBuilder;
import hr.ivlahek.presentation.testing.util.CountryBuilder;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * Created by ivlahek on 4.7.2016..
 */
public class ExternalRestInterfaceTestIT extends SystemTest {

    @Test
    public void should_do_complex_things_if_city_is_ok() {
        //BUILD
        Country country = CountryBuilder.aCountry().build();
        create(countryRepository, country);

        City city = CityBuilder.aCity().withCountry(country).build();
        create(cityRepository, city);

        Client client = Client.create();

        WebResource resource = client.resource("http://localhost:8080/presentation.testing-1.0-SNAPSHOT/api/testComplexClass/" + city.getId());
        //OPERATE
        UserDTO user = resource.type(MediaType.APPLICATION_XML_TYPE).accept(MediaType.APPLICATION_XML_TYPE).get(UserDTO.class);

        //CHECK
        assertThat(user).isNotNull();
        assertThat(userRepository.findById(user.getId())).isNotNull();
    }

    @Test
    public void should_inform_with_runtime_exception_is_city_does_not_exists() {
        //BUILD
        Country country = CountryBuilder.aCountry().build();
        create(countryRepository, country);

        City city = CityBuilder.aCity().withCountry(country).build();
        create(cityRepository, city);

        Client client = Client.create();

        WebResource resource = client.resource("http://localhost:8080/presentation.testing-1.0-SNAPSHOT/api/testComplexClass/" + -1);
        try {
            //OPERATE
            resource.type(MediaType.APPLICATION_XML_TYPE).accept(MediaType.APPLICATION_XML_TYPE).get(UserDTO.class);
            failBecauseExceptionWasNotThrown(UniformInterfaceException.class);
        } catch (UniformInterfaceException e) {
            assertThat(e.getResponse().getStatus()).isEqualTo(500);
        }
    }

}