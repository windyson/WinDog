package com.yunbot.windog.model.auto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.lang.Integer;

/**
 *  Boinfo 
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-13 23:02:09
 */
 @ApiModel(value="Boinfo", description="")
public class Boinfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
		
	/** 用户id **/
	@ApiModelProperty(value = "用户id")
	private Integer uid;
		
	/** 用户名字 **/
	@ApiModelProperty(value = "用户名字")
	private String name;
		
	/** 用户昵称 **/
	@ApiModelProperty(value = "用户昵称")
	private String nick;
		
	/** 注册日期 **/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value = "注册日期")
	private Date registerDt;
		
	/** 服务到期日 **/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value = "服务到期日")
	private Date serviceDt;
		
	/** 用户等级 **/
	@ApiModelProperty(value = "用户等级")
	private Integer grade;
		
	/** 可用资金 **/
	@ApiModelProperty(value = "可用资金")
	private BigDecimal funds;
		
	/** 初始资金 **/
	@ApiModelProperty(value = "初始资金")
	private BigDecimal fundsInit;
		
	/** 策略 **/
	@ApiModelProperty(value = "策略")
	private Integer policy;
		
	/** 止盈 **/
	@ApiModelProperty(value = "止盈")
	private BigDecimal win;
		
	/** 止损 **/
	@ApiModelProperty(value = "止损")
	private BigDecimal lost;
		
	/** 触发信号 **/
	@ApiModelProperty(value = "触发信号")
	private Integer alarm;
		
		
	public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
	 
			
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
	 
			
	public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
	 
			
	public Date getRegisterDt() {
        return registerDt;
    }

    public void setRegisterDt(Date registerDt) {
        this.registerDt = registerDt;
    }
	 
			
	public Date getServiceDt() {
        return serviceDt;
    }

    public void setServiceDt(Date serviceDt) {
        this.serviceDt = serviceDt;
    }
	 
			
	public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
	 
			
	public BigDecimal getFunds() {
        return funds;
    }

    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }
	 
			
	public BigDecimal getFundsInit() {
        return fundsInit;
    }

    public void setFundsInit(BigDecimal fundsInit) {
        this.fundsInit = fundsInit;
    }
	 
			
	public Integer getPolicy() {
        return policy;
    }

    public void setPolicy(Integer policy) {
        this.policy = policy;
    }
	 
			
	public BigDecimal getWin() {
        return win;
    }

    public void setWin(BigDecimal win) {
        this.win = win;
    }
	 
			
	public BigDecimal getLost() {
        return lost;
    }

    public void setLost(BigDecimal lost) {
        this.lost = lost;
    }
	 
			
	public Integer getAlarm() {
        return alarm;
    }

    public void setAlarm(Integer alarm) {
        this.alarm = alarm;
    }
	 
			
	public Boinfo() {
        super();
    }
    
																																																														
	public Boinfo(Integer uid,String name,String nick,Date registerDt,Date serviceDt,Integer grade,BigDecimal funds,BigDecimal fundsInit,Integer policy,BigDecimal win,BigDecimal lost,Integer alarm) {
	
		this.uid = uid;
		this.name = name;
		this.nick = nick;
		this.registerDt = registerDt;
		this.serviceDt = serviceDt;
		this.grade = grade;
		this.funds = funds;
		this.fundsInit = fundsInit;
		this.policy = policy;
		this.win = win;
		this.lost = lost;
		this.alarm = alarm;
		
	}
	
}