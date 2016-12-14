package com.javaforgood.goodandgenerous.data.repository;


import com.javaforgood.goodandgenerous.data.model.DailyJobLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyJobLogRepository extends JpaRepository<DailyJobLog, Long> {

}
