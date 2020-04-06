package com.nadeem.api.libraryapis.author;

import com.nadeem.api.libraryapis.exceptions.LibraryResourceAlreadyExistException;
import com.nadeem.api.libraryapis.exceptions.LibraryResourceNotFoundException;
import com.nadeem.api.libraryapis.utills.LibraryApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @PostMapping
    public ResponseEntity<?> addAuthor(@Valid @RequestBody Author author,@RequestHeader(value = "Trace-Id",defaultValue = "") String traceId) throws LibraryResourceAlreadyExistException {
        logger.debug("Request to add Author : {}",author);
        if(!LibraryApiUtils.doesStringValueExist(traceId)){
            traceId=UUID.randomUUID().toString();
        }
        logger.debug("Trace-Id Added: {}", traceId );
        authorService.addAuthor(author,traceId);
        logger.debug("Returning response for TraceId: {}", traceId);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }
    @DeleteMapping("/{authorId}")
    public ResponseEntity<?>deleteAuthor(@PathVariable Integer authorId, @RequestHeader(value = "Trace-Id",defaultValue = "")String traceId) throws LibraryResourceNotFoundException {
        if(!LibraryApiUtils.doesStringValueExist(traceId)){
            traceId=UUID.randomUUID().toString();
        }
        authorService.deleteAuthor(authorId,traceId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }
    @PutMapping(path = "/{authorId}")
    public ResponseEntity<?> updateAuthor(@PathVariable Integer authorId,
                                          @Valid @RequestBody Author author,
                                          @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId)
            throws LibraryResourceNotFoundException {

        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        author.setAuthorId(authorId);
        authorService.updateAuthor(author, traceId);

        return new ResponseEntity<>(author, HttpStatus.OK);
    }

}
