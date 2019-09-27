package com.wujk.springbootswagger2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "Swagger Learn", tags = {"book Api"})
public class SwaggerController {

    private List<Book> books = new ArrayList<Book>();

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    @ApiOperation(value = "添加列表", notes = "添加列表")
    @ApiImplicitParam(name="book", value = "图书实体book", required = true, dataType = "Book")
    public String add(@RequestBody Book book) {
        books.add(book);
        return "ok";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "查询列表", notes = "查询列表")
    public List<Book> list() {
        return books;
    }
}
