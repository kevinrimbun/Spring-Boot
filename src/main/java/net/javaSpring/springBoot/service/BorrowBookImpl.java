package net.javaSpring.springBoot.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import net.javaSpring.springBoot.model.dto.BorrowDto;
import net.javaSpring.springBoot.model.dto.ResponseData;
import net.javaSpring.springBoot.model.entity.Book;
import net.javaSpring.springBoot.model.entity.BorrowBook;
import net.javaSpring.springBoot.model.entity.User;
import net.javaSpring.springBoot.repository.BookRepository;
import net.javaSpring.springBoot.repository.BorrowBookRepo;
import net.javaSpring.springBoot.repository.UserRepository;

@Service
@Transactional
public class BorrowBookImpl implements BorrowBookService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowBookRepo borrowBookRepo;
    
    private static LocalDateTime LocalDateTime = java.time.LocalDateTime.now();
    private User user;
    private Book book;
    private BorrowBook borrowBook;
    private ResponseData<Object> responseData;

    @Override
    public ResponseData<Object> borrowBook(long id, BorrowDto request) {
        // TODO Auto-generated method stub
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            user = userOpt.get();
            borrowBook = new BorrowBook();
			book = bookRepository.findByTitle(request.getBookName());
            borrowBook.setBook(book);
            borrowBook.setUser(user);

            borrowBookRepo.save(borrowBook);
            responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "Borrowed Succesfully", borrowBook);
            return responseData;

        } else {
            responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "user Not Found", null);
        }
        return responseData;
    }

    @Override
    public ResponseData<Object> returnBook(long id) {
        // TODO Auto-generated method stub
        Optional<BorrowBook> borrowBookOpt = borrowBookRepo.findById(id);
        if (borrowBookOpt.isPresent()) {
            borrowBook = borrowBookOpt.get();
            // borrowBook = new BorrowBook();
            borrowBook.setBorrowed(false);
            borrowBook.setReturnedDate(LocalDateTime);

            borrowBookRepo.save(borrowBook);
            responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "Returned Succesfully", borrowBook);
            return responseData;

        } else {
            responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "Borrow List Not Found", null);
        }
        return responseData;
    }
}