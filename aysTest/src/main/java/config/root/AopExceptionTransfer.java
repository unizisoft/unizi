package config.root;

//ays
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;

import egovframework.rte.fdl.cmmn.aspect.ExceptionTransfer;

public class AopExceptionTransfer {

	private ExceptionTransfer exceptionTransfer;
	
	public void setExceptionTransfer(ExceptionTransfer exceptionTransfer) {
		this.exceptionTransfer = exceptionTransfer;
	}
	
	@Pointcut("execution(* egovframework.example..impl.*Impl.*(..))")
	private void exceptionTransferService() {}
	
	@AfterThrowing(pointcut = "exceptionTransferService()",throwing="ex")
	public void doAfterThrowingExceptionTransferService(JoinPoint thisJoinPoint, Exception ex) throws Exception{
			exceptionTransfer.transfer(thisJoinPoint, ex);
	}
}
