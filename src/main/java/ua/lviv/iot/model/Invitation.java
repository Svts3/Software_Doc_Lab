package ua.lviv.iot.model;


import java.util.Date;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invitations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @CsvIgnore
    private Long id;
    
    @Column(name = "creation_date")
    @CsvBindByName(column = "creationDate")
    @CsvDate(value = "yyyy-MM-dd")
    private Date creationDate;
    
    @ManyToOne
    @JoinColumn(name = "invited_operator_id", referencedColumnName = "id")
    @CsvIgnore
    private Operator invitedOperator;
    
    @ManyToOne
    @JoinColumn(name = "conversation_id", referencedColumnName = "id")
    @CsvIgnore
    private Conversation conversation;
    
}
