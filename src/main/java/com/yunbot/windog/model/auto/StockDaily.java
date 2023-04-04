package com.yunbot.windog.model.auto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.lang.Double;

/**
 *  StockDaily 
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2023-01-28 13:51:36
 */
 @ApiModel(value="StockDaily", description="")
public class StockDaily implements Serializable {

	private static final long serialVersionUID = 1L;
	
		
	/** 代码 **/
	@ApiModelProperty(value = "代码")
	private String tsCode;
		
	/** 交易日期 **/
	@ApiModelProperty(value = "交易日期")
	private String tradeDate;
		
	/** 开盘价 **/
	@ApiModelProperty(value = "开盘价")
	private Double open;
		
	/** 最高价 **/
	@ApiModelProperty(value = "最高价")
	private Double high;
		
	/** 最低价 **/
	@ApiModelProperty(value = "最低价")
	private Double low;
		
	/** 收盘价 **/
	@ApiModelProperty(value = "收盘价")
	private Double cls;
		
	/** 前日收盘 **/
	@ApiModelProperty(value = "前日收盘")
	private Double preClose;
		
	/** 涨跌 **/
	@ApiModelProperty(value = "涨跌")
	private Double chg;
		
	/** 幅度 **/
	@ApiModelProperty(value = "幅度")
	private Double pctChg;
		
	/** 成交量 **/
	@ApiModelProperty(value = "成交量")
	private Double vol;
		
	/** 成交金额 **/
	@ApiModelProperty(value = "成交金额")
	private Double amount;
		
		
	public String getTsCode() {
        return tsCode;
    }

    public void setTsCode(String tsCode) {
        this.tsCode = tsCode;
    }
	 
			
	public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }
	 
			
	public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }
	 
			
	public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }
	 
			
	public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }
	 
			
	public Double getCls() {
        return cls;
    }

    public void setCls(Double cls) {
        this.cls = cls;
    }
	 
			
	public Double getPreClose() {
        return preClose;
    }

    public void setPreClose(Double preClose) {
        this.preClose = preClose;
    }
	 
			
	public Double getChg() {
        return chg;
    }

    public void setChg(Double chg) {
        this.chg = chg;
    }
	 
			
	public Double getPctChg() {
        return pctChg;
    }

    public void setPctChg(Double pctChg) {
        this.pctChg = pctChg;
    }
	 
			
	public Double getVol() {
        return vol;
    }

    public void setVol(Double vol) {
        this.vol = vol;
    }
	 
			
	public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
	 
			
	public StockDaily() {
        super();
    }
    
																																																									
	public StockDaily(String tsCode,String tradeDate,Double open,Double high,Double low,Double cls,Double preClose,Double chg,Double pctChg,Double vol,Double amount) {
	
		this.tsCode = tsCode;
		this.tradeDate = tradeDate;
		this.open = open;
		this.high = high;
		this.low = low;
		this.cls = cls;
		this.preClose = preClose;
		this.chg = chg;
		this.pctChg = pctChg;
		this.vol = vol;
		this.amount = amount;
		
	}
	
}