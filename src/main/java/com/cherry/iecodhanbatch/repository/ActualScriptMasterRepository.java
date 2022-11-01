package com.cherry.iecodhanbatch.repository;

import com.cherry.iecodhanbatch.models.ActualScriptMaster;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


public interface ActualScriptMasterRepository extends CrudRepository<ActualScriptMaster,Integer> {


    @Query(value = "SELECT u FROM ActualScriptMaster u where u.sem_exm_exch_id=:semExmExchangeId and u.sem_trading_symbol=:semTradingSymbol and u.sem_instrument_name=:instrumentName")
    ActualScriptMaster findSecurityId(@Param("semExmExchangeId") String semExmExchangeId, @Param("semTradingSymbol") String semTradingSymbol, @Param("instrumentName") String instrumentName);


}
