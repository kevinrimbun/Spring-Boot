package net.javaSpring.springBoot.service;

import net.javaSpring.springBoot.exception.custom.CustomNotFound;
import net.javaSpring.springBoot.model.dto.BorrowDto;
import net.javaSpring.springBoot.model.dto.ResponseData;

public interface BorrowBookService2 {
    ResponseData<Object> borrowBook(long id, BorrowDto request) throws CustomNotFound, Exception;

    ResponseData<Object> returnBook(long id) throws CustomNotFound;
}
