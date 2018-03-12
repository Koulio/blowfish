package com.avantir.blowfish.services;

/**
 * Created by lekanomotayo on 14/10/2017.
 */

import com.avantir.blowfish.model.AcquirerMerchant;
import com.avantir.blowfish.model.MerchantTerminal;
import com.avantir.blowfish.repository.AcquirerMerchantRepository;
import com.avantir.blowfish.repository.MerchantTerminalRepository;
import com.avantir.blowfish.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service layer.
 * Specify transactional behavior and mainly
 * delegate calls to Repository.
 */
@Component
public class AcquirerMerchantService {

    public static final String ALL_ACQUIRER_MERCHANT = "ALL_ACQUIRER_MERCHANT";
    public static final String ACTIVE_ACQUIRER_MERCHANT = "ACTIVE_ACQUIRER_MERCHANT";


    @Autowired
    private AcquirerMerchantRepository acquirerMerchantRepository;



    @CachePut(cacheNames="acquirerMerchant")
    // , key = "#result.firstName + #result.lastName"
    @Transactional(readOnly=false)
    public AcquirerMerchant create(AcquirerMerchant acquirerMerchant) {
        AcquirerMerchant acquirerMerchant1 = acquirerMerchantRepository.save(acquirerMerchant);
        //evictAllCache();
        return acquirerMerchant1;
    }


    @CachePut(cacheNames="acquirerMerchant", unless="#result==null", key = "#result.id")
    @Transactional(readOnly=false)
    public AcquirerMerchant update(AcquirerMerchant newAcquirerMerchant) {
        if(newAcquirerMerchant != null){
            AcquirerMerchant oldMerchantTerminal = acquirerMerchantRepository.findByAcquirerMerchantId(newAcquirerMerchant.getAcquirerMerchantId());
            if(newAcquirerMerchant.getMerchantId() != 0)
                oldMerchantTerminal.setMerchantId(newAcquirerMerchant.getMerchantId());
            if(newAcquirerMerchant.getAcquirerId() != 0)
                oldMerchantTerminal.setAcquirerId(newAcquirerMerchant.getAcquirerId());
            if(!StringUtil.isEmpty(newAcquirerMerchant.getCreatedBy()))
                oldMerchantTerminal.setCreatedBy(newAcquirerMerchant.getCreatedBy());
            if(newAcquirerMerchant.getCreatedOn() != null)
                oldMerchantTerminal.setCreatedOn(newAcquirerMerchant.getCreatedOn());
            AcquirerMerchant merchantTerminal1 = acquirerMerchantRepository.save(oldMerchantTerminal);
            //evictAllCache();
            return oldMerchantTerminal;
        }
        return null;
    }


    @CacheEvict(value = "acquirerMerchant")
    @Transactional(readOnly=false)
    public void delete(long id) {
        acquirerMerchantRepository.delete(id);
    }

    @Cacheable(value = "acquirerMerchant")
    @Transactional(readOnly=true)
    public AcquirerMerchant findByAcquirerMerchantId(Long id) {

        try
        {
            //Optional<AcquirerMerchant> optional = acquirerMerchantRepository.findById(id);
            //return optional.orElse(null);
            return acquirerMerchantRepository.findByAcquirerMerchantId(id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Cacheable(value = "acquirerMerchant")
    @Transactional(readOnly=true)
    public List<AcquirerMerchant> findByAcquirerId(Long merchantId) {

        try
        {
            return acquirerMerchantRepository.findByAcquirerId(merchantId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


    @Cacheable(value = "acquirerMerchant")
    @Transactional(readOnly=true)
    public AcquirerMerchant findByMerchantId(Long merchantId) {

        try
        {
            return acquirerMerchantRepository.findByMerchantId(merchantId);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


    @Cacheable(value = "acquirerMerchant", key = "#root.target.ALL_ACQUIRER_MERCHANT")
    @Transactional(readOnly=true)
    public List<AcquirerMerchant> findAll() {

        try
        {
            List<AcquirerMerchant> list = acquirerMerchantRepository.findAll();
            return list;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


}
