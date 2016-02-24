package com.legomin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Виталий on 11.02.2016.
 */
@Entity
@Table(name="tokens")
public class Token {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "EXP_DATE")
    private Date expDate;

    public Token() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
