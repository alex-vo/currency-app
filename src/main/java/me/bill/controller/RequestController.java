package me.bill.controller;

import lombok.extern.slf4j.Slf4j;
import me.bill.dto.RequestDTO;
import me.bill.entity.Request;
import me.bill.repository.RequestRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.List;

@Controller
@Slf4j
public class RequestController {

    @Value("${request.log.page.size:10}")
    private Integer pageSize;

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "requests/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<RequestDTO> getRequests(@PathVariable("page") Integer page,
                                        @RequestParam("dateTime") String dateTime) {
        log.info("Getting requests at page {} with date greater than {}", page, dateTime);
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTime);
        Page<Request> requests = requestRepository.findByDateTimeLessThan(
                zonedDateTime,
                PageRequest.of(page, pageSize, Sort.Direction.DESC, "dateTime")
        );
        Type targetType = new TypeToken<List<RequestDTO>>() {}.getType();
        return modelMapper.map(requests.getContent(), targetType);
    }

}
