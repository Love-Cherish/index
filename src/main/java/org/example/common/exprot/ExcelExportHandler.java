package org.example.common.exprot;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import org.example.common.exprot.entity.ExcelExportContext;
import org.example.common.exprot.entity.ExcelSheetMeta;
import org.example.common.exprot.entity.ExcelSheetTableMeta;
import org.example.common.exprot.service.ExcelBeanRegistry;
import org.example.common.exprot.template.ExcelTemplateDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Component
public class ExcelExportHandler {

    @Autowired
    private TemplateLoader loader;

    @Autowired
    private ExcelBeanRegistry registry;

    // ==================== 核心导出入口 ====================
    public void export(ExcelExportContext ctx, OutputStream out) {
        ExcelTemplateDefine template = ctx.getTemplateDefine();
        ByteArrayInputStream templateStream = loader.loadTemplateBytes(template.getTemplatePath());
        ExcelWriter writer = EasyExcel.write(out)
                .withTemplate(templateStream)
                .autoCloseStream(false)
                .inMemory(false)
                .build();
        try {
            for (ExcelSheetMeta sheetMeta : template.getSheetMetaList()) {
                WriteSheet writeSheet = EasyExcel.writerSheet(sheetMeta.getSheetNo()).build();
                List<ExcelSheetTableMeta> tableMetaList = getTableMetaList(sheetMeta);
                // 全局填充配置：自动换行
                FillConfig fillConfig = FillConfig.builder().forceNewRow(true).build();
                // 遍历当前 sheet 所有 table
                for (ExcelSheetTableMeta tableMeta : tableMetaList) {
                    IExcelExportDataProvider<?,?> exportService = registry.get(tableMeta.getExportBizKey());
                    // ============== 1. 填充单个变量/Map/对象 {date}, {title} ==============
                    Map singleData = exportService.getFillHeaderData(ctx);
                    if (singleData != null && !singleData.isEmpty()) {
                        writer.fill(singleData, writeSheet);
                    }
                    // ============== 2. 填充列表（使用 FillWrapper + 模板前缀，如 t1、t2） ==============
                    String prefix = tableMeta.getTableCode();
                    List<?> dataList = exportService.getListData(ctx,null);
                    if (dataList != null && !dataList.isEmpty()) {
                        writer.fill(
                                new FillWrapper(prefix, dataList),
                                fillConfig,
                                writeSheet
                        );
                    }
                }
            }
        } finally {
            if (writer != null) {
                writer.finish();
            }
        }
    }


    // ==================== 核心导出入口 ====================
    public void excelWriter(ExcelExportContext ctx, OutputStream out) {
        ExcelTemplateDefine template = ctx.getTemplateDefine();
        ExcelWriter writer = EasyExcel.write(out)
                .autoCloseStream(false)
                .inMemory(false)
                .build();
        try {
            for (ExcelSheetMeta sheetMeta : template.getSheetMetaList()) {
                IExcelExportDataProvider<?,?> exportService = registry.get(sheetMeta.getExportBizKey());
                WriteSheet writeSheet = EasyExcel.writerSheet(sheetMeta.getSheetName()).head(exportService.getTargetClass()).build();
                    // ============== 2. 填充列表（使用 FillWrapper + 模板前缀，如 t1、t2） ==============
                    List<?> dataList = exportService.getListData(ctx,null);
                    if (dataList != null && !dataList.isEmpty()) {
                        writer.write(dataList,writeSheet);
                    }
            }
        } finally {
            if (writer != null) {
                writer.finish();
            }
        }
    }

    /**
     * 自动适配：单 Table → 包装成多 Table
     * 【无状态 + 纯工具方法 + 不修改任何变量 = 绝对线程安全】
     */
    private List<ExcelSheetTableMeta> getTableMetaList(ExcelSheetMeta sheetMeta) {
        // 配置了多 Table，直接使用
        if (sheetMeta.getTableMetaList() != null && !sheetMeta.getTableMetaList().isEmpty()) {
            return sheetMeta.getTableMetaList();
        }
        // 单 Table：封装成一个 TableMeta，统一逻辑
        ExcelSheetTableMeta singleTable = new ExcelSheetTableMeta();
        singleTable.setTableCode("list");
        singleTable.setExportBizKey(sheetMeta.getExportBizKey());
        singleTable.setDynamicColumn(sheetMeta.getDynamicColumn());
        singleTable.setTableName(sheetMeta.getSheetName());
        return Collections.singletonList(singleTable);
    }
}