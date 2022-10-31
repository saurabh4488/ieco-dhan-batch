package com.cherry.iecodhanbatch.controllers;

import com.cherry.iecodhanbatch.models.SecurityIdRequest;
import com.cherry.iecodhanbatch.services.ScriptMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ScriptMasterDataController {

    @Autowired
    private ScriptMasterService scriptMasterService;

    @PostMapping("/getdata")
    public String getFileData(@RequestParam MultipartFile file) throws IOException {
        System.out.println("Hitting");
        scriptMasterService.addMasterData(file);
        return "Data Fetched from file successfully";
    }

    @PostMapping("/getsecurityid")
    public String getSecurityId(@RequestBody SecurityIdRequest securityIdRequest){
        return scriptMasterService.getSecurityId(securityIdRequest);
    }

    @GetMapping("/getsecurityid")
    public String getBackUp(){
        return scriptMasterService.getBackUpTable();
    }
}
