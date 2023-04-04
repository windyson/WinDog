package com.yunbot.windog.model.auto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import cn.hutool.core.util.StrUtil;
/**
 *  BoholdExample
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-18 23:21:07
 */
public class BoholdExample {

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BoholdExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }
				
        public Criteria andTidIsNull() {
            addCriterion("tid is null");
            return (Criteria) this;
        }

        public Criteria andTidIsNotNull() {
            addCriterion("tid is not null");
            return (Criteria) this;
        }

        public Criteria andTidEqualTo(Integer value) {
            addCriterion("tid =", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotEqualTo(Integer value) {
            addCriterion("tid <>", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThan(Integer value) {
            addCriterion("tid >", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThanOrEqualTo(Integer value) {
            addCriterion("tid >=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThan(Integer value) {
            addCriterion("tid <", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThanOrEqualTo(Integer value) {
            addCriterion("tid <=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLike(Integer value) {
            addCriterion("tid like", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotLike(Integer value) {
            addCriterion("tid not like", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidIn(List<Integer> values) {
            addCriterion("tid in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotIn(List<Integer> values) {
            addCriterion("tid not in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidBetween(Integer value1, Integer value2) {
            addCriterion("tid between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotBetween(Integer value1, Integer value2) {
            addCriterion("tid not between", value1, value2, "tid");
            return (Criteria) this;
        }
        
			
        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLike(Integer value) {
            addCriterion("uid like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotLike(Integer value) {
            addCriterion("uid not like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }
        
			
        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }
        
			
        public Criteria andCnameIsNull() {
            addCriterion("cname is null");
            return (Criteria) this;
        }

        public Criteria andCnameIsNotNull() {
            addCriterion("cname is not null");
            return (Criteria) this;
        }

        public Criteria andCnameEqualTo(String value) {
            addCriterion("cname =", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotEqualTo(String value) {
            addCriterion("cname <>", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameGreaterThan(String value) {
            addCriterion("cname >", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameGreaterThanOrEqualTo(String value) {
            addCriterion("cname >=", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameLessThan(String value) {
            addCriterion("cname <", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameLessThanOrEqualTo(String value) {
            addCriterion("cname <=", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameLike(String value) {
            addCriterion("cname like", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotLike(String value) {
            addCriterion("cname not like", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameIn(List<String> values) {
            addCriterion("cname in", values, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotIn(List<String> values) {
            addCriterion("cname not in", values, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameBetween(String value1, String value2) {
            addCriterion("cname between", value1, value2, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotBetween(String value1, String value2) {
            addCriterion("cname not between", value1, value2, "cname");
            return (Criteria) this;
        }
        
			
        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLike(Integer value) {
            addCriterion("amount like", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotLike(Integer value) {
            addCriterion("amount not like", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }
        
			
        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLike(BigDecimal value) {
            addCriterion("price like", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotLike(BigDecimal value) {
            addCriterion("price not like", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }
        
			
        public Criteria andTopPriceIsNull() {
            addCriterion("top_price is null");
            return (Criteria) this;
        }

        public Criteria andTopPriceIsNotNull() {
            addCriterion("top_price is not null");
            return (Criteria) this;
        }

        public Criteria andTopPriceEqualTo(BigDecimal value) {
            addCriterion("top_price =", value, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceNotEqualTo(BigDecimal value) {
            addCriterion("top_price <>", value, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceGreaterThan(BigDecimal value) {
            addCriterion("top_price >", value, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("top_price >=", value, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceLessThan(BigDecimal value) {
            addCriterion("top_price <", value, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("top_price <=", value, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceLike(BigDecimal value) {
            addCriterion("top_price like", value, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceNotLike(BigDecimal value) {
            addCriterion("top_price not like", value, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceIn(List<BigDecimal> values) {
            addCriterion("top_price in", values, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceNotIn(List<BigDecimal> values) {
            addCriterion("top_price not in", values, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("top_price between", value1, value2, "topPrice");
            return (Criteria) this;
        }

        public Criteria andTopPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("top_price not between", value1, value2, "topPrice");
            return (Criteria) this;
        }
        
			
        public Criteria andCurDateIsNull() {
            addCriterion("cur_date is null");
            return (Criteria) this;
        }

        public Criteria andCurDateIsNotNull() {
            addCriterion("cur_date is not null");
            return (Criteria) this;
        }

        public Criteria andCurDateEqualTo(Date value) {
            addCriterion("cur_date =", value, "curDate");
            return (Criteria) this;
        }

        public Criteria andCurDateNotEqualTo(Date value) {
            addCriterion("cur_date <>", value, "curDate");
            return (Criteria) this;
        }

        public Criteria andCurDateGreaterThan(Date value) {
            addCriterion("cur_date >", value, "curDate");
            return (Criteria) this;
        }

        public Criteria andCurDateGreaterThanOrEqualTo(Date value) {
            addCriterion("cur_date >=", value, "curDate");
            return (Criteria) this;
        }

        public Criteria andCurDateLessThan(Date value) {
            addCriterion("cur_date <", value, "curDate");
            return (Criteria) this;
        }

        public Criteria andCurDateLessThanOrEqualTo(Date value) {
            addCriterion("cur_date <=", value, "curDate");
            return (Criteria) this;
        }

        public Criteria andCurDateLike(Date value) {
            addCriterion("cur_date like", value, "curDate");
            return (Criteria) this;
        }

        public Criteria andCurDateNotLike(Date value) {
            addCriterion("cur_date not like", value, "curDate");
            return (Criteria) this;
        }

        public Criteria andCurDateIn(List<Date> values) {
            addCriterion("cur_date in", values, "curDate");
            return (Criteria) this;
        }

        public Criteria andCurDateNotIn(List<Date> values) {
            addCriterion("cur_date not in", values, "curDate");
            return (Criteria) this;
        }

        public Criteria andCurDateBetween(Date value1, Date value2) {
            addCriterion("cur_date between", value1, value2, "curDate");
            return (Criteria) this;
        }

        public Criteria andCurDateNotBetween(Date value1, Date value2) {
            addCriterion("cur_date not between", value1, value2, "curDate");
            return (Criteria) this;
        }
        
		
		 public Criteria andLikeQuery(Bohold record) {
		 	List<String> list= new ArrayList<String>();
		 	List<String> list2= new ArrayList<String>();
        	StringBuffer buffer=new StringBuffer();
        	
        				 
			 if(record.getTid()!=null&&StrUtil.isNotEmpty(record.getTid().toString())) {
    			 list.add("ifnull(tid,'')");
    		 }
    		 
						 
			 if(record.getUid()!=null&&StrUtil.isNotEmpty(record.getUid().toString())) {
    			 list.add("ifnull(uid,'')");
    		 }
    		 
						 
			 if(record.getCode()!=null&&StrUtil.isNotEmpty(record.getCode().toString())) {
    			 list.add("ifnull(code,'')");
    		 }
    		 
						 
			 if(record.getCname()!=null&&StrUtil.isNotEmpty(record.getCname().toString())) {
    			 list.add("ifnull(cname,'')");
    		 }
    		 
						 
			 if(record.getAmount()!=null&&StrUtil.isNotEmpty(record.getAmount().toString())) {
    			 list.add("ifnull(amount,'')");
    		 }
    		 
						 
			 if(record.getPrice()!=null&&StrUtil.isNotEmpty(record.getPrice().toString())) {
    			 list.add("ifnull(price,'')");
    		 }
    		 
						 
			 if(record.getTopPrice()!=null&&StrUtil.isNotEmpty(record.getTopPrice().toString())) {
    			 list.add("ifnull(top_price,'')");
    		 }
    		 
						 
			 if(record.getCurDate()!=null&&StrUtil.isNotEmpty(record.getCurDate().toString())) {
    			 list.add("ifnull(cur_date,'')");
    		 }
    		 
									
			 if(record.getTid()!=null&&StrUtil.isNotEmpty(record.getTid().toString())) {
    			list2.add("'%"+record.getTid()+"%'");
    		 }
        		 
						
			 if(record.getUid()!=null&&StrUtil.isNotEmpty(record.getUid().toString())) {
    			list2.add("'%"+record.getUid()+"%'");
    		 }
        		 
						
			 if(record.getCode()!=null&&StrUtil.isNotEmpty(record.getCode().toString())) {
    			list2.add("'%"+record.getCode()+"%'");
    		 }
        		 
						
			 if(record.getCname()!=null&&StrUtil.isNotEmpty(record.getCname().toString())) {
    			list2.add("'%"+record.getCname()+"%'");
    		 }
        		 
						
			 if(record.getAmount()!=null&&StrUtil.isNotEmpty(record.getAmount().toString())) {
    			list2.add("'%"+record.getAmount()+"%'");
    		 }
        		 
						
			 if(record.getPrice()!=null&&StrUtil.isNotEmpty(record.getPrice().toString())) {
    			list2.add("'%"+record.getPrice()+"%'");
    		 }
        		 
						
			 if(record.getTopPrice()!=null&&StrUtil.isNotEmpty(record.getTopPrice().toString())) {
    			list2.add("'%"+record.getTopPrice()+"%'");
    		 }
        		 
						
			 if(record.getCurDate()!=null&&StrUtil.isNotEmpty(record.getCurDate().toString())) {
    			list2.add("'%"+record.getCurDate()+"%'");
    		 }
        		 
			        	
        	buffer.append(" CONCAT(");
	        buffer.append(StrUtil.join(",",list));
        	buffer.append(")");
        	buffer.append(" like CONCAT(");
        	
        	
        	buffer.append(StrUtil.join(",",list2));
        	
        	buffer.append(")");
        	if(!" CONCAT() like CONCAT()".equals(buffer.toString())) {
        		addCriterion(buffer.toString());
        	}
        	return (Criteria) this;
        }
        
        public Criteria andLikeQuery2(String searchText) {
		 	List<String> list= new ArrayList<String>();
		 
        	StringBuffer buffer=new StringBuffer();
        	
        	        	
    		list.add("ifnull(tid,'')");
			
			        	
    		list.add("ifnull(uid,'')");
			
			        	
    		list.add("ifnull(code,'')");
			
			        	
    		list.add("ifnull(cname,'')");
			
			        	
    		list.add("ifnull(amount,'')");
			
			        	
    		list.add("ifnull(price,'')");
			
			        	
    		list.add("ifnull(top_price,'')");
			
			        	
    		list.add("ifnull(cur_date,'')");
			
						
        	buffer.append(" CONCAT(");
	        buffer.append(StrUtil.join(",",list));
        	buffer.append(")");
        	buffer.append("like '%");
        	buffer.append(searchText);
        	buffer.append("%'");
        	
        	addCriterion(buffer.toString());
        	
        	return (Criteria) this;
        }
        
}
	
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}