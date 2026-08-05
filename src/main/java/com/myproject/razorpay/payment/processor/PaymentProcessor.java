package com.myproject.razorpay.payment.processor;

import com.myproject.razorpay.payment.processor.dto.PaymentProcessorRequest;
import com.myproject.razorpay.payment.processor.dto.PaymentProcessorResponse;

public interface PaymentProcessor {

    PaymentProcessorResponse charge(PaymentProcessorRequest request);

}
