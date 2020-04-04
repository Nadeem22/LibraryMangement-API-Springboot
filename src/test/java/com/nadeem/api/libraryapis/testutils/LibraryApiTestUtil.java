package com.nadeem.api.libraryapis.testutils;

import com.nadeem.api.libraryapis.publisher.model.Publisher;
import com.nadeem.api.libraryapis.publisher.model.PublisherEntity;

import javax.swing.text.html.Option;
import java.util.Optional;

public class LibraryApiTestUtil {
    public static Publisher createPublisher(){
        return new Publisher(null,TestConstants.TEST_PUBLISHER_NAME,TestConstants.TEST_PUBLISHER_EMAIL,TestConstants.TEST_PUBLISHER_PHONE);
    }

    public static PublisherEntity createPublisherEntity() {
        return new PublisherEntity(TestConstants.TEST_PUBLISHER_NAME,TestConstants.TEST_PUBLISHER_EMAIL,TestConstants.TEST_PUBLISHER_PHONE);

    }

    public static Optional<PublisherEntity> createPublisherEntityOptional() {
        return Optional.of(createPublisherEntity());
    }
}
