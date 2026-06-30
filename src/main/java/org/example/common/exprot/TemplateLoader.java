package org.example.common.exprot;

import org.example.exception.ExcelTemplateException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 导出模板加载
 */
@Component
public class TemplateLoader {

    public ByteArrayInputStream loadTemplateBytes(String path) {
        try (InputStream in = new ClassPathResource(path).getInputStream()) {
            // 读取文件字节
            return new ByteArrayInputStream(FileCopyUtils.copyToByteArray(in));
        } catch (IOException e) {
            // 这里自己封装成自定义异常
            throw ExcelTemplateException.throwEx("500","Excel模板加载失败，路径：" + path);
        }
    }

}