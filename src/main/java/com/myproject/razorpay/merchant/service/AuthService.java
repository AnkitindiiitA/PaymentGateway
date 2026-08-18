package com.myproject.razorpay.merchant.service;

import com.myproject.razorpay.merchant.dto.request.LoginRequest;
import com.myproject.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.myproject.razorpay.merchant.dto.response.LoginResponse;
import com.myproject.razorpay.merchant.dto.response.MerchantResponse;
import jakarta.validation.Valid;

public interface AuthService {
    public MerchantResponse singup(MerchantSignupRequest request);

    LoginResponse login(@Valid LoginRequest request);
}
