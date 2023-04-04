package com.yunbot.windog.model.auto;

import java.util.ArrayList;
import java.util.List;
import cn.hutool.core.util.StrUtil;
/**
 *  StockLibExample
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-13 23:03:08
 */
public class StockLibExample {

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StockLibExample() {
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
				
        public Criteria andSidIsNull() {
            addCriterion("sid is null");
            return (Criteria) this;
        }

        public Criteria andSidIsNotNull() {
            addCriterion("sid is not null");
            return (Criteria) this;
        }

        public Criteria andSidEqualTo(Integer value) {
            addCriterion("sid =", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotEqualTo(Integer value) {
            addCriterion("sid <>", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThan(Integer value) {
            addCriterion("sid >", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThanOrEqualTo(Integer value) {
            addCriterion("sid >=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThan(Integer value) {
            addCriterion("sid <", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThanOrEqualTo(Integer value) {
            addCriterion("sid <=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLike(Integer value) {
            addCriterion("sid like", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotLike(Integer value) {
            addCriterion("sid not like", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidIn(List<Integer> values) {
            addCriterion("sid in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotIn(List<Integer> values) {
            addCriterion("sid not in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidBetween(Integer value1, Integer value2) {
            addCriterion("sid between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotBetween(Integer value1, Integer value2) {
            addCriterion("sid not between", value1, value2, "sid");
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
        
			
        public Criteria andInsdtIsNull() {
            addCriterion("insdt is null");
            return (Criteria) this;
        }

        public Criteria andInsdtIsNotNull() {
            addCriterion("insdt is not null");
            return (Criteria) this;
        }

        public Criteria andInsdtEqualTo(String value) {
            addCriterion("insdt =", value, "insdt");
            return (Criteria) this;
        }

        public Criteria andInsdtNotEqualTo(String value) {
            addCriterion("insdt <>", value, "insdt");
            return (Criteria) this;
        }

        public Criteria andInsdtGreaterThan(String value) {
            addCriterion("insdt >", value, "insdt");
            return (Criteria) this;
        }

        public Criteria andInsdtGreaterThanOrEqualTo(String value) {
            addCriterion("insdt >=", value, "insdt");
            return (Criteria) this;
        }

        public Criteria andInsdtLessThan(String value) {
            addCriterion("insdt <", value, "insdt");
            return (Criteria) this;
        }

        public Criteria andInsdtLessThanOrEqualTo(String value) {
            addCriterion("insdt <=", value, "insdt");
            return (Criteria) this;
        }

        public Criteria andInsdtLike(String value) {
            addCriterion("insdt like", value, "insdt");
            return (Criteria) this;
        }

        public Criteria andInsdtNotLike(String value) {
            addCriterion("insdt not like", value, "insdt");
            return (Criteria) this;
        }

        public Criteria andInsdtIn(List<String> values) {
            addCriterion("insdt in", values, "insdt");
            return (Criteria) this;
        }

        public Criteria andInsdtNotIn(List<String> values) {
            addCriterion("insdt not in", values, "insdt");
            return (Criteria) this;
        }

        public Criteria andInsdtBetween(String value1, String value2) {
            addCriterion("insdt between", value1, value2, "insdt");
            return (Criteria) this;
        }

        public Criteria andInsdtNotBetween(String value1, String value2) {
            addCriterion("insdt not between", value1, value2, "insdt");
            return (Criteria) this;
        }
        
			
        public Criteria andMetricIsNull() {
            addCriterion("metric is null");
            return (Criteria) this;
        }

        public Criteria andMetricIsNotNull() {
            addCriterion("metric is not null");
            return (Criteria) this;
        }

        public Criteria andMetricEqualTo(String value) {
            addCriterion("metric =", value, "metric");
            return (Criteria) this;
        }

        public Criteria andMetricNotEqualTo(String value) {
            addCriterion("metric <>", value, "metric");
            return (Criteria) this;
        }

        public Criteria andMetricGreaterThan(String value) {
            addCriterion("metric >", value, "metric");
            return (Criteria) this;
        }

        public Criteria andMetricGreaterThanOrEqualTo(String value) {
            addCriterion("metric >=", value, "metric");
            return (Criteria) this;
        }

        public Criteria andMetricLessThan(String value) {
            addCriterion("metric <", value, "metric");
            return (Criteria) this;
        }

        public Criteria andMetricLessThanOrEqualTo(String value) {
            addCriterion("metric <=", value, "metric");
            return (Criteria) this;
        }

        public Criteria andMetricLike(String value) {
            addCriterion("metric like", value, "metric");
            return (Criteria) this;
        }

        public Criteria andMetricNotLike(String value) {
            addCriterion("metric not like", value, "metric");
            return (Criteria) this;
        }

        public Criteria andMetricIn(List<String> values) {
            addCriterion("metric in", values, "metric");
            return (Criteria) this;
        }

        public Criteria andMetricNotIn(List<String> values) {
            addCriterion("metric not in", values, "metric");
            return (Criteria) this;
        }

        public Criteria andMetricBetween(String value1, String value2) {
            addCriterion("metric between", value1, value2, "metric");
            return (Criteria) this;
        }

        public Criteria andMetricNotBetween(String value1, String value2) {
            addCriterion("metric not between", value1, value2, "metric");
            return (Criteria) this;
        }
        
			
        public Criteria andValIsNull() {
            addCriterion("val is null");
            return (Criteria) this;
        }

        public Criteria andValIsNotNull() {
            addCriterion("val is not null");
            return (Criteria) this;
        }

        public Criteria andValEqualTo(Integer value) {
            addCriterion("val =", value, "val");
            return (Criteria) this;
        }

        public Criteria andValNotEqualTo(Integer value) {
            addCriterion("val <>", value, "val");
            return (Criteria) this;
        }

        public Criteria andValGreaterThan(Integer value) {
            addCriterion("val >", value, "val");
            return (Criteria) this;
        }

        public Criteria andValGreaterThanOrEqualTo(Integer value) {
            addCriterion("val >=", value, "val");
            return (Criteria) this;
        }

        public Criteria andValLessThan(Integer value) {
            addCriterion("val <", value, "val");
            return (Criteria) this;
        }

        public Criteria andValLessThanOrEqualTo(Integer value) {
            addCriterion("val <=", value, "val");
            return (Criteria) this;
        }

        public Criteria andValLike(Integer value) {
            addCriterion("val like", value, "val");
            return (Criteria) this;
        }

        public Criteria andValNotLike(Integer value) {
            addCriterion("val not like", value, "val");
            return (Criteria) this;
        }

        public Criteria andValIn(List<Integer> values) {
            addCriterion("val in", values, "val");
            return (Criteria) this;
        }

        public Criteria andValNotIn(List<Integer> values) {
            addCriterion("val not in", values, "val");
            return (Criteria) this;
        }

        public Criteria andValBetween(Integer value1, Integer value2) {
            addCriterion("val between", value1, value2, "val");
            return (Criteria) this;
        }

        public Criteria andValNotBetween(Integer value1, Integer value2) {
            addCriterion("val not between", value1, value2, "val");
            return (Criteria) this;
        }
        
		
		 public Criteria andLikeQuery(StockLib record) {
		 	List<String> list= new ArrayList<String>();
		 	List<String> list2= new ArrayList<String>();
        	StringBuffer buffer=new StringBuffer();
        	
        				 
			 if(record.getSid()!=null&&StrUtil.isNotEmpty(record.getSid().toString())) {
    			 list.add("ifnull(sid,'')");
    		 }
    		 
						 
			 if(record.getCode()!=null&&StrUtil.isNotEmpty(record.getCode().toString())) {
    			 list.add("ifnull(code,'')");
    		 }
    		 
						 
			 if(record.getInsdt()!=null&&StrUtil.isNotEmpty(record.getInsdt().toString())) {
    			 list.add("ifnull(insdt,'')");
    		 }
    		 
						 
			 if(record.getMetric()!=null&&StrUtil.isNotEmpty(record.getMetric().toString())) {
    			 list.add("ifnull(metric,'')");
    		 }
    		 
						 
			 if(record.getVal()!=null&&StrUtil.isNotEmpty(record.getVal().toString())) {
    			 list.add("ifnull(val,'')");
    		 }
    		 
									
			 if(record.getSid()!=null&&StrUtil.isNotEmpty(record.getSid().toString())) {
    			list2.add("'%"+record.getSid()+"%'");
    		 }
        		 
						
			 if(record.getCode()!=null&&StrUtil.isNotEmpty(record.getCode().toString())) {
    			list2.add("'%"+record.getCode()+"%'");
    		 }
        		 
						
			 if(record.getInsdt()!=null&&StrUtil.isNotEmpty(record.getInsdt().toString())) {
    			list2.add("'%"+record.getInsdt()+"%'");
    		 }
        		 
						
			 if(record.getMetric()!=null&&StrUtil.isNotEmpty(record.getMetric().toString())) {
    			list2.add("'%"+record.getMetric()+"%'");
    		 }
        		 
						
			 if(record.getVal()!=null&&StrUtil.isNotEmpty(record.getVal().toString())) {
    			list2.add("'%"+record.getVal()+"%'");
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
        	
        	        	
    		list.add("ifnull(sid,'')");
			
			        	
    		list.add("ifnull(code,'')");
			
			        	
    		list.add("ifnull(insdt,'')");
			
			        	
    		list.add("ifnull(metric,'')");
			
			        	
    		list.add("ifnull(val,'')");
			
						
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