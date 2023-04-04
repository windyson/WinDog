package com.yunbot.windog.model.auto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.lang.Integer;

/**
 * VIEW BoholdView 
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-20 23:21:22
 */
 @ApiModel(value="BoholdView", description="VIEW")
public class BoholdView implements Serializable {

	private static final long serialVersionUID = 1L;
	
		
	/** 用户ID **/
	@ApiModelProperty(value = "用户ID")
	private Integer uid;
		
	/** 股票代码 **/
	@ApiModelProperty(value = "股票代码")
	private String code;
		
	/** 股票名称 **/
	@ApiModelProperty(value = "股票名称")
	private String cname;
		
	/** 数量 **/
	@ApiModelProperty(value = "数量")
	private Integer amount;
		
	/** 价格 **/
	@ApiModelProperty(value = "价格")
	private BigDecimal price;
		
	/** 最高价 **/
	@ApiModelProperty(value = "最高价")
	private BigDecimal topPrice;
		
	/** 最新价格 **/
	@ApiModelProperty(value = "最新价格")
	private BigDecimal curPrice;
		
	/**  **/
	@ApiModelProperty(value = "")
	private BigDecimal benefit;
		
	/** 交易时间 **/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value = "交易时间")
	private Date curDate;
		
		
	public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
	 
			
	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
	 
			
	public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
	 
			
	public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
	 
			
	public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
	 
			
	public BigDecimal getTopPrice() {
        return topPrice;
    }

    public void setTopPrice(BigDecimal topPrice) {
        this.topPrice = topPrice;
    }
	 
			
	public BigDecimal getCurPrice() {
        return curPrice;
    }

    public void setCurPrice(BigDecimal curPrice) {
        this.curPrice = curPrice;
    }
	 
			
	public BigDecimal getBenefit() {
        return benefit;
    }

    public void setBenefit(BigDecimal benefit) {
        this.benefit = benefit;
    }
	 
			
	public Date getCurDate() {
        return curDate;
    }

    public void setCurDate(Date curDate) {
        this.curDate = curDate;
    }
	 
			
	public BoholdView() {
        super();
    }
    
																																															
	public BoholdView(Integer uid,String code,String cname,Integer amount,BigDecimal price,BigDecimal topPrice,BigDecimal curPrice,BigDecimal benefit,Date curDate) {
	
		this.uid = uid;
		this.code = code;
		this.cname = cname;
		this.amount = amount;
		this.price = price;
		this.topPrice = topPrice;
		this.curPrice = curPrice;
		this.benefit = benefit;
		this.curDate = curDate;
		
	}
	
}