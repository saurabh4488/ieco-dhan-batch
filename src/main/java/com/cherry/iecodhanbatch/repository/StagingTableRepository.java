package com.cherry.iecodhanbatch.repository;

import com.cherry.iecodhanbatch.models.StagingScriptMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StagingTableRepository extends CrudRepository<StagingScriptMaster,Integer> {

}
