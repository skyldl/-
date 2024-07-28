package com.itxiaofeng.spzx.manager;

import com.alibaba.excel.EasyExcel;

import com.itxiaofeng.spzx.model.vo.product.CategoryExcelVo;

import java.util.ArrayList;
import java.util.List;

public class EasyExcelTest {

    public static void main(String[] args) {
        writeDataToExcel();
    }

    public static void writeDataToExcel() {
        List<CategoryExcelVo> list = new ArrayList<>() ;
        list.add(new CategoryExcelVo(1L , "数码办公" , "",0L, 1, 1)) ;
        list.add(new CategoryExcelVo(11L , "华为手机" , "",1L, 1, 2)) ;
        EasyExcel.write("D://分类数据1.xlsx" , CategoryExcelVo.class).sheet("分类数据1").doWrite(list);
    }
}