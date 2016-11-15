package com.insys.trapps.respositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.insys.trapps.model.Client;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

    public Client save(Client client);
}
