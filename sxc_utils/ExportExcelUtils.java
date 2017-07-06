package com.xz.base.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import net.sf.jxls.transformer.XLSTransformer;

/**
 * 模板导出EXCEL
 * @author andy
 *
 */

public class ExportExcelUtils {
    @SuppressWarnings("rawtypes")
	public static void exportExcel(Map beans,String srcPath,OutputStream os){
        XLSTransformer transformer = new XLSTransformer();
        try {
            //获得模板的输入流
            FileInputStream in = new FileInputStream(srcPath);
            //将beans通过模板输入流写到workbook中
            HSSFWorkbook workbook = transformer.transformXLS(in, beans);
            //System.out.println("!!!!!!!!!"+HSSFWorkbook.class.getProtectionDomain().getCodeSource().getLocation());
            //将workbook中的内容用输出流写出去
            workbook.write(os);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally{
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}