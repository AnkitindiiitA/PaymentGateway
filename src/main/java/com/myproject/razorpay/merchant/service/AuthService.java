package com.myproject.razorpay.merchant.service;

import com.myproject.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.myproject.razorpay.merchant.dto.response.MerchantResponse;
import jakarta.validation.Valid;

public interface AuthService {
    public MerchantResponse singup(MerchantSignupRequest request);
}
