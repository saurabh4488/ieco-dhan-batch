package com.cherry.iecodhanbatch.repository;

import com.cherry.iecodhanbatch.models.ScriptMasterDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


public interface ScriptMasterRepository extends CrudRepository<ScriptMasterDb,Integer> {

    @Modifying
    @Transactional
    @Query("delete from ScriptMasterDb s")
    void deleteTable();


    @Query(value = "SELECT u FROM ScriptMasterDb u where u.sem_exm_exch_id=:semExmExchangeId and u.sem_trading_symbol=:semTradingSymbol and u.sem_instrument_name=:instrumentName")
    ScriptMasterDb findSecurityId(@Param("semExmExchangeId") String semExmExchangeId, @Param("semTradingSymbol") String semTradingSymbol, @Param("instrumentName") String instrumentName);
}
