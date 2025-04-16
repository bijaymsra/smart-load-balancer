package com.smart.backend.config;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.util.Config;

public class KubernetesClientConfig {
    public static ApiClient getClient() throws Exception {
        return Config.defaultClient(); // works inside Kubernetes cluster
    }
}