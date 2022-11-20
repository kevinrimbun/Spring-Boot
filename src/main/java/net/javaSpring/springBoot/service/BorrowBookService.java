package net.javaSpring.springBoot.service;

import net.javaSpring.springBoot.model.dto.BorrowDto;
import net.javaSpring.springBoot.model.dto.ResponseData;

public interface BorrowBookService {
    ResponseData<Object> borrowBook(long id, BorrowDto request);

    ResponseData<Object> returnBook(long id, BorrowDto request);

    ResponseData<Object> getBorrower(Boolean status);
}