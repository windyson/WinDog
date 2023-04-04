package com.yunbot.windog.model.auto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.lang.Integer;

/**
 *  StockPool 
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-13 23:01:45
 */
 @ApiModel(value="StockPool", description="")
public class StockPool implements Serializable {

	private static final long serialVersionUID = 1L;
	
		
	/** 序号 **/
	@ApiModelProperty(value = "序号")
	private Integer sid;
		
	/** 代码 **/
	@ApiModelProperty(value = "代码")
	private String code;
		
	/** 股票名称 **/
	@ApiModelProperty(value = "股票名称")
	private String cname;
		
	/** 评级 **/
	@ApiModelProperty(value = "评级")
	private Integer grade;
		
	/** 动作 **/
	@ApiModelProperty(value = "动作")
	private Integer act;
		
	/** 更新日期 **/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value = "更新日期")
	private Date updateDt;
		
	/** 最新价格 **/
	@ApiModelProperty(value = "最新价格")
	private BigDecimal curPrice;
		
	/** 开盘价 **/
	@ApiModelProperty(value = "开盘价")
	private BigDecimal vbeg;
		
	/** 收盘价 **/
	@ApiModelProperty(value = "收盘价")
	private BigDecimal vend;
		
	/** 昨收盘 **/
	@ApiModelProperty(value = "昨收盘")
	private BigDecimal lastend;
		
	/** 最高 **/
	@ApiModelProperty(value = "最高")
	private BigDecimal vhigh;
		
	/** 最低 **/
	@ApiModelProperty(value = "最低")
	private BigDecimal vlow;
		
	/** 成交量 **/
	@ApiModelProperty(value = "成交量")
	private BigDecimal vtotal;
		
	/** 成交金额 **/
	@ApiModelProperty(value = "成交金额")
	private BigDecimal vamount;
		
	/**  **/
	@ApiModelProperty(value = "")
	private Integer vol1;
		
	/**  **/
	@ApiModelProperty(value = "")
	private Integer vol2;
		
	/**  **/
	@ApiModelProperty(value = "")
	private Integer vol3;
		
	/**  **/
	@ApiModelProperty(value = "")
	private Integer vol4;
		
	/**  **/
	@ApiModelProperty(value = "")
	private Integer vol5;
		
	/**  **/
	@ApiModelProperty(value = "")
	private Integer vol6;
		
	/**  **/
	@ApiModelProperty(value = "")
	private Integer vol7;
		
	/**  **/
	@ApiModelProperty(value = "")
	private Integer vol8;
		
	/**  **/
	@ApiModelProperty(value = "")
	private Integer vol9;
		
	/**  **/
	@ApiModelProperty(value = "")
	private Integer vol10;
		
	/**  **/
	@ApiModelProperty(value = "")
	private Integer vol11;
		
	/**  **/
	@ApiModelProperty(value = "")
	private Integer vol12;
		
	/**  **/
	@ApiModelProperty(value = "")
	private Integer vol13;
		
	/**  **/
	@ApiModelProperty(value = "")
	private Integer vol14;
		
	/**  **/
	@ApiModelProperty(value = "")
	private Integer vol15;
		
	/**  **/
	@ApiModelProperty(value = "")
	private String list;
		
	/** 描述 **/
	@ApiModelProperty(value = "描述")
	private String description;
		
		
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
	 
			
	public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
	 
			
	public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
	 
			
	public Integer getAct() {
        return act;
    }

    public void setAct(Integer act) {
        this.act = act;
    }
	 
			
	public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }
	 
			
	public BigDecimal getCurPrice() {
        return curPrice;
    }

    public void setCurPrice(BigDecimal curPrice) {
        this.curPrice = curPrice;
    }
	 
			
	public BigDecimal getVbeg() {
        return vbeg;
    }

    public void setVbeg(BigDecimal vbeg) {
        this.vbeg = vbeg;
    }
	 
			
	public BigDecimal getVend() {
        return vend;
    }

    public void setVend(BigDecimal vend) {
        this.vend = vend;
    }
	 
			
	public BigDecimal getLastend() {
        return lastend;
    }

    public void setLastend(BigDecimal lastend) {
        this.lastend = lastend;
    }
	 
			
	public BigDecimal getVhigh() {
        return vhigh;
    }

    public void setVhigh(BigDecimal vhigh) {
        this.vhigh = vhigh;
    }
	 
			
	public BigDecimal getVlow() {
        return vlow;
    }

    public void setVlow(BigDecimal vlow) {
        this.vlow = vlow;
    }
	 
			
	public BigDecimal getVtotal() {
        return vtotal;
    }

    public void setVtotal(BigDecimal vtotal) {
        this.vtotal = vtotal;
    }
	 
			
	public BigDecimal getVamount() {
        return vamount;
    }

    public void setVamount(BigDecimal vamount) {
        this.vamount = vamount;
    }
	 
			
	public Integer getVol1() {
        return vol1;
    }

    public void setVol1(Integer vol1) {
        this.vol1 = vol1;
    }
	 
			
	public Integer getVol2() {
        return vol2;
    }

    public void setVol2(Integer vol2) {
        this.vol2 = vol2;
    }
	 
			
	public Integer getVol3() {
        return vol3;
    }

    public void setVol3(Integer vol3) {
        this.vol3 = vol3;
    }
	 
			
	public Integer getVol4() {
        return vol4;
    }

    public void setVol4(Integer vol4) {
        this.vol4 = vol4;
    }
	 
			
	public Integer getVol5() {
        return vol5;
    }

    public void setVol5(Integer vol5) {
        this.vol5 = vol5;
    }
	 
			
	public Integer getVol6() {
        return vol6;
    }

    public void setVol6(Integer vol6) {
        this.vol6 = vol6;
    }
	 
			
	public Integer getVol7() {
        return vol7;
    }

    public void setVol7(Integer vol7) {
        this.vol7 = vol7;
    }
	 
			
	public Integer getVol8() {
        return vol8;
    }

    public void setVol8(Integer vol8) {
        this.vol8 = vol8;
    }
	 
			
	public Integer getVol9() {
        return vol9;
    }

    public void setVol9(Integer vol9) {
        this.vol9 = vol9;
    }
	 
			
	public Integer getVol10() {
        return vol10;
    }

    public void setVol10(Integer vol10) {
        this.vol10 = vol10;
    }
	 
			
	public Integer getVol11() {
        return vol11;
    }

    public void setVol11(Integer vol11) {
        this.vol11 = vol11;
    }
	 
			
	public Integer getVol12() {
        return vol12;
    }

    public void setVol12(Integer vol12) {
        this.vol12 = vol12;
    }
	 
			
	public Integer getVol13() {
        return vol13;
    }

    public void setVol13(Integer vol13) {
        this.vol13 = vol13;
    }
	 
			
	public Integer getVol14() {
        return vol14;
    }

    public void setVol14(Integer vol14) {
        this.vol14 = vol14;
    }
	 
			
	public Integer getVol15() {
        return vol15;
    }

    public void setVol15(Integer vol15) {
        this.vol15 = vol15;
    }
	 
			
	public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }
	 
			
	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
	 
			
	public StockPool() {
        super();
    }
    
																																																																																																																																																													
	public StockPool(Integer sid,String code,String cname,Integer grade,Integer act,Date updateDt,BigDecimal curPrice,BigDecimal vbeg,BigDecimal vend,BigDecimal lastend,BigDecimal vhigh,BigDecimal vlow,BigDecimal vtotal,BigDecimal vamount,Integer vol1,Integer vol2,Integer vol3,Integer vol4,Integer vol5,Integer vol6,Integer vol7,Integer vol8,Integer vol9,Integer vol10,Integer vol11,Integer vol12,Integer vol13,Integer vol14,Integer vol15,String list,String description) {
	
		this.sid = sid;
		this.code = code;
		this.cname = cname;
		this.grade = grade;
		this.act = act;
		this.updateDt = updateDt;
		this.curPrice = curPrice;
		this.vbeg = vbeg;
		this.vend = vend;
		this.lastend = lastend;
		this.vhigh = vhigh;
		this.vlow = vlow;
		this.vtotal = vtotal;
		this.vamount = vamount;
		this.vol1 = vol1;
		this.vol2 = vol2;
		this.vol3 = vol3;
		this.vol4 = vol4;
		this.vol5 = vol5;
		this.vol6 = vol6;
		this.vol7 = vol7;
		this.vol8 = vol8;
		this.vol9 = vol9;
		this.vol10 = vol10;
		this.vol11 = vol11;
		this.vol12 = vol12;
		this.vol13 = vol13;
		this.vol14 = vol14;
		this.vol15 = vol15;
		this.list = list;
		this.description = description;
		
	}
	
}