package com.dpointtt.restapiexample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    @GetMapping
    public List<ResponseBody> getAllBooks(){
        return new ArrayList<>();
    }

}
