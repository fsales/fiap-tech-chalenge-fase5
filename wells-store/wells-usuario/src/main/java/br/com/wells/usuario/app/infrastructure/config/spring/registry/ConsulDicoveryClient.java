package br.com.wells.usuario.app.infrastructure.config.spring.registry;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryClient;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ConsulDicoveryClient {

	private final ConsulDiscoveryClient discoveryClient;

	public String getServiceUrl(String serviceId) {
		List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
		if (!instances.isEmpty()) {
			ServiceInstance instance = instances.get(0);
			return instance.getUri().toString();
		}
		else {
			throw new RuntimeException("Service not found in Consul: " + serviceId);
		}
	}

}
