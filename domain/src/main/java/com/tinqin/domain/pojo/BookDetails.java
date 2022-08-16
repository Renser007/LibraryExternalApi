package com.tinqin.domain.pojo;

import lombok.*;

import java.util.ArrayList;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class BookDetails {

    private ArrayList<String> authors;
    private int book_id;
    private String cover;
    private String name;
    private int pages;
    private String published_date;
    private double rating;
    private String synopsis;
    private String url;
}