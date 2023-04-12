package com.vikas.urlshortner.controllers;


import com.vikas.urlshortner.dtos.UrlDto;
import com.vikas.urlshortner.services.UrlRedirectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UrlShortnerController {

    private final UrlRedirectService service;

    @PostMapping("/create")
    public ResponseEntity<Object> createRedirectUrl(@RequestBody UrlDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.setRedirectUrl(dto));
    }

    @GetMapping("/{shortUrlValue}")
    public ResponseEntity<Object> redirectUrl(@PathVariable String shortUrlValue) {
        //Status should only be 302 for redirect
        return ResponseEntity.status(HttpStatus.FOUND).location(service.getRedirectUrl(shortUrlValue)).build();
    }


}
