package com.myproject.razorpay.payment.config;

import com.myproject.razorpay.common.enums.PaymentMethod;
import com.myproject.razorpay.payment.entity.Payment;
import com.myproject.razorpay.payment.gateway.PaymentAdapter;
import com.myproject.razorpay.payment.gateway.adapter.CardPaymentAdapter;
import com.myproject.razorpay.payment.gateway.adapter.NetBankingAdapter;
import com.myproject.razorpay.payment.gateway.adapter.UpiPaymentAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class PaymentAdapterConfig {

    private final NetBankingAdapter netBankingAdapter;
    private final CardPaymentAdapter cardPaymentAdapter;
    private final UpiPaymentAdapter upiPaymentAdapter;

    @Bean
    public Map<PaymentMethod, PaymentAdapter> paymentAdapterMap(){
        return Map.of(
                PaymentMethod.CARD, cardPaymentAdapter,
                PaymentMethod.NETBANKING, netBankingAdapter,
                PaymentMethod.UPI, upiPaymentAdapter

        );
    }
}
