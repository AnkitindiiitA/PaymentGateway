package com.myproject.razorpay.merchant.service.impl;

import com.myproject.razorpay.common.enums.MerchantStatus;
import com.myproject.razorpay.common.enums.UserRole;
import com.myproject.razorpay.common.exception.DuplicateResourceException;
import com.myproject.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.myproject.razorpay.merchant.dto.response.MerchantResponse;
import com.myproject.razorpay.merchant.entity.AppUser;
import com.myproject.razorpay.merchant.entity.Merchant;
import com.myproject.razorpay.merchant.mapper.MerchantMapper;
import com.myproject.razorpay.merchant.repository.AppUserRepository;
import com.myproject.razorpay.merchant.repository.MerchantRepository;
import com.myproject.razorpay.merchant.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;
    private final MerchantRepository merchantRepository;
    private final MerchantMapper merchantMapper;

    @Override
    @Transactional
    public MerchantResponse singup(MerchantSignupRequest request) {
        if(merchantRepository.existsByEmail(request.email())){
            throw new DuplicateResourceException("DUPLICATE_MERCHANT_EMAIL",
                    "Merchant with email already exists: "+request.email());
        }

        Merchant merchant = merchantMapper.toEntityFromSignUpRequest(request);
        merchant.setStatus(MerchantStatus.PENDING_KYC);

        merchant = merchantRepository.save(merchant);

        AppUser appUser = AppUser.builder()
                .email(request.email())
                .merchant(merchant)
                .passwordHash(request.password()) //Todo: encrypt using bcrypt
                .role(UserRole.OWNER)
                .build();

        appUserRepository.save(appUser);


        return merchantMapper.toResponse(merchant);
    }
}
