package com.yunbot.windog.model.auto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.lang.Integer;

/**
 *  Botransaction 
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-18 23:22:27
 */
 @ApiModel(value="Botransaction", description="")
public class Botransaction implements Serializable {

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
	private BigDecimal amount;
		
	/** 卖出价 **/
	@ApiModelProperty(value = "卖出价")
	private BigDecimal price;
		
	/** 买入价 **/
	@ApiModelProperty(value = "买入价")
	private BigDecimal inPrice;
		
	/** 买入日期 **/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value = "买入日期")
	private Date inDate;
		
	/** 卖出日期 **/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value = "卖出日期")
	private Date curDate;
		
	/** 原因 **/
	@ApiModelProperty(value = "原因")
	private Integer type;
		
		
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
	 
			
	public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
	 
			
	public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
	 
			
	public BigDecimal getInPrice() {
        return inPrice;
    }

    public void setInPrice(BigDecimal inPrice) {
        this.inPrice = inPrice;
    }
	 
			
	public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }
	 
			
	public Date getCurDate() {
        return curDate;
    }

    public void setCurDate(Date curDate) {
        this.curDate = curDate;
    }
	 
			
	public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
	 
			
	public Botransaction() {
        super();
    }
    
																																																				
	public Botransaction(Integer tid,Integer uid,String code,String cname,BigDecimal amount,BigDecimal price,BigDecimal inPrice,Date inDate,Date curDate,Integer type) {
	
		this.tid = tid;
		this.uid = uid;
		this.code = code;
		this.cname = cname;
		this.amount = amount;
		this.price = price;
		this.inPrice = inPrice;
		this.inDate = inDate;
		this.curDate = curDate;
		this.type = type;
		
	}
	
}