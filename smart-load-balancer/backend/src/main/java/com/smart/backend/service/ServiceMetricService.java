package com.smart.backend.service;

import com.smart.backend.model.ServiceMetric;
import com.smart.backend.repository.ServiceMetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceMetricService {

    private static final int MAX_ROWS = 100;

    @Autowired
    private ServiceMetricRepository repository;

    public void saveMetric(String podName, String status, int cpu, int memory) {
        // Save new metric
        ServiceMetric metric = ServiceMetric.builder()
                .podName(podName)
                .status(status)
                .cpuUsage(cpu)
                .memoryUsage(memory)
                .timestamp(LocalDateTime.now())
                .build();
        repository.save(metric);

        // Check total rows
        long totalCount = repository.count();

        if (totalCount > MAX_ROWS) {
            long toDelete = totalCount - MAX_ROWS;
            List<ServiceMetric> oldestEntries = repository.findOldestEntries((int) toDelete);
            repository.deleteAll(oldestEntries);
        }
    }
}
