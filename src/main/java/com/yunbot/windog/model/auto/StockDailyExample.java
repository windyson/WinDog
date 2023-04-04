package com.yunbot.windog.model.auto;

import java.util.ArrayList;
import java.util.List;
import cn.hutool.core.util.StrUtil;
/**
 *  StockDailyExample
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2023-01-28 13:51:36
 */
public class StockDailyExample {

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StockDailyExample() {
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
				
        public Criteria andTsCodeIsNull() {
            addCriterion("ts_code is null");
            return (Criteria) this;
        }

        public Criteria andTsCodeIsNotNull() {
            addCriterion("ts_code is not null");
            return (Criteria) this;
        }

        public Criteria andTsCodeEqualTo(String value) {
            addCriterion("ts_code =", value, "tsCode");
            return (Criteria) this;
        }

        public Criteria andTsCodeNotEqualTo(String value) {
            addCriterion("ts_code <>", value, "tsCode");
            return (Criteria) this;
        }

        public Criteria andTsCodeGreaterThan(String value) {
            addCriterion("ts_code >", value, "tsCode");
            return (Criteria) this;
        }

        public Criteria andTsCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ts_code >=", value, "tsCode");
            return (Criteria) this;
        }

        public Criteria andTsCodeLessThan(String value) {
            addCriterion("ts_code <", value, "tsCode");
            return (Criteria) this;
        }

        public Criteria andTsCodeLessThanOrEqualTo(String value) {
            addCriterion("ts_code <=", value, "tsCode");
            return (Criteria) this;
        }

        public Criteria andTsCodeLike(String value) {
            addCriterion("ts_code like", value, "tsCode");
            return (Criteria) this;
        }

        public Criteria andTsCodeNotLike(String value) {
            addCriterion("ts_code not like", value, "tsCode");
            return (Criteria) this;
        }

        public Criteria andTsCodeIn(List<String> values) {
            addCriterion("ts_code in", values, "tsCode");
            return (Criteria) this;
        }

        public Criteria andTsCodeNotIn(List<String> values) {
            addCriterion("ts_code not in", values, "tsCode");
            return (Criteria) this;
        }

        public Criteria andTsCodeBetween(String value1, String value2) {
            addCriterion("ts_code between", value1, value2, "tsCode");
            return (Criteria) this;
        }

        public Criteria andTsCodeNotBetween(String value1, String value2) {
            addCriterion("ts_code not between", value1, value2, "tsCode");
            return (Criteria) this;
        }
        
			
        public Criteria andTradeDateIsNull() {
            addCriterion("trade_date is null");
            return (Criteria) this;
        }

        public Criteria andTradeDateIsNotNull() {
            addCriterion("trade_date is not null");
            return (Criteria) this;
        }

        public Criteria andTradeDateEqualTo(String value) {
            addCriterion("trade_date =", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateNotEqualTo(String value) {
            addCriterion("trade_date <>", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateGreaterThan(String value) {
            addCriterion("trade_date >", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateGreaterThanOrEqualTo(String value) {
            addCriterion("trade_date >=", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateLessThan(String value) {
            addCriterion("trade_date <", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateLessThanOrEqualTo(String value) {
            addCriterion("trade_date <=", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateLike(String value) {
            addCriterion("trade_date like", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateNotLike(String value) {
            addCriterion("trade_date not like", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateIn(List<String> values) {
            addCriterion("trade_date in", values, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateNotIn(List<String> values) {
            addCriterion("trade_date not in", values, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateBetween(String value1, String value2) {
            addCriterion("trade_date between", value1, value2, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateNotBetween(String value1, String value2) {
            addCriterion("trade_date not between", value1, value2, "tradeDate");
            return (Criteria) this;
        }
        
			
        public Criteria andOpenIsNull() {
            addCriterion("open is null");
            return (Criteria) this;
        }

        public Criteria andOpenIsNotNull() {
            addCriterion("open is not null");
            return (Criteria) this;
        }

        public Criteria andOpenEqualTo(Double value) {
            addCriterion("open =", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotEqualTo(Double value) {
            addCriterion("open <>", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenGreaterThan(Double value) {
            addCriterion("open >", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenGreaterThanOrEqualTo(Double value) {
            addCriterion("open >=", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenLessThan(Double value) {
            addCriterion("open <", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenLessThanOrEqualTo(Double value) {
            addCriterion("open <=", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenLike(Double value) {
            addCriterion("open like", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotLike(Double value) {
            addCriterion("open not like", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenIn(List<Double> values) {
            addCriterion("open in", values, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotIn(List<Double> values) {
            addCriterion("open not in", values, "open");
            return (Criteria) this;
        }

        public Criteria andOpenBetween(Double value1, Double value2) {
            addCriterion("open between", value1, value2, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotBetween(Double value1, Double value2) {
            addCriterion("open not between", value1, value2, "open");
            return (Criteria) this;
        }
        
			
        public Criteria andHighIsNull() {
            addCriterion("high is null");
            return (Criteria) this;
        }

        public Criteria andHighIsNotNull() {
            addCriterion("high is not null");
            return (Criteria) this;
        }

        public Criteria andHighEqualTo(Double value) {
            addCriterion("high =", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighNotEqualTo(Double value) {
            addCriterion("high <>", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighGreaterThan(Double value) {
            addCriterion("high >", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighGreaterThanOrEqualTo(Double value) {
            addCriterion("high >=", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighLessThan(Double value) {
            addCriterion("high <", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighLessThanOrEqualTo(Double value) {
            addCriterion("high <=", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighLike(Double value) {
            addCriterion("high like", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighNotLike(Double value) {
            addCriterion("high not like", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighIn(List<Double> values) {
            addCriterion("high in", values, "high");
            return (Criteria) this;
        }

        public Criteria andHighNotIn(List<Double> values) {
            addCriterion("high not in", values, "high");
            return (Criteria) this;
        }

        public Criteria andHighBetween(Double value1, Double value2) {
            addCriterion("high between", value1, value2, "high");
            return (Criteria) this;
        }

        public Criteria andHighNotBetween(Double value1, Double value2) {
            addCriterion("high not between", value1, value2, "high");
            return (Criteria) this;
        }
        
			
        public Criteria andLowIsNull() {
            addCriterion("low is null");
            return (Criteria) this;
        }

        public Criteria andLowIsNotNull() {
            addCriterion("low is not null");
            return (Criteria) this;
        }

        public Criteria andLowEqualTo(Double value) {
            addCriterion("low =", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowNotEqualTo(Double value) {
            addCriterion("low <>", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowGreaterThan(Double value) {
            addCriterion("low >", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowGreaterThanOrEqualTo(Double value) {
            addCriterion("low >=", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowLessThan(Double value) {
            addCriterion("low <", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowLessThanOrEqualTo(Double value) {
            addCriterion("low <=", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowLike(Double value) {
            addCriterion("low like", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowNotLike(Double value) {
            addCriterion("low not like", value, "low");
            return (Criteria) this;
        }

        public Criteria andLowIn(List<Double> values) {
            addCriterion("low in", values, "low");
            return (Criteria) this;
        }

        public Criteria andLowNotIn(List<Double> values) {
            addCriterion("low not in", values, "low");
            return (Criteria) this;
        }

        public Criteria andLowBetween(Double value1, Double value2) {
            addCriterion("low between", value1, value2, "low");
            return (Criteria) this;
        }

        public Criteria andLowNotBetween(Double value1, Double value2) {
            addCriterion("low not between", value1, value2, "low");
            return (Criteria) this;
        }
        
			
        public Criteria andClsIsNull() {
            addCriterion("cls is null");
            return (Criteria) this;
        }

        public Criteria andClsIsNotNull() {
            addCriterion("cls is not null");
            return (Criteria) this;
        }

        public Criteria andClsEqualTo(Double value) {
            addCriterion("cls =", value, "cls");
            return (Criteria) this;
        }

        public Criteria andClsNotEqualTo(Double value) {
            addCriterion("cls <>", value, "cls");
            return (Criteria) this;
        }

        public Criteria andClsGreaterThan(Double value) {
            addCriterion("cls >", value, "cls");
            return (Criteria) this;
        }

        public Criteria andClsGreaterThanOrEqualTo(Double value) {
            addCriterion("cls >=", value, "cls");
            return (Criteria) this;
        }

        public Criteria andClsLessThan(Double value) {
            addCriterion("cls <", value, "cls");
            return (Criteria) this;
        }

        public Criteria andClsLessThanOrEqualTo(Double value) {
            addCriterion("cls <=", value, "cls");
            return (Criteria) this;
        }

        public Criteria andClsLike(Double value) {
            addCriterion("cls like", value, "cls");
            return (Criteria) this;
        }

        public Criteria andClsNotLike(Double value) {
            addCriterion("cls not like", value, "cls");
            return (Criteria) this;
        }

        public Criteria andClsIn(List<Double> values) {
            addCriterion("cls in", values, "cls");
            return (Criteria) this;
        }

        public Criteria andClsNotIn(List<Double> values) {
            addCriterion("cls not in", values, "cls");
            return (Criteria) this;
        }

        public Criteria andClsBetween(Double value1, Double value2) {
            addCriterion("cls between", value1, value2, "cls");
            return (Criteria) this;
        }

        public Criteria andClsNotBetween(Double value1, Double value2) {
            addCriterion("cls not between", value1, value2, "cls");
            return (Criteria) this;
        }
        
			
        public Criteria andPreCloseIsNull() {
            addCriterion("pre_close is null");
            return (Criteria) this;
        }

        public Criteria andPreCloseIsNotNull() {
            addCriterion("pre_close is not null");
            return (Criteria) this;
        }

        public Criteria andPreCloseEqualTo(Double value) {
            addCriterion("pre_close =", value, "preClose");
            return (Criteria) this;
        }

        public Criteria andPreCloseNotEqualTo(Double value) {
            addCriterion("pre_close <>", value, "preClose");
            return (Criteria) this;
        }

        public Criteria andPreCloseGreaterThan(Double value) {
            addCriterion("pre_close >", value, "preClose");
            return (Criteria) this;
        }

        public Criteria andPreCloseGreaterThanOrEqualTo(Double value) {
            addCriterion("pre_close >=", value, "preClose");
            return (Criteria) this;
        }

        public Criteria andPreCloseLessThan(Double value) {
            addCriterion("pre_close <", value, "preClose");
            return (Criteria) this;
        }

        public Criteria andPreCloseLessThanOrEqualTo(Double value) {
            addCriterion("pre_close <=", value, "preClose");
            return (Criteria) this;
        }

        public Criteria andPreCloseLike(Double value) {
            addCriterion("pre_close like", value, "preClose");
            return (Criteria) this;
        }

        public Criteria andPreCloseNotLike(Double value) {
            addCriterion("pre_close not like", value, "preClose");
            return (Criteria) this;
        }

        public Criteria andPreCloseIn(List<Double> values) {
            addCriterion("pre_close in", values, "preClose");
            return (Criteria) this;
        }

        public Criteria andPreCloseNotIn(List<Double> values) {
            addCriterion("pre_close not in", values, "preClose");
            return (Criteria) this;
        }

        public Criteria andPreCloseBetween(Double value1, Double value2) {
            addCriterion("pre_close between", value1, value2, "preClose");
            return (Criteria) this;
        }

        public Criteria andPreCloseNotBetween(Double value1, Double value2) {
            addCriterion("pre_close not between", value1, value2, "preClose");
            return (Criteria) this;
        }
        
			
        public Criteria andChgIsNull() {
            addCriterion("chg is null");
            return (Criteria) this;
        }

        public Criteria andChgIsNotNull() {
            addCriterion("chg is not null");
            return (Criteria) this;
        }

        public Criteria andChgEqualTo(Double value) {
            addCriterion("chg =", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgNotEqualTo(Double value) {
            addCriterion("chg <>", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgGreaterThan(Double value) {
            addCriterion("chg >", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgGreaterThanOrEqualTo(Double value) {
            addCriterion("chg >=", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgLessThan(Double value) {
            addCriterion("chg <", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgLessThanOrEqualTo(Double value) {
            addCriterion("chg <=", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgLike(Double value) {
            addCriterion("chg like", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgNotLike(Double value) {
            addCriterion("chg not like", value, "chg");
            return (Criteria) this;
        }

        public Criteria andChgIn(List<Double> values) {
            addCriterion("chg in", values, "chg");
            return (Criteria) this;
        }

        public Criteria andChgNotIn(List<Double> values) {
            addCriterion("chg not in", values, "chg");
            return (Criteria) this;
        }

        public Criteria andChgBetween(Double value1, Double value2) {
            addCriterion("chg between", value1, value2, "chg");
            return (Criteria) this;
        }

        public Criteria andChgNotBetween(Double value1, Double value2) {
            addCriterion("chg not between", value1, value2, "chg");
            return (Criteria) this;
        }
        
			
        public Criteria andPctChgIsNull() {
            addCriterion("pct_chg is null");
            return (Criteria) this;
        }

        public Criteria andPctChgIsNotNull() {
            addCriterion("pct_chg is not null");
            return (Criteria) this;
        }

        public Criteria andPctChgEqualTo(Double value) {
            addCriterion("pct_chg =", value, "pctChg");
            return (Criteria) this;
        }

        public Criteria andPctChgNotEqualTo(Double value) {
            addCriterion("pct_chg <>", value, "pctChg");
            return (Criteria) this;
        }

        public Criteria andPctChgGreaterThan(Double value) {
            addCriterion("pct_chg >", value, "pctChg");
            return (Criteria) this;
        }

        public Criteria andPctChgGreaterThanOrEqualTo(Double value) {
            addCriterion("pct_chg >=", value, "pctChg");
            return (Criteria) this;
        }

        public Criteria andPctChgLessThan(Double value) {
            addCriterion("pct_chg <", value, "pctChg");
            return (Criteria) this;
        }

        public Criteria andPctChgLessThanOrEqualTo(Double value) {
            addCriterion("pct_chg <=", value, "pctChg");
            return (Criteria) this;
        }

        public Criteria andPctChgLike(Double value) {
            addCriterion("pct_chg like", value, "pctChg");
            return (Criteria) this;
        }

        public Criteria andPctChgNotLike(Double value) {
            addCriterion("pct_chg not like", value, "pctChg");
            return (Criteria) this;
        }

        public Criteria andPctChgIn(List<Double> values) {
            addCriterion("pct_chg in", values, "pctChg");
            return (Criteria) this;
        }

        public Criteria andPctChgNotIn(List<Double> values) {
            addCriterion("pct_chg not in", values, "pctChg");
            return (Criteria) this;
        }

        public Criteria andPctChgBetween(Double value1, Double value2) {
            addCriterion("pct_chg between", value1, value2, "pctChg");
            return (Criteria) this;
        }

        public Criteria andPctChgNotBetween(Double value1, Double value2) {
            addCriterion("pct_chg not between", value1, value2, "pctChg");
            return (Criteria) this;
        }
        
			
        public Criteria andVolIsNull() {
            addCriterion("vol is null");
            return (Criteria) this;
        }

        public Criteria andVolIsNotNull() {
            addCriterion("vol is not null");
            return (Criteria) this;
        }

        public Criteria andVolEqualTo(Double value) {
            addCriterion("vol =", value, "vol");
            return (Criteria) this;
        }

        public Criteria andVolNotEqualTo(Double value) {
            addCriterion("vol <>", value, "vol");
            return (Criteria) this;
        }

        public Criteria andVolGreaterThan(Double value) {
            addCriterion("vol >", value, "vol");
            return (Criteria) this;
        }

        public Criteria andVolGreaterThanOrEqualTo(Double value) {
            addCriterion("vol >=", value, "vol");
            return (Criteria) this;
        }

        public Criteria andVolLessThan(Double value) {
            addCriterion("vol <", value, "vol");
            return (Criteria) this;
        }

        public Criteria andVolLessThanOrEqualTo(Double value) {
            addCriterion("vol <=", value, "vol");
            return (Criteria) this;
        }

        public Criteria andVolLike(Double value) {
            addCriterion("vol like", value, "vol");
            return (Criteria) this;
        }

        public Criteria andVolNotLike(Double value) {
            addCriterion("vol not like", value, "vol");
            return (Criteria) this;
        }

        public Criteria andVolIn(List<Double> values) {
            addCriterion("vol in", values, "vol");
            return (Criteria) this;
        }

        public Criteria andVolNotIn(List<Double> values) {
            addCriterion("vol not in", values, "vol");
            return (Criteria) this;
        }

        public Criteria andVolBetween(Double value1, Double value2) {
            addCriterion("vol between", value1, value2, "vol");
            return (Criteria) this;
        }

        public Criteria andVolNotBetween(Double value1, Double value2) {
            addCriterion("vol not between", value1, value2, "vol");
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

        public Criteria andAmountEqualTo(Double value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Double value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Double value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Double value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Double value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLike(Double value) {
            addCriterion("amount like", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotLike(Double value) {
            addCriterion("amount not like", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Double> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Double> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Double value1, Double value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Double value1, Double value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }
        
		
		 public Criteria andLikeQuery(StockDaily record) {
		 	List<String> list= new ArrayList<String>();
		 	List<String> list2= new ArrayList<String>();
        	StringBuffer buffer=new StringBuffer();
        	
        				 
			 if(record.getTsCode()!=null&&StrUtil.isNotEmpty(record.getTsCode().toString())) {
    			 list.add("ifnull(ts_code,'')");
    		 }
    		 
						 
			 if(record.getTradeDate()!=null&&StrUtil.isNotEmpty(record.getTradeDate().toString())) {
    			 list.add("ifnull(trade_date,'')");
    		 }
    		 
						 
			 if(record.getOpen()!=null&&StrUtil.isNotEmpty(record.getOpen().toString())) {
    			 list.add("ifnull(open,'')");
    		 }
    		 
						 
			 if(record.getHigh()!=null&&StrUtil.isNotEmpty(record.getHigh().toString())) {
    			 list.add("ifnull(high,'')");
    		 }
    		 
						 
			 if(record.getLow()!=null&&StrUtil.isNotEmpty(record.getLow().toString())) {
    			 list.add("ifnull(low,'')");
    		 }
    		 
						 
			 if(record.getCls()!=null&&StrUtil.isNotEmpty(record.getCls().toString())) {
    			 list.add("ifnull(cls,'')");
    		 }
    		 
						 
			 if(record.getPreClose()!=null&&StrUtil.isNotEmpty(record.getPreClose().toString())) {
    			 list.add("ifnull(pre_close,'')");
    		 }
    		 
						 
			 if(record.getChg()!=null&&StrUtil.isNotEmpty(record.getChg().toString())) {
    			 list.add("ifnull(chg,'')");
    		 }
    		 
						 
			 if(record.getPctChg()!=null&&StrUtil.isNotEmpty(record.getPctChg().toString())) {
    			 list.add("ifnull(pct_chg,'')");
    		 }
    		 
						 
			 if(record.getVol()!=null&&StrUtil.isNotEmpty(record.getVol().toString())) {
    			 list.add("ifnull(vol,'')");
    		 }
    		 
						 
			 if(record.getAmount()!=null&&StrUtil.isNotEmpty(record.getAmount().toString())) {
    			 list.add("ifnull(amount,'')");
    		 }
    		 
									
			 if(record.getTsCode()!=null&&StrUtil.isNotEmpty(record.getTsCode().toString())) {
    			list2.add("'%"+record.getTsCode()+"%'");
    		 }
        		 
						
			 if(record.getTradeDate()!=null&&StrUtil.isNotEmpty(record.getTradeDate().toString())) {
    			list2.add("'%"+record.getTradeDate()+"%'");
    		 }
        		 
						
			 if(record.getOpen()!=null&&StrUtil.isNotEmpty(record.getOpen().toString())) {
    			list2.add("'%"+record.getOpen()+"%'");
    		 }
        		 
						
			 if(record.getHigh()!=null&&StrUtil.isNotEmpty(record.getHigh().toString())) {
    			list2.add("'%"+record.getHigh()+"%'");
    		 }
        		 
						
			 if(record.getLow()!=null&&StrUtil.isNotEmpty(record.getLow().toString())) {
    			list2.add("'%"+record.getLow()+"%'");
    		 }
        		 
						
			 if(record.getCls()!=null&&StrUtil.isNotEmpty(record.getCls().toString())) {
    			list2.add("'%"+record.getCls()+"%'");
    		 }
        		 
						
			 if(record.getPreClose()!=null&&StrUtil.isNotEmpty(record.getPreClose().toString())) {
    			list2.add("'%"+record.getPreClose()+"%'");
    		 }
        		 
						
			 if(record.getChg()!=null&&StrUtil.isNotEmpty(record.getChg().toString())) {
    			list2.add("'%"+record.getChg()+"%'");
    		 }
        		 
						
			 if(record.getPctChg()!=null&&StrUtil.isNotEmpty(record.getPctChg().toString())) {
    			list2.add("'%"+record.getPctChg()+"%'");
    		 }
        		 
						
			 if(record.getVol()!=null&&StrUtil.isNotEmpty(record.getVol().toString())) {
    			list2.add("'%"+record.getVol()+"%'");
    		 }
        		 
						
			 if(record.getAmount()!=null&&StrUtil.isNotEmpty(record.getAmount().toString())) {
    			list2.add("'%"+record.getAmount()+"%'");
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
        	
        	        	
    		list.add("ifnull(ts_code,'')");
			
			        	
    		list.add("ifnull(trade_date,'')");
			
			        	
    		list.add("ifnull(open,'')");
			
			        	
    		list.add("ifnull(high,'')");
			
			        	
    		list.add("ifnull(low,'')");
			
			        	
    		list.add("ifnull(cls,'')");
			
			        	
    		list.add("ifnull(pre_close,'')");
			
			        	
    		list.add("ifnull(chg,'')");
			
			        	
    		list.add("ifnull(pct_chg,'')");
			
			        	
    		list.add("ifnull(vol,'')");
			
			        	
    		list.add("ifnull(amount,'')");
			
						
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