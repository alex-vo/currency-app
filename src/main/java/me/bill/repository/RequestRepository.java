package me.bill.repository;

import me.bill.entity.Request;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface RequestRepository extends PagingAndSortingRepository<Request, UUID> {
}
