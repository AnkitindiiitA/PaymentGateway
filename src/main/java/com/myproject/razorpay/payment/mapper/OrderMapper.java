package com.myproject.razorpay.payment.mapper;

import com.myproject.razorpay.payment.dto.response.OrderResponse;
import com.myproject.razorpay.payment.entity.OrderRecord;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    OrderResponse toResponse(OrderRecord orderRecord);
}
