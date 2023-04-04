package com.yunbot.windog.model.auto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.lang.Integer;

/**
 *  Bohold 
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-18 23:21:07
 */
 @ApiModel(value="Bohold", description="")
public class Bohold implements Serializable {

	private static final long serialVersionUID = 1L;
	
		
	/** 序号 **/
	@ApiModelProperty(value = "序号")
	private Integer tid;
		
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
		
	/** 交易时间 **/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value = "交易时间")
	private Date curDate;
		
		
	public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
	 
			
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
	 
			
	public Date getCurDate() {
        return curDate;
    }

    public void setCurDate(Date curDate) {
        this.curDate = curDate;
    }
	 
			
	public Bohold() {
        super();
    }
    
																																										
	public Bohold(Integer tid,Integer uid,String code,String cname,Integer amount,BigDecimal price,BigDecimal topPrice,Date curDate) {
	
		this.tid = tid;
		this.uid = uid;
		this.code = code;
		this.cname = cname;
		this.amount = amount;
		this.price = price;
		this.topPrice = topPrice;
		this.curDate = curDate;
		
	}
	
}