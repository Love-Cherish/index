package org.example.common.exprot.template;


import org.example.common.exprot.entity.ExcelSheetMeta;

import java.util.List;

public interface ExcelTemplateDefine {
    String getTemplateCode();
    String getTemplatePath();
    String getTemplateName();
    List<ExcelSheetMeta> getSheetMetaList();
}