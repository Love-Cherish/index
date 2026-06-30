package org.example.common.exprot.service;

import org.example.common.exprot.ExcelExportBiz;
import org.example.common.exprot.IExcelExportDataProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ExcelBeanRegistry {

    private static final Map<String, IExcelExportDataProvider<?,?>> beanMap = new ConcurrentHashMap<>();

    public ExcelBeanRegistry(ApplicationContext ctx) {
        ctx.getBeansWithAnnotation(ExcelExportBiz.class).forEach((k, v)->{
            beanMap.put(k, (IExcelExportDataProvider<?,?>) v);
        });
    }

    public IExcelExportDataProvider<?,?> get(String key) {
        return beanMap.get(key);
    }
}