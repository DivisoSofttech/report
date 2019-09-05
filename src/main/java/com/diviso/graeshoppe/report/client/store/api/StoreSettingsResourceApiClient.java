package com.diviso.graeshoppe.report.client.store.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.diviso.graeshoppe.report.client.store.StoreClientConfiguration;

@FeignClient(name="${store.name:store}", url="${store.url:dev.ci1.divisosofttech.com:8071/}", configuration = StoreClientConfiguration.class)
public interface StoreSettingsResourceApiClient extends StoreSettingsResourceApi {
}