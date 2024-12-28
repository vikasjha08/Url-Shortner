package com.vikas.urlshortner.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "URLTABLE")
public class UrlDto {

    @Id
    @GeneratedValue
    private int id;
    private String shortUrl;
    private String longUrl;

}
