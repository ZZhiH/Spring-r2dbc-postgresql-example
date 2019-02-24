package com.spring.webflux.demo.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.webflux.demo.configuration.R2dbcConfiguration;
import com.spring.webflux.demo.entity.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by ZHIHAO
 * 2019年2月22日 下午5:33:02
 */

@Repository
public class ClientRepository {

	@Autowired
	private R2dbcConfiguration databaseClient;
	
	public Mono<Void> addClient(Mono<Client> client) {
		return databaseClient.getClient().insert()
				.into(Client.class)
				.using(client)
				.then();
	}
	
	public Mono<Map<String, Object>> findClientById(String id) {
		return databaseClient.getClient().execute()
				.sql("SELECT id, email, name, phone, username FROM client WHERE id = $1")
				.bind("$1", id)
				.fetch()
				.first();
	}
	
	public Flux<Map<String, Object>> findAllClients() {
		return databaseClient.getClient().execute()
				.sql("SELECT id, email, name, phone, username FROM client")
				.fetch()
				.all();
	}
	
	public Mono<Integer> updateClient(String id, Client client) {
		Mono<Map<String, Object>> oldClient = databaseClient.getClient().execute()
				.sql("SELECT id, email, name, phone, username FROM client WHERE id = $1")
				.bind("$1", id)
				.fetch()
				.first();
		return oldClient.map(oc -> client.update(oc))
				.flatMap(od -> databaseClient.getClient().execute()
						.sql("UPDATE client SET email = $2, name = $3, phone = $4, username = $5 WHERE id = $1")
						.bind("$1", id)
						.bind("$2", od.getEmail())
						.bind("$3", od.getName())
						.bind("$4", od.getPhone())
						.bind("$5", od.getUsername())
						.fetch()
						.rowsUpdated());
	}
	
	public Mono<Integer> deleteClientById(String id) {
		return databaseClient.getClient().execute()
				.sql("DELETE FROM client WHERE id = $1")
				.bind("$1", id)
				.fetch()
				.rowsUpdated();
	}
	
}
