package com.tinqin.core.processor;

import com.tinqin.api.base.Error;
import com.tinqin.api.error.BookDetailsNotFoundError;
import com.tinqin.api.model.BookDetailsRequest;
import com.tinqin.api.model.BookDetailsResponse;
import com.tinqin.api.operation.BookDetailsProcessor;
import com.tinqin.domain.pojo.BookDetails;
import com.tinqin.domain.service.interfaces.BookDetailsService;
import com.tinqin.domain.service.interfaces.BooksByBookNameService;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class BookDetailsProcessorCore implements BookDetailsProcessor {

    private final BookDetailsService bookDetailsService;
    private final BooksByBookNameService booksByBookNameService;

    public BookDetailsProcessorCore(BookDetailsService bookDetailsService, BooksByBookNameService booksByBookNameService) {
        this.bookDetailsService = bookDetailsService;
        this.booksByBookNameService = booksByBookNameService;
    }

    @Override
    public Either<Error, BookDetailsResponse> process(BookDetailsRequest input){

        return Try.of(() -> {

            final String bookId = booksByBookNameService.receiveBookId(input.getBookName());

            final BookDetails bookDetails = bookDetailsService.retrieveBookDetails(Long.valueOf(bookId));

            return BookDetailsResponse.builder()
                    .dateOfPublishing(bookDetails.getPublished_date())
                    .numberOfPages(String.valueOf(bookDetails.getPages()))
                    .resume(bookDetails.getSynopsis())
                    .rating(String.valueOf(bookDetails.getRating()))
                    .cover(bookDetails.getCover())
                    .build();

        }).toEither()
                .mapLeft(throwable -> new BookDetailsNotFoundError());
    }
}
