package hr.ivlahek.presentation.testing.service;

import hr.ivlahek.presentation.testing.entity.City;
import hr.ivlahek.presentation.testing.entity.User;
import hr.ivlahek.presentation.testing.repository.CityRepository;
import hr.ivlahek.presentation.testing.repository.UserRepository;

import javax.inject.Inject;

/**
 * Created by ivlahek on 23.6.2016..
 */
public class UserService {
  @Inject
  private CityRepository cityRepository;
  @Inject
  private UserRepository userRepository;

  public User createUser(Request request) {
    City city = cityRepository.findById(request.cityId);
    if (city == null) {
      throw new RuntimeException();
    }

    User user = new User();
    user.setAddress(request.address);
    user.setCity(city);
    user.setFirstName(request.name);
    user.setLastName(request.surname);
    user.setLanguage(request.language);

    userRepository.persist(user);
    return user;
  }

  public void setCityRepository(CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public static class Request {
    String name;
    String surname;
    String address;
    String language;
    long cityId;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getSurname() {
      return surname;
    }

    public void setSurname(String surname) {
      this.surname = surname;
    }

    public String getAddress() {
      return address;
    }

    public void setAddress(String address) {
      this.address = address;
    }

    public String getLanguage() {
      return language;
    }

    public void setLanguage(String language) {
      this.language = language;
    }

    public long getCityId() {
      return cityId;
    }

    public void setCityId(long cityId) {
      this.cityId = cityId;
    }


    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Request that = (Request) o;

      return com.google.common.base.Objects.equal(this.name, that.name) &&
          com.google.common.base.Objects.equal(this.surname, that.surname) &&
          com.google.common.base.Objects.equal(this.address, that.address) &&
          com.google.common.base.Objects.equal(this.language, that.language) &&
          com.google.common.base.Objects.equal(this.cityId, that.cityId);
    }

    @Override
    public int hashCode() {
      return com.google.common.base.Objects.hashCode(name, surname, address, language, cityId);
    }
  }
}
