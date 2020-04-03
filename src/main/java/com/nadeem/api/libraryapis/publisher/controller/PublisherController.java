package com.nadeem.api.libraryapis.publisher.controller;

import com.nadeem.api.libraryapis.exceptions.LibraryResourceAlreadyExistException;
import com.nadeem.api.libraryapis.publisher.model.Publisher;
import com.nadeem.api.libraryapis.publisher.service.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/publishers")
public class PublisherController {

    private PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping(path = "/{publisherId}")
    public Publisher getPublisher(@PathVariable Integer publisherId) {
        return new Publisher(publisherId, "nadeem", "nadeem@email.com", "123-456-789");
    }

    @PostMapping
    public ResponseEntity<?> addPublisher(@RequestBody Publisher publisher) {
        try {
            publisher = publisherService.addPublisher(publisher);
        } catch (LibraryResourceAlreadyExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(publisher, HttpStatus.CREATED);
    }
}

