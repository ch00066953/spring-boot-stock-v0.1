package com.ch.pojo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserWebCollectionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserWebCollectionExample() {
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andWebsiteIsNull() {
            addCriterion("website is null");
            return (Criteria) this;
        }

        public Criteria andWebsiteIsNotNull() {
            addCriterion("website is not null");
            return (Criteria) this;
        }

        public Criteria andWebsiteEqualTo(String value) {
            addCriterion("website =", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotEqualTo(String value) {
            addCriterion("website <>", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteGreaterThan(String value) {
            addCriterion("website >", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteGreaterThanOrEqualTo(String value) {
            addCriterion("website >=", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteLessThan(String value) {
            addCriterion("website <", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteLessThanOrEqualTo(String value) {
            addCriterion("website <=", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteLike(String value) {
            addCriterion("website like", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotLike(String value) {
            addCriterion("website not like", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteIn(List<String> values) {
            addCriterion("website in", values, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotIn(List<String> values) {
            addCriterion("website not in", values, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteBetween(String value1, String value2) {
            addCriterion("website between", value1, value2, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotBetween(String value1, String value2) {
            addCriterion("website not between", value1, value2, "website");
            return (Criteria) this;
        }

        public Criteria andMainstieIsNull() {
            addCriterion("mainstie is null");
            return (Criteria) this;
        }

        public Criteria andMainstieIsNotNull() {
            addCriterion("mainstie is not null");
            return (Criteria) this;
        }

        public Criteria andMainstieEqualTo(String value) {
            addCriterion("mainstie =", value, "mainstie");
            return (Criteria) this;
        }

        public Criteria andMainstieNotEqualTo(String value) {
            addCriterion("mainstie <>", value, "mainstie");
            return (Criteria) this;
        }

        public Criteria andMainstieGreaterThan(String value) {
            addCriterion("mainstie >", value, "mainstie");
            return (Criteria) this;
        }

        public Criteria andMainstieGreaterThanOrEqualTo(String value) {
            addCriterion("mainstie >=", value, "mainstie");
            return (Criteria) this;
        }

        public Criteria andMainstieLessThan(String value) {
            addCriterion("mainstie <", value, "mainstie");
            return (Criteria) this;
        }

        public Criteria andMainstieLessThanOrEqualTo(String value) {
            addCriterion("mainstie <=", value, "mainstie");
            return (Criteria) this;
        }

        public Criteria andMainstieLike(String value) {
            addCriterion("mainstie like", value, "mainstie");
            return (Criteria) this;
        }

        public Criteria andMainstieNotLike(String value) {
            addCriterion("mainstie not like", value, "mainstie");
            return (Criteria) this;
        }

        public Criteria andMainstieIn(List<String> values) {
            addCriterion("mainstie in", values, "mainstie");
            return (Criteria) this;
        }

        public Criteria andMainstieNotIn(List<String> values) {
            addCriterion("mainstie not in", values, "mainstie");
            return (Criteria) this;
        }

        public Criteria andMainstieBetween(String value1, String value2) {
            addCriterion("mainstie between", value1, value2, "mainstie");
            return (Criteria) this;
        }

        public Criteria andMainstieNotBetween(String value1, String value2) {
            addCriterion("mainstie not between", value1, value2, "mainstie");
            return (Criteria) this;
        }

        public Criteria andIconsiteIsNull() {
            addCriterion("iconsite is null");
            return (Criteria) this;
        }

        public Criteria andIconsiteIsNotNull() {
            addCriterion("iconsite is not null");
            return (Criteria) this;
        }

        public Criteria andIconsiteEqualTo(String value) {
            addCriterion("iconsite =", value, "iconsite");
            return (Criteria) this;
        }

        public Criteria andIconsiteNotEqualTo(String value) {
            addCriterion("iconsite <>", value, "iconsite");
            return (Criteria) this;
        }

        public Criteria andIconsiteGreaterThan(String value) {
            addCriterion("iconsite >", value, "iconsite");
            return (Criteria) this;
        }

        public Criteria andIconsiteGreaterThanOrEqualTo(String value) {
            addCriterion("iconsite >=", value, "iconsite");
            return (Criteria) this;
        }

        public Criteria andIconsiteLessThan(String value) {
            addCriterion("iconsite <", value, "iconsite");
            return (Criteria) this;
        }

        public Criteria andIconsiteLessThanOrEqualTo(String value) {
            addCriterion("iconsite <=", value, "iconsite");
            return (Criteria) this;
        }

        public Criteria andIconsiteLike(String value) {
            addCriterion("iconsite like", value, "iconsite");
            return (Criteria) this;
        }

        public Criteria andIconsiteNotLike(String value) {
            addCriterion("iconsite not like", value, "iconsite");
            return (Criteria) this;
        }

        public Criteria andIconsiteIn(List<String> values) {
            addCriterion("iconsite in", values, "iconsite");
            return (Criteria) this;
        }

        public Criteria andIconsiteNotIn(List<String> values) {
            addCriterion("iconsite not in", values, "iconsite");
            return (Criteria) this;
        }

        public Criteria andIconsiteBetween(String value1, String value2) {
            addCriterion("iconsite between", value1, value2, "iconsite");
            return (Criteria) this;
        }

        public Criteria andIconsiteNotBetween(String value1, String value2) {
            addCriterion("iconsite not between", value1, value2, "iconsite");
            return (Criteria) this;
        }

        public Criteria andWebtitleIsNull() {
            addCriterion("webtitle is null");
            return (Criteria) this;
        }

        public Criteria andWebtitleIsNotNull() {
            addCriterion("webtitle is not null");
            return (Criteria) this;
        }

        public Criteria andWebtitleEqualTo(String value) {
            addCriterion("webtitle =", value, "webtitle");
            return (Criteria) this;
        }

        public Criteria andWebtitleNotEqualTo(String value) {
            addCriterion("webtitle <>", value, "webtitle");
            return (Criteria) this;
        }

        public Criteria andWebtitleGreaterThan(String value) {
            addCriterion("webtitle >", value, "webtitle");
            return (Criteria) this;
        }

        public Criteria andWebtitleGreaterThanOrEqualTo(String value) {
            addCriterion("webtitle >=", value, "webtitle");
            return (Criteria) this;
        }

        public Criteria andWebtitleLessThan(String value) {
            addCriterion("webtitle <", value, "webtitle");
            return (Criteria) this;
        }

        public Criteria andWebtitleLessThanOrEqualTo(String value) {
            addCriterion("webtitle <=", value, "webtitle");
            return (Criteria) this;
        }

        public Criteria andWebtitleLike(String value) {
            addCriterion("webtitle like", value, "webtitle");
            return (Criteria) this;
        }

        public Criteria andWebtitleNotLike(String value) {
            addCriterion("webtitle not like", value, "webtitle");
            return (Criteria) this;
        }

        public Criteria andWebtitleIn(List<String> values) {
            addCriterion("webtitle in", values, "webtitle");
            return (Criteria) this;
        }

        public Criteria andWebtitleNotIn(List<String> values) {
            addCriterion("webtitle not in", values, "webtitle");
            return (Criteria) this;
        }

        public Criteria andWebtitleBetween(String value1, String value2) {
            addCriterion("webtitle between", value1, value2, "webtitle");
            return (Criteria) this;
        }

        public Criteria andWebtitleNotBetween(String value1, String value2) {
            addCriterion("webtitle not between", value1, value2, "webtitle");
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

        public Criteria andKeywordsIsNull() {
            addCriterion("keywords is null");
            return (Criteria) this;
        }

        public Criteria andKeywordsIsNotNull() {
            addCriterion("keywords is not null");
            return (Criteria) this;
        }

        public Criteria andKeywordsEqualTo(String value) {
            addCriterion("keywords =", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotEqualTo(String value) {
            addCriterion("keywords <>", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThan(String value) {
            addCriterion("keywords >", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThanOrEqualTo(String value) {
            addCriterion("keywords >=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThan(String value) {
            addCriterion("keywords <", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThanOrEqualTo(String value) {
            addCriterion("keywords <=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLike(String value) {
            addCriterion("keywords like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotLike(String value) {
            addCriterion("keywords not like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsIn(List<String> values) {
            addCriterion("keywords in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotIn(List<String> values) {
            addCriterion("keywords not in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsBetween(String value1, String value2) {
            addCriterion("keywords between", value1, value2, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotBetween(String value1, String value2) {
            addCriterion("keywords not between", value1, value2, "keywords");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("userid like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("userid not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("userid not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUsertypeIsNull() {
            addCriterion("usertype is null");
            return (Criteria) this;
        }

        public Criteria andUsertypeIsNotNull() {
            addCriterion("usertype is not null");
            return (Criteria) this;
        }

        public Criteria andUsertypeEqualTo(String value) {
            addCriterion("usertype =", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeNotEqualTo(String value) {
            addCriterion("usertype <>", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeGreaterThan(String value) {
            addCriterion("usertype >", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeGreaterThanOrEqualTo(String value) {
            addCriterion("usertype >=", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeLessThan(String value) {
            addCriterion("usertype <", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeLessThanOrEqualTo(String value) {
            addCriterion("usertype <=", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeLike(String value) {
            addCriterion("usertype like", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeNotLike(String value) {
            addCriterion("usertype not like", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeIn(List<String> values) {
            addCriterion("usertype in", values, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeNotIn(List<String> values) {
            addCriterion("usertype not in", values, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeBetween(String value1, String value2) {
            addCriterion("usertype between", value1, value2, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeNotBetween(String value1, String value2) {
            addCriterion("usertype not between", value1, value2, "usertype");
            return (Criteria) this;
        }

        public Criteria andSystemtypeIsNull() {
            addCriterion("systemtype is null");
            return (Criteria) this;
        }

        public Criteria andSystemtypeIsNotNull() {
            addCriterion("systemtype is not null");
            return (Criteria) this;
        }

        public Criteria andSystemtypeEqualTo(String value) {
            addCriterion("systemtype =", value, "systemtype");
            return (Criteria) this;
        }

        public Criteria andSystemtypeNotEqualTo(String value) {
            addCriterion("systemtype <>", value, "systemtype");
            return (Criteria) this;
        }

        public Criteria andSystemtypeGreaterThan(String value) {
            addCriterion("systemtype >", value, "systemtype");
            return (Criteria) this;
        }

        public Criteria andSystemtypeGreaterThanOrEqualTo(String value) {
            addCriterion("systemtype >=", value, "systemtype");
            return (Criteria) this;
        }

        public Criteria andSystemtypeLessThan(String value) {
            addCriterion("systemtype <", value, "systemtype");
            return (Criteria) this;
        }

        public Criteria andSystemtypeLessThanOrEqualTo(String value) {
            addCriterion("systemtype <=", value, "systemtype");
            return (Criteria) this;
        }

        public Criteria andSystemtypeLike(String value) {
            addCriterion("systemtype like", value, "systemtype");
            return (Criteria) this;
        }

        public Criteria andSystemtypeNotLike(String value) {
            addCriterion("systemtype not like", value, "systemtype");
            return (Criteria) this;
        }

        public Criteria andSystemtypeIn(List<String> values) {
            addCriterion("systemtype in", values, "systemtype");
            return (Criteria) this;
        }

        public Criteria andSystemtypeNotIn(List<String> values) {
            addCriterion("systemtype not in", values, "systemtype");
            return (Criteria) this;
        }

        public Criteria andSystemtypeBetween(String value1, String value2) {
            addCriterion("systemtype between", value1, value2, "systemtype");
            return (Criteria) this;
        }

        public Criteria andSystemtypeNotBetween(String value1, String value2) {
            addCriterion("systemtype not between", value1, value2, "systemtype");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
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