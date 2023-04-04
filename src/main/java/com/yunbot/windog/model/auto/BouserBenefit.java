package com.yunbot.windog.model.auto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.lang.Integer;

/**
 *  BouserBenefit 
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-19 21:54:37
 */
 @ApiModel(value="BouserBenefit", description="")
public class BouserBenefit implements Serializable {

	private static final long serialVersionUID = 1L;
	
		
	/** 序号 **/
	@ApiModelProperty(value = "序号")
	private Integer sid;
		
	/** 用户ID **/
	@ApiModelProperty(value = "用户ID")
	private Integer uid;
		
	/** 昵称 **/
	@ApiModelProperty(value = "昵称")
	private String nick;
		
	/** 日期 **/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value = "日期")
	private Date curdt;
		
	/** 累计天数 **/
	@ApiModelProperty(value = "累计天数")
	private Integer days;
		
	/** 累计收益 **/
	@ApiModelProperty(value = "累计收益")
	private BigDecimal benefit;
		
		
	public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
	 
			
	public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
	 
			
	public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
	 
			
	public Date getCurdt() {
        return curdt;
    }

    public void setCurdt(Date curdt) {
        this.curdt = curdt;
    }
	 
			
	public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
	 
			
	public BigDecimal getBenefit() {
        return benefit;
    }

    public void setBenefit(BigDecimal benefit) {
        this.benefit = benefit;
    }
	 
			
	public BouserBenefit() {
        super();
    }
    
																																
	public BouserBenefit(Integer sid,Integer uid,String nick,Date curdt,Integer days,BigDecimal benefit) {
	
		this.sid = sid;
		this.uid = uid;
		this.nick = nick;
		this.curdt = curdt;
		this.days = days;
		this.benefit = benefit;
		
	}
	
}