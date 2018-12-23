package me.bill.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
public class Request {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column
    private UUID id;
    @Column(nullable = false)
    private String ip;
    @Column(nullable = false)
    private ZonedDateTime dateTime;
    @Column(nullable = false)
    private String url;

}
