package com.shobs.springboot.springenvers.repository;

import com.shobs.springboot.springenvers.domain.LimitEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimitEntityRepository extends CrudRepository<LimitEntity, Long> {

    Optional<LimitEntity> findByLimitKey(String limitKey);
}
