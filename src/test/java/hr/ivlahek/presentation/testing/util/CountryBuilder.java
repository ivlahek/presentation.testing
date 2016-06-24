package hr.ivlahek.presentation.testing.util;

import hr.ivlahek.presentation.testing.entity.Country;

/**
 * Created by ivlahek on 23.6.2016..
 */
public class CountryBuilder {
    private String code = "HRV";
    private Long id;
    private String name = "Croatia";

    private CountryBuilder() {
    }

    public static CountryBuilder aCountry() {
        return new CountryBuilder();
    }

    public CountryBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public CountryBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CountryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CountryBuilder but() {
        return aCountry().withCode(code).withId(id).withName(name);
    }

    public Country build() {
        Country country = new Country();
        country.setCode(code);
        country.setId(id);
        country.setName(name);
        return country;
    }
}
