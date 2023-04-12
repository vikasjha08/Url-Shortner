package com.vikas.urlshortner.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "URLTABLE")
public class UrlDto {

    @Id
    @GeneratedValue
    private int id;
    private String shortUrl;
    private String longUrl;

}
