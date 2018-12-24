package me.bill.controller;

import me.bill.dto.RequestDTO;
import me.bill.entity.Request;
import me.bill.repository.RequestRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Type;
import java.util.List;

@Controller
public class RequestController {

    public static final Integer PAGE_SIZE = 10;

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "requests/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<RequestDTO> getRequests(@PathVariable("page") Integer page) {
        Page<Request> requests = requestRepository.findAll(PageRequest.of(page, PAGE_SIZE, Sort.Direction.DESC, "dateTime"));
        Type targetType = new TypeToken<List<RequestDTO>>() {}.getType();
        return modelMapper.map(requests.getContent(), targetType);
    }

}
