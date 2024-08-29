package config.root;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;

import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;
import egovframework.rte.fdl.idgnr.impl.strategy.EgovIdGnrStrategyImpl;

public class ContextIdgen {

	@Bean(destroyMethod="destroy")
	public EgovTableIdGnrServiceImpl egovIdGnrService(DataSource dataSource, EgovIdGnrStrategyImpl egovIdGnrStrategyImpl) {
		EgovTableIdGnrServiceImpl egovTableIdGnrServiceImpl = new EgovTableIdGnrServiceImpl();
		egovTableIdGnrServiceImpl.setDataSource(dataSource);
		egovTableIdGnrServiceImpl.setStrategy(egovIdGnrStrategyImpl);
		egovTableIdGnrServiceImpl.setBlockSize(10);
		egovTableIdGnrServiceImpl.setTable("IDS");
		egovTableIdGnrServiceImpl.setTableName("SAMPLE");
		return egovTableIdGnrServiceImpl;
	}
	
	@Bean
	public EgovIdGnrStrategyImpl mixPrefixSample() {
		EgovIdGnrStrategyImpl egovIdGnrStrategyImpl = new EgovIdGnrStrategyImpl();
		egovIdGnrStrategyImpl.setPrefix("SAMPLE-");
		egovIdGnrStrategyImpl.setCipers(5);
		egovIdGnrStrategyImpl.setFillChar((char) 0);
		return egovIdGnrStrategyImpl;
	}
	
}
