package com.diviso.graeshoppe.report.client.payment.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.diviso.graeshoppe.report.client.payment.ClientConfiguration;

@FeignClient(name="${payment.name:payment}", url="${payment.url:dev.ci2.divisosofttech.com:9090/}", configuration = ClientConfiguration.class)
public interface PaypalCommandResourceApiClient extends PaypalCommandResourceApi {
}