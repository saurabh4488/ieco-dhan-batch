package com.cherry.iecodhanbatch.models;

import com.opencsv.bean.CsvBindByName;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "SCRIPT_MASTER")
public class BackUpDb {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "SEM_EXM_EXCH_ID")
    private String sem_exm_exch_id;

    @Column(name = "SEM_SEGMENT")
    private String sem_segment;

    @Column(name = "SEM_SMST_SECURITY_ID")
    private String sem_smst_security_id;

    @Column(name = "SEM_INSTRUMENT_NAME")
    private String sem_instrument_name;

    @Column(name = "SEM_EXPIRY_CODE")
    private String sem_expiry_code;

    @Column(name = "SEM_TRADING_SYMBOL")
    private String sem_trading_symbol;

    @Column(name = "SEM_LOT_UNITS")
    private String sem_lot_units;

    @Column(name = "SEM_CUSTOM_SYMBOL")
    private String sem_custom_symbol;

    @Column(name = "SYMBOL")
    private String symbol;

    @Column(name = "DATE")
    private String date;
}
