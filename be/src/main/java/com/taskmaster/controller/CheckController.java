package com.taskmaster.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController("/")
public class CheckController {

    @GetMapping(value = "ping", produces = TEXT_PLAIN_VALUE)
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("ok");
    }



}
