package com.yunbot.windog.model.auto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.lang.Integer;

/**
 *  StockLib 
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-13 23:03:08
 */
 @ApiModel(value="StockLib", description="")
public class StockLib implements Serializable {

	private static final long serialVersionUID = 1L;
	
		
	/** 序号 **/
	@ApiModelProperty(value = "序号")
	private Integer sid;
		
	/** 股票代码 **/
	@ApiModelProperty(value = "股票代码")
	private String code;
		
	/** 日期 **/
	@ApiModelProperty(value = "日期")
	private String insdt;
		
	/** 指标名 **/
	@ApiModelProperty(value = "指标名")
	private String metric;
		
	/** 指标值 **/
	@ApiModelProperty(value = "指标值")
	private Integer val;
		
		
	public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
	 
			
	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
	 
			
	public String getInsdt() {
        return insdt;
    }

    public void setInsdt(String insdt) {
        this.insdt = insdt;
    }
	 
			
	public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }
	 
			
	public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
	 
			
	public StockLib() {
        super();
    }
    
																											
	public StockLib(Integer sid,String code,String insdt,String metric,Integer val) {
	
		this.sid = sid;
		this.code = code;
		this.insdt = insdt;
		this.metric = metric;
		this.val = val;
		
	}
	
}