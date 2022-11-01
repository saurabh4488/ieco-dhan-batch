package com.cherry.iecodhanbatch.repository;

import com.cherry.iecodhanbatch.models.BackUpDb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackUpTableRepository extends CrudRepository<BackUpDb,Integer> {

}
