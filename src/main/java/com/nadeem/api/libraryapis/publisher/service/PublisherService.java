package com.nadeem.api.libraryapis.publisher.service;

import com.nadeem.api.libraryapis.exceptions.LibraryResourceAlreadyExistException;
import com.nadeem.api.libraryapis.exceptions.LibraryResourceNotFoundException;
import com.nadeem.api.libraryapis.publisher.model.Publisher;
import com.nadeem.api.libraryapis.publisher.model.PublisherEntity;
import com.nadeem.api.libraryapis.publisher.repository.PublisherRepository;
import com.nadeem.api.libraryapis.utills.LibraryApiUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public void updatePublisher(Publisher publisherTobeUpdated) throws LibraryResourceNotFoundException {
        Optional<PublisherEntity>publisherEntity= publisherRepository.findById(publisherTobeUpdated.getPublisherId());
        Publisher publisher=null;

        if(publisherEntity.isPresent()){
            PublisherEntity pe=publisherEntity.get();
            if(LibraryApiUtils.doesStringValueExist(publisherTobeUpdated.getEmailId())){
                  pe.setEmailId(publisherTobeUpdated.getEmailId());
            }
            if(LibraryApiUtils.doesStringValueExist(publisherTobeUpdated.getPhoneNumber())){
                pe.setPhoneNumber(publisherTobeUpdated.getPhoneNumber());
            }
            publisherRepository.save(pe);
            publisherTobeUpdated=createPublisherFromEntity(pe);
        }else {
            throw new LibraryResourceNotFoundException("Publisher id " +publisherTobeUpdated.getPublisherId()+ " Not Found");
        }
    }
    private Publisher createPublisherFromEntity(PublisherEntity pe) {
        return new Publisher(pe.getPublisherid(),pe.getName(),pe.getEmailId(),pe.getPhoneNumber());
    }


    public void deletePublisher(Integer publisherId) throws LibraryResourceNotFoundException {
        try {
            publisherRepository.deleteById(publisherId);
        }catch (EmptyResultDataAccessException e){
            throw new LibraryResourceNotFoundException("Publisher id " +publisherId+ " Not Found");
        }

    }
}
