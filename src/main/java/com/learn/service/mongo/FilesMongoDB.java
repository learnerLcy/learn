package com.learn.service.mongo;

import com.learn.PoJo.mongo.Files;
import com.learn.service.MongoDBService;
import org.springframework.stereotype.Service;

/**
 * @ClassName:FilesMongoDB
 * @Description:
 * @Author:lvchunyang
 * @Date:14:59
 **/
@Service
public class FilesMongoDB extends MongoDBService<Files> {
    @Override
    public Class<Files> getEntityClass() {
        return Files.class;
    }
}
