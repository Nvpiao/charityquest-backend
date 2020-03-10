package com.forever17.project.charityquest.pojos;

import java.util.ArrayList;
import java.util.List;

/**
 * Example of CharityUser Class
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 27 Feb 2020
 * @since 1.0
 */
public class CharityUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CharityUserExample() {
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
            addCriterion("charity_user.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("charity_user.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("charity_user.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("charity_user.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("charity_user.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("charity_user.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("charity_user.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("charity_user.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("charity_user.id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("charity_user.id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("charity_user.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("charity_user.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("charity_user.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("charity_user.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("charity_user.email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("charity_user.email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("charity_user.email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("charity_user.email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("charity_user.email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("charity_user.email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("charity_user.email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("charity_user.email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("charity_user.email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("charity_user.email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("charity_user.email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("charity_user.email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("charity_user.email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("charity_user.email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("charity_user.`password` is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("charity_user.`password` is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("charity_user.`password` =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("charity_user.`password` <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("charity_user.`password` >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("charity_user.`password` >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("charity_user.`password` <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("charity_user.`password` <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("charity_user.`password` like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("charity_user.`password` not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("charity_user.`password` in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("charity_user.`password` not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("charity_user.`password` between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("charity_user.`password` not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("charity_user.`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("charity_user.`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("charity_user.`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("charity_user.`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("charity_user.`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("charity_user.`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("charity_user.`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("charity_user.`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("charity_user.`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("charity_user.`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("charity_user.`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("charity_user.`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("charity_user.`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("charity_user.`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("charity_user.`number` is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("charity_user.`number` is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(String value) {
            addCriterion("charity_user.`number` =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(String value) {
            addCriterion("charity_user.`number` <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(String value) {
            addCriterion("charity_user.`number` >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(String value) {
            addCriterion("charity_user.`number` >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(String value) {
            addCriterion("charity_user.`number` <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(String value) {
            addCriterion("charity_user.`number` <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLike(String value) {
            addCriterion("charity_user.`number` like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotLike(String value) {
            addCriterion("charity_user.`number` not like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<String> values) {
            addCriterion("charity_user.`number` in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<String> values) {
            addCriterion("charity_user.`number` not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(String value1, String value2) {
            addCriterion("charity_user.`number` between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(String value1, String value2) {
            addCriterion("charity_user.`number` not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNull() {
            addCriterion("charity_user.photo is null");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNotNull() {
            addCriterion("charity_user.photo is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoEqualTo(String value) {
            addCriterion("charity_user.photo =", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotEqualTo(String value) {
            addCriterion("charity_user.photo <>", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThan(String value) {
            addCriterion("charity_user.photo >", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("charity_user.photo >=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThan(String value) {
            addCriterion("charity_user.photo <", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThanOrEqualTo(String value) {
            addCriterion("charity_user.photo <=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLike(String value) {
            addCriterion("charity_user.photo like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotLike(String value) {
            addCriterion("charity_user.photo not like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoIn(List<String> values) {
            addCriterion("charity_user.photo in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotIn(List<String> values) {
            addCriterion("charity_user.photo not in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoBetween(String value1, String value2) {
            addCriterion("charity_user.photo between", value1, value2, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotBetween(String value1, String value2) {
            addCriterion("charity_user.photo not between", value1, value2, "photo");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("charity_user.description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("charity_user.description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("charity_user.description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("charity_user.description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("charity_user.description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("charity_user.description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("charity_user.description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("charity_user.description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("charity_user.description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("charity_user.description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("charity_user.description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("charity_user.description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("charity_user.description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("charity_user.description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andCasePhotoIsNull() {
            addCriterion("charity_user.case_photo is null");
            return (Criteria) this;
        }

        public Criteria andCasePhotoIsNotNull() {
            addCriterion("charity_user.case_photo is not null");
            return (Criteria) this;
        }

        public Criteria andCasePhotoEqualTo(String value) {
            addCriterion("charity_user.case_photo =", value, "casePhoto");
            return (Criteria) this;
        }

        public Criteria andCasePhotoNotEqualTo(String value) {
            addCriterion("charity_user.case_photo <>", value, "casePhoto");
            return (Criteria) this;
        }

        public Criteria andCasePhotoGreaterThan(String value) {
            addCriterion("charity_user.case_photo >", value, "casePhoto");
            return (Criteria) this;
        }

        public Criteria andCasePhotoGreaterThanOrEqualTo(String value) {
            addCriterion("charity_user.case_photo >=", value, "casePhoto");
            return (Criteria) this;
        }

        public Criteria andCasePhotoLessThan(String value) {
            addCriterion("charity_user.case_photo <", value, "casePhoto");
            return (Criteria) this;
        }

        public Criteria andCasePhotoLessThanOrEqualTo(String value) {
            addCriterion("charity_user.case_photo <=", value, "casePhoto");
            return (Criteria) this;
        }

        public Criteria andCasePhotoLike(String value) {
            addCriterion("charity_user.case_photo like", value, "casePhoto");
            return (Criteria) this;
        }

        public Criteria andCasePhotoNotLike(String value) {
            addCriterion("charity_user.case_photo not like", value, "casePhoto");
            return (Criteria) this;
        }

        public Criteria andCasePhotoIn(List<String> values) {
            addCriterion("charity_user.case_photo in", values, "casePhoto");
            return (Criteria) this;
        }

        public Criteria andCasePhotoNotIn(List<String> values) {
            addCriterion("charity_user.case_photo not in", values, "casePhoto");
            return (Criteria) this;
        }

        public Criteria andCasePhotoBetween(String value1, String value2) {
            addCriterion("charity_user.case_photo between", value1, value2, "casePhoto");
            return (Criteria) this;
        }

        public Criteria andCasePhotoNotBetween(String value1, String value2) {
            addCriterion("charity_user.case_photo not between", value1, value2, "casePhoto");
            return (Criteria) this;
        }

        public Criteria andCaseVideoIsNull() {
            addCriterion("charity_user.case_video is null");
            return (Criteria) this;
        }

        public Criteria andCaseVideoIsNotNull() {
            addCriterion("charity_user.case_video is not null");
            return (Criteria) this;
        }

        public Criteria andCaseVideoEqualTo(String value) {
            addCriterion("charity_user.case_video =", value, "caseVideo");
            return (Criteria) this;
        }

        public Criteria andCaseVideoNotEqualTo(String value) {
            addCriterion("charity_user.case_video <>", value, "caseVideo");
            return (Criteria) this;
        }

        public Criteria andCaseVideoGreaterThan(String value) {
            addCriterion("charity_user.case_video >", value, "caseVideo");
            return (Criteria) this;
        }

        public Criteria andCaseVideoGreaterThanOrEqualTo(String value) {
            addCriterion("charity_user.case_video >=", value, "caseVideo");
            return (Criteria) this;
        }

        public Criteria andCaseVideoLessThan(String value) {
            addCriterion("charity_user.case_video <", value, "caseVideo");
            return (Criteria) this;
        }

        public Criteria andCaseVideoLessThanOrEqualTo(String value) {
            addCriterion("charity_user.case_video <=", value, "caseVideo");
            return (Criteria) this;
        }

        public Criteria andCaseVideoLike(String value) {
            addCriterion("charity_user.case_video like", value, "caseVideo");
            return (Criteria) this;
        }

        public Criteria andCaseVideoNotLike(String value) {
            addCriterion("charity_user.case_video not like", value, "caseVideo");
            return (Criteria) this;
        }

        public Criteria andCaseVideoIn(List<String> values) {
            addCriterion("charity_user.case_video in", values, "caseVideo");
            return (Criteria) this;
        }

        public Criteria andCaseVideoNotIn(List<String> values) {
            addCriterion("charity_user.case_video not in", values, "caseVideo");
            return (Criteria) this;
        }

        public Criteria andCaseVideoBetween(String value1, String value2) {
            addCriterion("charity_user.case_video between", value1, value2, "caseVideo");
            return (Criteria) this;
        }

        public Criteria andCaseVideoNotBetween(String value1, String value2) {
            addCriterion("charity_user.case_video not between", value1, value2, "caseVideo");
            return (Criteria) this;
        }

        public Criteria andCaseDescIsNull() {
            addCriterion("charity_user.case_desc is null");
            return (Criteria) this;
        }

        public Criteria andCaseDescIsNotNull() {
            addCriterion("charity_user.case_desc is not null");
            return (Criteria) this;
        }

        public Criteria andCaseDescEqualTo(String value) {
            addCriterion("charity_user.case_desc =", value, "caseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseDescNotEqualTo(String value) {
            addCriterion("charity_user.case_desc <>", value, "caseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseDescGreaterThan(String value) {
            addCriterion("charity_user.case_desc >", value, "caseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseDescGreaterThanOrEqualTo(String value) {
            addCriterion("charity_user.case_desc >=", value, "caseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseDescLessThan(String value) {
            addCriterion("charity_user.case_desc <", value, "caseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseDescLessThanOrEqualTo(String value) {
            addCriterion("charity_user.case_desc <=", value, "caseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseDescLike(String value) {
            addCriterion("charity_user.case_desc like", value, "caseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseDescNotLike(String value) {
            addCriterion("charity_user.case_desc not like", value, "caseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseDescIn(List<String> values) {
            addCriterion("charity_user.case_desc in", values, "caseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseDescNotIn(List<String> values) {
            addCriterion("charity_user.case_desc not in", values, "caseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseDescBetween(String value1, String value2) {
            addCriterion("charity_user.case_desc between", value1, value2, "caseDesc");
            return (Criteria) this;
        }

        public Criteria andCaseDescNotBetween(String value1, String value2) {
            addCriterion("charity_user.case_desc not between", value1, value2, "caseDesc");
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