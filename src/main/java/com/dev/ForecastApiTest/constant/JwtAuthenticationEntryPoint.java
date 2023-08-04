package com.dev.ForecastApiTest.constant;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    
	@Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, java.io.IOException {
		int exception = (int) request.getAttribute("exception");
		if(exception == 1003) {
            setResponse(response, ErrorCode.UNKNOWN_ERROR);
        }
        //잘못된 타입의 토큰인 경우
		else if(exception == ErrorCode.WRONG_TYPE_TOKEN.getCode()) {
            setResponse(response, ErrorCode.WRONG_TYPE_TOKEN);
        }
        //토큰 만료된 경우
        else if(exception == ErrorCode.EXPIRED_TOKEN.getCode()) {
            setResponse(response, ErrorCode.EXPIRED_TOKEN);
        }
        //지원되지 않는 토큰인 경우
        else if(exception == ErrorCode.UNSUPPORTED_TOKEN.getCode()) {
           
        	setResponse(response, ErrorCode.UNSUPPORTED_TOKEN);
        }
        else if(exception == ErrorCode.UNKNOWN_ERROR.getCode()) {
        	
        	setResponse(response, ErrorCode.UNKNOWN_ERROR);
        }
        else {
            setResponse(response, ErrorCode.ACCESS_DENIED);
        }
		// 유효한 자격증명을 제공하지 않고 접근하려 할때 401
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
	
	//한글 출력을 위해 getWriter() 사용
    private void setResponse(HttpServletResponse response, ErrorCode code) throws IOException {
//        System.out.println("setResponse");
//        System.out.println(code.toString());
    	response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject responseJson = new JSONObject();
        responseJson.put("message", code.getMessage());
        responseJson.put("code", code.getCode());

        response.getWriter().print(responseJson);
    }
}
