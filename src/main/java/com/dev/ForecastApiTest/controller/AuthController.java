package com.dev.ForecastApiTest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ForecastApiTest.constant.TokenProvider;
import com.dev.ForecastApiTest.dto.LoginDto;
import com.dev.ForecastApiTest.dto.TokenDto;
import com.dev.ForecastApiTest.filter.JwtFilter;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    
	private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authorize(@Valid @RequestBody LoginDto loginDto) {
    	System.out.println("authenticate");
    	UsernamePasswordAuthenticationToken authenticationToken = 
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        

        // authenticate 메소드가 실행이 될 때 CustomUserDetailsService class의 loadUserByUsername 메소드가 실행
    	try {
    		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    		 // 해당 객체를 SecurityContextHolder에 저장하고
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
            String jwt = tokenProvider.createToken(authentication);

            HttpHeaders httpHeaders = new HttpHeaders();
            // response header에 jwt token에 넣어줌
            httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
         // tokenDto를 이용해 response body에도 넣어서 리턴
            return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    	}catch(UsernameNotFoundException e) {
    		System.out.println("UsernameNotFoundException");
    		Map<String, Object> result = new HashMap<>();
            result.put("status", 1003);
            result.put("message", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.valueOf(404));
    	}catch(BadCredentialsException e) {
    		System.out.println("BadCredentialsException");
    		Map<String, Object> result = new HashMap<>();
            result.put("status", 10032);
            result.put("message", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.valueOf(404));
    	}
    	
        
    }
}