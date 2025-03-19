package com.xsg.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author bsh
 * @since 2023-11-15
 */
@Data
@HeadRowHeight(20)
@ColumnWidth(20)
public class IESearchExcelDTO {

    @Schema(description = "企业名称")
    @ExcelProperty("企业名称")
    private String orgName;

    @Schema(description = "所属区域")
    @ExcelProperty("所属区域")
    private String region;

    @Schema(description = "报关单号")
    @ExcelProperty("报关单号")
    private String seqNo;

    @Schema(description = "一线/二线关单")
    @ExcelProperty("一线/二线关单")
    private String decLevel;

    @Schema(description = "监管方式")
    @ExcelProperty("监管方式")
    private String tradeMode;

    @Schema(description = "申报日期")
    @ExcelProperty("申报日期")
    @DateTimeFormat("yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @JsonProperty("dDate")
    private String dDate;

    @Schema(description = "进出口日期")
    @ExcelProperty("进出口日期")
    @DateTimeFormat("yyyy-MM-dd")
    @JsonProperty("importExportDate")
    private String importExportDate;

    @Schema(description = "进出口")
    @ExcelProperty("进出口")
    @JsonProperty("iEFlag")
    private String iEFlag;

    @Schema(description = "运输方式")
    @ExcelProperty("运输方式")
    private String transportMode;

    @Schema(description = "海关关区")
    @ExcelProperty("海关关区")
    private String customsName;

    @Schema(description = "贸易国")
    @ExcelProperty("贸易国")
    private String tradeAreaName;

    @Schema(description = "运抵国/启运国")
    @ExcelProperty("运抵国/启运国")
    private String arrivalDepartureAreaName;

    @Schema(description = "境内目的地/境内货源地")
    @ExcelProperty("境内目的地/境内货源地")
    private String districtName;

    @Schema(description = "商品编号")
    @ExcelProperty("商品编号")
    private String codeTs;

    @Schema(description = "商品名称")
    @ExcelProperty("商品名称")
    @JsonProperty("gName")
    private String gName;

    @Schema(description = "数量")
    @ExcelProperty("数量")
    @JsonProperty("gQty")
    private BigDecimal gQty;

    @Schema(description = "单位")
    @ExcelProperty("单位")
    @JsonProperty("gUnitName")
    private String gUnitName;

    @Schema(description = "币种")
    @ExcelProperty("币种")
    private String tradeCurrName;

    @Schema(description = "总价")
    @ExcelProperty("总价")
    private BigDecimal declTotal;

    @Schema(description = "原产国")
    @ExcelProperty("原产国")
    private String originCountryName;

    @Schema(description = "最终目的国")
    @ExcelProperty("最终目的国")
    private String destinationCountryName;
}
