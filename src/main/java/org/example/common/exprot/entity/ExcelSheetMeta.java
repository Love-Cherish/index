package org.example.common.exprot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelSheetMeta {
    private String sheetCode;
    private String sheetName;
    private Integer sheetNo;
    // 单Table
    private String exportBizKey;
    private Boolean dynamicColumn = false;
    // 多Table
    private List<ExcelSheetTableMeta> tableMetaList;
}