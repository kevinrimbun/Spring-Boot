package net.javaSpring.springBoot.model.entity;

import java.security.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "borrow_book")
@NoArgsConstructor
public class BorrowBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    // join entity User (user_id)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // join entity Book (book_id)
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    // @CreationTimestamp
    @Column(nullable = true, name = "borrowed_date", updatable = false)
    private LocalDateTime borrowedDate = LocalDateTime.now();
    
    // @UpdateTimestamp
    @Column(name = "returned_date")
    private Timestamp returned_date;

    // tgl peminjaman
    // private LocalDateTime borrowedDate = LocalDateTime.now();

    // tgl pengembalian
    // private LocalDateTime returnedDate = LocalDateTime.now();
}