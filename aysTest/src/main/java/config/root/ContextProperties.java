package config.root;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;

import egovframework.rte.fdl.property.impl.EgovPropertyServiceImpl;

public class ContextProperties {
	
	@Bean(destroyMethod="destroy")
	public EgovPropertyServiceImpl propertiesService() {
		Map<String,String> propMap = new HashMap<String,String>();
		
		propMap.put("pageUnit", "10");
		propMap.put("pageSize", "10");
		EgovPropertyServiceImpl egovPropertyServiceImpl = new EgovPropertyServiceImpl();
		egovPropertyServiceImpl.setProperties(propMap);
		return egovPropertyServiceImpl;
	}
}
