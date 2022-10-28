package com.cherry.iecodhanbatch.repository;

import com.cherry.iecodhanbatch.models.ScriptMasterDb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ScriptMasterRepository extends CrudRepository<ScriptMasterDb,Integer> {

    @Query("SELECT t FROM Todo t WHERE t.title = 'title'")
    public String findSecurityId();
}
