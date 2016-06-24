package hr.ivlahek.presentation.testing.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by ivlahek on 23.6.2016..
 */
@Entity(name = "COUNTRY")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COUNTRY")
    @SequenceGenerator(name = "SEQ_COUNTRY", sequenceName = "SEQ_COUNTRY", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "CODE")
    private String code;
    @OneToMany(mappedBy = "country")
    private Collection<City> cities;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(id, country.id) &&
                Objects.equals(name, country.name) &&
                Objects.equals(code, country.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }


    public Collection<City> getCities() {
        return cities;
    }

    public void setCities(Collection<City> cities) {
        this.cities = cities;
    }
}
