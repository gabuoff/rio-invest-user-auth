package com.rioinvest.authms.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class LoginAudit {
    @Id
    private String id;
    private String userId, ipAddress;
    private Date loginDate;
    private boolean sucesss;
}
