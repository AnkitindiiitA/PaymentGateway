package com.myproject.razorpay.payment.config;

import com.myproject.razorpay.common.enums.PaymentMethod;
import com.myproject.razorpay.payment.entity.Payment;
import com.myproject.razorpay.payment.gateway.PaymentAdapter;
import com.myproject.razorpay.payment.gateway.adapter.CardPaymentAdapter;
import com.myproject.razorpay.payment.gateway.adapter.NetBankingAdapter;
import com.myproject.razorpay.payment.gateway.adapter.UpiPaymentAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PaymentAdapterConfig {

    @Bean
    Map<PaymentMethod, PaymentAdapter> paymentAdapterMap(){
        return Map.of(
                PaymentMethod.CARD, new CardPaymentAdapter(),
                PaymentMethod.NETBANKING, new NetBankingAdapter(),
                PaymentMethod.UPI, new UpiPaymentAdapter()

        );
    }
}
