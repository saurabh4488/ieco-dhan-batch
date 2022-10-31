package com.cherry.iecodhanbatch.services.impl;

import com.cherry.iecodhanbatch.models.ScriptMasterDb;
import com.cherry.iecodhanbatch.domain.SecurityIdRequest;
import com.cherry.iecodhanbatch.repository.ScriptMasterRepository;
import com.cherry.iecodhanbatch.services.ScriptMasterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@Service
@Slf4j
public class ScriptMasterServiceImpl implements ScriptMasterService {

    @Autowired
    private ScriptMasterRepository scriptMasterRepository;

    @Override
    public void addMasterData(MultipartFile file) throws IOException {
        XSSFWorkbook workbook=new XSSFWorkbook(file.getInputStream());
        log.info("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
        log.info("Retrieving Sheets using for-each loop");
        DataFormatter dataFormatter = new DataFormatter();
        for(Sheet sheet: workbook) {
            System.out.println("=> " + sheet.getSheetName());

            for (Row row: sheet) {
                ScriptMasterDb scriptMasterDb=new ScriptMasterDb();

                String equityCheck=dataFormatter.formatCellValue(row.getCell(3));
                if(equityCheck.equals("EQUITY")){
                    scriptMasterDb.setSem_exm_exch_id(dataFormatter.formatCellValue(row.getCell(0)));
                    scriptMasterDb.setSem_segment(dataFormatter.formatCellValue(row.getCell(1)));
                    scriptMasterDb.setSem_smst_security_id(dataFormatter.formatCellValue(row.getCell(2)));
                    scriptMasterDb.setSem_instrument_name(dataFormatter.formatCellValue(row.getCell(3)));
                    scriptMasterDb.setSem_expiry_code(dataFormatter.formatCellValue(row.getCell(4)));
                    scriptMasterDb.setSem_trading_symbol(dataFormatter.formatCellValue(row.getCell(5)));
                    scriptMasterDb.setSem_lot_units(dataFormatter.formatCellValue(row.getCell(6)));
                    scriptMasterDb.setSem_custom_symbol(dataFormatter.formatCellValue(row.getCell(7)));
                    scriptMasterRepository.save(scriptMasterDb);
                }

            }

        }

    }

    @Override
    public HashMap<String, Object> getSecurityId(SecurityIdRequest securityIdRequest) {
        HashMap<String,Object> response=new HashMap<>();
        log.info("hitting setting security");
        String exchangeSymbol=securityIdRequest.getSemExmExchangeId();
        log.info(exchangeSymbol);
        String tradingSymbol=securityIdRequest.getSemTradingSymbol();
        log.info(tradingSymbol);
        String instrumentName=securityIdRequest.getSemInstrumentName();
        try {
            ScriptMasterDb securityId = scriptMasterRepository.findSecurityId(exchangeSymbol, tradingSymbol, instrumentName);
            log.info(securityId.getSem_smst_security_id());
            response.put("message","success");
            response.put("securityId",securityId.getSem_smst_security_id());
            return response;
        }
        catch (Exception e){
            response.put("message","failure");
            response.put("securityId","No Security Id present with given data");
            return response ;
        }

    }
}
