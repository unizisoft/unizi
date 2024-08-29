package config.root;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class ContextDataSource {

	@Bean
	public DataSource dataSource() {
//		EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
//		embeddedDatabaseBuilder.setType(EmbeddedDatabaseType.HSQL);
//		embeddedDatabaseBuilder.addScript("classpath:/db/sampledb.sql");
//		embeddedDatabaseBuilder.build();
//		return (DataSource) embeddedDatabaseBuilder;
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
											.addScript("classpath:/db/sampledb.sql")
											.build();
	}
	
	/**
	@Bean(destroyMethod ="close")
	public DataSource basicDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		basicDataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:example");
		basicDataSource.setUsername("user");
		basicDataSource.setPassword("password");
		return basicDataSource;
	}
	**/
}
