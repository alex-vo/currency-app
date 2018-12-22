package me.bill.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Currency {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column
    private UUID id;
    @Column(nullable = false, length = 3)
    private String code;
    @Column(nullable = false)
    private Integer num;
    @Column(nullable = false)
    private String e;
    @Column(nullable = false)
    private String currency;

}
