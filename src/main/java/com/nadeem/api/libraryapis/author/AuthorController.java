package com.nadeem.api.libraryapis.author;

import com.nadeem.api.libraryapis.exceptions.LibraryResourceNotFoundException;
import com.nadeem.api.libraryapis.utills.LibraryApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {
    private static Logger logger= LoggerFactory.getLogger(AuthorController.class);
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping("/{authorId}")
    public ResponseEntity<?> getAuthor(@PathVariable Integer authorId, @RequestHeader(value = "Trace-Id",defaultValue = "")String traceId) throws LibraryResourceNotFoundException {
        if(!LibraryApiUtils.doesStringValueExist(traceId)){
            traceId= UUID.randomUUID().toString();
        }
        return  new ResponseEntity<>(authorService.getAuthor(authorId,traceId), HttpStatus.OK);

    }

}
