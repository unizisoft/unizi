package config.root;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.AntPathMatcher;

import egovframework.example.cmmn.EgovSampleExcepHndlr;
import egovframework.example.cmmn.EgovSampleOthersExcepHndlr;
import egovframework.rte.fdl.cmmn.aspect.ExceptionTransfer;
import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;
import egovframework.rte.fdl.cmmn.exception.manager.DefaultExceptionHandleManager;

@Configuration
@EnableAspectJAutoProxy
public class ContextAspect {
	@Bean
	public AopExceptionTransfer aopExceptionTransfer(ExceptionTransfer exceptionTransfer) {
		AopExceptionTransfer aopExceptionTransfer = new AopExceptionTransfer();
		aopExceptionTransfer.setExceptionTransfer(exceptionTransfer);
		return aopExceptionTransfer;
	}
	
	@Bean
	public ExceptionTransfer exceptionTransfer(@Qualifier("defaultExceptionHandleManager") DefaultExceptionHandleManager defaultExceptionHandleManager,@Qualifier("otherExceptionHandleManager") DefaultExceptionHandleManager otherExceptionHandleManager)
	{
		ExceptionTransfer exceptionTransfer = new ExceptionTransfer();
		exceptionTransfer.setExceptionHandlerService(new DefaultExceptionHandleManager[] {defaultExceptionHandleManager,otherExceptionHandleManager} );
		return exceptionTransfer;
	}
	@Bean
	public DefaultExceptionHandleManager defaultExceptionHandleManager(AntPathMatcher antPathMater,EgovSampleExcepHndlr egovHandler) {
		DefaultExceptionHandleManager defaultExceptionHandleManager = new DefaultExceptionHandleManager();
		defaultExceptionHandleManager.setReqExpMatcher(antPathMater);
		defaultExceptionHandleManager.setPatterns(new String[] {"**service.impl.*"});
		defaultExceptionHandleManager.setHandlers(new ExceptionHandler[]{egovHandler});
		return defaultExceptionHandleManager;
	}
	
	@Bean
	public DefaultExceptionHandleManager otherExceptionHandleManager(AntPathMatcher antPathMater,EgovSampleExcepHndlr otherHandler) {
		DefaultExceptionHandleManager otherExceptionHandleManager = new DefaultExceptionHandleManager();
		otherExceptionHandleManager.setReqExpMatcher(antPathMater);
		otherExceptionHandleManager.setPatterns(new String[] {"**service.impl.*"});
		otherExceptionHandleManager.setHandlers(new ExceptionHandler[] {otherHandler});
		return otherExceptionHandleManager;
	}
	
	@Bean
	public EgovSampleExcepHndlr egovHandler() {
		EgovSampleExcepHndlr egovSampleExcepHndlr = new EgovSampleExcepHndlr();
		return egovSampleExcepHndlr;
	}
	
	@Bean
	public EgovSampleOthersExcepHndlr egovSampleOthersExcepHndlr() {
		EgovSampleOthersExcepHndlr egovSampleOthersExcepHndlr = new EgovSampleOthersExcepHndlr();
		return egovSampleOthersExcepHndlr;
	}
	
	
}
