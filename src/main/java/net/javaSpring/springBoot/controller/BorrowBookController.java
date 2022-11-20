package net.javaSpring.springBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.javaSpring.springBoot.model.dto.BookDto;
import net.javaSpring.springBoot.model.dto.BorrowDto;
import net.javaSpring.springBoot.model.dto.ResponseData;
import net.javaSpring.springBoot.service.BorrowBookService;

@RestController
@RequestMapping("/borrow")
public class BorrowBookController {
    @Autowired
    private BorrowBookService borrowBookService;
    private ResponseData<Object> responseData;

    @GetMapping
    public ResponseEntity<Object> getBorrower(@RequestParam(value = "status", defaultValue = "") Boolean status) {
        responseData = borrowBookService.getBorrower(status);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> borrowBook(@PathVariable Long id, @RequestBody BorrowDto request) {
        responseData = borrowBookService.borrowBook(id, request);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }

    @PostMapping("/return")
    public ResponseEntity<Object> returnBook(@PathVariable Long id, @RequestBody BorrowDto request) {
        responseData = borrowBookService.returnBook(id, request);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
}
