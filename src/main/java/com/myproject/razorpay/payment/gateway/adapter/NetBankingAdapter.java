package com.myproject.razorpay.payment.gateway.adapter;

import com.myproject.razorpay.payment.gateway.PaymentAdapter;
import com.myproject.razorpay.payment.gateway.dto.PaymentRequest;
import com.myproject.razorpay.payment.gateway.dto.PaymentResult;

public class NetBankingAdapter implements PaymentAdapter {

    @Override
    public PaymentResult initiate(PaymentRequest request){
        return null;
    }
}
