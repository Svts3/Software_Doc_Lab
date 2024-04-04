package ua.lviv.iot.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "people")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type")
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CsvIgnore
    private Long id;

    @Column(name = "first_name")
    @CsvBindByName(column = "firstName")
    private String firstName;

    @Column(name = "last_name")
    @CsvBindByName(column = "lastName")
    private String lastName;

    @CsvBindByName(column = "email")
    private String email;

}
