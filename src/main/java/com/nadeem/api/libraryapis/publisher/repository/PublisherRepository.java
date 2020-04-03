package com.nadeem.api.libraryapis.publisher.repository;


import com.nadeem.api.libraryapis.publisher.model.PublisherEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends CrudRepository<PublisherEntity, Integer> {
    List<PublisherEntity> findByNameContaining(String name);
}
