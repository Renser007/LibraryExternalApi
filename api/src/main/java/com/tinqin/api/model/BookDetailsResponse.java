package com.tinqin.api.model;

import com.tinqin.api.base.OperationResult;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class BookDetailsResponse implements OperationResult {

    private String dateOfPublishing;
    private String numberOfPages;
    private String resume;
    private String rating;
    private String cover;

}
