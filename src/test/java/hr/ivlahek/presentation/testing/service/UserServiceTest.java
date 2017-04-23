package hr.ivlahek.presentation.testing.service;

import hr.ivlahek.presentation.testing.entity.City;
import hr.ivlahek.presentation.testing.entity.Country;
import hr.ivlahek.presentation.testing.entity.User;
import hr.ivlahek.presentation.testing.repository.CityRepository;
import hr.ivlahek.presentation.testing.repository.CountryRepository;
import hr.ivlahek.presentation.testing.repository.UserRepository;
import hr.ivlahek.presentation.testing.util.ArquillianBaseClass;
import hr.ivlahek.presentation.testing.util.CityBuilder;
import hr.ivlahek.presentation.testing.util.CountryBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * Created by ivlahek on 24.6.2016..
 */
public class UserServiceTest extends ArquillianBaseClass {
  @Inject
  private UserService userService;
  @Inject
  private CityRepository cityRepository;
  @Inject
  private UserRepository userRepository;
  @Inject
  CountryRepository countryRepository;

  private City city;

  @Before
  public void setUp() throws Exception {

    Country country = CountryBuilder.aCountry().build();
    countryRepository.persist(country);

    city = CityBuilder.aCity().withCountry(country).build();
    cityRepository.persist(city);
  }

  @Test
  public void should_create_user() {
    UserService.Request request = createRequest(city.getId());

    //OPERATE
    User user = userService.createUser(request);

    //CHECK
    User createdUser = userRepository.findById(user.getId());
    assertThat(createdUser).isNotNull();
  }

  @Test
  public void should_throw_exception_if_user_can_not_be_found() {
    UserService.Request request = createRequest(0l);

    //OPERATE
    try {
      userService.createUser(request);
      failBecauseExceptionWasNotThrown(RuntimeException.class);
    } catch (RuntimeException e) {
    }
  }


  private UserService.Request createRequest(long cityId) {
    UserService.Request request = new UserService.Request();
    request.setAddress("address");
    request.setLanguage("hr");
    request.setName("John");
    request.setSurname("Doe");
    request.setCityId(cityId);
    return request;
  }


}
