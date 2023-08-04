package com.dev.ForecastApiTest.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.ForecastApiTest.dto.UserDto;
import com.dev.ForecastApiTest.model.admin.Administration;
import com.dev.ForecastApiTest.model.admin.Authority;
import com.dev.ForecastApiTest.repository.UserRepository;
import com.dev.ForecastApiTest.utils.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public Administration signup(UserDto userDto) {
        if (userRepository.findOneByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        // 가입되어 있지 않은 회원이면,
        // 권한 정보 만들고
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        // 유저 정보를 만들어서 save
        Administration user = Administration.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .roles(userDto.getRole())
                .activated(true)
                .build();

        return userRepository.save(user);
    }

    // 유저,권한 정보를 가져오는 메소드
    @Transactional(readOnly = true)
    public Optional<Administration> getUserWithAuthorities(String username) {
        return userRepository.findOneByUsername(username);
    }

    // 현재 securityContext에 저장된 username의 정보만 가져오는 메소드
    @Transactional(readOnly = true)
    public Optional<Administration> getMyUserWithAuthorities() {
    	System.out.println("getMyUserWithAuthorities");
        return SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findOneByUsername);
    }
}
