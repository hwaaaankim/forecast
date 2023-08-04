package com.dev.ForecastApiTest.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dev.ForecastApiTest.constant.ErrorCode;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter{
	
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
	
		System.out.println("doFilterInternal");
		response.setCharacterEncoding("utf-8");
        try{
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e){
            //만료 에러
            request.setAttribute("exception", ErrorCode.EXPIRED_TOKEN.getCode());
            filterChain.doFilter(request, response);

        } catch (io.jsonwebtoken.security.SecurityException e ){

            //변조 에러
            request.setAttribute("exception", ErrorCode.WRONG_TYPE_TOKEN.getCode());
            filterChain.doFilter(request, response);
        }catch(MalformedJwtException e) {
        	
        	 request.setAttribute("exception", ErrorCode.UNKNOWN_ERROR.getCode());
        	 filterChain.doFilter(request, response);
        	
        } catch(HttpRequestMethodNotSupportedException e) {
        	request.setAttribute("exception", ErrorCode.METHOD_ERROR.getCode());
        	filterChain.doFilter(request, response);
        } 

    }
}
