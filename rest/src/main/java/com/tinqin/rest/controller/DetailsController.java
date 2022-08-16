package com.tinqin.rest.controller;

import com.tinqin.api.base.Error;
import com.tinqin.api.model.BookDetailsRequest;
import com.tinqin.api.model.BookDetailsResponse;
import com.tinqin.api.operation.BookDetailsProcessor;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DetailsController {

    private final BookDetailsProcessor bookDetailsProcessor;

    public DetailsController(BookDetailsProcessor bookDetailsProcessor) {
        this.bookDetailsProcessor = bookDetailsProcessor;
    }

    @PostMapping("/getBookDetails")
    public ResponseEntity<?> changePublisher(@RequestBody final BookDetailsRequest bookDetailsRequest) throws IOException, InterruptedException {
        Either<Error, BookDetailsResponse> response=bookDetailsProcessor.process(bookDetailsRequest);
        if(response.isLeft()){
            return ResponseEntity.status(response.getLeft().getCode()).body(response.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.get());
    }

}
