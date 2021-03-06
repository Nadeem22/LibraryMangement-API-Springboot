package com.nadeem.api.libraryapis.publisher.service;

import com.nadeem.api.libraryapis.exceptions.LibraryResourceAlreadyExistException;
import com.nadeem.api.libraryapis.exceptions.LibraryResourceNotFoundException;
import com.nadeem.api.libraryapis.publisher.model.Publisher;
import com.nadeem.api.libraryapis.publisher.model.PublisherEntity;
import com.nadeem.api.libraryapis.publisher.repository.PublisherRepository;
import com.nadeem.api.libraryapis.utills.LibraryApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublisherService {
    private static Logger log= LoggerFactory.getLogger(PublisherService.class);
    private PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public void addPublisher(Publisher publisherToBeAdded, String traceId)
            throws LibraryResourceAlreadyExistException {
        log.debug("TraceIs: {}, Publisher added: {}" , traceId,publisherToBeAdded);
        PublisherEntity publisherEntity = new PublisherEntity(
                publisherToBeAdded.getName(),
                publisherToBeAdded.getEmailId(),
                publisherToBeAdded.getPhoneNumber()
        );

        PublisherEntity addedPublisher = null;

        try {
            addedPublisher = publisherRepository.save(publisherEntity);
        } catch (DataIntegrityViolationException e) {
            log.error("TraceId: {} ,Publisher already exists!! ", traceId,e);
            throw new LibraryResourceAlreadyExistException(traceId, " Publisher already exists!!");
        }

        publisherToBeAdded.setPublisherId(addedPublisher.getPublisherid());
        log.info("TraceIs: {}, Publisher added: {}" , traceId,publisherToBeAdded);

    }

    public Publisher getPublisher(Integer publisherId, String traceId) throws LibraryResourceNotFoundException {
       Optional<PublisherEntity>publisherEntity= publisherRepository.findById(publisherId);
       Publisher publisher=null;

           if(publisherEntity.isPresent()){
                    PublisherEntity pe=publisherEntity.get();
                    publisher=createPublisherFromEntity(pe);
           }else {
               throw new LibraryResourceNotFoundException(traceId, " Publisher id" +publisherId+ "Not Found");
           }
           return publisher;

    }
    public void updatePublisher(Publisher publisherTobeUpdated, String traceId) throws LibraryResourceNotFoundException {
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
            throw new LibraryResourceNotFoundException(traceId," Publisher id " +publisherTobeUpdated.getPublisherId()+ " Not Found");
        }
    }



    public void deletePublisher(Integer publisherId, String traceId) throws LibraryResourceNotFoundException {
        try {
            publisherRepository.deleteById(publisherId);
        }catch (EmptyResultDataAccessException e){
            log.error("TraceIs: {}, Publisher Id {} Not Found: " , traceId,publisherId,e);
            throw new LibraryResourceNotFoundException(traceId, " Publisher id " +publisherId+ " Not Found");
        }

    }

    public List searchPublisher(String name, String traceId) {
        List<PublisherEntity> publisherEntities=null;
        if(LibraryApiUtils.doesStringValueExist(name)){
            publisherEntities=publisherRepository.findByNameContaining(name);
        }
        if(publisherEntities!=null && publisherEntities.size()>0){
            return createPublisherForSearchResponse(publisherEntities);
        }else {
            return  Collections.emptyList();
        }
    }

    private List createPublisherForSearchResponse(List<PublisherEntity> publisherEntities) {
     return   publisherEntities.stream().map(pe->createPublisherFromEntity(pe)).collect(Collectors.toList());
    }
    private Publisher createPublisherFromEntity(PublisherEntity pe) {
        return new Publisher(pe.getPublisherid(),pe.getName(),pe.getEmailId(),pe.getPhoneNumber());
    }
}
