package com.cherry.iecodhanbatch.services.impl;

import com.cherry.iecodhanbatch.models.BackUpDb;
import com.cherry.iecodhanbatch.models.ScriptMasterDb;
import com.cherry.iecodhanbatch.models.SecurityIdRequest;
import com.cherry.iecodhanbatch.repository.BackUpTableRepository;
import com.cherry.iecodhanbatch.repository.ScriptMasterRepository;
import com.cherry.iecodhanbatch.services.ScriptMasterService;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ScriptMasterServiceImpl implements ScriptMasterService {

    @Autowired
    private ScriptMasterRepository scriptMasterRepository;

    @Autowired
    private BackUpTableRepository backUpTableRepository;

    @Override
    public void addMasterData(MultipartFile file) throws IOException {


        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();

        List<ScriptMasterDb> scriptData =new CsvToBeanBuilder(new FileReader(convFile)).withType(ScriptMasterDb.class)
                .withIgnoreQuotations(true)
                .withThrowExceptions(false) //1
                .build().parse();

//        List<BackUpDb> backUpDbs =new CsvToBeanBuilder(new FileReader(convFile)).withType(BackUpDb.class)
//                .withIgnoreQuotations(true)
//                .withThrowExceptions(false)
//                .build().parse();
//
//        BackUpDb backUpDb=new BackUpDb();
//
//        for(BackUpDb back: backUpDbs){
//            if (back.getSem_instrument_name().equals("EQUITY")){
//                backUpDb.setSem_exm_exch_id(back.getSem_exm_exch_id());
//                backUpDb.setSem_segment(back.getSem_segment());
//                backUpDb.setSem_smst_security_id(back.getSem_smst_security_id());
//                backUpDb.setSem_instrument_name(back.getSem_instrument_name());
//                backUpDb.setSem_expiry_code(back.getSem_expiry_code());
//                backUpDb.setSem_trading_symbol(back.getSem_trading_symbol());
//                backUpDb.setSem_lot_units(back.getSem_lot_units());
//                backUpDb.setSem_custom_symbol(back.getSem_custom_symbol());
//                backUpDb.setSymbol(back.getSymbol());
//
//                backUpTableRepository.save(backUpDb);
//            }
//        }
//
//        scriptMasterRepository.deleteTable();

        ScriptMasterDb scriptMasterDb=new ScriptMasterDb();

        for(ScriptMasterDb script: scriptData){
            if (script.getSem_instrument_name().equals("EQUITY")){
                scriptMasterDb.setSem_exm_exch_id(script.getSem_exm_exch_id());
                scriptMasterDb.setSem_segment(script.getSem_segment());
                scriptMasterDb.setSem_smst_security_id(script.getSem_smst_security_id());
                scriptMasterDb.setSem_instrument_name(script.getSem_instrument_name());
                scriptMasterDb.setSem_expiry_code(script.getSem_expiry_code());
                scriptMasterDb.setSem_trading_symbol(script.getSem_trading_symbol());
                scriptMasterDb.setSem_lot_units(script.getSem_lot_units());
                scriptMasterDb.setSem_custom_symbol(script.getSem_custom_symbol());
                scriptMasterDb.setSymbol(script.getSymbol());
                scriptMasterRepository.save(scriptMasterDb);
            }
        }

//        XSSFWorkbook workbook=new XSSFWorkbook(file.getInputStream());
//        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
//        System.out.println("Retrieving Sheets using for-each loop");
//        DataFormatter dataFormatter = new DataFormatter();
//        for(Sheet sheet: workbook) {
//            System.out.println("=> " + sheet.getSheetName());
//
//            for (Row row: sheet) {
//                ScriptMasterDb scriptMasterDb=new ScriptMasterDb();
//
//                String equityCheck=dataFormatter.formatCellValue(row.getCell(3));
//                if(equityCheck.equals("EQUITY")){
//                    scriptMasterDb.setSem_exm_exch_id(dataFormatter.formatCellValue(row.getCell(0)));
//                    scriptMasterDb.setSem_segment(dataFormatter.formatCellValue(row.getCell(1)));
//                    scriptMasterDb.setSem_smst_security_id(dataFormatter.formatCellValue(row.getCell(2)));
//                    scriptMasterDb.setSem_instrument_name(dataFormatter.formatCellValue(row.getCell(3)));
//                    scriptMasterDb.setSem_expiry_code(dataFormatter.formatCellValue(row.getCell(4)));
//                    scriptMasterDb.setSem_trading_symbol(dataFormatter.formatCellValue(row.getCell(5)));
//                    scriptMasterDb.setSem_lot_units(dataFormatter.formatCellValue(row.getCell(6)));
//                    scriptMasterDb.setSem_custom_symbol(dataFormatter.formatCellValue(row.getCell(7)));
//                    scriptMasterRepository.save(scriptMasterDb);
//                }
//
//            }
//
//        }

    }

    @Override
    public String getSecurityId(SecurityIdRequest securityIdRequest) {
        log.info("hitting setting security");
        String exchangeSymbol=securityIdRequest.getSemExmExchangeId();
        log.info(exchangeSymbol);
        String tradingSymbol=securityIdRequest.getSemTradingSymbol();
        log.info(tradingSymbol);
        String instrumentName=securityIdRequest.getSemInstrumentName();
        ScriptMasterDb securityId=scriptMasterRepository.findSecurityId(exchangeSymbol,tradingSymbol,instrumentName);
        log.info(securityId.getSem_smst_security_id());
        return securityId.getSem_smst_security_id();
    }

}
