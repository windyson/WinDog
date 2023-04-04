package com.yunbot.windog.model.auto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import cn.hutool.core.util.StrUtil;
/**
 *  BoinfoExample
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-13 23:02:09
 */
public class BoinfoExample {

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BoinfoExample() {
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
        
			
        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }
        
			
        public Criteria andNickIsNull() {
            addCriterion("nick is null");
            return (Criteria) this;
        }

        public Criteria andNickIsNotNull() {
            addCriterion("nick is not null");
            return (Criteria) this;
        }

        public Criteria andNickEqualTo(String value) {
            addCriterion("nick =", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotEqualTo(String value) {
            addCriterion("nick <>", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickGreaterThan(String value) {
            addCriterion("nick >", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickGreaterThanOrEqualTo(String value) {
            addCriterion("nick >=", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLessThan(String value) {
            addCriterion("nick <", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLessThanOrEqualTo(String value) {
            addCriterion("nick <=", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLike(String value) {
            addCriterion("nick like", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotLike(String value) {
            addCriterion("nick not like", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickIn(List<String> values) {
            addCriterion("nick in", values, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotIn(List<String> values) {
            addCriterion("nick not in", values, "nick");
            return (Criteria) this;
        }

        public Criteria andNickBetween(String value1, String value2) {
            addCriterion("nick between", value1, value2, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotBetween(String value1, String value2) {
            addCriterion("nick not between", value1, value2, "nick");
            return (Criteria) this;
        }
        
			
        public Criteria andRegisterDtIsNull() {
            addCriterion("register_dt is null");
            return (Criteria) this;
        }

        public Criteria andRegisterDtIsNotNull() {
            addCriterion("register_dt is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterDtEqualTo(Date value) {
            addCriterion("register_dt =", value, "registerDt");
            return (Criteria) this;
        }

        public Criteria andRegisterDtNotEqualTo(Date value) {
            addCriterion("register_dt <>", value, "registerDt");
            return (Criteria) this;
        }

        public Criteria andRegisterDtGreaterThan(Date value) {
            addCriterion("register_dt >", value, "registerDt");
            return (Criteria) this;
        }

        public Criteria andRegisterDtGreaterThanOrEqualTo(Date value) {
            addCriterion("register_dt >=", value, "registerDt");
            return (Criteria) this;
        }

        public Criteria andRegisterDtLessThan(Date value) {
            addCriterion("register_dt <", value, "registerDt");
            return (Criteria) this;
        }

        public Criteria andRegisterDtLessThanOrEqualTo(Date value) {
            addCriterion("register_dt <=", value, "registerDt");
            return (Criteria) this;
        }

        public Criteria andRegisterDtLike(Date value) {
            addCriterion("register_dt like", value, "registerDt");
            return (Criteria) this;
        }

        public Criteria andRegisterDtNotLike(Date value) {
            addCriterion("register_dt not like", value, "registerDt");
            return (Criteria) this;
        }

        public Criteria andRegisterDtIn(List<Date> values) {
            addCriterion("register_dt in", values, "registerDt");
            return (Criteria) this;
        }

        public Criteria andRegisterDtNotIn(List<Date> values) {
            addCriterion("register_dt not in", values, "registerDt");
            return (Criteria) this;
        }

        public Criteria andRegisterDtBetween(Date value1, Date value2) {
            addCriterion("register_dt between", value1, value2, "registerDt");
            return (Criteria) this;
        }

        public Criteria andRegisterDtNotBetween(Date value1, Date value2) {
            addCriterion("register_dt not between", value1, value2, "registerDt");
            return (Criteria) this;
        }
        
			
        public Criteria andServiceDtIsNull() {
            addCriterion("service_dt is null");
            return (Criteria) this;
        }

        public Criteria andServiceDtIsNotNull() {
            addCriterion("service_dt is not null");
            return (Criteria) this;
        }

        public Criteria andServiceDtEqualTo(Date value) {
            addCriterion("service_dt =", value, "serviceDt");
            return (Criteria) this;
        }

        public Criteria andServiceDtNotEqualTo(Date value) {
            addCriterion("service_dt <>", value, "serviceDt");
            return (Criteria) this;
        }

        public Criteria andServiceDtGreaterThan(Date value) {
            addCriterion("service_dt >", value, "serviceDt");
            return (Criteria) this;
        }

        public Criteria andServiceDtGreaterThanOrEqualTo(Date value) {
            addCriterion("service_dt >=", value, "serviceDt");
            return (Criteria) this;
        }

        public Criteria andServiceDtLessThan(Date value) {
            addCriterion("service_dt <", value, "serviceDt");
            return (Criteria) this;
        }

        public Criteria andServiceDtLessThanOrEqualTo(Date value) {
            addCriterion("service_dt <=", value, "serviceDt");
            return (Criteria) this;
        }

        public Criteria andServiceDtLike(Date value) {
            addCriterion("service_dt like", value, "serviceDt");
            return (Criteria) this;
        }

        public Criteria andServiceDtNotLike(Date value) {
            addCriterion("service_dt not like", value, "serviceDt");
            return (Criteria) this;
        }

        public Criteria andServiceDtIn(List<Date> values) {
            addCriterion("service_dt in", values, "serviceDt");
            return (Criteria) this;
        }

        public Criteria andServiceDtNotIn(List<Date> values) {
            addCriterion("service_dt not in", values, "serviceDt");
            return (Criteria) this;
        }

        public Criteria andServiceDtBetween(Date value1, Date value2) {
            addCriterion("service_dt between", value1, value2, "serviceDt");
            return (Criteria) this;
        }

        public Criteria andServiceDtNotBetween(Date value1, Date value2) {
            addCriterion("service_dt not between", value1, value2, "serviceDt");
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
        
			
        public Criteria andFundsIsNull() {
            addCriterion("funds is null");
            return (Criteria) this;
        }

        public Criteria andFundsIsNotNull() {
            addCriterion("funds is not null");
            return (Criteria) this;
        }

        public Criteria andFundsEqualTo(BigDecimal value) {
            addCriterion("funds =", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsNotEqualTo(BigDecimal value) {
            addCriterion("funds <>", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsGreaterThan(BigDecimal value) {
            addCriterion("funds >", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("funds >=", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsLessThan(BigDecimal value) {
            addCriterion("funds <", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("funds <=", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsLike(BigDecimal value) {
            addCriterion("funds like", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsNotLike(BigDecimal value) {
            addCriterion("funds not like", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsIn(List<BigDecimal> values) {
            addCriterion("funds in", values, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsNotIn(List<BigDecimal> values) {
            addCriterion("funds not in", values, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("funds between", value1, value2, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("funds not between", value1, value2, "funds");
            return (Criteria) this;
        }
        
			
        public Criteria andFundsInitIsNull() {
            addCriterion("funds_init is null");
            return (Criteria) this;
        }

        public Criteria andFundsInitIsNotNull() {
            addCriterion("funds_init is not null");
            return (Criteria) this;
        }

        public Criteria andFundsInitEqualTo(BigDecimal value) {
            addCriterion("funds_init =", value, "fundsInit");
            return (Criteria) this;
        }

        public Criteria andFundsInitNotEqualTo(BigDecimal value) {
            addCriterion("funds_init <>", value, "fundsInit");
            return (Criteria) this;
        }

        public Criteria andFundsInitGreaterThan(BigDecimal value) {
            addCriterion("funds_init >", value, "fundsInit");
            return (Criteria) this;
        }

        public Criteria andFundsInitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("funds_init >=", value, "fundsInit");
            return (Criteria) this;
        }

        public Criteria andFundsInitLessThan(BigDecimal value) {
            addCriterion("funds_init <", value, "fundsInit");
            return (Criteria) this;
        }

        public Criteria andFundsInitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("funds_init <=", value, "fundsInit");
            return (Criteria) this;
        }

        public Criteria andFundsInitLike(BigDecimal value) {
            addCriterion("funds_init like", value, "fundsInit");
            return (Criteria) this;
        }

        public Criteria andFundsInitNotLike(BigDecimal value) {
            addCriterion("funds_init not like", value, "fundsInit");
            return (Criteria) this;
        }

        public Criteria andFundsInitIn(List<BigDecimal> values) {
            addCriterion("funds_init in", values, "fundsInit");
            return (Criteria) this;
        }

        public Criteria andFundsInitNotIn(List<BigDecimal> values) {
            addCriterion("funds_init not in", values, "fundsInit");
            return (Criteria) this;
        }

        public Criteria andFundsInitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("funds_init between", value1, value2, "fundsInit");
            return (Criteria) this;
        }

        public Criteria andFundsInitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("funds_init not between", value1, value2, "fundsInit");
            return (Criteria) this;
        }
        
			
        public Criteria andPolicyIsNull() {
            addCriterion("policy is null");
            return (Criteria) this;
        }

        public Criteria andPolicyIsNotNull() {
            addCriterion("policy is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyEqualTo(Integer value) {
            addCriterion("policy =", value, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyNotEqualTo(Integer value) {
            addCriterion("policy <>", value, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyGreaterThan(Integer value) {
            addCriterion("policy >", value, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyGreaterThanOrEqualTo(Integer value) {
            addCriterion("policy >=", value, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyLessThan(Integer value) {
            addCriterion("policy <", value, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyLessThanOrEqualTo(Integer value) {
            addCriterion("policy <=", value, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyLike(Integer value) {
            addCriterion("policy like", value, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyNotLike(Integer value) {
            addCriterion("policy not like", value, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyIn(List<Integer> values) {
            addCriterion("policy in", values, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyNotIn(List<Integer> values) {
            addCriterion("policy not in", values, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyBetween(Integer value1, Integer value2) {
            addCriterion("policy between", value1, value2, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyNotBetween(Integer value1, Integer value2) {
            addCriterion("policy not between", value1, value2, "policy");
            return (Criteria) this;
        }
        
			
        public Criteria andWinIsNull() {
            addCriterion("win is null");
            return (Criteria) this;
        }

        public Criteria andWinIsNotNull() {
            addCriterion("win is not null");
            return (Criteria) this;
        }

        public Criteria andWinEqualTo(BigDecimal value) {
            addCriterion("win =", value, "win");
            return (Criteria) this;
        }

        public Criteria andWinNotEqualTo(BigDecimal value) {
            addCriterion("win <>", value, "win");
            return (Criteria) this;
        }

        public Criteria andWinGreaterThan(BigDecimal value) {
            addCriterion("win >", value, "win");
            return (Criteria) this;
        }

        public Criteria andWinGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("win >=", value, "win");
            return (Criteria) this;
        }

        public Criteria andWinLessThan(BigDecimal value) {
            addCriterion("win <", value, "win");
            return (Criteria) this;
        }

        public Criteria andWinLessThanOrEqualTo(BigDecimal value) {
            addCriterion("win <=", value, "win");
            return (Criteria) this;
        }

        public Criteria andWinLike(BigDecimal value) {
            addCriterion("win like", value, "win");
            return (Criteria) this;
        }

        public Criteria andWinNotLike(BigDecimal value) {
            addCriterion("win not like", value, "win");
            return (Criteria) this;
        }

        public Criteria andWinIn(List<BigDecimal> values) {
            addCriterion("win in", values, "win");
            return (Criteria) this;
        }

        public Criteria andWinNotIn(List<BigDecimal> values) {
            addCriterion("win not in", values, "win");
            return (Criteria) this;
        }

        public Criteria andWinBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("win between", value1, value2, "win");
            return (Criteria) this;
        }

        public Criteria andWinNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("win not between", value1, value2, "win");
            return (Criteria) this;
        }
        
			
        public Criteria andLostIsNull() {
            addCriterion("lost is null");
            return (Criteria) this;
        }

        public Criteria andLostIsNotNull() {
            addCriterion("lost is not null");
            return (Criteria) this;
        }

        public Criteria andLostEqualTo(BigDecimal value) {
            addCriterion("lost =", value, "lost");
            return (Criteria) this;
        }

        public Criteria andLostNotEqualTo(BigDecimal value) {
            addCriterion("lost <>", value, "lost");
            return (Criteria) this;
        }

        public Criteria andLostGreaterThan(BigDecimal value) {
            addCriterion("lost >", value, "lost");
            return (Criteria) this;
        }

        public Criteria andLostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("lost >=", value, "lost");
            return (Criteria) this;
        }

        public Criteria andLostLessThan(BigDecimal value) {
            addCriterion("lost <", value, "lost");
            return (Criteria) this;
        }

        public Criteria andLostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("lost <=", value, "lost");
            return (Criteria) this;
        }

        public Criteria andLostLike(BigDecimal value) {
            addCriterion("lost like", value, "lost");
            return (Criteria) this;
        }

        public Criteria andLostNotLike(BigDecimal value) {
            addCriterion("lost not like", value, "lost");
            return (Criteria) this;
        }

        public Criteria andLostIn(List<BigDecimal> values) {
            addCriterion("lost in", values, "lost");
            return (Criteria) this;
        }

        public Criteria andLostNotIn(List<BigDecimal> values) {
            addCriterion("lost not in", values, "lost");
            return (Criteria) this;
        }

        public Criteria andLostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lost between", value1, value2, "lost");
            return (Criteria) this;
        }

        public Criteria andLostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lost not between", value1, value2, "lost");
            return (Criteria) this;
        }
        
			
        public Criteria andAlarmIsNull() {
            addCriterion("alarm is null");
            return (Criteria) this;
        }

        public Criteria andAlarmIsNotNull() {
            addCriterion("alarm is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmEqualTo(Integer value) {
            addCriterion("alarm =", value, "alarm");
            return (Criteria) this;
        }

        public Criteria andAlarmNotEqualTo(Integer value) {
            addCriterion("alarm <>", value, "alarm");
            return (Criteria) this;
        }

        public Criteria andAlarmGreaterThan(Integer value) {
            addCriterion("alarm >", value, "alarm");
            return (Criteria) this;
        }

        public Criteria andAlarmGreaterThanOrEqualTo(Integer value) {
            addCriterion("alarm >=", value, "alarm");
            return (Criteria) this;
        }

        public Criteria andAlarmLessThan(Integer value) {
            addCriterion("alarm <", value, "alarm");
            return (Criteria) this;
        }

        public Criteria andAlarmLessThanOrEqualTo(Integer value) {
            addCriterion("alarm <=", value, "alarm");
            return (Criteria) this;
        }

        public Criteria andAlarmLike(Integer value) {
            addCriterion("alarm like", value, "alarm");
            return (Criteria) this;
        }

        public Criteria andAlarmNotLike(Integer value) {
            addCriterion("alarm not like", value, "alarm");
            return (Criteria) this;
        }

        public Criteria andAlarmIn(List<Integer> values) {
            addCriterion("alarm in", values, "alarm");
            return (Criteria) this;
        }

        public Criteria andAlarmNotIn(List<Integer> values) {
            addCriterion("alarm not in", values, "alarm");
            return (Criteria) this;
        }

        public Criteria andAlarmBetween(Integer value1, Integer value2) {
            addCriterion("alarm between", value1, value2, "alarm");
            return (Criteria) this;
        }

        public Criteria andAlarmNotBetween(Integer value1, Integer value2) {
            addCriterion("alarm not between", value1, value2, "alarm");
            return (Criteria) this;
        }
        
		
		 public Criteria andLikeQuery(Boinfo record) {
		 	List<String> list= new ArrayList<String>();
		 	List<String> list2= new ArrayList<String>();
        	StringBuffer buffer=new StringBuffer();
        	
        				 
			 if(record.getUid()!=null&&StrUtil.isNotEmpty(record.getUid().toString())) {
    			 list.add("ifnull(uid,'')");
    		 }
    		 
						 
			 if(record.getName()!=null&&StrUtil.isNotEmpty(record.getName().toString())) {
    			 list.add("ifnull(name,'')");
    		 }
    		 
						 
			 if(record.getNick()!=null&&StrUtil.isNotEmpty(record.getNick().toString())) {
    			 list.add("ifnull(nick,'')");
    		 }
    		 
						 
			 if(record.getRegisterDt()!=null&&StrUtil.isNotEmpty(record.getRegisterDt().toString())) {
    			 list.add("ifnull(register_dt,'')");
    		 }
    		 
						 
			 if(record.getServiceDt()!=null&&StrUtil.isNotEmpty(record.getServiceDt().toString())) {
    			 list.add("ifnull(service_dt,'')");
    		 }
    		 
						 
			 if(record.getGrade()!=null&&StrUtil.isNotEmpty(record.getGrade().toString())) {
    			 list.add("ifnull(grade,'')");
    		 }
    		 
						 
			 if(record.getFunds()!=null&&StrUtil.isNotEmpty(record.getFunds().toString())) {
    			 list.add("ifnull(funds,'')");
    		 }
    		 
						 
			 if(record.getFundsInit()!=null&&StrUtil.isNotEmpty(record.getFundsInit().toString())) {
    			 list.add("ifnull(funds_init,'')");
    		 }
    		 
						 
			 if(record.getPolicy()!=null&&StrUtil.isNotEmpty(record.getPolicy().toString())) {
    			 list.add("ifnull(policy,'')");
    		 }
    		 
						 
			 if(record.getWin()!=null&&StrUtil.isNotEmpty(record.getWin().toString())) {
    			 list.add("ifnull(win,'')");
    		 }
    		 
						 
			 if(record.getLost()!=null&&StrUtil.isNotEmpty(record.getLost().toString())) {
    			 list.add("ifnull(lost,'')");
    		 }
    		 
						 
			 if(record.getAlarm()!=null&&StrUtil.isNotEmpty(record.getAlarm().toString())) {
    			 list.add("ifnull(alarm,'')");
    		 }
    		 
									
			 if(record.getUid()!=null&&StrUtil.isNotEmpty(record.getUid().toString())) {
    			list2.add("'%"+record.getUid()+"%'");
    		 }
        		 
						
			 if(record.getName()!=null&&StrUtil.isNotEmpty(record.getName().toString())) {
    			list2.add("'%"+record.getName()+"%'");
    		 }
        		 
						
			 if(record.getNick()!=null&&StrUtil.isNotEmpty(record.getNick().toString())) {
    			list2.add("'%"+record.getNick()+"%'");
    		 }
        		 
						
			 if(record.getRegisterDt()!=null&&StrUtil.isNotEmpty(record.getRegisterDt().toString())) {
    			list2.add("'%"+record.getRegisterDt()+"%'");
    		 }
        		 
						
			 if(record.getServiceDt()!=null&&StrUtil.isNotEmpty(record.getServiceDt().toString())) {
    			list2.add("'%"+record.getServiceDt()+"%'");
    		 }
        		 
						
			 if(record.getGrade()!=null&&StrUtil.isNotEmpty(record.getGrade().toString())) {
    			list2.add("'%"+record.getGrade()+"%'");
    		 }
        		 
						
			 if(record.getFunds()!=null&&StrUtil.isNotEmpty(record.getFunds().toString())) {
    			list2.add("'%"+record.getFunds()+"%'");
    		 }
        		 
						
			 if(record.getFundsInit()!=null&&StrUtil.isNotEmpty(record.getFundsInit().toString())) {
    			list2.add("'%"+record.getFundsInit()+"%'");
    		 }
        		 
						
			 if(record.getPolicy()!=null&&StrUtil.isNotEmpty(record.getPolicy().toString())) {
    			list2.add("'%"+record.getPolicy()+"%'");
    		 }
        		 
						
			 if(record.getWin()!=null&&StrUtil.isNotEmpty(record.getWin().toString())) {
    			list2.add("'%"+record.getWin()+"%'");
    		 }
        		 
						
			 if(record.getLost()!=null&&StrUtil.isNotEmpty(record.getLost().toString())) {
    			list2.add("'%"+record.getLost()+"%'");
    		 }
        		 
						
			 if(record.getAlarm()!=null&&StrUtil.isNotEmpty(record.getAlarm().toString())) {
    			list2.add("'%"+record.getAlarm()+"%'");
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
        	
        	        	
    		list.add("ifnull(uid,'')");
			
			        	
    		list.add("ifnull(name,'')");
			
			        	
    		list.add("ifnull(nick,'')");
			
			        	
    		list.add("ifnull(register_dt,'')");
			
			        	
    		list.add("ifnull(service_dt,'')");
			
			        	
    		list.add("ifnull(grade,'')");
			
			        	
    		list.add("ifnull(funds,'')");
			
			        	
    		list.add("ifnull(funds_init,'')");
			
			        	
    		list.add("ifnull(policy,'')");
			
			        	
    		list.add("ifnull(win,'')");
			
			        	
    		list.add("ifnull(lost,'')");
			
			        	
    		list.add("ifnull(alarm,'')");
			
						
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