package org.example.common.exprot.template;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.common.exprot.entity.ExcelSheetMeta;
import org.example.common.exprot.entity.ExcelSheetTableMeta;
import org.example.exception.ExcelTemplateException;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum BizExcelTemplateEnum implements ExcelTemplateDefine {

    SHEET_MIX_EXPORT("SHEET_MIX_EXPORT", "templates/check_tallying_record.xlsx", "测试代码", Arrays.asList(
            // Sheet1：单Table
            new ExcelSheetMeta("USER_SHEET", "test", 0,  "userExportProvider", false, null))),

    SHEET_MIX_T_EXPORT("SHEET_MIX_T_EXPORT", "templates/multiple_table.xlsx", "混合导出", Arrays.asList(
            // Sheet1：单Table
            new ExcelSheetMeta("USER_SHEET", "test1", 0, "userExportProvider", false, null),
            // Sheet2：多Table
            new ExcelSheetMeta("STAT_SHEET", "test2", 1,  null,  false,
                    Arrays.asList(
                            new ExcelSheetTableMeta("table1", "usertable1exportprovider", true, "月度业绩"),
                            new ExcelSheetTableMeta("table2", "usertable2exportprovider", false, "年度汇总"))))
    );

    private final String templateCode;
    private final String templatePath;
    private final String templateName;
    private final List<ExcelSheetMeta> sheetMetaList;

    public static BizExcelTemplateEnum getByCode(String code) {
        for (BizExcelTemplateEnum bizExcelTemplateEnum : values()) {
            if (bizExcelTemplateEnum.getTemplateCode().equals(code)) {
                return bizExcelTemplateEnum;
            }
        }
        throw ExcelTemplateException.throwEx("500","编码匹配不正确");
    }
}