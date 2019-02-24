package com.spring.webflux.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.function.DatabaseClient;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;

/**
 * Created by ZHIHAO
 * 2019年2月24日 下午4:05:57
 */

@Configuration
public class R2dbcConfiguration {

	@Value("${postgresql.host}")
	private String host;
	
	@Value("${postgresql.port}")
	private Integer port;
	
	@Value("${postgresql.username}")
	private String username;
	
	@Value("${postgresql.password}")
	private String password;
	
	@Value("${postgresql.database}")
	private String database;
	
	@Bean
	public DatabaseClient getClient() {
		ConnectionFactory connectionFactory = new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
				.host(host)
				.port(port)
				.username(username)
				.password(password)
				.database(database)
				.build());
		
		return DatabaseClient.create(connectionFactory); 
	}
}
