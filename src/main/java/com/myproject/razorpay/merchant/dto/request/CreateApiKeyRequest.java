package com.myproject.razorpay.merchant.dto.request;

import com.myproject.razorpay.common.enums.Environment;

public record CreateApiKeyRequest(
        Environment environment
) {
}
