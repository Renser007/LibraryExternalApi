package com.tinqin.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqin.domain.service.interfaces.BooksByBookNameService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class BooksByBookNameServiceImpl implements BooksByBookNameService {

    private final ObjectMapper objectMapper;

    public BooksByBookNameServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String receiveBookId(String bookName) throws IOException, InterruptedException {

        String correctedName = bookName.replace(' ', '+');

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://hapi-books.p.rapidapi.com/search/" + correctedName))
                .header("X-RapidAPI-Key", "f63aa3577cmshb4d7c4e277921d3p12322cjsn3e78dc99aad2")
                .header("X-RapidAPI-Host", "hapi-books.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());


        String json = response.body();
        String[] parts = json.split(",");
        parts = parts[0].split(":");

        return parts[1];
    }
}
