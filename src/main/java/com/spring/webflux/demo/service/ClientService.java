package com.spring.webflux.demo.service;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.spring.webflux.demo.entity.Client;
import com.spring.webflux.demo.repository.ClientRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by ZHIHAO
 * 2019年2月22日 下午6:20:03
 */

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Mono<ServerResponse> addClient(ServerRequest request) {
		Mono<Client> client = request.body(BodyExtractors.toMono(Client.class));
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(Mono.from(clientRepository.addClient(client)), Void.class);
	}
	
	public Mono<ServerResponse> findClientById(ServerRequest request) {
		final String id = request.pathVariable("id");
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(Mono.from(clientRepository.findClientById(id)), Map.class);
	}
	
	public Mono<ServerResponse> findAllClients(ServerRequest request) {
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(Flux.from(clientRepository.findAllClients()), Map.class);
	}
	
	public Mono<ServerResponse> updateClient(ServerRequest request) {
		final String id = request.pathVariable("id");
		Mono<Client> client = request.body(BodyExtractors.toMono(Client.class));
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(Mono.from(client.flatMap(c -> clientRepository.updateClient(id, c))), Integer.class);
	}
	
	public Mono<ServerResponse> deleteClientById(ServerRequest request) {
		final String id = request.pathVariable("id");
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(Mono.from(clientRepository.deleteClientById(id)), Integer.class);
	}
}
