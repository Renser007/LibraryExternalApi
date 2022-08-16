package com.tinqin.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqin.domain.exception.BookDetailsException;
import com.tinqin.domain.pojo.BookDetails;
import com.tinqin.domain.service.interfaces.BookDetailsService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class BookDetailsServiceImpl implements BookDetailsService {

    private final ObjectMapper objectMapper;

    public BookDetailsServiceImpl() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public BookDetails retrieveBookDetails(Long bookId) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://hapi-books.p.rapidapi.com/book/" + bookId))
                .header("X-RapidAPI-Key", "f63aa3577cmshb4d7c4e277921d3p12322cjsn3e78dc99aad2")
                .header("X-RapidAPI-Host", "hapi-books.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        try {
            return objectMapper.readValue(response.body(), BookDetails.class);
        }catch (Exception e){
            throw new BookDetailsException();
        }
    }
}
