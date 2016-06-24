package hr.ivlahek.presentation.testing.util;

import hr.ivlahek.presentation.testing.entity.City;
import hr.ivlahek.presentation.testing.entity.User;

/**
 * Created by ivlahek on 24.6.2016..
 */
public class UserBuilder {
    private Long id;
    private String firstName = "John";
    private String lastName = "Doe";
    private String address = "Ulica grada Vukovara 269";
    private String language = "hr";
    private City city;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public UserBuilder withLanguage(String language) {
        this.language = language;
        return this;
    }

    public UserBuilder withCity(City city) {
        this.city = city;
        return this;
    }

    public UserBuilder but() {
        return anUser().withId(id).withFirstName(firstName).withLastName(lastName).withAddress(address).withLanguage(language).withCity(city);
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setLanguage(language);
        user.setCity(city);
        return user;
    }
}
