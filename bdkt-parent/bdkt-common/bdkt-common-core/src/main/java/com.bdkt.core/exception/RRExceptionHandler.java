package com.bdkt.core.exception;

import com.bdkt.core.utils.ReturnEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public ReturnEntity handleRRException(RRException e){
		ReturnEntity re = new ReturnEntity();
		re.setCode(e.getCode());
		re.setMsg(e.getMessage());
		return re;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ReturnEntity handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return new ReturnEntity().errorLogic("数据库中已存在该记录");
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ReturnEntity handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		logger.error(e.getMessage(), e);
		return new ReturnEntity().errorLogic("违反数据完整性约束");
	}
	
	@ExceptionHandler(QueryTimeoutException.class)
	public ReturnEntity handleQueryTimeoutException(QueryTimeoutException e) {
		logger.error(e.getMessage(), e);
		return new ReturnEntity().errorLogic("查询超时或数据库服务中断" + e.getMessage());
	}
	
//	@ExceptionHandler(MyBatisSystemException.class)
//	public ReturnEntity MyBatisSystemException(MyBatisSystemException e) {
//		logger.error(e.getMessage(), e);
//		return new ReturnEntity().errorLogic("数据库异常，操作无法完成！"+ e.getMessage());
//	}
	@ExceptionHandler({MethodArgumentNotValidException.class})
    public ReturnEntity requestTypeMismatch(MethodArgumentNotValidException ex) {
        System.out.println("400..TypeMismatchException");
        return new ReturnEntity().error(getErrorMessage(ex));
    }
	private String getErrorMessage(MethodArgumentNotValidException exception) {
    	StringBuilder sb = new StringBuilder();
    	for(FieldError fieldError:exception.getBindingResult().getFieldErrors()) {
    		sb.append(fieldError.getDefaultMessage()).append(";");
    	}
    	return new String(sb);
    }

	@ExceptionHandler(Exception.class)
	public ReturnEntity handleException(Exception e){
		logger.error(e.getMessage(), e);
		return new ReturnEntity().error(e.getMessage());
	}
}
