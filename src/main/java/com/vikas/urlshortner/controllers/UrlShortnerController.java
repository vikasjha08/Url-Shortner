package com.vikas.urlshortner.controllers;


import com.vikas.urlshortner.dtos.InputDto;
import com.vikas.urlshortner.services.UrlRedirectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UrlShortnerController {

    private final UrlRedirectService service;

    @PostMapping("/create")
    public ResponseEntity<Object> createRedirectUrl(@RequestBody InputDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.setRedirectUrl(dto));
    }

    @GetMapping("/{shortUrlValue}")
    public ResponseEntity<Object> redirectUrl(@PathVariable String shortUrlValue) {
        return ResponseEntity.status(HttpStatus.FOUND).location(service.getRedirectUrl(shortUrlValue)).build();
    }


}
