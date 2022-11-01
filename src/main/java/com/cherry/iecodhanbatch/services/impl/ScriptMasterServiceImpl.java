package com.cherry.iecodhanbatch.services.impl;

import com.cherry.iecodhanbatch.models.StagingScriptMaster;
import com.cherry.iecodhanbatch.models.ActualScriptMaster;
import com.cherry.iecodhanbatch.models.SecurityIdRequest;
import com.cherry.iecodhanbatch.repository.StagingTableRepository;
import com.cherry.iecodhanbatch.repository.ActualScriptMasterRepository;
import com.cherry.iecodhanbatch.services.ScriptMasterService;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ScriptMasterServiceImpl implements ScriptMasterService {

    @Autowired
    private ActualScriptMasterRepository actualScriptMasterRepository;

    @Autowired
    private StagingTableRepository stagingTableRepository;

    @Override
    public void addMasterData(MultipartFile file) throws IOException {


        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();

        List<ActualScriptMaster> csvDataList =new CsvToBeanBuilder(new FileReader(convFile)).withType(ActualScriptMaster.class)
                .withIgnoreQuotations(true)
                .withThrowExceptions(false)
                .build().parse();

        actualScriptMasterRepository.deleteAll();

        List<ActualScriptMaster> actualScriptMasterDbList = new ArrayList<>();
        for(ActualScriptMaster script: csvDataList){
            if (script.getSem_instrument_name().equals("EQUITY")){
                ActualScriptMaster actualScriptMaster =new ActualScriptMaster();
                actualScriptMaster.setSem_exm_exch_id(script.getSem_exm_exch_id());
                actualScriptMaster.setSem_segment(script.getSem_segment());
                actualScriptMaster.setSem_smst_security_id(script.getSem_smst_security_id());
                actualScriptMaster.setSem_instrument_name(script.getSem_instrument_name());
                actualScriptMaster.setSem_expiry_code(script.getSem_expiry_code());
                actualScriptMaster.setSem_trading_symbol(script.getSem_trading_symbol());
                actualScriptMaster.setSem_lot_units(script.getSem_lot_units());
                actualScriptMaster.setSem_custom_symbol(script.getSem_custom_symbol());
                actualScriptMasterDbList.add(actualScriptMaster);
            }
        }

        actualScriptMasterRepository.saveAll(actualScriptMasterDbList);

        List<ActualScriptMaster> actualScriptMasterList = (List<ActualScriptMaster>) actualScriptMasterRepository.findAll();
        List<StagingScriptMaster> stagingScriptMasterArrayList = new ArrayList<>();
        for (ActualScriptMaster actualScriptMaster : actualScriptMasterList) {
            StagingScriptMaster stagingScriptMaster = new StagingScriptMaster();
            stagingScriptMaster.setSem_segment(actualScriptMaster.getSem_segment());
            stagingScriptMaster.setSem_custom_symbol(actualScriptMaster.getSem_custom_symbol());
            stagingScriptMaster.setSem_exm_exch_id(actualScriptMaster.getSem_exm_exch_id());
            stagingScriptMaster.setSem_lot_units(actualScriptMaster.getSem_lot_units());
            stagingScriptMaster.setSem_expiry_code(actualScriptMaster.getSem_expiry_code());
            stagingScriptMaster.setSem_smst_security_id(actualScriptMaster.getSem_smst_security_id());
            stagingScriptMaster.setSem_instrument_name(actualScriptMaster.getSem_instrument_name());
            stagingScriptMaster.setSem_trading_symbol(actualScriptMaster.getSem_trading_symbol());
            stagingScriptMasterArrayList.add(stagingScriptMaster);
        }
        stagingTableRepository.deleteAll();
        stagingTableRepository.saveAll(stagingScriptMasterArrayList);



    }

    @Override
    public String getSecurityId(SecurityIdRequest securityIdRequest) {
        log.info("hitting setting security");
        String exchangeSymbol=securityIdRequest.getSemExmExchangeId();
        log.info(exchangeSymbol);
        String tradingSymbol=securityIdRequest.getSemTradingSymbol();
        log.info(tradingSymbol);
        String instrumentName=securityIdRequest.getSemInstrumentName();
        ActualScriptMaster securityId= actualScriptMasterRepository.findSecurityId(exchangeSymbol,tradingSymbol,instrumentName);
        log.info(securityId.getSem_smst_security_id());
        return securityId.getSem_smst_security_id();
    }

}
