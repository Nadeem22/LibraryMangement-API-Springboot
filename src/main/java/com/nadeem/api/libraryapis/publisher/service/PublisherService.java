package com.nadeem.api.libraryapis.publisher.service;

import com.nadeem.api.libraryapis.exceptions.LibraryResourceAlreadyExistException;
import com.nadeem.api.libraryapis.exceptions.LibraryResourceNotFoundException;
import com.nadeem.api.libraryapis.publisher.model.Publisher;
import com.nadeem.api.libraryapis.publisher.model.PublisherEntity;
import com.nadeem.api.libraryapis.publisher.repository.PublisherRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublisherService {

    private PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public void addPublisher(Publisher publisherToBeAdded)
            throws LibraryResourceAlreadyExistException {

        PublisherEntity publisherEntity = new PublisherEntity(
                publisherToBeAdded.getName(),
                publisherToBeAdded.getEmailId(),
                publisherToBeAdded.getPhoneNumber()
        );

        PublisherEntity addedPublisher = null;

        try {
            addedPublisher = publisherRepository.save(publisherEntity);
        } catch (DataIntegrityViolationException e) {
            throw new LibraryResourceAlreadyExistException("Publisher already exists!!");
        }

        publisherToBeAdded.setPublisherId(addedPublisher.getPublisherid());

    }

    public Publisher getPublisher(Integer publisherId) throws LibraryResourceNotFoundException {
       Optional<PublisherEntity>publisherEntity= publisherRepository.findById(publisherId);
       Publisher publisher=null;

           if(publisherEntity.isPresent()){
                    PublisherEntity pe=publisherEntity.get();
                    publisher=createPublisherFromEntity(pe);
           }else {
               throw new LibraryResourceNotFoundException("Publisher id" +publisherId+ "Not Found");
           }
           return publisher;

    }

    private Publisher createPublisherFromEntity(PublisherEntity pe) {
        return new Publisher(pe.getPublisherid(),pe.getName(),pe.getEmailId(),pe.getPhoneNumber());
    }
}
