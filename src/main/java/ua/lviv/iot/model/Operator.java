package ua.lviv.iot.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "operators")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Operator extends Person {

    private String position;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @CsvIgnore
    private Company company;
}
