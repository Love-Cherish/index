package org.example.common.exprot.service;


import jakarta.servlet.http.HttpServletResponse;
import org.example.common.exprot.entity.ExcelExportContext;

public interface ExcelService {

    void syncExcelWriter(ExcelExportContext ctx, HttpServletResponse resp) throws Exception;

    void syncExcelFill(ExcelExportContext ctx , HttpServletResponse resp)throws Exception;

}