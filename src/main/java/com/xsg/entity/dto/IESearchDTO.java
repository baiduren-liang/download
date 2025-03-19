package com.xsg.entity.dto;

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
public class IESearchDTO {

    @Schema(description = "企业名称")
    private String orgName;

    @Schema(description = "所属区域")
    private String region;

    @Schema(description = "报关单号")
    private String seqNo;

    @Schema(description = "一线/二线关单")
    private String decLevel;

    @Schema(description = "监管方式")
    private String tradeMode;

    @Schema(description = "申报日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @JsonProperty("dDate")
    private String dDate;

    @Schema(description = "进出口日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @JsonProperty("importExportDate")
    private String importExportDate;

    @Schema(description = "进出口")
    @JsonProperty("iEFlag")
    private String iEFlag;

    @Schema(description = "运输方式")
    private String transportMode;

    @Schema(description = "海关关区")
    private String customsName;

    @Schema(description = "贸易国")
    private String tradeAreaName;

    @Schema(description = "运抵国/启运国")
    private String arrivalDepartureAreaName;

    @Schema(description = "境内目的地/境内货源地")
    private String districtName;

    @Schema(description = "商品编号")
    private String codeTs;

    @Schema(description = "商品名称")
    @JsonProperty("gName")
    private String gName;

    @Schema(description = "数量")
    @JsonProperty("gQty")
    private BigDecimal gQty;

    @Schema(description = "单位")
    @JsonProperty("gUnitName")
    private String gUnitName;

    @Schema(description = "币种")
    private String tradeCurrName;

    @Schema(description = "总价")
    private BigDecimal declTotal;

    @Schema(description = "原产国")
    private String originCountryName;

    @Schema(description = "最终目的国")
    private String destinationCountryName;

    @Schema(description = "电商企业名称")
    private String ecommerceOrgName;

    @Schema(description = "电商企业编码")
    private String ecommerceOrgCode;

    @Schema(description = "口岸海关关区代码")
    private String portCustoms;

    @Schema(description = "口岸海关名称")
    private String portCustomsName;

    @Schema(description = "毛重（千克）")
    private BigDecimal grossWeight;

    @Schema(description = "件数")
    private Integer packagesNumber;

    @Schema(description = "数据来源 report-默认报关单 9610-9610模版上传")
    private String dataSource = "report";
}
