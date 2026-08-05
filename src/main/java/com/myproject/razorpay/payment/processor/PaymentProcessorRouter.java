package com.myproject.razorpay.payment.processor;

import com.myproject.razorpay.common.enums.PaymentMethod;
import com.myproject.razorpay.common.exception.ResourceNotFoundException;
import com.myproject.razorpay.payment.processor.dto.PaymentProcessorRequest;
import com.myproject.razorpay.payment.processor.dto.PaymentProcessorResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaymentProcessorRouter {

    private Map<PaymentMethod, PaymentProcessor> paymentProcessors;

    public PaymentProcessorResponse charge(PaymentProcessorRequest request){
        PaymentProcessor processor = paymentProcessors.get(request.method());

        if(processor == null){
            throw new IllegalArgumentException("No payment processor registered for method: "+ request.method());
        }

        return processor.charge(request);
    }
}
