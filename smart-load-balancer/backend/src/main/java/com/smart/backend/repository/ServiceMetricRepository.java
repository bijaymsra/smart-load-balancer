package com.smart.backend.repository;

import com.smart.backend.model.ServiceMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceMetricRepository extends JpaRepository<ServiceMetric, Long> {
    ServiceMetric findTopByPodNameOrderByTimestampDesc(String podName);

    @Query(value = "SELECT * FROM service_metric ORDER BY timestamp ASC LIMIT :limit", nativeQuery = true)
    List<ServiceMetric> findOldestEntries(@Param("limit") int limit);
}
