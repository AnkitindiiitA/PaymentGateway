package com.myproject.razorpay.merchant.service;

import com.myproject.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.myproject.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.myproject.razorpay.merchant.dto.response.ApiKeyResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ApiKeyService {
    ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request);

    List<ApiKeyResponse> listByMerchant(UUID merchantId);

    void revoke(UUID merchantId, UUID keyId);

    ApiKeyCreateResponse rotate(UUID merchantId, UUID keyId);
}
