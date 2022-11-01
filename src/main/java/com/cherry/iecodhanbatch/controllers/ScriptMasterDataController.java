package com.cherry.iecodhanbatch.controllers;

import com.cherry.iecodhanbatch.models.SecurityIdRequest;
import com.cherry.iecodhanbatch.services.ScriptMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class ScriptMasterDataController {

    @Autowired
    private ScriptMasterService scriptMasterService;

    @PostMapping("/getcsvdata")
    public String getFileData(@RequestParam MultipartFile file) throws IOException {
        log.info("Hitting");
        scriptMasterService.addMasterData(file);
        return "Data Fetched from file successfully";
    }

    @PostMapping("/getsecurityid")
    public String getSecurityId(@RequestBody SecurityIdRequest securityIdRequest){
        return scriptMasterService.getSecurityId(securityIdRequest);
    }

}
