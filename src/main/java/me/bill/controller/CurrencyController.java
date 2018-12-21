package me.bill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CurrencyController {

    @GetMapping("currency/{code}")
    @ResponseBody
    public String get(@PathVariable("code") String code) {
        return code;
    }

}
