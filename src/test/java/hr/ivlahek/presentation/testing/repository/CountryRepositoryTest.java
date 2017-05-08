package hr.ivlahek.presentation.testing.repository;


import hr.ivlahek.presentation.testing.entity.Country;
import hr.ivlahek.presentation.testing.util.ArquillianBaseClass;
import hr.ivlahek.presentation.testing.util.CountryBuilder;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ivlahek on 23.6.2016..
 */
public class CountryRepositoryTest extends ArquillianBaseClass {

  @Test
  public void should_create_country() {
    //BUILD
    Country country = CountryBuilder.aCountry().build();

    //OPERATE
    countryRepository.persist(country);

    //CHECK
    assertThat(country.getId()).isNotNull();
  }

  @Test
  public void should_find_all_countries() {
    //BUILD
    Country country1 = CountryBuilder.aCountry().withName("Slovenia").withCode("SLO").build();
    Country country2 = CountryBuilder.aCountry().build();
    countryRepository.persist(country1);
    countryRepository.persist(country2);

    //OPERATE
    List<Country> countries = countryRepository.findAll();

    //CHECK
    assertThat(countries).hasSize(2).containsOnly(country1, country2);
  }

}
