package com.cherry.iecodhanbatch.services;

import com.cherry.iecodhanbatch.domain.SecurityIdRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@Service
public interface ScriptMasterService {
    void addMasterData(MultipartFile file) throws IOException;

    HashMap<String, Object> getSecurityId(SecurityIdRequest securityIdRequest);
}
