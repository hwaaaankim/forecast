package com.dev.ForecastApiTest.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		Map<String, Object> result = new HashMap<>();
        result.put("status", 1008);
        result.put("message", e.getMessage());
        return new ResponseEntity<>(result, HttpStatus.valueOf(404));
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Post Method를 지원하지 않습니다.");
    }
	
	@ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Object> malformedJwtException(MalformedJwtException e) {
		Map<String, Object> result = new HashMap<>();
        result.put("status", 1003);
        result.put("message", e.getMessage());
        return new ResponseEntity<>(result, HttpStatus.valueOf(404));
    }
	
	@ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> usernameNotFoundException(UsernameNotFoundException e) {
		Map<String, Object> result = new HashMap<>();
        result.put("status", 10013);
        result.put("message", e.getMessage());
        return new ResponseEntity<>(result, HttpStatus.valueOf(404));
    }
//	private String CLASS_NAME = "Common";
//
//    private Map<String, Object> errorMap = new LinkedHashMap<>();
//
//    @Value("${jwt.secret-key}")
//    private String secretKey;
//
//    /**
//     * 공통 에러 처리 핸들러 (에러메세지 처리)
//     * 로그상에 ERROR 메세지로 기록되고 두레이 메신저 알림이 옴
//     * @param e
//     * @param request
//     * @param webRequest
//     * @return
//     * @throws Exception
//     */
//    @ExceptionHandler({
//            // HTTP 요청 에러
//            HttpMessageNotReadableException.class,
//            // 지원되지 않는 HTTP METHOD 에러 핸들러
//            HttpRequestMethodNotSupportedException.class,
//
//            // 데이터 등록 실패 에러핸들러
//            DataRegistrationFailedException.class,
//            // 임시 파일 적용 시 BODY가 없을 때
//            DataNotFoundException.class,
//            // 중복된 데이터 처리 에러 핸들러
//            DuplicateDataException.class,
//            // 데이터베이스 중복 키 에러
//            DuplicateKeyException.class,
//
//            // RestTemplate API 통신 에러 핸들러
//            RestClientException.class,
//            // RestTemplate API 통신 에러 핸들러
//            ResourceAccessException.class,
//
//            // 최대 파일 용량 초과 에러
//            MaxUploadSizeExceededException.class,
//            // 파일이 용량 제한 에러
//            SizeLimitExceededException.class,
//            // MultiPart 파일 전송 누락 에러
//            MissingServletRequestPartException.class,
//            // MultiPart 파일 전송 요청 에러
//            MultipartException.class,
//
//            //프로세스를 더이상 진행할 수 없을 때
//            UnprocessableException.class,
//            // 파일이 존재하지 않을 때
//            FileNotExistException.class,
//            // 파일 등록 파일 유무 체크
//            FileRequestFileNotException.class,
//            // 파일 덮어쓰기 여부 확인
//            FileDuplicateException.class,
//            // 파일 등록 필수 파라메터 체크
//            FileRequestParamRequiredException.class,
//
//            // JWT 토큰 복호화 ERROR 핸들러
//            InvalidSignatureException.class,
//
//            // 파라메터 유효성 체크 실패 에러 핸들러
//            ParameterValidationFailedException.class,
//            // 사용자 정의 필수 파라메터 누락 에러 핸들러
//            RequestParamRequiredException.class,
//            // Parameter Validation Error
//            MissingServletRequestParameterException.class,
//            // Parameter Validation Error
//            BindException.class,
//            // Parameter Validation Error
//            MethodArgumentNotValidException.class,
//
//            // AES 복호화 에러
//            AesDecryptException.class,
//            // AES 암호화 에러
//            AesEncryptException.class,
//
//            // 엑셀 데이터 처리중 에러
//            ExcelComponentException.class,
//            // 엑셀 업로드 필드 에러 핸들러
//            ExcelReaderFieldException.class,
//            // 엑셀 업로드 파일 에러 핸들러
//            ExcelReaderFileException.class,
//            // 엑셀 업로드 파일 확장자 에러 핸들러
//            ExcelReaderFileExtentionException.class,
//            // 엑셀 업로드 시 캐치 하지 못한 그외 에러
//            ExcelReaderException.class,
//    })
//    public CommonResult<?> CommonErrorException(Exception e, HttpServletRequest request, WebRequest webRequest) throws Exception {
//
//        // 공통에러상수 객체 셋팅
//        CommonError error = CommonError.getCommonError(e);
//
//        String ERROR_MSG = error.isException() && StringUtils.isNotEmpty(e.getMessage()) ? error.getMessage()+e.getMessage() : error.getMessage();
//
//        errorMap = ErrorUtils.setErrorMap(request, webRequest, secretKey); // 에러 맵 기본 셋팅
//
//        CommonResult<?> commonResult = null;
//        String CUSTOM_MSG = null; //커스텀 Exception Message
//
//        /* Exception 별 커스텀 처리 */
//        switch (error) {
//            case MaxUploadSizeExceededException: // 최대 파일 용량 초과 에러
//                long permittedSize=0L; //제한용량
//                long actualSize=0L; //요청 파일용량
//                MaxUploadSizeExceededException me = (MaxUploadSizeExceededException) e;
//                Throwable cause = me.getCause();
//                if(cause instanceof SizeLimitExceededException) {
//                    SizeLimitExceededException sizeLimit = (SizeLimitExceededException) cause;
//                    permittedSize = sizeLimit.getPermittedSize() != 0L ? sizeLimit.getPermittedSize()/1024/1024 : 0L;
//                    actualSize = sizeLimit.getActualSize() != 0L ? sizeLimit.getActualSize()/1024/1024 : 0L;
//                    CUSTOM_MSG = "최대 업로드 파일용량 제한초과 :: 제한용량 = "+permittedSize+"MB, 요청 파일용량 = "+actualSize+"MB";
//                }
//                ERROR_MSG += "최대 "+permittedSize+"MB까지 등록할 수 있습니다: 요청 파일용량 = "+actualSize+"MB";
//                break;
//            case BindException: // Parameter Validation Error
//                ERROR_MSG = getBindResultFieldErrorMessage(((BindException) e).getBindingResult());
//                break;
//            case MethodArgumentNotValidException: // Parameter Validation Error
//                ERROR_MSG = getBindResultFieldErrorMessage(((MethodArgumentNotValidException) e).getBindingResult());
//                break;
//            case ExcelReaderFieldException: // 엑셀 업로드 필드 에러 핸들러
//                commonResult = new CommonResult<>(error.getResCode(), ERROR_MSG, ExcelReader.errorFieldList);
//                errorMap.put("CommonResult", commonResult);
//                break;
//        }
//        if(Objects.isNull(commonResult))
//            commonResult = new CommonResult<>(error.getResCode(), ERROR_MSG, errorMap);
//
//        // Level 별 에러메시지 출력
//        ErrorUtils.logWriter(error, CLASS_NAME, ERROR_MSG, errorMap, e, CUSTOM_MSG);
//        return commonResult;
//    }
//
//    /**
//     * Handler 에서 예외처리 되지 않은 Exception 처리
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(Exception.class)
//    public CommonResult<Map<String, Object>> allException(Exception e, HttpServletRequest request, WebRequest webRequest) throws Exception {
//        String ERROR_MSG = e.getMessage();
//
//        errorMap = ErrorUtils.setErrorMap(request, webRequest, secretKey); // 에러 맵 기본 셋팅
//
//        ErrorUtils.errorWriter(CLASS_NAME, ERROR_MSG, errorMap, e); //에러메세지 출력
//        return new CommonResult<>(ERROR, ERROR_MSG, errorMap);
//    }
//
//    /**
//     * BindException Field 메세지 가공
//     * @param bindingResult
//     * @return
//     */
//    protected String getBindResultFieldErrorMessage(BindingResult bindingResult) {
//
//        Map<String, Object> resultMap = new LinkedHashMap<>();
//
//        resultMap.put("title","Parameter Validation Error");
//        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
//        List<Map<String, Object>> paramList = new ArrayList<>();
//        for (FieldError fieldError: fieldErrorList){
//
//            Map<String, Object> resultParam = new LinkedHashMap<>();
//            resultParam.put(fieldError.getField(),fieldError.getRejectedValue());
//            resultParam.put("message",fieldError.getDefaultMessage());
//            paramList.add(resultParam);
//            /*
//            log.debug("## ERROR getField = {}", fieldError.getField());
//            log.debug("## ERROR getRejectedValue = {}", fieldError.getRejectedValue());
//            log.debug("## ERROR getArguments = {}", fieldError.getArguments());
//            log.debug("## ERROR getCode = {}", fieldError.getCode());
//            log.debug("## ERROR getCodes = {}", fieldError.getCodes());
//            log.debug("## ERROR getObjectName = {}", fieldError.getObjectName());
//            log.debug("## ERROR getDefaultMessage = {}", fieldError.getDefaultMessage());
//            */
//        }
//        resultMap.put("fields",paramList);
//
//        return JsonUtils.toMapperPrettyJson(resultMap);
//    }
}
