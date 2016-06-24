package hr.ivlahek.presentation.testing.util;

import hr.ivlahek.presentation.testing.entity.City;
import hr.ivlahek.presentation.testing.entity.Country;
import hr.ivlahek.presentation.testing.entity.User;

import java.util.Collection;

/**
 * Created by ivlahek on 24.6.2016..
 */
public class CityBuilder {
    private Long id;
    private String name;
    private Integer postalCode;
    private Country country;
    private Collection<User> users;

    private CityBuilder() {
    }

    public static CityBuilder aCity() {
        return new CityBuilder();
    }

    public CityBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CityBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CityBuilder withPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public CityBuilder withCountry(Country country) {
        this.country = country;
        return this;
    }

    public CityBuilder withUsers(Collection<User> users) {
        this.users = users;
        return this;
    }

    public CityBuilder but() {
        return aCity().withId(id).withName(name).withPostalCode(postalCode).withCountry(country).withUsers(users);
    }

    public City build() {
        City city = new City();
        city.setId(id);
        city.setName(name);
        city.setPostalCode(postalCode);
        city.setCountry(country);
        city.setUsers(users);
        return city;
    }
}
