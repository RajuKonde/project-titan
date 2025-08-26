package com.projecttitan.query_api.repository;

import com.projecttitan.query_api.model.Log;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogRepository extends ElasticsearchRepository<Log, String> {
    // Spring Data will automatically implement the database methods for us
}
