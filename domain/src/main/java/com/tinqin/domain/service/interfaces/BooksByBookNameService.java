package com.tinqin.domain.service.interfaces;

import java.io.IOException;

public interface BooksByBookNameService {

    String receiveBookId(String bookName) throws IOException, InterruptedException;

}
