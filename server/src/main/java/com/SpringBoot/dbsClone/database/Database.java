package com.SpringBoot.dbsClone.database;

import com.SpringBoot.dbsClone.models.Factory;
import com.SpringBoot.dbsClone.repositories.FactoryRepository;
import com.SpringBoot.dbsClone.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository, FactoryRepository factoryRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                if (factoryRepository.count() == 0) {
                    factoryRepository.save(new Factory("AP-01","APPLE","Mỹ"));
                    factoryRepository.save(new Factory("AS-01","ASUS","ĐÀI LOAN"));
                    factoryRepository.save(new Factory("CA-01","CASIO","NHẬT BẢN"));
                    factoryRepository.save(new Factory("CI-01","CITIZEN","NHẬT BẢN"));
                    factoryRepository.save(new Factory("D-01","DELL","MỸ"));
                    factoryRepository.save(new Factory("SS-01","SAMSUNG","HÀN XẺNG"));
                    factoryRepository.save(new Factory("V-01","VSMART","VIỆT NAM"));
                }

                if (productRepository.count() == 0) {
                    logger.info("Database empty");
                }
            }
        };
    }
}
