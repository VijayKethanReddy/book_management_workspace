package com.book;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import com.book.controller.BookControllerTest;
import com.book.service.AuthorServiceTest;
import com.book.service.BookServiceTest;


@SuppressWarnings("deprecation")
@RunWith(JUnitPlatform.class)
@SelectClasses({BookControllerTest.class, AuthorServiceTest.class, BookServiceTest.class})
public class TestSuite {
}
