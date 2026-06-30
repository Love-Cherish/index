package org.example.common.exprot.entity;

import lombok.Data;
import org.example.common.exprot.template.ExcelTemplateDefine;

import java.io.Serializable;
import java.util.Map;

@Data
public class ExcelExportContext<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 导出文件名 */
    private String fileName;
    /** 全局公共参数 */
    private Map<String,Object> globalParam;
    /** 服务参数 */
    private T query;
    /** 模板信息  */
    private ExcelTemplateDefine templateDefine;
    /** 单页分页条数 */
    private Integer pageSize = 1000;
    /** 最大导出限制行数 */
    private Long maxExportLimit = 200000L;

    /** 是否使用Fill填充模式 true=是 false=样式克隆动态写入 */
    private Boolean useFill = true;

}