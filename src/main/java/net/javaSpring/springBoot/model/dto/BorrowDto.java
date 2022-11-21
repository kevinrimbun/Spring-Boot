package net.javaSpring.springBoot.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BorrowDto {
    private String bookName;
    private Date returnedDate;
}