package com.yunbot.windog.model.auto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.lang.Integer;

/**
 *  StockCal 
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-13 23:01:36
 */
 @ApiModel(value="StockCal", description="")
public class StockCal implements Serializable {

	private static final long serialVersionUID = 1L;
	
		
	/** 序号 **/
	@ApiModelProperty(value = "序号")
	private Integer id;
		
	/** 交易日 **/
	@ApiModelProperty(value = "交易日")
	private String opendt;
		
		
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	 
			
	public String getOpendt() {
        return opendt;
    }

    public void setOpendt(String opendt) {
        this.opendt = opendt;
    }
	 
			
	public StockCal() {
        super();
    }
    
												
	public StockCal(Integer id,String opendt) {
	
		this.id = id;
		this.opendt = opendt;
		
	}
	
}