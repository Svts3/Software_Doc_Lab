package ua.lviv.iot.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "streets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Street {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CsvIgnore
    private Long id;

    @CsvBindByName(column = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    @CsvIgnore
    private City city;

}
