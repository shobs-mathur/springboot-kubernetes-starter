package com.shobs.springboot.springenvers.repository;

import com.shobs.springboot.springenvers.domain.SequenceNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepository extends CrudRepository<SequenceNumber, Long> {

}
