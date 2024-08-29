package config.root;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import egovframework.rte.psl.dataaccess.mapper.MapperConfigurer;
import egovframework.rte.psl.orm.ibatis.SqlMapClientFactoryBean;

/**
import egovframework.rte.psl.orm.ibatis.SqlMapClientFactoryBean;
**/

public class ContextMapper {
	
	/** mybatis **/
	@Bean
	public SqlSessionFactoryBean sqlSession(DataSource dataSource) throws IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(pmrpr.getResource("classpath:/egovframework/sqlmap/example/sql-mapper-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(pmrpr.getResources("classpath:/egovframework/sqlmap/example/mappers/*.xml"));
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperConfigurer mapperConfigurer(DataSource dataSource) {
		MapperConfigurer mapperConfigurer = new MapperConfigurer();
		mapperConfigurer.setBasePackage("egovframework.example.sample.service.impl");
		return mapperConfigurer;
	}
	
}
