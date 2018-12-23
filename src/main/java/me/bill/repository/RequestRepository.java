package me.bill.repository;

import me.bill.entity.Request;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RequestRepository extends CrudRepository<Request, UUID> {
}
