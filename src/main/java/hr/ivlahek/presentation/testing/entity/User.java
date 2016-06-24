package hr.ivlahek.presentation.testing.entity;

import javax.persistence.*;

/**
 * Created by ivlahek on 23.6.2016..
 */
@Entity(name = "USER")
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_END_USER")
    @SequenceGenerator(name = "SEQ_END_USER", sequenceName = "SEQ_END_USER", allocationSize = 1)
    private Long id;
    @Basic
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic
    @Column(name = "LAST_NAME")
    private String lastName;
    @Basic
    @Column(name = "ADDRESS")
    private String address;
    @Basic
    @Column(name = "LANGUAGE")
    private String language;
    @ManyToOne
    @JoinColumn(name = "CITY_ID", referencedColumnName = "ID", nullable = false)
    private City city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
