package com.yunbot.windog.model.auto;

import java.util.ArrayList;
import java.util.List;
import cn.hutool.core.util.StrUtil;
/**
 *  StockCalExample
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-13 23:01:36
 */
public class StockCalExample {

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StockCalExample() {
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
				
        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(Integer value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(Integer value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }
        
			
        public Criteria andOpendtIsNull() {
            addCriterion("opendt is null");
            return (Criteria) this;
        }

        public Criteria andOpendtIsNotNull() {
            addCriterion("opendt is not null");
            return (Criteria) this;
        }

        public Criteria andOpendtEqualTo(String value) {
            addCriterion("opendt =", value, "opendt");
            return (Criteria) this;
        }

        public Criteria andOpendtNotEqualTo(String value) {
            addCriterion("opendt <>", value, "opendt");
            return (Criteria) this;
        }

        public Criteria andOpendtGreaterThan(String value) {
            addCriterion("opendt >", value, "opendt");
            return (Criteria) this;
        }

        public Criteria andOpendtGreaterThanOrEqualTo(String value) {
            addCriterion("opendt >=", value, "opendt");
            return (Criteria) this;
        }

        public Criteria andOpendtLessThan(String value) {
            addCriterion("opendt <", value, "opendt");
            return (Criteria) this;
        }

        public Criteria andOpendtLessThanOrEqualTo(String value) {
            addCriterion("opendt <=", value, "opendt");
            return (Criteria) this;
        }

        public Criteria andOpendtLike(String value) {
            addCriterion("opendt like", value, "opendt");
            return (Criteria) this;
        }

        public Criteria andOpendtNotLike(String value) {
            addCriterion("opendt not like", value, "opendt");
            return (Criteria) this;
        }

        public Criteria andOpendtIn(List<String> values) {
            addCriterion("opendt in", values, "opendt");
            return (Criteria) this;
        }

        public Criteria andOpendtNotIn(List<String> values) {
            addCriterion("opendt not in", values, "opendt");
            return (Criteria) this;
        }

        public Criteria andOpendtBetween(String value1, String value2) {
            addCriterion("opendt between", value1, value2, "opendt");
            return (Criteria) this;
        }

        public Criteria andOpendtNotBetween(String value1, String value2) {
            addCriterion("opendt not between", value1, value2, "opendt");
            return (Criteria) this;
        }
        
		
		 public Criteria andLikeQuery(StockCal record) {
		 	List<String> list= new ArrayList<String>();
		 	List<String> list2= new ArrayList<String>();
        	StringBuffer buffer=new StringBuffer();
        	
        				 
			 if(record.getId()!=null&&StrUtil.isNotEmpty(record.getId().toString())) {
    			 list.add("ifnull(id,'')");
    		 }
    		 
						 
			 if(record.getOpendt()!=null&&StrUtil.isNotEmpty(record.getOpendt().toString())) {
    			 list.add("ifnull(opendt,'')");
    		 }
    		 
									
			 if(record.getId()!=null&&StrUtil.isNotEmpty(record.getId().toString())) {
    			list2.add("'%"+record.getId()+"%'");
    		 }
        		 
						
			 if(record.getOpendt()!=null&&StrUtil.isNotEmpty(record.getOpendt().toString())) {
    			list2.add("'%"+record.getOpendt()+"%'");
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
        	
        	        	
    		list.add("ifnull(id,'')");
			
			        	
    		list.add("ifnull(opendt,'')");
			
						
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