package com.smart.backend.controller;

import com.google.gson.internal.LinkedTreeMap;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.CustomObjectsApi;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.Config;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;

import com.smart.backend.service.ServiceMetricService;


import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "http://localhost:3000")
public class ServiceController {

    private final CoreV1Api coreV1Api;
    private final CustomObjectsApi customObjectsApi;
    private final AppsV1Api appsV1Api;

    private final ServiceMetricService metricService;


    @Autowired
    public ServiceController(ServiceMetricService metricService) throws IOException {
        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);
        this.coreV1Api = new CoreV1Api(client);
        this.customObjectsApi = new CustomObjectsApi(client);
        this.appsV1Api = new AppsV1Api(client);
        this.metricService = metricService;
    }


    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllServices() {
        List<Map<String, Object>> serviceList = new ArrayList<>();

        try {
            V1PodList podList = coreV1Api.listNamespacedPod("default", null, null, null,
                    null, null, null, null, null, null, false);

            for (V1Pod pod : podList.getItems()) {
                String podName = pod.getMetadata().getName();
                Map<String, Object> podInfo = new HashMap<>();
                podInfo.put("id", podName);
                podInfo.put("name", podName);
                podInfo.put("status", pod.getStatus().getPhase());
            
                int totalCPU = 0;
                int totalMemory = 0;
            
                try {
                    Object metrics = customObjectsApi.getNamespacedCustomObject(
                            "metrics.k8s.io", "v1beta1", "default", "pods", podName
                    );
            
                    LinkedTreeMap<String, Object> metricMap = (LinkedTreeMap<String, Object>) metrics;
                    List<LinkedTreeMap<String, Object>> containers = (List<LinkedTreeMap<String, Object>>) metricMap.get("containers");
            
                    for (LinkedTreeMap<String, Object> container : containers) {
                        LinkedTreeMap<String, String> usage = (LinkedTreeMap<String, String>) container.get("usage");
                        totalCPU += parseCPU(usage.get("cpu"));
                        totalMemory += parseMemory(usage.get("memory"));
                    }
            
                } catch (Exception e) {
                    totalCPU = 0;
                    totalMemory = 0;
                }
            
                podInfo.put("cpuUsage", totalCPU);
                podInfo.put("memoryUsage", totalMemory);
            
                // ✅ Save to MySQL
                metricService.saveMetric(podName, pod.getStatus().getPhase(), totalCPU, totalMemory);
            
                serviceList.add(podInfo);
            }
            

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }

        return ResponseEntity.ok(serviceList);
    }

    @PostMapping("/load")
    public ResponseEntity<String> simulateLoad(@RequestParam String url, @RequestParam int requests) {
        try {
            // Constructing the load test command
            String cmd = String.format("ab -n %d -c 100 %s", requests, url);
            Process process = Runtime.getRuntime().exec(cmd);
            return ResponseEntity.ok("Load test started for: " + url);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to start load: " + e.getMessage());
        }
    }

    private int parseCPU(String cpuStr) {
        if (cpuStr.endsWith("n")) return Integer.parseInt(cpuStr.replace("n", "")) / 1000000;
        if (cpuStr.endsWith("m")) return Integer.parseInt(cpuStr.replace("m", ""));
        return (int) (Double.parseDouble(cpuStr) * 1000);
    }

    private int parseMemory(String memStr) {
        if (memStr.endsWith("Ki")) return Integer.parseInt(memStr.replace("Ki", "")) / 1024;
        if (memStr.endsWith("Mi")) return Integer.parseInt(memStr.replace("Mi", ""));
        if (memStr.endsWith("Gi")) return Integer.parseInt(memStr.replace("Gi", "")) * 1024;
        return (int) (Double.parseDouble(memStr) / (1024 * 1024));
    }


}