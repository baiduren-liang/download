package com.xsg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 进出口明细数据临时表
 * </p>
 *
 * @author bsh
 * @since 2023-11-15
 */

@TableName("import_export_info_temp")
@Setter
@Getter
public class ImportExportInfoTempEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "项号")
    private String goodNo;

    @Schema(description = "企业18位信用代码")
    private String orgCode;

    @Schema(description = "周期管理ID")
    private Long cycleId;

    @Schema(description = "报关单号")
    private String seqNo;

    @Schema(description = "监管方式")
    private String tradeMode;

    @Schema(description = "运输方式")
    private String transportMode;

    @Schema(description = "监管方式代码")
    private String tradeModeCode;

    @Schema(description = "运输方式代码")
    private String transportModeCode;

    @Schema(description = "申报日期")
    private Date dDate;

    @Schema(description = "进出口日期")
    private Date importExportDate;

    @Schema(description = "进出口;1-进口、2-出口")
    private Integer iEFlag;

    @Schema(description = "贸易国")
    private String tradeAreaCode;

    @Schema(description = "海关关别")
    private String customsName;

    @Schema(description = "海关关别code")
    private String customs;

    @Schema(description = "境内目的地/境内货源地")
    private String districtCode;

    @Schema(description = "商品编号")
    private String codeTs;

    @Schema(description = "商品名称")
    private String gName;

    @Schema(description = "数量")
    private BigDecimal gQty;

    @Schema(description = "单位")
    private String gUnit;

    @Schema(description = "币种")
    private String tradeCurr;

    @Schema(description = "总价")
    private BigDecimal declTotal;

    @Schema(description = "创建人")
    private String createdBy;

    @Schema(description = "创建时间")
    private Date createdTime;

    @Schema(description = "更新人")
    private String updatedBy;

    @Schema(description = "更新时间")
    private Date updatedTime;

    @Schema(description = "删除标志, 1-已删除 0-未删除")
    private Integer deleteFlag;

    @Schema(description = "贸易国")
    private String tradeAreaName;

    @Schema(description = "单位")
    private String gUnitName;

    @Schema(description = "币种")
    private String tradeCurrName;

    @Schema(description = "境内目的地/境内货源地")
    private String districtName;

    @Schema(description = "人民币总额")
    private BigDecimal rmbAmount;

    @Schema(description = "美元总额")
    private BigDecimal dollarAmount;

    @Schema(description = "1-一线、2-二线")
    private Integer decLevel;

    @Schema(description = "运抵国/启运国")
    private String arrivalDepartureAreaName;

    @Schema(description = "运抵国/启运国代码")
    private String arrivalDepartureAreaCode;

    @Schema(description = "原产国")
    private String originCountryName;

    @Schema(description = "原产国代码")
    private String originCountryCode;

    @Schema(description = "最终目的国")
    private String destinationCountryName;

    @Schema(description = "最终目的国代码")
    private String destinationCountryCode;

    @Schema(description = "附件表ID")
    private Long fileInfoId;

    @Schema(description = "报关单类型")
    private Integer infoType;

    @Schema(description = "报关单名称类型")
    private String reportType;

    @Schema(description = "成交方式")
    private String transactionMethod;

    @Schema(description = "运费币制")
    private String yTrade;

    @Schema(description = "运费金额")
    private String yAmount;

    @Schema(description = "运费标记")
    private String yMark;

    @Schema(description = "保费币制")
    private String bTrade;

    @Schema(description = "保费金额")
    private String bAmount;

    @Schema(description = "保费标记")
    private String bMark;

    @Schema(description = "杂费币制")
    private String zTrade;

    @Schema(description = "杂费金额")
    private String zAmount;

    @Schema(description = "杂费标记")
    private String zMark;

    @Schema(description = "报关单goods解析明细")
    private String goodsInfo;

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
