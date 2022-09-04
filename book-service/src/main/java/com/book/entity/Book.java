package com.book.entity;


import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	
	@NotBlank(message = "logo cannot be blank#######")
	private String logo;
	
	@NotBlank(message = "title cannot be blank#######")
	private String title;
	
	@NotBlank(message = "category cannot be blank#######")
	private String category;
	
	@Min(value = 1, message = "price cannot be less than 1")
	private int price;
	
	@ManyToOne
	private Author author;
	
	@NotBlank(message = "publisher cannot be blank#######")
	private String publisher;
	
	@NotNull(message = "publishedDate cannot be blank#######")
	@DateTimeFormat(style = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate publishedDate;
	
	@NotBlank(message = "content cannot be blank#######")
	private String content;
	
	@NotNull
	private Boolean active;
}
