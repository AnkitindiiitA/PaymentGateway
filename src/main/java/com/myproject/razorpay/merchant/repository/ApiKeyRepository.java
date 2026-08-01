package com.myproject.razorpay.merchant.repository;

import com.myproject.razorpay.merchant.dto.response.ApiKeyResponse;
import com.myproject.razorpay.merchant.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ApiKeyRepository extends JpaRepository<ApiKey, UUID> {
    List<ApiKey> findByMerchant_id(UUID merchantId);

}
