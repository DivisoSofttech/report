package com.diviso.graeshoppe.report.client.product.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.diviso.graeshoppe.report.client.product.ProductClientConfiguration;

@FeignClient(name="${product.name:product}", url="${product.url:dev.ci1.divisosofttech.com:8084/}", configuration = ProductClientConfiguration.class)
public interface ReasonResourceApiClient extends ReasonResourceApi {
}