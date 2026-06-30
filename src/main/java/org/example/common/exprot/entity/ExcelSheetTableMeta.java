package org.example.common.exprot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelSheetTableMeta {
    private String tableCode;
    private String exportBizKey;
    private Boolean dynamicColumn = false;
    private String tableName;
}