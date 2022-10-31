package com.cherry.iecodhanbatch.services;

import com.cherry.iecodhanbatch.models.SecurityIdRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface ScriptMasterService {
    void addMasterData(MultipartFile file) throws IOException;

    String getSecurityId(SecurityIdRequest securityIdRequest);

}
