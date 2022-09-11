package com.alperencubuk.taboo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Data
public class Card {

    @Id @GeneratedValue
    private long id;

    private String word;

    private String[] forbidden;

}
