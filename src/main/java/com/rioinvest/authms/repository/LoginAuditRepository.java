package com.rioinvest.authms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rioinvest.authms.domain.login.LoginAudit;

public interface LoginAuditRepository extends MongoRepository<LoginAudit, String> {

    
}
