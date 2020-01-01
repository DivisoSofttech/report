package com.diviso.graeshoppe.report.client.product.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.diviso.graeshoppe.report.client.product.ClientConfiguration;

@FeignClient(name="${product.name:product}", url="${product.url:dev.ci1.divisosofttech.com:8084/}", configuration = ClientConfiguration.class)
public interface UserResourceApiClient extends UserResourceApi {
}