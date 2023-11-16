package io.github.SilenceShine.shine.core.util.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;
import io.github.SilenceShine.shine.core.exception.BizException;
import io.github.SilenceShine.shine.util.excel.DefaultExcelData;
import io.github.SilenceShine.shine.util.excel.DefaultExcelReadListener;
import io.github.SilenceShine.shine.util.log.LogUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.net.URI;
import java.util.List;

/**
 * @author SilenceShine
 * @github <a href="https://github.com/SilenceShine">SilenceShine</a>
 * @since 1.0
 */
@Slf4j
public class EasyExcelUtil {

    public static <E extends DefaultExcelData> List<E> importCos(String url, Class<E> excelVo) {
        return importCos(url, excelVo, 1);
    }

    public static <E extends DefaultExcelData> List<E> importCos(String url, Class<E> excelVo, int headRowNumber) {
        DefaultExcelReadListener<E> listener = new DefaultExcelReadListener<>();
        importCos(url, excelVo, listener, headRowNumber);
        return listener.getList();
    }

    public static <E> void importCos(String url, Class<E> excelVo, ReadListener<E> listener, int headRowNumber) {
        URI uri = URI.create(url);
        try (InputStream inputStream = uri.toURL().openStream()) {
            EasyExcel.read(inputStream, excelVo, listener)
                .sheet()
                .headRowNumber(headRowNumber)
                .doRead();
        } catch (Exception e) {
            LogUtil.error(EasyExcelUtil.class, "文件导入异常:{}", e.getMessage());
            throw new BizException("文件导入异常:" + e.getMessage());
        }
    }

}
