package org.example.common.exprot.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.common.exprot.ExcelExportHandler;
import org.example.common.exprot.entity.ExcelExportContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private ExcelExportHandler handler;

    @Override
    public void syncExcelWriter(ExcelExportContext ctx, HttpServletResponse resp) throws Exception {
        ctx.setUseFill(false);
        resp.setContentType("application/vnd.ms-excel");
        resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(ctx.getFileName(),"UTF-8")+".xlsx");
        try {
            handler.excelWriter(ctx, resp.getOutputStream());
        } catch (Exception exception){
            log.error(exception.getMessage());
        }finally {
            resp.flushBuffer();
        }
    }

    @Override
    public void syncExcelFill(ExcelExportContext ctx, HttpServletResponse resp) throws Exception {
        ctx.setUseFill(true);
        resp.setContentType("application/vnd.ms-excel");
        resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(ctx.getFileName(),"UTF-8")+".xlsx");
        try {
            handler.export(ctx, resp.getOutputStream());
        } catch (Exception exception){
            log.error(exception.getMessage());
        }finally {
            resp.flushBuffer();
        }
    }
}