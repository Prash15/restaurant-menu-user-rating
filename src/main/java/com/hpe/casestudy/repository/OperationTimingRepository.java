package com.hpe.casestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hpe.casestudy.entity.OperationTiming;

@Repository
public interface OperationTimingRepository extends JpaRepository<OperationTiming, Long>{

}
