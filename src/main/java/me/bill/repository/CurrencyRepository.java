package me.bill.repository;

import me.bill.entity.Currency;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CurrencyRepository extends CrudRepository<Currency, UUID> {

    List<Currency> getByCodeIgnoreCase(String code);

}
