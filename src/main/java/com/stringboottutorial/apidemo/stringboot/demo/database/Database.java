package com.stringboottutorial.apidemo.stringboot.demo.database;

import com.stringboottutorial.apidemo.stringboot.demo.models.Product;
import com.stringboottutorial.apidemo.stringboot.demo.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    //chua cac Bean Method
    //logger
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository){

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product productA = new Product("Iphone 5",12,802.500,"");
                Product productB = new Product("Ipad 1",23,954.670,"");
                logger.info("insert data: "+productRepository.save(productA));
                logger.info("insert data: "+productRepository.save(productB));
            }
        };
    }

}
