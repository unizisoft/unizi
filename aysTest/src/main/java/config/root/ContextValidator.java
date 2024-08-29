package config.root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springmodules.validation.commons.DefaultBeanValidator;
import org.springmodules.validation.commons.DefaultValidatorFactory;
import org.springmodules.validation.commons.ValidatorFactory;

public class ContextValidator {
	
	@Autowired
	ApplicationContext ac;
	
	@Bean
	public DefaultBeanValidator beanValidator(ValidatorFactory validatorFactory) {
		DefaultBeanValidator defaultBeanValidator = new DefaultBeanValidator();
		defaultBeanValidator.setValidatorFactory(validatorFactory);
		return defaultBeanValidator;
	}
	
	
	@Bean
	public DefaultBeanValidator defaultBeanValidator(ValidatorFactory validatorFactory) {
		DefaultBeanValidator defaultBeanValidator = new DefaultBeanValidator();
		defaultBeanValidator.setValidatorFactory(validatorFactory);
		return defaultBeanValidator;
	}
	
	@Bean 
	public DefaultValidatorFactory defaultValidatorFactory() {
		DefaultValidatorFactory defaultValidatorFactory = new DefaultValidatorFactory();
		ac.getResource("/WEB-INF/config/egovframework/validator/validator-rules.xml");
		//추상클래스는 new 객체 생성 불가
		
		/** 이렇게 해도 되고
		Resource[] rs =  {ac.getResource("/WEB-INF/config/egovframework/validator/validator-rules.xml"),ac.getResource("/WEB-INF/config/egovframework/validator/validator.xml")};
		defaultValidatorFactory.setValidationConfigLocations(rs);
		**/
		defaultValidatorFactory.setValidationConfigLocations(new Resource[] {ac.getResource("/WEB-INF/config/egovframework/validator/validator-rules.xml"),ac.getResource("/WEB-INF/config/egovframework/validator/validator.xml")});
		return defaultValidatorFactory;
	}
}
