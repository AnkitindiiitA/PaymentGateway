package com.myproject.razorpay.payment.processor.strategy;

import com.myproject.razorpay.common.util.RandomizerUtil;
import com.myproject.razorpay.payment.processor.PaymentProcessor;
import com.myproject.razorpay.payment.processor.dto.PaymentProcessorRequest;
import com.myproject.razorpay.payment.processor.dto.PaymentProcessorResponse;
import org.springframework.stereotype.Component;


@Component
public class UpiPaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentProcessorResponse charge(PaymentProcessorRequest request) {
        final String BANK_CODE_FAIL = "fail@okaxis";

        String bankCode = request.methodDetails() != null?
                request.methodDetails().get("vpa").toString(): null;

        //simulation
        if(BANK_CODE_FAIL.equals(bankCode)){
            return new PaymentProcessorResponse.Failure("UPI_REJECTED",
                    "Bank rejected the transaction registration"
            );
        }

        String processorRef = "UPI_PROCESSOR_"+ RandomizerUtil.randomBase64(16);

//        String bankRef = "BANK_REF"+RandomizerUtil.randomBase64(16);

        return new PaymentProcessorResponse.Pending(processorRef);
    }
}
