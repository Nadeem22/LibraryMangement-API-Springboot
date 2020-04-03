package com.nadeem.api.libraryapis.publisher.repository;


import com.nadeem.api.libraryapis.publisher.model.PublisherEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends CrudRepository<PublisherEntity, Integer> {
}
