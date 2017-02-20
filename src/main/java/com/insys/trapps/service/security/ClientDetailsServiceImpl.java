package com.insys.trapps.service.security;

import com.insys.trapps.model.security.Client;
import com.insys.trapps.respositories.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by msabir on 2/16/17.
 */
@Service("ClientDetailsServiceImpl")
public class ClientDetailsServiceImpl implements ClientDetailsService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ClientRepository clientRepository;

    public ClientDetailsServiceImpl(ClientRepository clientRepository) {
        logger.debug("Enter: ClientDetailsServiceImpl()");
        this.clientRepository=clientRepository;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        logger.debug("Enter: ClientDetailsServiceImpl.loadClientByClientId()" + clientId);
        List<Client> clients=clientRepository.findByClientId(clientId);
        if(clients.size()==0) {
            throw new ClientRegistrationException("Client " + clientId + " is not valid");
        }
        return clients.get(0);
    }
}
