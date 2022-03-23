package com.wipro.bankofamerica.estore.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.wipro.bankofamerica.estore.model.FourDigitNumber;
@Repository
public interface FourDigitNumberRepository extends CrudRepository<FourDigitNumber, Serializable> {

}
