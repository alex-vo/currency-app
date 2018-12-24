package me.bill.controller;

import me.bill.dto.RequestDTO;
import me.bill.entity.Request;
import me.bill.repository.RequestRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Type;
import java.util.List;

@Controller
public class RequestController {

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "requests", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<RequestDTO> getCurrencyByCode() {
        List<Request> requests = requestRepository.findByOrderByDateTimeDesc();
        Type targetType = new TypeToken<List<RequestDTO>>() {}.getType();
        return modelMapper.map(requests, targetType);
    }

}
