package com.poc.service;

import com.poc.model.ExcelFieldName;
import com.poc.util.AppUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

@Service
@Log4j2
public class ExcelService {

    private void writeXSLtoFilesystem(String excelFileName, XSSFWorkbook workbook) {
        try {
            FileOutputStream out = new FileOutputStream(new File(AppUtil.getProjectDir() + File.separator + excelFileName));
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeSheetPerObjList(String workBookName, Object... obj) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        for (Object j : obj) {
            writeSheetPerObject(workbook, j);
        }
        writeXSLtoFilesystem(workBookName, workbook);
    }


    public void writeSheetPerObject(XSSFWorkbook workbook, Object obj) {
        String calSheetName = "";
        if (obj instanceof List) {
            List myList = (List) obj;
            Set<String> headers = new LinkedHashSet<>();
            List<List<String>> valuesList = new ArrayList<>();
            for (Object object : myList) {
                List<String> values = new ArrayList<>();
                for (Field field : object.getClass().getDeclaredFields()) {
                    calSheetName = object.getClass().getSimpleName();
                    field.setAccessible(true);
                    headers.add(field.getAnnotation(ExcelFieldName.class).value());
                    try {
                        values.add(String.valueOf(field.get(object)));
                    } catch (IllegalAccessException e) {
                        log.error(e);
                        values.add("");
                    }
                }
                valuesList.add(values);
            }
            Map<String, Object[]> rowMap = new LinkedHashMap<String, Object[]>();
            rowMap.put(getRandom(), headers.toArray());
            for (List<String> val : valuesList) {
                rowMap.put(getRandom(), val.toArray());
            }
            XSSFSheet spreadsheet = workbook.createSheet(calSheetName);
            writeRowMapToSpreadSheet(spreadsheet, rowMap);
        }
    }

    private void writeRowMapToSpreadSheet(XSSFSheet spreadsheet, Map<String, Object[]> rowMap) {
        XSSFRow row;
        Set<String> keyid = rowMap.keySet();
        int rowid = 0;
        for (String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = rowMap.get(key);
            int cellid = 0;
            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue(ObjectUtils.getDisplayString(obj));
            }
        }
    }

    private String getRandom() {
        return RandomStringUtils.random(5, false, true);
    }

}
