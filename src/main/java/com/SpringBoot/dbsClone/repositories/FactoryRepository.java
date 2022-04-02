package com.SpringBoot.dbsClone.repositories;

import com.SpringBoot.dbsClone.models.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FactoryRepository extends JpaRepository<Factory, String> {

}
