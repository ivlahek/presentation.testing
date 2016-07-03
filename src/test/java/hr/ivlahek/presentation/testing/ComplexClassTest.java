package hr.ivlahek.presentation.testing;

import hr.ivlahek.presentation.testing.entity.User;
import hr.ivlahek.presentation.testing.external.ExternalSystemClient;
import hr.ivlahek.presentation.testing.service.UserService;
import hr.ivlahek.presentation.testing.util.UserBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Mockito.*;

/**
 * Created by ivlahek on 24.6.2016..
 */
public class ComplexClassTest {

    private ComplexClass complexClass;
    private String validAddress = "Ulica grada Vukovara";
    private int houseNumber = 269;
    private int postalCode = 10000;
    private String surname = "Doe";
    private String name = "John";
    private String language = "hr";
    private User expectedUser;
    private ExternalSystemClient externalSystemClient;
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        complexClass = new ComplexClass();
        externalSystemClient = mock(ExternalSystemClient.class);
        when(externalSystemClient.checkAddressExists(validAddress, houseNumber, postalCode)).thenReturn(true);
        complexClass.setExternalSystemClient(externalSystemClient);

        userService = mock(UserService.class);

        UserService.Request request = new UserService.Request();
        request.setSurname(surname);
        request.setName(name);
        request.setCityId(postalCode);
        request.setLanguage(language);
        request.setAddress("Ulica grada Vukovara 269");


        expectedUser = UserBuilder.anUser().build();
        when(userService.createUser(request)).thenReturn(expectedUser);

        complexClass.setUserService(userService);

    }

    @Test
    //by setting method to be package private, instead of private, we can test that method
    public void should_build_address() {
        assertThat(complexClass.buildAddress(validAddress, houseNumber)).isEqualTo("Ulica grada Vukovara 269");
    }

    @Test
    public void should_create_user_if_address_is_real() {
        ComplexRequest request = new ComplexRequest();
        request.houseNumber = houseNumber;
        request.address = validAddress;
        request.cityId = postalCode;
        request.postalCode = postalCode;
        request.language = language;
        request.name = name;
        request.surname = surname;

        //OPERATE
        User user = complexClass.doSomething(request);

        //CHECK
        assertThat(user.getId()).isEqualTo(expectedUser.getId());
    }

    @Test
    public void should_throw_runtime_exception_if_address_is_not_valid() {
        ComplexRequest request = new ComplexRequest();
        request.houseNumber = houseNumber;
        request.address = "invalidAddres";
        request.cityId = postalCode;
        request.postalCode = postalCode;
        request.language = language;
        request.name = name;
        request.surname = surname;

        //OPERATE
        try {
            User user = complexClass.doSomething(request);
            failBecauseExceptionWasNotThrown(RuntimeException.class);
        } catch (RuntimeException e) {
            //CHECK
            verify(externalSystemClient, times(1)).checkAddressExists("invalidAddres", houseNumber, postalCode);
            verify(userService, times(0)).createUser(any(UserService.Request.class));
        }
    }

    @Test
    public void should_throw_runtime_exception_if_city_id_is_not_valid() {
        ComplexRequest request = new ComplexRequest();
        request.houseNumber = houseNumber;
        request.address = validAddress;
        int wrong = 10020;
        request.cityId = wrong;
        request.postalCode = postalCode;
        request.language = language;
        request.name = name;
        request.surname = surname;


        UserService.Request userRequest = new UserService.Request();
        userRequest.setSurname(surname);
        userRequest.setName(name);
        userRequest.setCityId(wrong);
        userRequest.setLanguage(language);
        userRequest.setAddress("Ulica grada Vukovara 269");

        when(userService.createUser(userRequest)).thenThrow(RuntimeException.class);

        //OPERATE
        try {
            User user = complexClass.doSomething(request);
            failBecauseExceptionWasNotThrown(RuntimeException.class);
        } catch (RuntimeException e) {
            //CHECK
            verify(externalSystemClient, times(1)).checkAddressExists(validAddress, houseNumber, postalCode);
            verify(userService, times(1)).createUser(any(UserService.Request.class));
        }
    }


}