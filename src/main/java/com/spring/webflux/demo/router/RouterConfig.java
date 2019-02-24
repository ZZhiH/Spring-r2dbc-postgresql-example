package com.spring.webflux.demo.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.spring.webflux.demo.service.ClientService;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PATCH;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Created by ZHIHAO
 * 2019年2月22日 下午3:33:16
 */

@Configuration
public class RouterConfig {
	
	@Autowired
	private ClientService userService;
	
	@Bean
	public RouterFunction<ServerResponse> timeRouter() {
		return route(POST("/client"), userService::addClient)
				.andRoute(GET("/client/{id}"), userService::findClientById)
				.andRoute(GET("/client"), userService::findAllClients)
				.andRoute(PATCH("/client/{id}"), userService::updateClient)
				.andRoute(DELETE("/client/{id}"), userService::deleteClientById);
	}
}
