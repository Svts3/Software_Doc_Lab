package ua.lviv.iot.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
@Table(name = "messages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CsvIgnore
    private Long id;
    @CsvBindByName(column = "content")
    private String content;

    @ManyToOne
    @Cascade(CascadeType.REMOVE)
    @CsvIgnore
    private Conversation conversation;

}
