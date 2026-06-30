package org.example.common.exprot;

import org.example.common.exprot.entity.ExcelExportContext;
import org.example.utils.PageVO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IExcelExportDataProvider< Q extends Serializable,T>  {

    /**
     * 获取用于 Excel 导出的数据列表
     */
    List<T> getListData(ExcelExportContext<Q> context , PageVO pageVO);

    /**
     * 导出头填充
     * @param context
     * @return
     */
    default Map<String,Object> getFillHeaderData(ExcelExportContext<Q> context) { return context.getGlobalParam(); }

    /**
     * 使用注解方式
     * @return
     */
    default Class<T> getTargetClass(){
        return null;
    }
}
