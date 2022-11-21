package net.javaSpring.springBoot.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import net.javaSpring.springBoot.exception.custom.CustomNotFound;
import net.javaSpring.springBoot.model.dto.BorrowDto;
import net.javaSpring.springBoot.model.dto.ResponseData;
import net.javaSpring.springBoot.model.entity.Book;
import net.javaSpring.springBoot.model.entity.BorrowBook;
import net.javaSpring.springBoot.model.entity.User;
import net.javaSpring.springBoot.repository.BookRepository;
import net.javaSpring.springBoot.repository.BorrowBookRepo;
import net.javaSpring.springBoot.repository.UserRepository;
import net.javaSpring.springBoot.validator.UserValidator;

@Service
@Transactional
public class BorrowBookImpl2 implements BorrowBookService2{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowBookRepo borrowBookRepo;

    private UserValidator userValidator;
    private BorrowBook borrowBook;
    private User user;
    private Book book;
    private ResponseData<Object> responseData;



    @Override
    public ResponseData<Object> borrowBook(long id, BorrowDto request) throws CustomNotFound, Exception {
        // TODO Auto-generated method stub
        Optional<User> userOpt = userRepository.findById(id);

        // validate user not found
        userValidator.validateUserNotFound(userOpt);
        
        user = userOpt.get();
        borrowBook = new BorrowBook();
		book = bookRepository.findByTitle(request.getBookName());
        borrowBook.setBook(book);
        borrowBook.setUser(user);

        borrowBookRepo.save(borrowBook);

        // String message = "Borrowing book '" + borrowBook.getBook().getTitle() + "' success";

        responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "borrowed successfully", borrowBook);
        return responseData;

    }

    @Override
    public ResponseData<Object> returnBook(long id) throws CustomNotFound {
        // TODO Auto-generated method stub
        return null;
    }
    
}
