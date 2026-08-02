package com.myproject.razorpay.merchant.service.impl;

import com.myproject.razorpay.common.exception.ResourceNotFoundException;
import com.myproject.razorpay.common.util.RandomizerUtil;
import com.myproject.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.myproject.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.myproject.razorpay.merchant.dto.response.ApiKeyResponse;
import com.myproject.razorpay.merchant.entity.ApiKey;
import com.myproject.razorpay.merchant.entity.Merchant;
import com.myproject.razorpay.merchant.mapper.ApiKeyMapper;
import com.myproject.razorpay.merchant.repository.ApiKeyRepository;
import com.myproject.razorpay.merchant.repository.MerchantRepository;
import com.myproject.razorpay.merchant.service.ApiKeyService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ApiKeyServiceImpl implements ApiKeyService {
    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;
    private final ApiKeyMapper apiKeyMapper;

    @Override
    @Transactional
    public ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request){
        Merchant merchant = merchantRepository.findById(merchantId).
                orElseThrow(() -> new ResourceNotFoundException("merchant", merchantId));

        String keyId = "rzp_" + request.environment().name().toLowerCase() + "_" + RandomizerUtil.randomBase64(24);
        String rawSecret = RandomizerUtil.randomBase64(40); //Todo replace with cryptography hex

        ApiKey apiKey = ApiKey.builder()
                .merchant(merchant)
                .keyId(keyId)
                .keySecretHash(rawSecret) //todo: encode with bcryptPasswordencoder
                .environment(request.environment())
                .build();

        apiKey = apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId(), keyId, rawSecret, request.environment());
    }

    @Override
    public List<ApiKeyResponse> listByMerchant(UUID merchantId) {
        return apiKeyMapper.toResponseList(apiKeyRepository.findByMerchant_id(merchantId));


    }

    @Override
    @Transactional
    public void revoke(UUID merchantId, UUID keyId) {
        ApiKey key = apiKeyRepository.findById(keyId)
                .filter(k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey",keyId));

        key.setEnabled(false);

        apiKeyRepository.save(key);

    }

    @Override
    @Transactional
    public ApiKeyCreateResponse rotate(UUID merchantId, UUID keyId) {
        ApiKey apiKey = apiKeyRepository.findById(keyId)
                .filter(k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey",keyId));

        if(!apiKey.isEnabled()) throw new RuntimeException("Cannot rotate a disabled key");

        String newRawSecret = RandomizerUtil.randomBase64(40); //Todo replace with cryptography hex

        apiKey.setPreviousKeySecretHash(apiKey.getKeySecretHash());
        apiKey.setKeySecretHash(newRawSecret);

        apiKey.setRotatedAt(LocalDateTime.now());
        apiKey.setGracePeriodExpiresAt(LocalDateTime.now().plusHours(24));

        apiKey  = apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId(), apiKey.getKeyId(), newRawSecret, apiKey.getEnvironment());
    }


}
