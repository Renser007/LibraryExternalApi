package com.tinqin.api.base;

import io.vavr.control.Either;

import java.io.IOException;

public interface OperationProcessor < I extends OperationInput, R extends OperationResult>{

    Either<Error, R> process(I input) throws IOException, InterruptedException;

}
