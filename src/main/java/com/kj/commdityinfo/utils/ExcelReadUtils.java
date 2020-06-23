package com.kj.commdityinfo.utils;


import com.kj.commdityinfo.bean.Item;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author kj
 * @Date 2020/5/21 18:20
 * @Version 1.0
 */
public class ExcelReadUtils {

    public static final Integer TOTAL = 521;

    public static List<Item> getItems() throws Exception{
        List<Item> items = new ArrayList<>();
        Random random = new Random();
        //1.读取Excel文档对象
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("C:/Users/kuangjie/Desktop/shop/market-final.xlsx"));

        System.out.println(xssfWorkbook);
        //2.获取要解析的表格（第一个表格）
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        //获得最后一行的行号
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= TOTAL; i++) {//遍历每一行
            //3.获得要解析的行
            XSSFRow row = sheet.getRow(i);
            //4.获得每个单元格中的内容（String）
            String description = null;
            if(row.getCell(4) != null){
                description = row.getCell(4).toString();
            }else {
                description = "";
            }
            String itemIdStr = row.getCell(0).toString();
            itemIdStr = itemIdStr.replaceAll("\\.0", "");
            Integer itemId = Integer.valueOf(itemIdStr);
            String name = row.getCell(1).toString();
            String priceStr = row.getCell(2).toString();
            Double price = Double.valueOf(priceStr);
//            String unit = row.getCell(3).toString();
            String categoryIdStr = row.getCell(6).toString();
            categoryIdStr = categoryIdStr.replaceAll("\\.0", "");
            Integer categoryId = Integer.valueOf(categoryIdStr);
            String location = row.getCell(7).toString();
            String imgUrl = itemId.toString() + ".jpg";
            Item item = new Item(itemId,name,location,new Date(System.currentTimeMillis()),description,1,random.nextInt(1000),price,"https://www.123.com", 1, imgUrl, categoryId, null);
//            Item item = new Item(null, name, "http://", price, description, 0, categoryId, 0, 0.0d, 0, unit, null, null);
            items.add(item);
        }
        return items;
    }

//    public static void main(String[] args){
//        try{
//            List<Item> items = ExcelReadUtils.getItems();
//            System.out.println(items.get(10));
//            System.out.println(items.get(13));
//            System.out.println(items.get(520));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
}
