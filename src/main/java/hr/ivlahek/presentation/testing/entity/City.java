package hr.ivlahek.presentation.testing.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by ivlahek on 23.6.2016..
 */
@Entity(name = "CITY")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CITY")
    @SequenceGenerator(name = "SEQ_CITY", sequenceName = "SEQ_CITY", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "POSTAL_CODE")
    private Integer postalCode;
    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID", referencedColumnName = "ID", nullable = false)
    private Country country;
    @OneToMany(mappedBy = "city")
    private Collection<User> users;


    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) &&
                Objects.equals(postalCode, city.postalCode) &&
                Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, postalCode);
    }


    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }


}
