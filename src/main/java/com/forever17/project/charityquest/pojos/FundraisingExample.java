package com.forever17.project.charityquest.pojos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Example of Fundraising Class
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 27 Feb 2020
 * @since 1.0
 */
public class FundraisingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FundraisingExample() {
        oredCriteria = new ArrayList<>();
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
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
        return new Criteria();
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
            criteria = new ArrayList<>();
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
            addCriterion("fundraising.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("fundraising.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("fundraising.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("fundraising.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("fundraising.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("fundraising.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("fundraising.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("fundraising.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("fundraising.id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("fundraising.id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("fundraising.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("fundraising.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("fundraising.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("fundraising.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCharityIdIsNull() {
            addCriterion("fundraising.charity_id is null");
            return (Criteria) this;
        }

        public Criteria andCharityIdIsNotNull() {
            addCriterion("fundraising.charity_id is not null");
            return (Criteria) this;
        }

        public Criteria andCharityIdEqualTo(String value) {
            addCriterion("fundraising.charity_id =", value, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdNotEqualTo(String value) {
            addCriterion("fundraising.charity_id <>", value, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdGreaterThan(String value) {
            addCriterion("fundraising.charity_id >", value, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdGreaterThanOrEqualTo(String value) {
            addCriterion("fundraising.charity_id >=", value, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdLessThan(String value) {
            addCriterion("fundraising.charity_id <", value, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdLessThanOrEqualTo(String value) {
            addCriterion("fundraising.charity_id <=", value, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdLike(String value) {
            addCriterion("fundraising.charity_id like", value, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdNotLike(String value) {
            addCriterion("fundraising.charity_id not like", value, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdIn(List<String> values) {
            addCriterion("fundraising.charity_id in", values, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdNotIn(List<String> values) {
            addCriterion("fundraising.charity_id not in", values, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdBetween(String value1, String value2) {
            addCriterion("fundraising.charity_id between", value1, value2, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdNotBetween(String value1, String value2) {
            addCriterion("fundraising.charity_id not between", value1, value2, "charityId");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("fundraising.url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("fundraising.url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("fundraising.url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("fundraising.url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("fundraising.url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("fundraising.url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("fundraising.url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("fundraising.url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("fundraising.url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("fundraising.url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("fundraising.url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("fundraising.url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("fundraising.url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("fundraising.url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("fundraising.start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("fundraising.start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(LocalDateTime value) {
            addCriterion("fundraising.start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(LocalDateTime value) {
            addCriterion("fundraising.start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(LocalDateTime value) {
            addCriterion("fundraising.start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("fundraising.start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(LocalDateTime value) {
            addCriterion("fundraising.start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("fundraising.start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<LocalDateTime> values) {
            addCriterion("fundraising.start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<LocalDateTime> values) {
            addCriterion("fundraising.start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("fundraising.start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("fundraising.start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("fundraising.end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("fundraising.end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(LocalDateTime value) {
            addCriterion("fundraising.end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(LocalDateTime value) {
            addCriterion("fundraising.end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(LocalDateTime value) {
            addCriterion("fundraising.end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("fundraising.end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(LocalDateTime value) {
            addCriterion("fundraising.end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("fundraising.end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<LocalDateTime> values) {
            addCriterion("fundraising.end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<LocalDateTime> values) {
            addCriterion("fundraising.end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("fundraising.end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("fundraising.end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andRaiseMoneyIsNull() {
            addCriterion("fundraising.raise_money is null");
            return (Criteria) this;
        }

        public Criteria andRaiseMoneyIsNotNull() {
            addCriterion("fundraising.raise_money is not null");
            return (Criteria) this;
        }

        public Criteria andRaiseMoneyEqualTo(Integer value) {
            addCriterion("fundraising.raise_money =", value, "raiseMoney");
            return (Criteria) this;
        }

        public Criteria andRaiseMoneyNotEqualTo(Integer value) {
            addCriterion("fundraising.raise_money <>", value, "raiseMoney");
            return (Criteria) this;
        }

        public Criteria andRaiseMoneyGreaterThan(Integer value) {
            addCriterion("fundraising.raise_money >", value, "raiseMoney");
            return (Criteria) this;
        }

        public Criteria andRaiseMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("fundraising.raise_money >=", value, "raiseMoney");
            return (Criteria) this;
        }

        public Criteria andRaiseMoneyLessThan(Integer value) {
            addCriterion("fundraising.raise_money <", value, "raiseMoney");
            return (Criteria) this;
        }

        public Criteria andRaiseMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("fundraising.raise_money <=", value, "raiseMoney");
            return (Criteria) this;
        }

        public Criteria andRaiseMoneyIn(List<Integer> values) {
            addCriterion("fundraising.raise_money in", values, "raiseMoney");
            return (Criteria) this;
        }

        public Criteria andRaiseMoneyNotIn(List<Integer> values) {
            addCriterion("fundraising.raise_money not in", values, "raiseMoney");
            return (Criteria) this;
        }

        public Criteria andRaiseMoneyBetween(Integer value1, Integer value2) {
            addCriterion("fundraising.raise_money between", value1, value2, "raiseMoney");
            return (Criteria) this;
        }

        public Criteria andRaiseMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("fundraising.raise_money not between", value1, value2, "raiseMoney");
            return (Criteria) this;
        }

        public Criteria andTargetMoneyIsNull() {
            addCriterion("fundraising.target_money is null");
            return (Criteria) this;
        }

        public Criteria andTargetMoneyIsNotNull() {
            addCriterion("fundraising.target_money is not null");
            return (Criteria) this;
        }

        public Criteria andTargetMoneyEqualTo(Integer value) {
            addCriterion("fundraising.target_money =", value, "targetMoney");
            return (Criteria) this;
        }

        public Criteria andTargetMoneyNotEqualTo(Integer value) {
            addCriterion("fundraising.target_money <>", value, "targetMoney");
            return (Criteria) this;
        }

        public Criteria andTargetMoneyGreaterThan(Integer value) {
            addCriterion("fundraising.target_money >", value, "targetMoney");
            return (Criteria) this;
        }

        public Criteria andTargetMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("fundraising.target_money >=", value, "targetMoney");
            return (Criteria) this;
        }

        public Criteria andTargetMoneyLessThan(Integer value) {
            addCriterion("fundraising.target_money <", value, "targetMoney");
            return (Criteria) this;
        }

        public Criteria andTargetMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("fundraising.target_money <=", value, "targetMoney");
            return (Criteria) this;
        }

        public Criteria andTargetMoneyIn(List<Integer> values) {
            addCriterion("fundraising.target_money in", values, "targetMoney");
            return (Criteria) this;
        }

        public Criteria andTargetMoneyNotIn(List<Integer> values) {
            addCriterion("fundraising.target_money not in", values, "targetMoney");
            return (Criteria) this;
        }

        public Criteria andTargetMoneyBetween(Integer value1, Integer value2) {
            addCriterion("fundraising.target_money between", value1, value2, "targetMoney");
            return (Criteria) this;
        }

        public Criteria andTargetMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("fundraising.target_money not between", value1, value2, "targetMoney");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("fundraising.description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("fundraising.description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("fundraising.description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("fundraising.description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("fundraising.description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("fundraising.description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("fundraising.description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("fundraising.description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("fundraising.description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("fundraising.description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("fundraising.description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("fundraising.description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("fundraising.description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("fundraising.description not between", value1, value2, "description");
            return (Criteria) this;
        }
    }

    /**
     *
     */
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
    }
}