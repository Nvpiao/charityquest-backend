package com.forever17.project.charityquest.pojos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Example of Donation Class
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 27 Feb 2020
 * @since 1.0
 */
public class DonationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DonationExample() {
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
            addCriterion("donation.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("donation.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("donation.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("donation.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("donation.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("donation.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("donation.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("donation.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("donation.id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("donation.id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("donation.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("donation.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("donation.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("donation.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPublicIdIsNull() {
            addCriterion("donation.public_id is null");
            return (Criteria) this;
        }

        public Criteria andPublicIdIsNotNull() {
            addCriterion("donation.public_id is not null");
            return (Criteria) this;
        }

        public Criteria andPublicIdEqualTo(String value) {
            addCriterion("donation.public_id =", value, "publicId");
            return (Criteria) this;
        }

        public Criteria andPublicIdNotEqualTo(String value) {
            addCriterion("donation.public_id <>", value, "publicId");
            return (Criteria) this;
        }

        public Criteria andPublicIdGreaterThan(String value) {
            addCriterion("donation.public_id >", value, "publicId");
            return (Criteria) this;
        }

        public Criteria andPublicIdGreaterThanOrEqualTo(String value) {
            addCriterion("donation.public_id >=", value, "publicId");
            return (Criteria) this;
        }

        public Criteria andPublicIdLessThan(String value) {
            addCriterion("donation.public_id <", value, "publicId");
            return (Criteria) this;
        }

        public Criteria andPublicIdLessThanOrEqualTo(String value) {
            addCriterion("donation.public_id <=", value, "publicId");
            return (Criteria) this;
        }

        public Criteria andPublicIdLike(String value) {
            addCriterion("donation.public_id like", value, "publicId");
            return (Criteria) this;
        }

        public Criteria andPublicIdNotLike(String value) {
            addCriterion("donation.public_id not like", value, "publicId");
            return (Criteria) this;
        }

        public Criteria andPublicIdIn(List<String> values) {
            addCriterion("donation.public_id in", values, "publicId");
            return (Criteria) this;
        }

        public Criteria andPublicIdNotIn(List<String> values) {
            addCriterion("donation.public_id not in", values, "publicId");
            return (Criteria) this;
        }

        public Criteria andPublicIdBetween(String value1, String value2) {
            addCriterion("donation.public_id between", value1, value2, "publicId");
            return (Criteria) this;
        }

        public Criteria andPublicIdNotBetween(String value1, String value2) {
            addCriterion("donation.public_id not between", value1, value2, "publicId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("donation.`type` is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("donation.`type` is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("donation.`type` =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("donation.`type` <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("donation.`type` >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("donation.`type` >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("donation.`type` <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("donation.`type` <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("donation.`type` like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("donation.`type` not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("donation.`type` in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("donation.`type` not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("donation.`type` between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("donation.`type` not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCharityIdIsNull() {
            addCriterion("donation.charity_id is null");
            return (Criteria) this;
        }

        public Criteria andCharityIdIsNotNull() {
            addCriterion("donation.charity_id is not null");
            return (Criteria) this;
        }

        public Criteria andCharityIdEqualTo(String value) {
            addCriterion("donation.charity_id =", value, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdNotEqualTo(String value) {
            addCriterion("donation.charity_id <>", value, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdGreaterThan(String value) {
            addCriterion("donation.charity_id >", value, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdGreaterThanOrEqualTo(String value) {
            addCriterion("donation.charity_id >=", value, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdLessThan(String value) {
            addCriterion("donation.charity_id <", value, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdLessThanOrEqualTo(String value) {
            addCriterion("donation.charity_id <=", value, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdLike(String value) {
            addCriterion("donation.charity_id like", value, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdNotLike(String value) {
            addCriterion("donation.charity_id not like", value, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdIn(List<String> values) {
            addCriterion("donation.charity_id in", values, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdNotIn(List<String> values) {
            addCriterion("donation.charity_id not in", values, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdBetween(String value1, String value2) {
            addCriterion("donation.charity_id between", value1, value2, "charityId");
            return (Criteria) this;
        }

        public Criteria andCharityIdNotBetween(String value1, String value2) {
            addCriterion("donation.charity_id not between", value1, value2, "charityId");
            return (Criteria) this;
        }

        public Criteria andFundraisingIdIsNull() {
            addCriterion("donation.fundraising_id is null");
            return (Criteria) this;
        }

        public Criteria andFundraisingIdIsNotNull() {
            addCriterion("donation.fundraising_id is not null");
            return (Criteria) this;
        }

        public Criteria andFundraisingIdEqualTo(String value) {
            addCriterion("donation.fundraising_id =", value, "fundraisingId");
            return (Criteria) this;
        }

        public Criteria andFundraisingIdNotEqualTo(String value) {
            addCriterion("donation.fundraising_id <>", value, "fundraisingId");
            return (Criteria) this;
        }

        public Criteria andFundraisingIdGreaterThan(String value) {
            addCriterion("donation.fundraising_id >", value, "fundraisingId");
            return (Criteria) this;
        }

        public Criteria andFundraisingIdGreaterThanOrEqualTo(String value) {
            addCriterion("donation.fundraising_id >=", value, "fundraisingId");
            return (Criteria) this;
        }

        public Criteria andFundraisingIdLessThan(String value) {
            addCriterion("donation.fundraising_id <", value, "fundraisingId");
            return (Criteria) this;
        }

        public Criteria andFundraisingIdLessThanOrEqualTo(String value) {
            addCriterion("donation.fundraising_id <=", value, "fundraisingId");
            return (Criteria) this;
        }

        public Criteria andFundraisingIdLike(String value) {
            addCriterion("donation.fundraising_id like", value, "fundraisingId");
            return (Criteria) this;
        }

        public Criteria andFundraisingIdNotLike(String value) {
            addCriterion("donation.fundraising_id not like", value, "fundraisingId");
            return (Criteria) this;
        }

        public Criteria andFundraisingIdIn(List<String> values) {
            addCriterion("donation.fundraising_id in", values, "fundraisingId");
            return (Criteria) this;
        }

        public Criteria andFundraisingIdNotIn(List<String> values) {
            addCriterion("donation.fundraising_id not in", values, "fundraisingId");
            return (Criteria) this;
        }

        public Criteria andFundraisingIdBetween(String value1, String value2) {
            addCriterion("donation.fundraising_id between", value1, value2, "fundraisingId");
            return (Criteria) this;
        }

        public Criteria andFundraisingIdNotBetween(String value1, String value2) {
            addCriterion("donation.fundraising_id not between", value1, value2, "fundraisingId");
            return (Criteria) this;
        }

        public Criteria andDonateTypeIsNull() {
            addCriterion("donation.donate_type is null");
            return (Criteria) this;
        }

        public Criteria andDonateTypeIsNotNull() {
            addCriterion("donation.donate_type is not null");
            return (Criteria) this;
        }

        public Criteria andDonateTypeEqualTo(String value) {
            addCriterion("donation.donate_type =", value, "donateType");
            return (Criteria) this;
        }

        public Criteria andDonateTypeNotEqualTo(String value) {
            addCriterion("donation.donate_type <>", value, "donateType");
            return (Criteria) this;
        }

        public Criteria andDonateTypeGreaterThan(String value) {
            addCriterion("donation.donate_type >", value, "donateType");
            return (Criteria) this;
        }

        public Criteria andDonateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("donation.donate_type >=", value, "donateType");
            return (Criteria) this;
        }

        public Criteria andDonateTypeLessThan(String value) {
            addCriterion("donation.donate_type <", value, "donateType");
            return (Criteria) this;
        }

        public Criteria andDonateTypeLessThanOrEqualTo(String value) {
            addCriterion("donation.donate_type <=", value, "donateType");
            return (Criteria) this;
        }

        public Criteria andDonateTypeLike(String value) {
            addCriterion("donation.donate_type like", value, "donateType");
            return (Criteria) this;
        }

        public Criteria andDonateTypeNotLike(String value) {
            addCriterion("donation.donate_type not like", value, "donateType");
            return (Criteria) this;
        }

        public Criteria andDonateTypeIn(List<String> values) {
            addCriterion("donation.donate_type in", values, "donateType");
            return (Criteria) this;
        }

        public Criteria andDonateTypeNotIn(List<String> values) {
            addCriterion("donation.donate_type not in", values, "donateType");
            return (Criteria) this;
        }

        public Criteria andDonateTypeBetween(String value1, String value2) {
            addCriterion("donation.donate_type between", value1, value2, "donateType");
            return (Criteria) this;
        }

        public Criteria andDonateTypeNotBetween(String value1, String value2) {
            addCriterion("donation.donate_type not between", value1, value2, "donateType");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("donation.money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("donation.money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(Integer value) {
            addCriterion("donation.money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(Integer value) {
            addCriterion("donation.money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(Integer value) {
            addCriterion("donation.money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("donation.money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(Integer value) {
            addCriterion("donation.money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("donation.money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<Integer> values) {
            addCriterion("donation.money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<Integer> values) {
            addCriterion("donation.money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(Integer value1, Integer value2) {
            addCriterion("donation.money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("donation.money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("donation.`time` is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("donation.`time` is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(LocalDateTime value) {
            addCriterion("donation.`time` =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(LocalDateTime value) {
            addCriterion("donation.`time` <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(LocalDateTime value) {
            addCriterion("donation.`time` >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("donation.`time` >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(LocalDateTime value) {
            addCriterion("donation.`time` <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("donation.`time` <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<LocalDateTime> values) {
            addCriterion("donation.`time` in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<LocalDateTime> values) {
            addCriterion("donation.`time` not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("donation.`time` between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("donation.`time` not between", value1, value2, "time");
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