package com.aurora.springbatch.repository;

import com.aurora.springbatch.entity.TimeCheckLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeCheckLogRepository extends JpaRepository<TimeCheckLog, Long> {

    List<TimeCheckLog> findAll();

}
