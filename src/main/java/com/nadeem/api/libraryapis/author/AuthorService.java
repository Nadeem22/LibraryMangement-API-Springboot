package com.nadeem.api.libraryapis.author;

import com.nadeem.api.libraryapis.exceptions.LibraryResourceAlreadyExistException;
import com.nadeem.api.libraryapis.exceptions.LibraryResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    private static Logger logger= LoggerFactory.getLogger(AuthorService.class);
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(Integer authorId, String traceId) throws LibraryResourceNotFoundException {
          Optional<AuthorEntity> authorEntity=  authorRepository.findById(authorId);
          Author author=null;
          if(authorEntity.isPresent()){
              AuthorEntity ae=authorEntity.get();
              author=createAuthorFromEntity(ae);
          }else {
              throw new LibraryResourceNotFoundException(traceId,"Author Id: " +authorId + "Not Found");
          }
          return author;
    }

    private Author createAuthorFromEntity(AuthorEntity ae) {

        return new Author(ae.getAuthorId(),ae.getFirstName(),ae.getLastName(),ae.getDateOfBirth(),ae.getGender());
    }

    public void addAuthor(Author authorToBeAdded, String traceId) throws LibraryResourceAlreadyExistException {
        logger.debug("Trace-Id: {},Request to add Author: {} ",traceId, authorToBeAdded);
        AuthorEntity authorEntity=new AuthorEntity(authorToBeAdded.getFirstName(),authorToBeAdded.getLastName(),authorToBeAdded.getDateOfBirth(),authorToBeAdded.getGender());
        AuthorEntity addedAuthor=null;
        try{
            addedAuthor=authorRepository.save(authorEntity);
        }catch (DataIntegrityViolationException e){
            logger.error("Trace-Id: {}, Author AlreadyExist" ,traceId);
            throw  new LibraryResourceAlreadyExistException(traceId,"Author Already Exist");
        }
        authorToBeAdded.setAuthorId(addedAuthor.getAuthorId());
        logger.info("Trace-Id : {}, Author added: {}",traceId,authorToBeAdded);
    }
}
