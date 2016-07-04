package hr.ivlahek.presentation.testing;

import hr.ivlahek.presentation.testing.dto.UserDTO;
import hr.ivlahek.presentation.testing.entity.User;
import hr.ivlahek.presentation.testing.repository.CityRepository;
import hr.ivlahek.presentation.testing.repository.CountryRepository;
import hr.ivlahek.presentation.testing.request.ComplexRequest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    @Produces("application/xml")
    @Path("/testComplexClass/{cityId}")
    public UserDTO doComplexThing(@PathParam("cityId") int cityId) {
        ComplexRequest request = new ComplexRequest();
        request.address = "Ulica grada Vukovara";
        request.houseNumber = 269;
        request.postalCode = 10000;
        request.surname = "Doe";
        request.name = "John";
        request.cityId = cityId;
        request.language = "hr";
        User user = complexClass.doSomething(request);

        UserDTO userDTO = new UserDTO();
        userDTO.setLastName(user.getLastName());
        userDTO.setId(user.getId());
        userDTO.setAddress(user.getAddress());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLanguage(user.getLanguage());

        return userDTO;
    }
}
