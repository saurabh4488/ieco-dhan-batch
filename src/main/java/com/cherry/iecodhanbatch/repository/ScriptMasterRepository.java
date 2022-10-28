package com.cherry.iecodhanbatch.repository;

import com.cherry.iecodhanbatch.models.ScriptMasterDb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ScriptMasterRepository extends CrudRepository<ScriptMasterDb,Integer> {

    @Query(value = "SELECT u FROM ScriptMasterDb u where u.sem_exm_exch_id=:semExmExchangeId and u.sem_trading_symbol=:semTradingSymbol and u.sem_instrument_name=:instrumentName")
    ScriptMasterDb findSecurityId(@Param("semExmExchangeId") String semExmExchangeId, @Param("semTradingSymbol") String semTradingSymbol, @Param("instrumentName") String instrumentName);
}
