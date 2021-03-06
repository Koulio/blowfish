package com.avantir.blowfish.messaging.services;

/**
 * Created by lekanomotayo on 14/10/2017.
 */

import com.avantir.blowfish.messaging.entity.TCPEndpoint;
import com.avantir.blowfish.messaging.repository.TCPEndpointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer.
 * Specify transactional behavior and mainly
 * delegate calls to Repository.
 */
@Service
public class TCPEndpointService {

    @Autowired
    private TCPEndpointRepository tcpEndpointRepository;


    @Transactional(readOnly=true)
    public TCPEndpoint findByTCPEndpointId(Long tcpEndpointId) {

        try
        {
            return tcpEndpointRepository.findByTcpEndpointId(tcpEndpointId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Transactional(readOnly=true)
    public List<TCPEndpoint> findAllActive() {

        try
        {
            List<TCPEndpoint> tcpEndpointList = tcpEndpointRepository.findByStatus(1);
            return tcpEndpointList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

}
