package com.yunbot.windog.model.auto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import cn.hutool.core.util.StrUtil;
/**
 *  StockPoolExample
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-13 23:01:45
 */
public class StockPoolExample {

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StockPoolExample() {
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
        
			
        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(Integer value) {
            addCriterion("grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(Integer value) {
            addCriterion("grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(Integer value) {
            addCriterion("grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(Integer value) {
            addCriterion("grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(Integer value) {
            addCriterion("grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLike(Integer value) {
            addCriterion("grade like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotLike(Integer value) {
            addCriterion("grade not like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<Integer> values) {
            addCriterion("grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<Integer> values) {
            addCriterion("grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(Integer value1, Integer value2) {
            addCriterion("grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("grade not between", value1, value2, "grade");
            return (Criteria) this;
        }
        
			
        public Criteria andActIsNull() {
            addCriterion("act is null");
            return (Criteria) this;
        }

        public Criteria andActIsNotNull() {
            addCriterion("act is not null");
            return (Criteria) this;
        }

        public Criteria andActEqualTo(Integer value) {
            addCriterion("act =", value, "act");
            return (Criteria) this;
        }

        public Criteria andActNotEqualTo(Integer value) {
            addCriterion("act <>", value, "act");
            return (Criteria) this;
        }

        public Criteria andActGreaterThan(Integer value) {
            addCriterion("act >", value, "act");
            return (Criteria) this;
        }

        public Criteria andActGreaterThanOrEqualTo(Integer value) {
            addCriterion("act >=", value, "act");
            return (Criteria) this;
        }

        public Criteria andActLessThan(Integer value) {
            addCriterion("act <", value, "act");
            return (Criteria) this;
        }

        public Criteria andActLessThanOrEqualTo(Integer value) {
            addCriterion("act <=", value, "act");
            return (Criteria) this;
        }

        public Criteria andActLike(Integer value) {
            addCriterion("act like", value, "act");
            return (Criteria) this;
        }

        public Criteria andActNotLike(Integer value) {
            addCriterion("act not like", value, "act");
            return (Criteria) this;
        }

        public Criteria andActIn(List<Integer> values) {
            addCriterion("act in", values, "act");
            return (Criteria) this;
        }

        public Criteria andActNotIn(List<Integer> values) {
            addCriterion("act not in", values, "act");
            return (Criteria) this;
        }

        public Criteria andActBetween(Integer value1, Integer value2) {
            addCriterion("act between", value1, value2, "act");
            return (Criteria) this;
        }

        public Criteria andActNotBetween(Integer value1, Integer value2) {
            addCriterion("act not between", value1, value2, "act");
            return (Criteria) this;
        }
        
			
        public Criteria andUpdateDtIsNull() {
            addCriterion("update_dt is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDtIsNotNull() {
            addCriterion("update_dt is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDtEqualTo(Date value) {
            addCriterion("update_dt =", value, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtNotEqualTo(Date value) {
            addCriterion("update_dt <>", value, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtGreaterThan(Date value) {
            addCriterion("update_dt >", value, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtGreaterThanOrEqualTo(Date value) {
            addCriterion("update_dt >=", value, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtLessThan(Date value) {
            addCriterion("update_dt <", value, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtLessThanOrEqualTo(Date value) {
            addCriterion("update_dt <=", value, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtLike(Date value) {
            addCriterion("update_dt like", value, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtNotLike(Date value) {
            addCriterion("update_dt not like", value, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtIn(List<Date> values) {
            addCriterion("update_dt in", values, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtNotIn(List<Date> values) {
            addCriterion("update_dt not in", values, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtBetween(Date value1, Date value2) {
            addCriterion("update_dt between", value1, value2, "updateDt");
            return (Criteria) this;
        }

        public Criteria andUpdateDtNotBetween(Date value1, Date value2) {
            addCriterion("update_dt not between", value1, value2, "updateDt");
            return (Criteria) this;
        }
        
			
        public Criteria andCurPriceIsNull() {
            addCriterion("cur_price is null");
            return (Criteria) this;
        }

        public Criteria andCurPriceIsNotNull() {
            addCriterion("cur_price is not null");
            return (Criteria) this;
        }

        public Criteria andCurPriceEqualTo(BigDecimal value) {
            addCriterion("cur_price =", value, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceNotEqualTo(BigDecimal value) {
            addCriterion("cur_price <>", value, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceGreaterThan(BigDecimal value) {
            addCriterion("cur_price >", value, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cur_price >=", value, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceLessThan(BigDecimal value) {
            addCriterion("cur_price <", value, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cur_price <=", value, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceLike(BigDecimal value) {
            addCriterion("cur_price like", value, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceNotLike(BigDecimal value) {
            addCriterion("cur_price not like", value, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceIn(List<BigDecimal> values) {
            addCriterion("cur_price in", values, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceNotIn(List<BigDecimal> values) {
            addCriterion("cur_price not in", values, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cur_price between", value1, value2, "curPrice");
            return (Criteria) this;
        }

        public Criteria andCurPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cur_price not between", value1, value2, "curPrice");
            return (Criteria) this;
        }
        
			
        public Criteria andVbegIsNull() {
            addCriterion("vbeg is null");
            return (Criteria) this;
        }

        public Criteria andVbegIsNotNull() {
            addCriterion("vbeg is not null");
            return (Criteria) this;
        }

        public Criteria andVbegEqualTo(BigDecimal value) {
            addCriterion("vbeg =", value, "vbeg");
            return (Criteria) this;
        }

        public Criteria andVbegNotEqualTo(BigDecimal value) {
            addCriterion("vbeg <>", value, "vbeg");
            return (Criteria) this;
        }

        public Criteria andVbegGreaterThan(BigDecimal value) {
            addCriterion("vbeg >", value, "vbeg");
            return (Criteria) this;
        }

        public Criteria andVbegGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("vbeg >=", value, "vbeg");
            return (Criteria) this;
        }

        public Criteria andVbegLessThan(BigDecimal value) {
            addCriterion("vbeg <", value, "vbeg");
            return (Criteria) this;
        }

        public Criteria andVbegLessThanOrEqualTo(BigDecimal value) {
            addCriterion("vbeg <=", value, "vbeg");
            return (Criteria) this;
        }

        public Criteria andVbegLike(BigDecimal value) {
            addCriterion("vbeg like", value, "vbeg");
            return (Criteria) this;
        }

        public Criteria andVbegNotLike(BigDecimal value) {
            addCriterion("vbeg not like", value, "vbeg");
            return (Criteria) this;
        }

        public Criteria andVbegIn(List<BigDecimal> values) {
            addCriterion("vbeg in", values, "vbeg");
            return (Criteria) this;
        }

        public Criteria andVbegNotIn(List<BigDecimal> values) {
            addCriterion("vbeg not in", values, "vbeg");
            return (Criteria) this;
        }

        public Criteria andVbegBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vbeg between", value1, value2, "vbeg");
            return (Criteria) this;
        }

        public Criteria andVbegNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vbeg not between", value1, value2, "vbeg");
            return (Criteria) this;
        }
        
			
        public Criteria andVendIsNull() {
            addCriterion("vend is null");
            return (Criteria) this;
        }

        public Criteria andVendIsNotNull() {
            addCriterion("vend is not null");
            return (Criteria) this;
        }

        public Criteria andVendEqualTo(BigDecimal value) {
            addCriterion("vend =", value, "vend");
            return (Criteria) this;
        }

        public Criteria andVendNotEqualTo(BigDecimal value) {
            addCriterion("vend <>", value, "vend");
            return (Criteria) this;
        }

        public Criteria andVendGreaterThan(BigDecimal value) {
            addCriterion("vend >", value, "vend");
            return (Criteria) this;
        }

        public Criteria andVendGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("vend >=", value, "vend");
            return (Criteria) this;
        }

        public Criteria andVendLessThan(BigDecimal value) {
            addCriterion("vend <", value, "vend");
            return (Criteria) this;
        }

        public Criteria andVendLessThanOrEqualTo(BigDecimal value) {
            addCriterion("vend <=", value, "vend");
            return (Criteria) this;
        }

        public Criteria andVendLike(BigDecimal value) {
            addCriterion("vend like", value, "vend");
            return (Criteria) this;
        }

        public Criteria andVendNotLike(BigDecimal value) {
            addCriterion("vend not like", value, "vend");
            return (Criteria) this;
        }

        public Criteria andVendIn(List<BigDecimal> values) {
            addCriterion("vend in", values, "vend");
            return (Criteria) this;
        }

        public Criteria andVendNotIn(List<BigDecimal> values) {
            addCriterion("vend not in", values, "vend");
            return (Criteria) this;
        }

        public Criteria andVendBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vend between", value1, value2, "vend");
            return (Criteria) this;
        }

        public Criteria andVendNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vend not between", value1, value2, "vend");
            return (Criteria) this;
        }
        
			
        public Criteria andLastendIsNull() {
            addCriterion("lastend is null");
            return (Criteria) this;
        }

        public Criteria andLastendIsNotNull() {
            addCriterion("lastend is not null");
            return (Criteria) this;
        }

        public Criteria andLastendEqualTo(BigDecimal value) {
            addCriterion("lastend =", value, "lastend");
            return (Criteria) this;
        }

        public Criteria andLastendNotEqualTo(BigDecimal value) {
            addCriterion("lastend <>", value, "lastend");
            return (Criteria) this;
        }

        public Criteria andLastendGreaterThan(BigDecimal value) {
            addCriterion("lastend >", value, "lastend");
            return (Criteria) this;
        }

        public Criteria andLastendGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("lastend >=", value, "lastend");
            return (Criteria) this;
        }

        public Criteria andLastendLessThan(BigDecimal value) {
            addCriterion("lastend <", value, "lastend");
            return (Criteria) this;
        }

        public Criteria andLastendLessThanOrEqualTo(BigDecimal value) {
            addCriterion("lastend <=", value, "lastend");
            return (Criteria) this;
        }

        public Criteria andLastendLike(BigDecimal value) {
            addCriterion("lastend like", value, "lastend");
            return (Criteria) this;
        }

        public Criteria andLastendNotLike(BigDecimal value) {
            addCriterion("lastend not like", value, "lastend");
            return (Criteria) this;
        }

        public Criteria andLastendIn(List<BigDecimal> values) {
            addCriterion("lastend in", values, "lastend");
            return (Criteria) this;
        }

        public Criteria andLastendNotIn(List<BigDecimal> values) {
            addCriterion("lastend not in", values, "lastend");
            return (Criteria) this;
        }

        public Criteria andLastendBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lastend between", value1, value2, "lastend");
            return (Criteria) this;
        }

        public Criteria andLastendNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lastend not between", value1, value2, "lastend");
            return (Criteria) this;
        }
        
			
        public Criteria andVhighIsNull() {
            addCriterion("vhigh is null");
            return (Criteria) this;
        }

        public Criteria andVhighIsNotNull() {
            addCriterion("vhigh is not null");
            return (Criteria) this;
        }

        public Criteria andVhighEqualTo(BigDecimal value) {
            addCriterion("vhigh =", value, "vhigh");
            return (Criteria) this;
        }

        public Criteria andVhighNotEqualTo(BigDecimal value) {
            addCriterion("vhigh <>", value, "vhigh");
            return (Criteria) this;
        }

        public Criteria andVhighGreaterThan(BigDecimal value) {
            addCriterion("vhigh >", value, "vhigh");
            return (Criteria) this;
        }

        public Criteria andVhighGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("vhigh >=", value, "vhigh");
            return (Criteria) this;
        }

        public Criteria andVhighLessThan(BigDecimal value) {
            addCriterion("vhigh <", value, "vhigh");
            return (Criteria) this;
        }

        public Criteria andVhighLessThanOrEqualTo(BigDecimal value) {
            addCriterion("vhigh <=", value, "vhigh");
            return (Criteria) this;
        }

        public Criteria andVhighLike(BigDecimal value) {
            addCriterion("vhigh like", value, "vhigh");
            return (Criteria) this;
        }

        public Criteria andVhighNotLike(BigDecimal value) {
            addCriterion("vhigh not like", value, "vhigh");
            return (Criteria) this;
        }

        public Criteria andVhighIn(List<BigDecimal> values) {
            addCriterion("vhigh in", values, "vhigh");
            return (Criteria) this;
        }

        public Criteria andVhighNotIn(List<BigDecimal> values) {
            addCriterion("vhigh not in", values, "vhigh");
            return (Criteria) this;
        }

        public Criteria andVhighBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vhigh between", value1, value2, "vhigh");
            return (Criteria) this;
        }

        public Criteria andVhighNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vhigh not between", value1, value2, "vhigh");
            return (Criteria) this;
        }
        
			
        public Criteria andVlowIsNull() {
            addCriterion("vlow is null");
            return (Criteria) this;
        }

        public Criteria andVlowIsNotNull() {
            addCriterion("vlow is not null");
            return (Criteria) this;
        }

        public Criteria andVlowEqualTo(BigDecimal value) {
            addCriterion("vlow =", value, "vlow");
            return (Criteria) this;
        }

        public Criteria andVlowNotEqualTo(BigDecimal value) {
            addCriterion("vlow <>", value, "vlow");
            return (Criteria) this;
        }

        public Criteria andVlowGreaterThan(BigDecimal value) {
            addCriterion("vlow >", value, "vlow");
            return (Criteria) this;
        }

        public Criteria andVlowGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("vlow >=", value, "vlow");
            return (Criteria) this;
        }

        public Criteria andVlowLessThan(BigDecimal value) {
            addCriterion("vlow <", value, "vlow");
            return (Criteria) this;
        }

        public Criteria andVlowLessThanOrEqualTo(BigDecimal value) {
            addCriterion("vlow <=", value, "vlow");
            return (Criteria) this;
        }

        public Criteria andVlowLike(BigDecimal value) {
            addCriterion("vlow like", value, "vlow");
            return (Criteria) this;
        }

        public Criteria andVlowNotLike(BigDecimal value) {
            addCriterion("vlow not like", value, "vlow");
            return (Criteria) this;
        }

        public Criteria andVlowIn(List<BigDecimal> values) {
            addCriterion("vlow in", values, "vlow");
            return (Criteria) this;
        }

        public Criteria andVlowNotIn(List<BigDecimal> values) {
            addCriterion("vlow not in", values, "vlow");
            return (Criteria) this;
        }

        public Criteria andVlowBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vlow between", value1, value2, "vlow");
            return (Criteria) this;
        }

        public Criteria andVlowNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vlow not between", value1, value2, "vlow");
            return (Criteria) this;
        }
        
			
        public Criteria andVtotalIsNull() {
            addCriterion("vtotal is null");
            return (Criteria) this;
        }

        public Criteria andVtotalIsNotNull() {
            addCriterion("vtotal is not null");
            return (Criteria) this;
        }

        public Criteria andVtotalEqualTo(BigDecimal value) {
            addCriterion("vtotal =", value, "vtotal");
            return (Criteria) this;
        }

        public Criteria andVtotalNotEqualTo(BigDecimal value) {
            addCriterion("vtotal <>", value, "vtotal");
            return (Criteria) this;
        }

        public Criteria andVtotalGreaterThan(BigDecimal value) {
            addCriterion("vtotal >", value, "vtotal");
            return (Criteria) this;
        }

        public Criteria andVtotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("vtotal >=", value, "vtotal");
            return (Criteria) this;
        }

        public Criteria andVtotalLessThan(BigDecimal value) {
            addCriterion("vtotal <", value, "vtotal");
            return (Criteria) this;
        }

        public Criteria andVtotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("vtotal <=", value, "vtotal");
            return (Criteria) this;
        }

        public Criteria andVtotalLike(BigDecimal value) {
            addCriterion("vtotal like", value, "vtotal");
            return (Criteria) this;
        }

        public Criteria andVtotalNotLike(BigDecimal value) {
            addCriterion("vtotal not like", value, "vtotal");
            return (Criteria) this;
        }

        public Criteria andVtotalIn(List<BigDecimal> values) {
            addCriterion("vtotal in", values, "vtotal");
            return (Criteria) this;
        }

        public Criteria andVtotalNotIn(List<BigDecimal> values) {
            addCriterion("vtotal not in", values, "vtotal");
            return (Criteria) this;
        }

        public Criteria andVtotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vtotal between", value1, value2, "vtotal");
            return (Criteria) this;
        }

        public Criteria andVtotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vtotal not between", value1, value2, "vtotal");
            return (Criteria) this;
        }
        
			
        public Criteria andVamountIsNull() {
            addCriterion("vamount is null");
            return (Criteria) this;
        }

        public Criteria andVamountIsNotNull() {
            addCriterion("vamount is not null");
            return (Criteria) this;
        }

        public Criteria andVamountEqualTo(BigDecimal value) {
            addCriterion("vamount =", value, "vamount");
            return (Criteria) this;
        }

        public Criteria andVamountNotEqualTo(BigDecimal value) {
            addCriterion("vamount <>", value, "vamount");
            return (Criteria) this;
        }

        public Criteria andVamountGreaterThan(BigDecimal value) {
            addCriterion("vamount >", value, "vamount");
            return (Criteria) this;
        }

        public Criteria andVamountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("vamount >=", value, "vamount");
            return (Criteria) this;
        }

        public Criteria andVamountLessThan(BigDecimal value) {
            addCriterion("vamount <", value, "vamount");
            return (Criteria) this;
        }

        public Criteria andVamountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("vamount <=", value, "vamount");
            return (Criteria) this;
        }

        public Criteria andVamountLike(BigDecimal value) {
            addCriterion("vamount like", value, "vamount");
            return (Criteria) this;
        }

        public Criteria andVamountNotLike(BigDecimal value) {
            addCriterion("vamount not like", value, "vamount");
            return (Criteria) this;
        }

        public Criteria andVamountIn(List<BigDecimal> values) {
            addCriterion("vamount in", values, "vamount");
            return (Criteria) this;
        }

        public Criteria andVamountNotIn(List<BigDecimal> values) {
            addCriterion("vamount not in", values, "vamount");
            return (Criteria) this;
        }

        public Criteria andVamountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vamount between", value1, value2, "vamount");
            return (Criteria) this;
        }

        public Criteria andVamountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vamount not between", value1, value2, "vamount");
            return (Criteria) this;
        }
        
			
        public Criteria andVol1IsNull() {
            addCriterion("vol1 is null");
            return (Criteria) this;
        }

        public Criteria andVol1IsNotNull() {
            addCriterion("vol1 is not null");
            return (Criteria) this;
        }

        public Criteria andVol1EqualTo(Integer value) {
            addCriterion("vol1 =", value, "vol1");
            return (Criteria) this;
        }

        public Criteria andVol1NotEqualTo(Integer value) {
            addCriterion("vol1 <>", value, "vol1");
            return (Criteria) this;
        }

        public Criteria andVol1GreaterThan(Integer value) {
            addCriterion("vol1 >", value, "vol1");
            return (Criteria) this;
        }

        public Criteria andVol1GreaterThanOrEqualTo(Integer value) {
            addCriterion("vol1 >=", value, "vol1");
            return (Criteria) this;
        }

        public Criteria andVol1LessThan(Integer value) {
            addCriterion("vol1 <", value, "vol1");
            return (Criteria) this;
        }

        public Criteria andVol1LessThanOrEqualTo(Integer value) {
            addCriterion("vol1 <=", value, "vol1");
            return (Criteria) this;
        }

        public Criteria andVol1Like(Integer value) {
            addCriterion("vol1 like", value, "vol1");
            return (Criteria) this;
        }

        public Criteria andVol1NotLike(Integer value) {
            addCriterion("vol1 not like", value, "vol1");
            return (Criteria) this;
        }

        public Criteria andVol1In(List<Integer> values) {
            addCriterion("vol1 in", values, "vol1");
            return (Criteria) this;
        }

        public Criteria andVol1NotIn(List<Integer> values) {
            addCriterion("vol1 not in", values, "vol1");
            return (Criteria) this;
        }

        public Criteria andVol1Between(Integer value1, Integer value2) {
            addCriterion("vol1 between", value1, value2, "vol1");
            return (Criteria) this;
        }

        public Criteria andVol1NotBetween(Integer value1, Integer value2) {
            addCriterion("vol1 not between", value1, value2, "vol1");
            return (Criteria) this;
        }
        
			
        public Criteria andVol2IsNull() {
            addCriterion("vol2 is null");
            return (Criteria) this;
        }

        public Criteria andVol2IsNotNull() {
            addCriterion("vol2 is not null");
            return (Criteria) this;
        }

        public Criteria andVol2EqualTo(Integer value) {
            addCriterion("vol2 =", value, "vol2");
            return (Criteria) this;
        }

        public Criteria andVol2NotEqualTo(Integer value) {
            addCriterion("vol2 <>", value, "vol2");
            return (Criteria) this;
        }

        public Criteria andVol2GreaterThan(Integer value) {
            addCriterion("vol2 >", value, "vol2");
            return (Criteria) this;
        }

        public Criteria andVol2GreaterThanOrEqualTo(Integer value) {
            addCriterion("vol2 >=", value, "vol2");
            return (Criteria) this;
        }

        public Criteria andVol2LessThan(Integer value) {
            addCriterion("vol2 <", value, "vol2");
            return (Criteria) this;
        }

        public Criteria andVol2LessThanOrEqualTo(Integer value) {
            addCriterion("vol2 <=", value, "vol2");
            return (Criteria) this;
        }

        public Criteria andVol2Like(Integer value) {
            addCriterion("vol2 like", value, "vol2");
            return (Criteria) this;
        }

        public Criteria andVol2NotLike(Integer value) {
            addCriterion("vol2 not like", value, "vol2");
            return (Criteria) this;
        }

        public Criteria andVol2In(List<Integer> values) {
            addCriterion("vol2 in", values, "vol2");
            return (Criteria) this;
        }

        public Criteria andVol2NotIn(List<Integer> values) {
            addCriterion("vol2 not in", values, "vol2");
            return (Criteria) this;
        }

        public Criteria andVol2Between(Integer value1, Integer value2) {
            addCriterion("vol2 between", value1, value2, "vol2");
            return (Criteria) this;
        }

        public Criteria andVol2NotBetween(Integer value1, Integer value2) {
            addCriterion("vol2 not between", value1, value2, "vol2");
            return (Criteria) this;
        }
        
			
        public Criteria andVol3IsNull() {
            addCriterion("vol3 is null");
            return (Criteria) this;
        }

        public Criteria andVol3IsNotNull() {
            addCriterion("vol3 is not null");
            return (Criteria) this;
        }

        public Criteria andVol3EqualTo(Integer value) {
            addCriterion("vol3 =", value, "vol3");
            return (Criteria) this;
        }

        public Criteria andVol3NotEqualTo(Integer value) {
            addCriterion("vol3 <>", value, "vol3");
            return (Criteria) this;
        }

        public Criteria andVol3GreaterThan(Integer value) {
            addCriterion("vol3 >", value, "vol3");
            return (Criteria) this;
        }

        public Criteria andVol3GreaterThanOrEqualTo(Integer value) {
            addCriterion("vol3 >=", value, "vol3");
            return (Criteria) this;
        }

        public Criteria andVol3LessThan(Integer value) {
            addCriterion("vol3 <", value, "vol3");
            return (Criteria) this;
        }

        public Criteria andVol3LessThanOrEqualTo(Integer value) {
            addCriterion("vol3 <=", value, "vol3");
            return (Criteria) this;
        }

        public Criteria andVol3Like(Integer value) {
            addCriterion("vol3 like", value, "vol3");
            return (Criteria) this;
        }

        public Criteria andVol3NotLike(Integer value) {
            addCriterion("vol3 not like", value, "vol3");
            return (Criteria) this;
        }

        public Criteria andVol3In(List<Integer> values) {
            addCriterion("vol3 in", values, "vol3");
            return (Criteria) this;
        }

        public Criteria andVol3NotIn(List<Integer> values) {
            addCriterion("vol3 not in", values, "vol3");
            return (Criteria) this;
        }

        public Criteria andVol3Between(Integer value1, Integer value2) {
            addCriterion("vol3 between", value1, value2, "vol3");
            return (Criteria) this;
        }

        public Criteria andVol3NotBetween(Integer value1, Integer value2) {
            addCriterion("vol3 not between", value1, value2, "vol3");
            return (Criteria) this;
        }
        
			
        public Criteria andVol4IsNull() {
            addCriterion("vol4 is null");
            return (Criteria) this;
        }

        public Criteria andVol4IsNotNull() {
            addCriterion("vol4 is not null");
            return (Criteria) this;
        }

        public Criteria andVol4EqualTo(Integer value) {
            addCriterion("vol4 =", value, "vol4");
            return (Criteria) this;
        }

        public Criteria andVol4NotEqualTo(Integer value) {
            addCriterion("vol4 <>", value, "vol4");
            return (Criteria) this;
        }

        public Criteria andVol4GreaterThan(Integer value) {
            addCriterion("vol4 >", value, "vol4");
            return (Criteria) this;
        }

        public Criteria andVol4GreaterThanOrEqualTo(Integer value) {
            addCriterion("vol4 >=", value, "vol4");
            return (Criteria) this;
        }

        public Criteria andVol4LessThan(Integer value) {
            addCriterion("vol4 <", value, "vol4");
            return (Criteria) this;
        }

        public Criteria andVol4LessThanOrEqualTo(Integer value) {
            addCriterion("vol4 <=", value, "vol4");
            return (Criteria) this;
        }

        public Criteria andVol4Like(Integer value) {
            addCriterion("vol4 like", value, "vol4");
            return (Criteria) this;
        }

        public Criteria andVol4NotLike(Integer value) {
            addCriterion("vol4 not like", value, "vol4");
            return (Criteria) this;
        }

        public Criteria andVol4In(List<Integer> values) {
            addCriterion("vol4 in", values, "vol4");
            return (Criteria) this;
        }

        public Criteria andVol4NotIn(List<Integer> values) {
            addCriterion("vol4 not in", values, "vol4");
            return (Criteria) this;
        }

        public Criteria andVol4Between(Integer value1, Integer value2) {
            addCriterion("vol4 between", value1, value2, "vol4");
            return (Criteria) this;
        }

        public Criteria andVol4NotBetween(Integer value1, Integer value2) {
            addCriterion("vol4 not between", value1, value2, "vol4");
            return (Criteria) this;
        }
        
			
        public Criteria andVol5IsNull() {
            addCriterion("vol5 is null");
            return (Criteria) this;
        }

        public Criteria andVol5IsNotNull() {
            addCriterion("vol5 is not null");
            return (Criteria) this;
        }

        public Criteria andVol5EqualTo(Integer value) {
            addCriterion("vol5 =", value, "vol5");
            return (Criteria) this;
        }

        public Criteria andVol5NotEqualTo(Integer value) {
            addCriterion("vol5 <>", value, "vol5");
            return (Criteria) this;
        }

        public Criteria andVol5GreaterThan(Integer value) {
            addCriterion("vol5 >", value, "vol5");
            return (Criteria) this;
        }

        public Criteria andVol5GreaterThanOrEqualTo(Integer value) {
            addCriterion("vol5 >=", value, "vol5");
            return (Criteria) this;
        }

        public Criteria andVol5LessThan(Integer value) {
            addCriterion("vol5 <", value, "vol5");
            return (Criteria) this;
        }

        public Criteria andVol5LessThanOrEqualTo(Integer value) {
            addCriterion("vol5 <=", value, "vol5");
            return (Criteria) this;
        }

        public Criteria andVol5Like(Integer value) {
            addCriterion("vol5 like", value, "vol5");
            return (Criteria) this;
        }

        public Criteria andVol5NotLike(Integer value) {
            addCriterion("vol5 not like", value, "vol5");
            return (Criteria) this;
        }

        public Criteria andVol5In(List<Integer> values) {
            addCriterion("vol5 in", values, "vol5");
            return (Criteria) this;
        }

        public Criteria andVol5NotIn(List<Integer> values) {
            addCriterion("vol5 not in", values, "vol5");
            return (Criteria) this;
        }

        public Criteria andVol5Between(Integer value1, Integer value2) {
            addCriterion("vol5 between", value1, value2, "vol5");
            return (Criteria) this;
        }

        public Criteria andVol5NotBetween(Integer value1, Integer value2) {
            addCriterion("vol5 not between", value1, value2, "vol5");
            return (Criteria) this;
        }
        
			
        public Criteria andVol6IsNull() {
            addCriterion("vol6 is null");
            return (Criteria) this;
        }

        public Criteria andVol6IsNotNull() {
            addCriterion("vol6 is not null");
            return (Criteria) this;
        }

        public Criteria andVol6EqualTo(Integer value) {
            addCriterion("vol6 =", value, "vol6");
            return (Criteria) this;
        }

        public Criteria andVol6NotEqualTo(Integer value) {
            addCriterion("vol6 <>", value, "vol6");
            return (Criteria) this;
        }

        public Criteria andVol6GreaterThan(Integer value) {
            addCriterion("vol6 >", value, "vol6");
            return (Criteria) this;
        }

        public Criteria andVol6GreaterThanOrEqualTo(Integer value) {
            addCriterion("vol6 >=", value, "vol6");
            return (Criteria) this;
        }

        public Criteria andVol6LessThan(Integer value) {
            addCriterion("vol6 <", value, "vol6");
            return (Criteria) this;
        }

        public Criteria andVol6LessThanOrEqualTo(Integer value) {
            addCriterion("vol6 <=", value, "vol6");
            return (Criteria) this;
        }

        public Criteria andVol6Like(Integer value) {
            addCriterion("vol6 like", value, "vol6");
            return (Criteria) this;
        }

        public Criteria andVol6NotLike(Integer value) {
            addCriterion("vol6 not like", value, "vol6");
            return (Criteria) this;
        }

        public Criteria andVol6In(List<Integer> values) {
            addCriterion("vol6 in", values, "vol6");
            return (Criteria) this;
        }

        public Criteria andVol6NotIn(List<Integer> values) {
            addCriterion("vol6 not in", values, "vol6");
            return (Criteria) this;
        }

        public Criteria andVol6Between(Integer value1, Integer value2) {
            addCriterion("vol6 between", value1, value2, "vol6");
            return (Criteria) this;
        }

        public Criteria andVol6NotBetween(Integer value1, Integer value2) {
            addCriterion("vol6 not between", value1, value2, "vol6");
            return (Criteria) this;
        }
        
			
        public Criteria andVol7IsNull() {
            addCriterion("vol7 is null");
            return (Criteria) this;
        }

        public Criteria andVol7IsNotNull() {
            addCriterion("vol7 is not null");
            return (Criteria) this;
        }

        public Criteria andVol7EqualTo(Integer value) {
            addCriterion("vol7 =", value, "vol7");
            return (Criteria) this;
        }

        public Criteria andVol7NotEqualTo(Integer value) {
            addCriterion("vol7 <>", value, "vol7");
            return (Criteria) this;
        }

        public Criteria andVol7GreaterThan(Integer value) {
            addCriterion("vol7 >", value, "vol7");
            return (Criteria) this;
        }

        public Criteria andVol7GreaterThanOrEqualTo(Integer value) {
            addCriterion("vol7 >=", value, "vol7");
            return (Criteria) this;
        }

        public Criteria andVol7LessThan(Integer value) {
            addCriterion("vol7 <", value, "vol7");
            return (Criteria) this;
        }

        public Criteria andVol7LessThanOrEqualTo(Integer value) {
            addCriterion("vol7 <=", value, "vol7");
            return (Criteria) this;
        }

        public Criteria andVol7Like(Integer value) {
            addCriterion("vol7 like", value, "vol7");
            return (Criteria) this;
        }

        public Criteria andVol7NotLike(Integer value) {
            addCriterion("vol7 not like", value, "vol7");
            return (Criteria) this;
        }

        public Criteria andVol7In(List<Integer> values) {
            addCriterion("vol7 in", values, "vol7");
            return (Criteria) this;
        }

        public Criteria andVol7NotIn(List<Integer> values) {
            addCriterion("vol7 not in", values, "vol7");
            return (Criteria) this;
        }

        public Criteria andVol7Between(Integer value1, Integer value2) {
            addCriterion("vol7 between", value1, value2, "vol7");
            return (Criteria) this;
        }

        public Criteria andVol7NotBetween(Integer value1, Integer value2) {
            addCriterion("vol7 not between", value1, value2, "vol7");
            return (Criteria) this;
        }
        
			
        public Criteria andVol8IsNull() {
            addCriterion("vol8 is null");
            return (Criteria) this;
        }

        public Criteria andVol8IsNotNull() {
            addCriterion("vol8 is not null");
            return (Criteria) this;
        }

        public Criteria andVol8EqualTo(Integer value) {
            addCriterion("vol8 =", value, "vol8");
            return (Criteria) this;
        }

        public Criteria andVol8NotEqualTo(Integer value) {
            addCriterion("vol8 <>", value, "vol8");
            return (Criteria) this;
        }

        public Criteria andVol8GreaterThan(Integer value) {
            addCriterion("vol8 >", value, "vol8");
            return (Criteria) this;
        }

        public Criteria andVol8GreaterThanOrEqualTo(Integer value) {
            addCriterion("vol8 >=", value, "vol8");
            return (Criteria) this;
        }

        public Criteria andVol8LessThan(Integer value) {
            addCriterion("vol8 <", value, "vol8");
            return (Criteria) this;
        }

        public Criteria andVol8LessThanOrEqualTo(Integer value) {
            addCriterion("vol8 <=", value, "vol8");
            return (Criteria) this;
        }

        public Criteria andVol8Like(Integer value) {
            addCriterion("vol8 like", value, "vol8");
            return (Criteria) this;
        }

        public Criteria andVol8NotLike(Integer value) {
            addCriterion("vol8 not like", value, "vol8");
            return (Criteria) this;
        }

        public Criteria andVol8In(List<Integer> values) {
            addCriterion("vol8 in", values, "vol8");
            return (Criteria) this;
        }

        public Criteria andVol8NotIn(List<Integer> values) {
            addCriterion("vol8 not in", values, "vol8");
            return (Criteria) this;
        }

        public Criteria andVol8Between(Integer value1, Integer value2) {
            addCriterion("vol8 between", value1, value2, "vol8");
            return (Criteria) this;
        }

        public Criteria andVol8NotBetween(Integer value1, Integer value2) {
            addCriterion("vol8 not between", value1, value2, "vol8");
            return (Criteria) this;
        }
        
			
        public Criteria andVol9IsNull() {
            addCriterion("vol9 is null");
            return (Criteria) this;
        }

        public Criteria andVol9IsNotNull() {
            addCriterion("vol9 is not null");
            return (Criteria) this;
        }

        public Criteria andVol9EqualTo(Integer value) {
            addCriterion("vol9 =", value, "vol9");
            return (Criteria) this;
        }

        public Criteria andVol9NotEqualTo(Integer value) {
            addCriterion("vol9 <>", value, "vol9");
            return (Criteria) this;
        }

        public Criteria andVol9GreaterThan(Integer value) {
            addCriterion("vol9 >", value, "vol9");
            return (Criteria) this;
        }

        public Criteria andVol9GreaterThanOrEqualTo(Integer value) {
            addCriterion("vol9 >=", value, "vol9");
            return (Criteria) this;
        }

        public Criteria andVol9LessThan(Integer value) {
            addCriterion("vol9 <", value, "vol9");
            return (Criteria) this;
        }

        public Criteria andVol9LessThanOrEqualTo(Integer value) {
            addCriterion("vol9 <=", value, "vol9");
            return (Criteria) this;
        }

        public Criteria andVol9Like(Integer value) {
            addCriterion("vol9 like", value, "vol9");
            return (Criteria) this;
        }

        public Criteria andVol9NotLike(Integer value) {
            addCriterion("vol9 not like", value, "vol9");
            return (Criteria) this;
        }

        public Criteria andVol9In(List<Integer> values) {
            addCriterion("vol9 in", values, "vol9");
            return (Criteria) this;
        }

        public Criteria andVol9NotIn(List<Integer> values) {
            addCriterion("vol9 not in", values, "vol9");
            return (Criteria) this;
        }

        public Criteria andVol9Between(Integer value1, Integer value2) {
            addCriterion("vol9 between", value1, value2, "vol9");
            return (Criteria) this;
        }

        public Criteria andVol9NotBetween(Integer value1, Integer value2) {
            addCriterion("vol9 not between", value1, value2, "vol9");
            return (Criteria) this;
        }
        
			
        public Criteria andVol10IsNull() {
            addCriterion("vol10 is null");
            return (Criteria) this;
        }

        public Criteria andVol10IsNotNull() {
            addCriterion("vol10 is not null");
            return (Criteria) this;
        }

        public Criteria andVol10EqualTo(Integer value) {
            addCriterion("vol10 =", value, "vol10");
            return (Criteria) this;
        }

        public Criteria andVol10NotEqualTo(Integer value) {
            addCriterion("vol10 <>", value, "vol10");
            return (Criteria) this;
        }

        public Criteria andVol10GreaterThan(Integer value) {
            addCriterion("vol10 >", value, "vol10");
            return (Criteria) this;
        }

        public Criteria andVol10GreaterThanOrEqualTo(Integer value) {
            addCriterion("vol10 >=", value, "vol10");
            return (Criteria) this;
        }

        public Criteria andVol10LessThan(Integer value) {
            addCriterion("vol10 <", value, "vol10");
            return (Criteria) this;
        }

        public Criteria andVol10LessThanOrEqualTo(Integer value) {
            addCriterion("vol10 <=", value, "vol10");
            return (Criteria) this;
        }

        public Criteria andVol10Like(Integer value) {
            addCriterion("vol10 like", value, "vol10");
            return (Criteria) this;
        }

        public Criteria andVol10NotLike(Integer value) {
            addCriterion("vol10 not like", value, "vol10");
            return (Criteria) this;
        }

        public Criteria andVol10In(List<Integer> values) {
            addCriterion("vol10 in", values, "vol10");
            return (Criteria) this;
        }

        public Criteria andVol10NotIn(List<Integer> values) {
            addCriterion("vol10 not in", values, "vol10");
            return (Criteria) this;
        }

        public Criteria andVol10Between(Integer value1, Integer value2) {
            addCriterion("vol10 between", value1, value2, "vol10");
            return (Criteria) this;
        }

        public Criteria andVol10NotBetween(Integer value1, Integer value2) {
            addCriterion("vol10 not between", value1, value2, "vol10");
            return (Criteria) this;
        }
        
			
        public Criteria andVol11IsNull() {
            addCriterion("vol11 is null");
            return (Criteria) this;
        }

        public Criteria andVol11IsNotNull() {
            addCriterion("vol11 is not null");
            return (Criteria) this;
        }

        public Criteria andVol11EqualTo(Integer value) {
            addCriterion("vol11 =", value, "vol11");
            return (Criteria) this;
        }

        public Criteria andVol11NotEqualTo(Integer value) {
            addCriterion("vol11 <>", value, "vol11");
            return (Criteria) this;
        }

        public Criteria andVol11GreaterThan(Integer value) {
            addCriterion("vol11 >", value, "vol11");
            return (Criteria) this;
        }

        public Criteria andVol11GreaterThanOrEqualTo(Integer value) {
            addCriterion("vol11 >=", value, "vol11");
            return (Criteria) this;
        }

        public Criteria andVol11LessThan(Integer value) {
            addCriterion("vol11 <", value, "vol11");
            return (Criteria) this;
        }

        public Criteria andVol11LessThanOrEqualTo(Integer value) {
            addCriterion("vol11 <=", value, "vol11");
            return (Criteria) this;
        }

        public Criteria andVol11Like(Integer value) {
            addCriterion("vol11 like", value, "vol11");
            return (Criteria) this;
        }

        public Criteria andVol11NotLike(Integer value) {
            addCriterion("vol11 not like", value, "vol11");
            return (Criteria) this;
        }

        public Criteria andVol11In(List<Integer> values) {
            addCriterion("vol11 in", values, "vol11");
            return (Criteria) this;
        }

        public Criteria andVol11NotIn(List<Integer> values) {
            addCriterion("vol11 not in", values, "vol11");
            return (Criteria) this;
        }

        public Criteria andVol11Between(Integer value1, Integer value2) {
            addCriterion("vol11 between", value1, value2, "vol11");
            return (Criteria) this;
        }

        public Criteria andVol11NotBetween(Integer value1, Integer value2) {
            addCriterion("vol11 not between", value1, value2, "vol11");
            return (Criteria) this;
        }
        
			
        public Criteria andVol12IsNull() {
            addCriterion("vol12 is null");
            return (Criteria) this;
        }

        public Criteria andVol12IsNotNull() {
            addCriterion("vol12 is not null");
            return (Criteria) this;
        }

        public Criteria andVol12EqualTo(Integer value) {
            addCriterion("vol12 =", value, "vol12");
            return (Criteria) this;
        }

        public Criteria andVol12NotEqualTo(Integer value) {
            addCriterion("vol12 <>", value, "vol12");
            return (Criteria) this;
        }

        public Criteria andVol12GreaterThan(Integer value) {
            addCriterion("vol12 >", value, "vol12");
            return (Criteria) this;
        }

        public Criteria andVol12GreaterThanOrEqualTo(Integer value) {
            addCriterion("vol12 >=", value, "vol12");
            return (Criteria) this;
        }

        public Criteria andVol12LessThan(Integer value) {
            addCriterion("vol12 <", value, "vol12");
            return (Criteria) this;
        }

        public Criteria andVol12LessThanOrEqualTo(Integer value) {
            addCriterion("vol12 <=", value, "vol12");
            return (Criteria) this;
        }

        public Criteria andVol12Like(Integer value) {
            addCriterion("vol12 like", value, "vol12");
            return (Criteria) this;
        }

        public Criteria andVol12NotLike(Integer value) {
            addCriterion("vol12 not like", value, "vol12");
            return (Criteria) this;
        }

        public Criteria andVol12In(List<Integer> values) {
            addCriterion("vol12 in", values, "vol12");
            return (Criteria) this;
        }

        public Criteria andVol12NotIn(List<Integer> values) {
            addCriterion("vol12 not in", values, "vol12");
            return (Criteria) this;
        }

        public Criteria andVol12Between(Integer value1, Integer value2) {
            addCriterion("vol12 between", value1, value2, "vol12");
            return (Criteria) this;
        }

        public Criteria andVol12NotBetween(Integer value1, Integer value2) {
            addCriterion("vol12 not between", value1, value2, "vol12");
            return (Criteria) this;
        }
        
			
        public Criteria andVol13IsNull() {
            addCriterion("vol13 is null");
            return (Criteria) this;
        }

        public Criteria andVol13IsNotNull() {
            addCriterion("vol13 is not null");
            return (Criteria) this;
        }

        public Criteria andVol13EqualTo(Integer value) {
            addCriterion("vol13 =", value, "vol13");
            return (Criteria) this;
        }

        public Criteria andVol13NotEqualTo(Integer value) {
            addCriterion("vol13 <>", value, "vol13");
            return (Criteria) this;
        }

        public Criteria andVol13GreaterThan(Integer value) {
            addCriterion("vol13 >", value, "vol13");
            return (Criteria) this;
        }

        public Criteria andVol13GreaterThanOrEqualTo(Integer value) {
            addCriterion("vol13 >=", value, "vol13");
            return (Criteria) this;
        }

        public Criteria andVol13LessThan(Integer value) {
            addCriterion("vol13 <", value, "vol13");
            return (Criteria) this;
        }

        public Criteria andVol13LessThanOrEqualTo(Integer value) {
            addCriterion("vol13 <=", value, "vol13");
            return (Criteria) this;
        }

        public Criteria andVol13Like(Integer value) {
            addCriterion("vol13 like", value, "vol13");
            return (Criteria) this;
        }

        public Criteria andVol13NotLike(Integer value) {
            addCriterion("vol13 not like", value, "vol13");
            return (Criteria) this;
        }

        public Criteria andVol13In(List<Integer> values) {
            addCriterion("vol13 in", values, "vol13");
            return (Criteria) this;
        }

        public Criteria andVol13NotIn(List<Integer> values) {
            addCriterion("vol13 not in", values, "vol13");
            return (Criteria) this;
        }

        public Criteria andVol13Between(Integer value1, Integer value2) {
            addCriterion("vol13 between", value1, value2, "vol13");
            return (Criteria) this;
        }

        public Criteria andVol13NotBetween(Integer value1, Integer value2) {
            addCriterion("vol13 not between", value1, value2, "vol13");
            return (Criteria) this;
        }
        
			
        public Criteria andVol14IsNull() {
            addCriterion("vol14 is null");
            return (Criteria) this;
        }

        public Criteria andVol14IsNotNull() {
            addCriterion("vol14 is not null");
            return (Criteria) this;
        }

        public Criteria andVol14EqualTo(Integer value) {
            addCriterion("vol14 =", value, "vol14");
            return (Criteria) this;
        }

        public Criteria andVol14NotEqualTo(Integer value) {
            addCriterion("vol14 <>", value, "vol14");
            return (Criteria) this;
        }

        public Criteria andVol14GreaterThan(Integer value) {
            addCriterion("vol14 >", value, "vol14");
            return (Criteria) this;
        }

        public Criteria andVol14GreaterThanOrEqualTo(Integer value) {
            addCriterion("vol14 >=", value, "vol14");
            return (Criteria) this;
        }

        public Criteria andVol14LessThan(Integer value) {
            addCriterion("vol14 <", value, "vol14");
            return (Criteria) this;
        }

        public Criteria andVol14LessThanOrEqualTo(Integer value) {
            addCriterion("vol14 <=", value, "vol14");
            return (Criteria) this;
        }

        public Criteria andVol14Like(Integer value) {
            addCriterion("vol14 like", value, "vol14");
            return (Criteria) this;
        }

        public Criteria andVol14NotLike(Integer value) {
            addCriterion("vol14 not like", value, "vol14");
            return (Criteria) this;
        }

        public Criteria andVol14In(List<Integer> values) {
            addCriterion("vol14 in", values, "vol14");
            return (Criteria) this;
        }

        public Criteria andVol14NotIn(List<Integer> values) {
            addCriterion("vol14 not in", values, "vol14");
            return (Criteria) this;
        }

        public Criteria andVol14Between(Integer value1, Integer value2) {
            addCriterion("vol14 between", value1, value2, "vol14");
            return (Criteria) this;
        }

        public Criteria andVol14NotBetween(Integer value1, Integer value2) {
            addCriterion("vol14 not between", value1, value2, "vol14");
            return (Criteria) this;
        }
        
			
        public Criteria andVol15IsNull() {
            addCriterion("vol15 is null");
            return (Criteria) this;
        }

        public Criteria andVol15IsNotNull() {
            addCriterion("vol15 is not null");
            return (Criteria) this;
        }

        public Criteria andVol15EqualTo(Integer value) {
            addCriterion("vol15 =", value, "vol15");
            return (Criteria) this;
        }

        public Criteria andVol15NotEqualTo(Integer value) {
            addCriterion("vol15 <>", value, "vol15");
            return (Criteria) this;
        }

        public Criteria andVol15GreaterThan(Integer value) {
            addCriterion("vol15 >", value, "vol15");
            return (Criteria) this;
        }

        public Criteria andVol15GreaterThanOrEqualTo(Integer value) {
            addCriterion("vol15 >=", value, "vol15");
            return (Criteria) this;
        }

        public Criteria andVol15LessThan(Integer value) {
            addCriterion("vol15 <", value, "vol15");
            return (Criteria) this;
        }

        public Criteria andVol15LessThanOrEqualTo(Integer value) {
            addCriterion("vol15 <=", value, "vol15");
            return (Criteria) this;
        }

        public Criteria andVol15Like(Integer value) {
            addCriterion("vol15 like", value, "vol15");
            return (Criteria) this;
        }

        public Criteria andVol15NotLike(Integer value) {
            addCriterion("vol15 not like", value, "vol15");
            return (Criteria) this;
        }

        public Criteria andVol15In(List<Integer> values) {
            addCriterion("vol15 in", values, "vol15");
            return (Criteria) this;
        }

        public Criteria andVol15NotIn(List<Integer> values) {
            addCriterion("vol15 not in", values, "vol15");
            return (Criteria) this;
        }

        public Criteria andVol15Between(Integer value1, Integer value2) {
            addCriterion("vol15 between", value1, value2, "vol15");
            return (Criteria) this;
        }

        public Criteria andVol15NotBetween(Integer value1, Integer value2) {
            addCriterion("vol15 not between", value1, value2, "vol15");
            return (Criteria) this;
        }
        
			
        public Criteria andListIsNull() {
            addCriterion("list is null");
            return (Criteria) this;
        }

        public Criteria andListIsNotNull() {
            addCriterion("list is not null");
            return (Criteria) this;
        }

        public Criteria andListEqualTo(String value) {
            addCriterion("list =", value, "list");
            return (Criteria) this;
        }

        public Criteria andListNotEqualTo(String value) {
            addCriterion("list <>", value, "list");
            return (Criteria) this;
        }

        public Criteria andListGreaterThan(String value) {
            addCriterion("list >", value, "list");
            return (Criteria) this;
        }

        public Criteria andListGreaterThanOrEqualTo(String value) {
            addCriterion("list >=", value, "list");
            return (Criteria) this;
        }

        public Criteria andListLessThan(String value) {
            addCriterion("list <", value, "list");
            return (Criteria) this;
        }

        public Criteria andListLessThanOrEqualTo(String value) {
            addCriterion("list <=", value, "list");
            return (Criteria) this;
        }

        public Criteria andListLike(String value) {
            addCriterion("list like", value, "list");
            return (Criteria) this;
        }

        public Criteria andListNotLike(String value) {
            addCriterion("list not like", value, "list");
            return (Criteria) this;
        }

        public Criteria andListIn(List<String> values) {
            addCriterion("list in", values, "list");
            return (Criteria) this;
        }

        public Criteria andListNotIn(List<String> values) {
            addCriterion("list not in", values, "list");
            return (Criteria) this;
        }

        public Criteria andListBetween(String value1, String value2) {
            addCriterion("list between", value1, value2, "list");
            return (Criteria) this;
        }

        public Criteria andListNotBetween(String value1, String value2) {
            addCriterion("list not between", value1, value2, "list");
            return (Criteria) this;
        }
        
			
        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }
        
		
		 public Criteria andLikeQuery(StockPool record) {
		 	List<String> list= new ArrayList<String>();
		 	List<String> list2= new ArrayList<String>();
        	StringBuffer buffer=new StringBuffer();
        	
        				 
			 if(record.getSid()!=null&&StrUtil.isNotEmpty(record.getSid().toString())) {
    			 list.add("ifnull(sid,'')");
    		 }
    		 
						 
			 if(record.getCode()!=null&&StrUtil.isNotEmpty(record.getCode().toString())) {
    			 list.add("ifnull(code,'')");
    		 }
    		 
						 
			 if(record.getCname()!=null&&StrUtil.isNotEmpty(record.getCname().toString())) {
    			 list.add("ifnull(cname,'')");
    		 }
    		 
						 
			 if(record.getGrade()!=null&&StrUtil.isNotEmpty(record.getGrade().toString())) {
    			 list.add("ifnull(grade,'')");
    		 }
    		 
						 
			 if(record.getAct()!=null&&StrUtil.isNotEmpty(record.getAct().toString())) {
    			 list.add("ifnull(act,'')");
    		 }
    		 
						 
			 if(record.getUpdateDt()!=null&&StrUtil.isNotEmpty(record.getUpdateDt().toString())) {
    			 list.add("ifnull(update_dt,'')");
    		 }
    		 
						 
			 if(record.getCurPrice()!=null&&StrUtil.isNotEmpty(record.getCurPrice().toString())) {
    			 list.add("ifnull(cur_price,'')");
    		 }
    		 
						 
			 if(record.getVbeg()!=null&&StrUtil.isNotEmpty(record.getVbeg().toString())) {
    			 list.add("ifnull(vbeg,'')");
    		 }
    		 
						 
			 if(record.getVend()!=null&&StrUtil.isNotEmpty(record.getVend().toString())) {
    			 list.add("ifnull(vend,'')");
    		 }
    		 
						 
			 if(record.getLastend()!=null&&StrUtil.isNotEmpty(record.getLastend().toString())) {
    			 list.add("ifnull(lastend,'')");
    		 }
    		 
						 
			 if(record.getVhigh()!=null&&StrUtil.isNotEmpty(record.getVhigh().toString())) {
    			 list.add("ifnull(vhigh,'')");
    		 }
    		 
						 
			 if(record.getVlow()!=null&&StrUtil.isNotEmpty(record.getVlow().toString())) {
    			 list.add("ifnull(vlow,'')");
    		 }
    		 
						 
			 if(record.getVtotal()!=null&&StrUtil.isNotEmpty(record.getVtotal().toString())) {
    			 list.add("ifnull(vtotal,'')");
    		 }
    		 
						 
			 if(record.getVamount()!=null&&StrUtil.isNotEmpty(record.getVamount().toString())) {
    			 list.add("ifnull(vamount,'')");
    		 }
    		 
						 
			 if(record.getVol1()!=null&&StrUtil.isNotEmpty(record.getVol1().toString())) {
    			 list.add("ifnull(vol1,'')");
    		 }
    		 
						 
			 if(record.getVol2()!=null&&StrUtil.isNotEmpty(record.getVol2().toString())) {
    			 list.add("ifnull(vol2,'')");
    		 }
    		 
						 
			 if(record.getVol3()!=null&&StrUtil.isNotEmpty(record.getVol3().toString())) {
    			 list.add("ifnull(vol3,'')");
    		 }
    		 
						 
			 if(record.getVol4()!=null&&StrUtil.isNotEmpty(record.getVol4().toString())) {
    			 list.add("ifnull(vol4,'')");
    		 }
    		 
						 
			 if(record.getVol5()!=null&&StrUtil.isNotEmpty(record.getVol5().toString())) {
    			 list.add("ifnull(vol5,'')");
    		 }
    		 
						 
			 if(record.getVol6()!=null&&StrUtil.isNotEmpty(record.getVol6().toString())) {
    			 list.add("ifnull(vol6,'')");
    		 }
    		 
						 
			 if(record.getVol7()!=null&&StrUtil.isNotEmpty(record.getVol7().toString())) {
    			 list.add("ifnull(vol7,'')");
    		 }
    		 
						 
			 if(record.getVol8()!=null&&StrUtil.isNotEmpty(record.getVol8().toString())) {
    			 list.add("ifnull(vol8,'')");
    		 }
    		 
						 
			 if(record.getVol9()!=null&&StrUtil.isNotEmpty(record.getVol9().toString())) {
    			 list.add("ifnull(vol9,'')");
    		 }
    		 
						 
			 if(record.getVol10()!=null&&StrUtil.isNotEmpty(record.getVol10().toString())) {
    			 list.add("ifnull(vol10,'')");
    		 }
    		 
						 
			 if(record.getVol11()!=null&&StrUtil.isNotEmpty(record.getVol11().toString())) {
    			 list.add("ifnull(vol11,'')");
    		 }
    		 
						 
			 if(record.getVol12()!=null&&StrUtil.isNotEmpty(record.getVol12().toString())) {
    			 list.add("ifnull(vol12,'')");
    		 }
    		 
						 
			 if(record.getVol13()!=null&&StrUtil.isNotEmpty(record.getVol13().toString())) {
    			 list.add("ifnull(vol13,'')");
    		 }
    		 
						 
			 if(record.getVol14()!=null&&StrUtil.isNotEmpty(record.getVol14().toString())) {
    			 list.add("ifnull(vol14,'')");
    		 }
    		 
						 
			 if(record.getVol15()!=null&&StrUtil.isNotEmpty(record.getVol15().toString())) {
    			 list.add("ifnull(vol15,'')");
    		 }
    		 
						 
			 if(record.getList()!=null&&StrUtil.isNotEmpty(record.getList().toString())) {
    			 list.add("ifnull(list,'')");
    		 }
    		 
						 
			 if(record.getDescription()!=null&&StrUtil.isNotEmpty(record.getDescription().toString())) {
    			 list.add("ifnull(description,'')");
    		 }
    		 
									
			 if(record.getSid()!=null&&StrUtil.isNotEmpty(record.getSid().toString())) {
    			list2.add("'%"+record.getSid()+"%'");
    		 }
        		 
						
			 if(record.getCode()!=null&&StrUtil.isNotEmpty(record.getCode().toString())) {
    			list2.add("'%"+record.getCode()+"%'");
    		 }
        		 
						
			 if(record.getCname()!=null&&StrUtil.isNotEmpty(record.getCname().toString())) {
    			list2.add("'%"+record.getCname()+"%'");
    		 }
        		 
						
			 if(record.getGrade()!=null&&StrUtil.isNotEmpty(record.getGrade().toString())) {
    			list2.add("'%"+record.getGrade()+"%'");
    		 }
        		 
						
			 if(record.getAct()!=null&&StrUtil.isNotEmpty(record.getAct().toString())) {
    			list2.add("'%"+record.getAct()+"%'");
    		 }
        		 
						
			 if(record.getUpdateDt()!=null&&StrUtil.isNotEmpty(record.getUpdateDt().toString())) {
    			list2.add("'%"+record.getUpdateDt()+"%'");
    		 }
        		 
						
			 if(record.getCurPrice()!=null&&StrUtil.isNotEmpty(record.getCurPrice().toString())) {
    			list2.add("'%"+record.getCurPrice()+"%'");
    		 }
        		 
						
			 if(record.getVbeg()!=null&&StrUtil.isNotEmpty(record.getVbeg().toString())) {
    			list2.add("'%"+record.getVbeg()+"%'");
    		 }
        		 
						
			 if(record.getVend()!=null&&StrUtil.isNotEmpty(record.getVend().toString())) {
    			list2.add("'%"+record.getVend()+"%'");
    		 }
        		 
						
			 if(record.getLastend()!=null&&StrUtil.isNotEmpty(record.getLastend().toString())) {
    			list2.add("'%"+record.getLastend()+"%'");
    		 }
        		 
						
			 if(record.getVhigh()!=null&&StrUtil.isNotEmpty(record.getVhigh().toString())) {
    			list2.add("'%"+record.getVhigh()+"%'");
    		 }
        		 
						
			 if(record.getVlow()!=null&&StrUtil.isNotEmpty(record.getVlow().toString())) {
    			list2.add("'%"+record.getVlow()+"%'");
    		 }
        		 
						
			 if(record.getVtotal()!=null&&StrUtil.isNotEmpty(record.getVtotal().toString())) {
    			list2.add("'%"+record.getVtotal()+"%'");
    		 }
        		 
						
			 if(record.getVamount()!=null&&StrUtil.isNotEmpty(record.getVamount().toString())) {
    			list2.add("'%"+record.getVamount()+"%'");
    		 }
        		 
						
			 if(record.getVol1()!=null&&StrUtil.isNotEmpty(record.getVol1().toString())) {
    			list2.add("'%"+record.getVol1()+"%'");
    		 }
        		 
						
			 if(record.getVol2()!=null&&StrUtil.isNotEmpty(record.getVol2().toString())) {
    			list2.add("'%"+record.getVol2()+"%'");
    		 }
        		 
						
			 if(record.getVol3()!=null&&StrUtil.isNotEmpty(record.getVol3().toString())) {
    			list2.add("'%"+record.getVol3()+"%'");
    		 }
        		 
						
			 if(record.getVol4()!=null&&StrUtil.isNotEmpty(record.getVol4().toString())) {
    			list2.add("'%"+record.getVol4()+"%'");
    		 }
        		 
						
			 if(record.getVol5()!=null&&StrUtil.isNotEmpty(record.getVol5().toString())) {
    			list2.add("'%"+record.getVol5()+"%'");
    		 }
        		 
						
			 if(record.getVol6()!=null&&StrUtil.isNotEmpty(record.getVol6().toString())) {
    			list2.add("'%"+record.getVol6()+"%'");
    		 }
        		 
						
			 if(record.getVol7()!=null&&StrUtil.isNotEmpty(record.getVol7().toString())) {
    			list2.add("'%"+record.getVol7()+"%'");
    		 }
        		 
						
			 if(record.getVol8()!=null&&StrUtil.isNotEmpty(record.getVol8().toString())) {
    			list2.add("'%"+record.getVol8()+"%'");
    		 }
        		 
						
			 if(record.getVol9()!=null&&StrUtil.isNotEmpty(record.getVol9().toString())) {
    			list2.add("'%"+record.getVol9()+"%'");
    		 }
        		 
						
			 if(record.getVol10()!=null&&StrUtil.isNotEmpty(record.getVol10().toString())) {
    			list2.add("'%"+record.getVol10()+"%'");
    		 }
        		 
						
			 if(record.getVol11()!=null&&StrUtil.isNotEmpty(record.getVol11().toString())) {
    			list2.add("'%"+record.getVol11()+"%'");
    		 }
        		 
						
			 if(record.getVol12()!=null&&StrUtil.isNotEmpty(record.getVol12().toString())) {
    			list2.add("'%"+record.getVol12()+"%'");
    		 }
        		 
						
			 if(record.getVol13()!=null&&StrUtil.isNotEmpty(record.getVol13().toString())) {
    			list2.add("'%"+record.getVol13()+"%'");
    		 }
        		 
						
			 if(record.getVol14()!=null&&StrUtil.isNotEmpty(record.getVol14().toString())) {
    			list2.add("'%"+record.getVol14()+"%'");
    		 }
        		 
						
			 if(record.getVol15()!=null&&StrUtil.isNotEmpty(record.getVol15().toString())) {
    			list2.add("'%"+record.getVol15()+"%'");
    		 }
        		 
						
			 if(record.getList()!=null&&StrUtil.isNotEmpty(record.getList().toString())) {
    			list2.add("'%"+record.getList()+"%'");
    		 }
        		 
						
			 if(record.getDescription()!=null&&StrUtil.isNotEmpty(record.getDescription().toString())) {
    			list2.add("'%"+record.getDescription()+"%'");
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
			
			        	
    		list.add("ifnull(cname,'')");
			
			        	
    		list.add("ifnull(grade,'')");
			
			        	
    		list.add("ifnull(act,'')");
			
			        	
    		list.add("ifnull(update_dt,'')");
			
			        	
    		list.add("ifnull(cur_price,'')");
			
			        	
    		list.add("ifnull(vbeg,'')");
			
			        	
    		list.add("ifnull(vend,'')");
			
			        	
    		list.add("ifnull(lastend,'')");
			
			        	
    		list.add("ifnull(vhigh,'')");
			
			        	
    		list.add("ifnull(vlow,'')");
			
			        	
    		list.add("ifnull(vtotal,'')");
			
			        	
    		list.add("ifnull(vamount,'')");
			
			        	
    		list.add("ifnull(vol1,'')");
			
			        	
    		list.add("ifnull(vol2,'')");
			
			        	
    		list.add("ifnull(vol3,'')");
			
			        	
    		list.add("ifnull(vol4,'')");
			
			        	
    		list.add("ifnull(vol5,'')");
			
			        	
    		list.add("ifnull(vol6,'')");
			
			        	
    		list.add("ifnull(vol7,'')");
			
			        	
    		list.add("ifnull(vol8,'')");
			
			        	
    		list.add("ifnull(vol9,'')");
			
			        	
    		list.add("ifnull(vol10,'')");
			
			        	
    		list.add("ifnull(vol11,'')");
			
			        	
    		list.add("ifnull(vol12,'')");
			
			        	
    		list.add("ifnull(vol13,'')");
			
			        	
    		list.add("ifnull(vol14,'')");
			
			        	
    		list.add("ifnull(vol15,'')");
			
			        	
    		list.add("ifnull(list,'')");
			
			        	
    		list.add("ifnull(description,'')");
			
						
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