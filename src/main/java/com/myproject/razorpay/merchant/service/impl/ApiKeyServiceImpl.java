package com.myproject.razorpay.merchant.service.impl;

import com.myproject.razorpay.common.exception.ResourceNotFoundException;
import com.myproject.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.myproject.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.myproject.razorpay.merchant.entity.ApiKey;
import com.myproject.razorpay.merchant.entity.Merchant;
import com.myproject.razorpay.merchant.repository.ApiKeyRepository;
import com.myproject.razorpay.merchant.repository.MerchantRepository;
import com.myproject.razorpay.merchant.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiKeyServiceImpl implements ApiKeyService {
    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;
    @Override
    public ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request){
        Merchant merchant = merchantRepository.findById(merchantId).
                orElseThrow(() -> new ResourceNotFoundException("merchant", merchantId));

        String keyId = "rzp_"+request.environment().name().toUpperCase()+"big_random_string";
        String rawSecret = "big_random_secret"; //Todo replace with cryptography hex

        ApiKey apiKey = ApiKey.builder()
                .merchant(merchant)
                .keyId(keyId)
                .keySecretHash(rawSecret) //todo: encode with bcrypt encoder
                .environment(request.environment())
                .build();

        apiKey = apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId(), keyId, rawSecret, request.environment());
    }
}
