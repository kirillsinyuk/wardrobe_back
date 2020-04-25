package com.parser.controller;

import com.parser.services.parsing.LaModa.LamodaCommonParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ParserController {

    @Autowired
    private LamodaCommonParseService lamodaCommonParseService;

    @GetMapping("/test")
    public HttpStatus startParsing(){
        new Thread(() ->  lamodaCommonParseService.getItems()).start();
        return HttpStatus.OK;
    }
}
