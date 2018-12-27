package me.bill.repository;

import me.bill.entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.ZonedDateTime;
import java.util.UUID;

public interface RequestRepository extends PagingAndSortingRepository<Request, UUID> {
    Page<Request> findByDateTimeLessThan(ZonedDateTime zonedDateTime, Pageable pageable);
}
