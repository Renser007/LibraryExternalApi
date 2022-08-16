package com.tinqin.domain.service.interfaces;

import com.tinqin.domain.pojo.BookDetails;

import java.io.IOException;

public interface BookDetailsService {

        BookDetails retrieveBookDetails(Long bookId) throws IOException, InterruptedException;

}
