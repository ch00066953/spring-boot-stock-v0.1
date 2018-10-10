package com.ch.pojo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StockInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StockInfoExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCompabbreIsNull() {
            addCriterion("CompAbbre is null");
            return (Criteria) this;
        }

        public Criteria andCompabbreIsNotNull() {
            addCriterion("CompAbbre is not null");
            return (Criteria) this;
        }

        public Criteria andCompabbreEqualTo(String value) {
            addCriterion("CompAbbre =", value, "compabbre");
            return (Criteria) this;
        }

        public Criteria andCompabbreNotEqualTo(String value) {
            addCriterion("CompAbbre <>", value, "compabbre");
            return (Criteria) this;
        }

        public Criteria andCompabbreGreaterThan(String value) {
            addCriterion("CompAbbre >", value, "compabbre");
            return (Criteria) this;
        }

        public Criteria andCompabbreGreaterThanOrEqualTo(String value) {
            addCriterion("CompAbbre >=", value, "compabbre");
            return (Criteria) this;
        }

        public Criteria andCompabbreLessThan(String value) {
            addCriterion("CompAbbre <", value, "compabbre");
            return (Criteria) this;
        }

        public Criteria andCompabbreLessThanOrEqualTo(String value) {
            addCriterion("CompAbbre <=", value, "compabbre");
            return (Criteria) this;
        }

        public Criteria andCompabbreLike(String value) {
            addCriterion("CompAbbre like", value, "compabbre");
            return (Criteria) this;
        }

        public Criteria andCompabbreNotLike(String value) {
            addCriterion("CompAbbre not like", value, "compabbre");
            return (Criteria) this;
        }

        public Criteria andCompabbreIn(List<String> values) {
            addCriterion("CompAbbre in", values, "compabbre");
            return (Criteria) this;
        }

        public Criteria andCompabbreNotIn(List<String> values) {
            addCriterion("CompAbbre not in", values, "compabbre");
            return (Criteria) this;
        }

        public Criteria andCompabbreBetween(String value1, String value2) {
            addCriterion("CompAbbre between", value1, value2, "compabbre");
            return (Criteria) this;
        }

        public Criteria andCompabbreNotBetween(String value1, String value2) {
            addCriterion("CompAbbre not between", value1, value2, "compabbre");
            return (Criteria) this;
        }

        public Criteria andCompnameIsNull() {
            addCriterion("CompName is null");
            return (Criteria) this;
        }

        public Criteria andCompnameIsNotNull() {
            addCriterion("CompName is not null");
            return (Criteria) this;
        }

        public Criteria andCompnameEqualTo(String value) {
            addCriterion("CompName =", value, "compname");
            return (Criteria) this;
        }

        public Criteria andCompnameNotEqualTo(String value) {
            addCriterion("CompName <>", value, "compname");
            return (Criteria) this;
        }

        public Criteria andCompnameGreaterThan(String value) {
            addCriterion("CompName >", value, "compname");
            return (Criteria) this;
        }

        public Criteria andCompnameGreaterThanOrEqualTo(String value) {
            addCriterion("CompName >=", value, "compname");
            return (Criteria) this;
        }

        public Criteria andCompnameLessThan(String value) {
            addCriterion("CompName <", value, "compname");
            return (Criteria) this;
        }

        public Criteria andCompnameLessThanOrEqualTo(String value) {
            addCriterion("CompName <=", value, "compname");
            return (Criteria) this;
        }

        public Criteria andCompnameLike(String value) {
            addCriterion("CompName like", value, "compname");
            return (Criteria) this;
        }

        public Criteria andCompnameNotLike(String value) {
            addCriterion("CompName not like", value, "compname");
            return (Criteria) this;
        }

        public Criteria andCompnameIn(List<String> values) {
            addCriterion("CompName in", values, "compname");
            return (Criteria) this;
        }

        public Criteria andCompnameNotIn(List<String> values) {
            addCriterion("CompName not in", values, "compname");
            return (Criteria) this;
        }

        public Criteria andCompnameBetween(String value1, String value2) {
            addCriterion("CompName between", value1, value2, "compname");
            return (Criteria) this;
        }

        public Criteria andCompnameNotBetween(String value1, String value2) {
            addCriterion("CompName not between", value1, value2, "compname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameIsNull() {
            addCriterion("EnglishName is null");
            return (Criteria) this;
        }

        public Criteria andEnglishnameIsNotNull() {
            addCriterion("EnglishName is not null");
            return (Criteria) this;
        }

        public Criteria andEnglishnameEqualTo(String value) {
            addCriterion("EnglishName =", value, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameNotEqualTo(String value) {
            addCriterion("EnglishName <>", value, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameGreaterThan(String value) {
            addCriterion("EnglishName >", value, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameGreaterThanOrEqualTo(String value) {
            addCriterion("EnglishName >=", value, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameLessThan(String value) {
            addCriterion("EnglishName <", value, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameLessThanOrEqualTo(String value) {
            addCriterion("EnglishName <=", value, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameLike(String value) {
            addCriterion("EnglishName like", value, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameNotLike(String value) {
            addCriterion("EnglishName not like", value, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameIn(List<String> values) {
            addCriterion("EnglishName in", values, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameNotIn(List<String> values) {
            addCriterion("EnglishName not in", values, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameBetween(String value1, String value2) {
            addCriterion("EnglishName between", value1, value2, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameNotBetween(String value1, String value2) {
            addCriterion("EnglishName not between", value1, value2, "englishname");
            return (Criteria) this;
        }

        public Criteria andRegaddIsNull() {
            addCriterion("RegAdd is null");
            return (Criteria) this;
        }

        public Criteria andRegaddIsNotNull() {
            addCriterion("RegAdd is not null");
            return (Criteria) this;
        }

        public Criteria andRegaddEqualTo(String value) {
            addCriterion("RegAdd =", value, "regadd");
            return (Criteria) this;
        }

        public Criteria andRegaddNotEqualTo(String value) {
            addCriterion("RegAdd <>", value, "regadd");
            return (Criteria) this;
        }

        public Criteria andRegaddGreaterThan(String value) {
            addCriterion("RegAdd >", value, "regadd");
            return (Criteria) this;
        }

        public Criteria andRegaddGreaterThanOrEqualTo(String value) {
            addCriterion("RegAdd >=", value, "regadd");
            return (Criteria) this;
        }

        public Criteria andRegaddLessThan(String value) {
            addCriterion("RegAdd <", value, "regadd");
            return (Criteria) this;
        }

        public Criteria andRegaddLessThanOrEqualTo(String value) {
            addCriterion("RegAdd <=", value, "regadd");
            return (Criteria) this;
        }

        public Criteria andRegaddLike(String value) {
            addCriterion("RegAdd like", value, "regadd");
            return (Criteria) this;
        }

        public Criteria andRegaddNotLike(String value) {
            addCriterion("RegAdd not like", value, "regadd");
            return (Criteria) this;
        }

        public Criteria andRegaddIn(List<String> values) {
            addCriterion("RegAdd in", values, "regadd");
            return (Criteria) this;
        }

        public Criteria andRegaddNotIn(List<String> values) {
            addCriterion("RegAdd not in", values, "regadd");
            return (Criteria) this;
        }

        public Criteria andRegaddBetween(String value1, String value2) {
            addCriterion("RegAdd between", value1, value2, "regadd");
            return (Criteria) this;
        }

        public Criteria andRegaddNotBetween(String value1, String value2) {
            addCriterion("RegAdd not between", value1, value2, "regadd");
            return (Criteria) this;
        }

        public Criteria andSharescodeIsNull() {
            addCriterion("SharesCode is null");
            return (Criteria) this;
        }

        public Criteria andSharescodeIsNotNull() {
            addCriterion("SharesCode is not null");
            return (Criteria) this;
        }

        public Criteria andSharescodeEqualTo(String value) {
            addCriterion("SharesCode =", value, "sharescode");
            return (Criteria) this;
        }

        public Criteria andSharescodeNotEqualTo(String value) {
            addCriterion("SharesCode <>", value, "sharescode");
            return (Criteria) this;
        }

        public Criteria andSharescodeGreaterThan(String value) {
            addCriterion("SharesCode >", value, "sharescode");
            return (Criteria) this;
        }

        public Criteria andSharescodeGreaterThanOrEqualTo(String value) {
            addCriterion("SharesCode >=", value, "sharescode");
            return (Criteria) this;
        }

        public Criteria andSharescodeLessThan(String value) {
            addCriterion("SharesCode <", value, "sharescode");
            return (Criteria) this;
        }

        public Criteria andSharescodeLessThanOrEqualTo(String value) {
            addCriterion("SharesCode <=", value, "sharescode");
            return (Criteria) this;
        }

        public Criteria andSharescodeLike(String value) {
            addCriterion("SharesCode like", value, "sharescode");
            return (Criteria) this;
        }

        public Criteria andSharescodeNotLike(String value) {
            addCriterion("SharesCode not like", value, "sharescode");
            return (Criteria) this;
        }

        public Criteria andSharescodeIn(List<String> values) {
            addCriterion("SharesCode in", values, "sharescode");
            return (Criteria) this;
        }

        public Criteria andSharescodeNotIn(List<String> values) {
            addCriterion("SharesCode not in", values, "sharescode");
            return (Criteria) this;
        }

        public Criteria andSharescodeBetween(String value1, String value2) {
            addCriterion("SharesCode between", value1, value2, "sharescode");
            return (Criteria) this;
        }

        public Criteria andSharescodeNotBetween(String value1, String value2) {
            addCriterion("SharesCode not between", value1, value2, "sharescode");
            return (Criteria) this;
        }

        public Criteria andSharesaddreIsNull() {
            addCriterion("SharesAddre is null");
            return (Criteria) this;
        }

        public Criteria andSharesaddreIsNotNull() {
            addCriterion("SharesAddre is not null");
            return (Criteria) this;
        }

        public Criteria andSharesaddreEqualTo(String value) {
            addCriterion("SharesAddre =", value, "sharesaddre");
            return (Criteria) this;
        }

        public Criteria andSharesaddreNotEqualTo(String value) {
            addCriterion("SharesAddre <>", value, "sharesaddre");
            return (Criteria) this;
        }

        public Criteria andSharesaddreGreaterThan(String value) {
            addCriterion("SharesAddre >", value, "sharesaddre");
            return (Criteria) this;
        }

        public Criteria andSharesaddreGreaterThanOrEqualTo(String value) {
            addCriterion("SharesAddre >=", value, "sharesaddre");
            return (Criteria) this;
        }

        public Criteria andSharesaddreLessThan(String value) {
            addCriterion("SharesAddre <", value, "sharesaddre");
            return (Criteria) this;
        }

        public Criteria andSharesaddreLessThanOrEqualTo(String value) {
            addCriterion("SharesAddre <=", value, "sharesaddre");
            return (Criteria) this;
        }

        public Criteria andSharesaddreLike(String value) {
            addCriterion("SharesAddre like", value, "sharesaddre");
            return (Criteria) this;
        }

        public Criteria andSharesaddreNotLike(String value) {
            addCriterion("SharesAddre not like", value, "sharesaddre");
            return (Criteria) this;
        }

        public Criteria andSharesaddreIn(List<String> values) {
            addCriterion("SharesAddre in", values, "sharesaddre");
            return (Criteria) this;
        }

        public Criteria andSharesaddreNotIn(List<String> values) {
            addCriterion("SharesAddre not in", values, "sharesaddre");
            return (Criteria) this;
        }

        public Criteria andSharesaddreBetween(String value1, String value2) {
            addCriterion("SharesAddre between", value1, value2, "sharesaddre");
            return (Criteria) this;
        }

        public Criteria andSharesaddreNotBetween(String value1, String value2) {
            addCriterion("SharesAddre not between", value1, value2, "sharesaddre");
            return (Criteria) this;
        }

        public Criteria andTimemarketIsNull() {
            addCriterion("TimeMarket is null");
            return (Criteria) this;
        }

        public Criteria andTimemarketIsNotNull() {
            addCriterion("TimeMarket is not null");
            return (Criteria) this;
        }

        public Criteria andTimemarketEqualTo(String value) {
            addCriterion("TimeMarket =", value, "timemarket");
            return (Criteria) this;
        }

        public Criteria andTimemarketNotEqualTo(String value) {
            addCriterion("TimeMarket <>", value, "timemarket");
            return (Criteria) this;
        }

        public Criteria andTimemarketGreaterThan(String value) {
            addCriterion("TimeMarket >", value, "timemarket");
            return (Criteria) this;
        }

        public Criteria andTimemarketGreaterThanOrEqualTo(String value) {
            addCriterion("TimeMarket >=", value, "timemarket");
            return (Criteria) this;
        }

        public Criteria andTimemarketLessThan(String value) {
            addCriterion("TimeMarket <", value, "timemarket");
            return (Criteria) this;
        }

        public Criteria andTimemarketLessThanOrEqualTo(String value) {
            addCriterion("TimeMarket <=", value, "timemarket");
            return (Criteria) this;
        }

        public Criteria andTimemarketLike(String value) {
            addCriterion("TimeMarket like", value, "timemarket");
            return (Criteria) this;
        }

        public Criteria andTimemarketNotLike(String value) {
            addCriterion("TimeMarket not like", value, "timemarket");
            return (Criteria) this;
        }

        public Criteria andTimemarketIn(List<String> values) {
            addCriterion("TimeMarket in", values, "timemarket");
            return (Criteria) this;
        }

        public Criteria andTimemarketNotIn(List<String> values) {
            addCriterion("TimeMarket not in", values, "timemarket");
            return (Criteria) this;
        }

        public Criteria andTimemarketBetween(String value1, String value2) {
            addCriterion("TimeMarket between", value1, value2, "timemarket");
            return (Criteria) this;
        }

        public Criteria andTimemarketNotBetween(String value1, String value2) {
            addCriterion("TimeMarket not between", value1, value2, "timemarket");
            return (Criteria) this;
        }

        public Criteria andTotalequityIsNull() {
            addCriterion("TotalEquity is null");
            return (Criteria) this;
        }

        public Criteria andTotalequityIsNotNull() {
            addCriterion("TotalEquity is not null");
            return (Criteria) this;
        }

        public Criteria andTotalequityEqualTo(String value) {
            addCriterion("TotalEquity =", value, "totalequity");
            return (Criteria) this;
        }

        public Criteria andTotalequityNotEqualTo(String value) {
            addCriterion("TotalEquity <>", value, "totalequity");
            return (Criteria) this;
        }

        public Criteria andTotalequityGreaterThan(String value) {
            addCriterion("TotalEquity >", value, "totalequity");
            return (Criteria) this;
        }

        public Criteria andTotalequityGreaterThanOrEqualTo(String value) {
            addCriterion("TotalEquity >=", value, "totalequity");
            return (Criteria) this;
        }

        public Criteria andTotalequityLessThan(String value) {
            addCriterion("TotalEquity <", value, "totalequity");
            return (Criteria) this;
        }

        public Criteria andTotalequityLessThanOrEqualTo(String value) {
            addCriterion("TotalEquity <=", value, "totalequity");
            return (Criteria) this;
        }

        public Criteria andTotalequityLike(String value) {
            addCriterion("TotalEquity like", value, "totalequity");
            return (Criteria) this;
        }

        public Criteria andTotalequityNotLike(String value) {
            addCriterion("TotalEquity not like", value, "totalequity");
            return (Criteria) this;
        }

        public Criteria andTotalequityIn(List<String> values) {
            addCriterion("TotalEquity in", values, "totalequity");
            return (Criteria) this;
        }

        public Criteria andTotalequityNotIn(List<String> values) {
            addCriterion("TotalEquity not in", values, "totalequity");
            return (Criteria) this;
        }

        public Criteria andTotalequityBetween(String value1, String value2) {
            addCriterion("TotalEquity between", value1, value2, "totalequity");
            return (Criteria) this;
        }

        public Criteria andTotalequityNotBetween(String value1, String value2) {
            addCriterion("TotalEquity not between", value1, value2, "totalequity");
            return (Criteria) this;
        }

        public Criteria andFlowequityIsNull() {
            addCriterion("FlowEquity is null");
            return (Criteria) this;
        }

        public Criteria andFlowequityIsNotNull() {
            addCriterion("FlowEquity is not null");
            return (Criteria) this;
        }

        public Criteria andFlowequityEqualTo(String value) {
            addCriterion("FlowEquity =", value, "flowequity");
            return (Criteria) this;
        }

        public Criteria andFlowequityNotEqualTo(String value) {
            addCriterion("FlowEquity <>", value, "flowequity");
            return (Criteria) this;
        }

        public Criteria andFlowequityGreaterThan(String value) {
            addCriterion("FlowEquity >", value, "flowequity");
            return (Criteria) this;
        }

        public Criteria andFlowequityGreaterThanOrEqualTo(String value) {
            addCriterion("FlowEquity >=", value, "flowequity");
            return (Criteria) this;
        }

        public Criteria andFlowequityLessThan(String value) {
            addCriterion("FlowEquity <", value, "flowequity");
            return (Criteria) this;
        }

        public Criteria andFlowequityLessThanOrEqualTo(String value) {
            addCriterion("FlowEquity <=", value, "flowequity");
            return (Criteria) this;
        }

        public Criteria andFlowequityLike(String value) {
            addCriterion("FlowEquity like", value, "flowequity");
            return (Criteria) this;
        }

        public Criteria andFlowequityNotLike(String value) {
            addCriterion("FlowEquity not like", value, "flowequity");
            return (Criteria) this;
        }

        public Criteria andFlowequityIn(List<String> values) {
            addCriterion("FlowEquity in", values, "flowequity");
            return (Criteria) this;
        }

        public Criteria andFlowequityNotIn(List<String> values) {
            addCriterion("FlowEquity not in", values, "flowequity");
            return (Criteria) this;
        }

        public Criteria andFlowequityBetween(String value1, String value2) {
            addCriterion("FlowEquity between", value1, value2, "flowequity");
            return (Criteria) this;
        }

        public Criteria andFlowequityNotBetween(String value1, String value2) {
            addCriterion("FlowEquity not between", value1, value2, "flowequity");
            return (Criteria) this;
        }

        public Criteria andBsharescodeIsNull() {
            addCriterion("BSharesCode is null");
            return (Criteria) this;
        }

        public Criteria andBsharescodeIsNotNull() {
            addCriterion("BSharesCode is not null");
            return (Criteria) this;
        }

        public Criteria andBsharescodeEqualTo(String value) {
            addCriterion("BSharesCode =", value, "bsharescode");
            return (Criteria) this;
        }

        public Criteria andBsharescodeNotEqualTo(String value) {
            addCriterion("BSharesCode <>", value, "bsharescode");
            return (Criteria) this;
        }

        public Criteria andBsharescodeGreaterThan(String value) {
            addCriterion("BSharesCode >", value, "bsharescode");
            return (Criteria) this;
        }

        public Criteria andBsharescodeGreaterThanOrEqualTo(String value) {
            addCriterion("BSharesCode >=", value, "bsharescode");
            return (Criteria) this;
        }

        public Criteria andBsharescodeLessThan(String value) {
            addCriterion("BSharesCode <", value, "bsharescode");
            return (Criteria) this;
        }

        public Criteria andBsharescodeLessThanOrEqualTo(String value) {
            addCriterion("BSharesCode <=", value, "bsharescode");
            return (Criteria) this;
        }

        public Criteria andBsharescodeLike(String value) {
            addCriterion("BSharesCode like", value, "bsharescode");
            return (Criteria) this;
        }

        public Criteria andBsharescodeNotLike(String value) {
            addCriterion("BSharesCode not like", value, "bsharescode");
            return (Criteria) this;
        }

        public Criteria andBsharescodeIn(List<String> values) {
            addCriterion("BSharesCode in", values, "bsharescode");
            return (Criteria) this;
        }

        public Criteria andBsharescodeNotIn(List<String> values) {
            addCriterion("BSharesCode not in", values, "bsharescode");
            return (Criteria) this;
        }

        public Criteria andBsharescodeBetween(String value1, String value2) {
            addCriterion("BSharesCode between", value1, value2, "bsharescode");
            return (Criteria) this;
        }

        public Criteria andBsharescodeNotBetween(String value1, String value2) {
            addCriterion("BSharesCode not between", value1, value2, "bsharescode");
            return (Criteria) this;
        }

        public Criteria andBsharesaddreIsNull() {
            addCriterion("BSharesAddre is null");
            return (Criteria) this;
        }

        public Criteria andBsharesaddreIsNotNull() {
            addCriterion("BSharesAddre is not null");
            return (Criteria) this;
        }

        public Criteria andBsharesaddreEqualTo(String value) {
            addCriterion("BSharesAddre =", value, "bsharesaddre");
            return (Criteria) this;
        }

        public Criteria andBsharesaddreNotEqualTo(String value) {
            addCriterion("BSharesAddre <>", value, "bsharesaddre");
            return (Criteria) this;
        }

        public Criteria andBsharesaddreGreaterThan(String value) {
            addCriterion("BSharesAddre >", value, "bsharesaddre");
            return (Criteria) this;
        }

        public Criteria andBsharesaddreGreaterThanOrEqualTo(String value) {
            addCriterion("BSharesAddre >=", value, "bsharesaddre");
            return (Criteria) this;
        }

        public Criteria andBsharesaddreLessThan(String value) {
            addCriterion("BSharesAddre <", value, "bsharesaddre");
            return (Criteria) this;
        }

        public Criteria andBsharesaddreLessThanOrEqualTo(String value) {
            addCriterion("BSharesAddre <=", value, "bsharesaddre");
            return (Criteria) this;
        }

        public Criteria andBsharesaddreLike(String value) {
            addCriterion("BSharesAddre like", value, "bsharesaddre");
            return (Criteria) this;
        }

        public Criteria andBsharesaddreNotLike(String value) {
            addCriterion("BSharesAddre not like", value, "bsharesaddre");
            return (Criteria) this;
        }

        public Criteria andBsharesaddreIn(List<String> values) {
            addCriterion("BSharesAddre in", values, "bsharesaddre");
            return (Criteria) this;
        }

        public Criteria andBsharesaddreNotIn(List<String> values) {
            addCriterion("BSharesAddre not in", values, "bsharesaddre");
            return (Criteria) this;
        }

        public Criteria andBsharesaddreBetween(String value1, String value2) {
            addCriterion("BSharesAddre between", value1, value2, "bsharesaddre");
            return (Criteria) this;
        }

        public Criteria andBsharesaddreNotBetween(String value1, String value2) {
            addCriterion("BSharesAddre not between", value1, value2, "bsharesaddre");
            return (Criteria) this;
        }

        public Criteria andBtimemarketIsNull() {
            addCriterion("BTimeMarket is null");
            return (Criteria) this;
        }

        public Criteria andBtimemarketIsNotNull() {
            addCriterion("BTimeMarket is not null");
            return (Criteria) this;
        }

        public Criteria andBtimemarketEqualTo(String value) {
            addCriterion("BTimeMarket =", value, "btimemarket");
            return (Criteria) this;
        }

        public Criteria andBtimemarketNotEqualTo(String value) {
            addCriterion("BTimeMarket <>", value, "btimemarket");
            return (Criteria) this;
        }

        public Criteria andBtimemarketGreaterThan(String value) {
            addCriterion("BTimeMarket >", value, "btimemarket");
            return (Criteria) this;
        }

        public Criteria andBtimemarketGreaterThanOrEqualTo(String value) {
            addCriterion("BTimeMarket >=", value, "btimemarket");
            return (Criteria) this;
        }

        public Criteria andBtimemarketLessThan(String value) {
            addCriterion("BTimeMarket <", value, "btimemarket");
            return (Criteria) this;
        }

        public Criteria andBtimemarketLessThanOrEqualTo(String value) {
            addCriterion("BTimeMarket <=", value, "btimemarket");
            return (Criteria) this;
        }

        public Criteria andBtimemarketLike(String value) {
            addCriterion("BTimeMarket like", value, "btimemarket");
            return (Criteria) this;
        }

        public Criteria andBtimemarketNotLike(String value) {
            addCriterion("BTimeMarket not like", value, "btimemarket");
            return (Criteria) this;
        }

        public Criteria andBtimemarketIn(List<String> values) {
            addCriterion("BTimeMarket in", values, "btimemarket");
            return (Criteria) this;
        }

        public Criteria andBtimemarketNotIn(List<String> values) {
            addCriterion("BTimeMarket not in", values, "btimemarket");
            return (Criteria) this;
        }

        public Criteria andBtimemarketBetween(String value1, String value2) {
            addCriterion("BTimeMarket between", value1, value2, "btimemarket");
            return (Criteria) this;
        }

        public Criteria andBtimemarketNotBetween(String value1, String value2) {
            addCriterion("BTimeMarket not between", value1, value2, "btimemarket");
            return (Criteria) this;
        }

        public Criteria andBtotalequityIsNull() {
            addCriterion("BTotalEquity is null");
            return (Criteria) this;
        }

        public Criteria andBtotalequityIsNotNull() {
            addCriterion("BTotalEquity is not null");
            return (Criteria) this;
        }

        public Criteria andBtotalequityEqualTo(String value) {
            addCriterion("BTotalEquity =", value, "btotalequity");
            return (Criteria) this;
        }

        public Criteria andBtotalequityNotEqualTo(String value) {
            addCriterion("BTotalEquity <>", value, "btotalequity");
            return (Criteria) this;
        }

        public Criteria andBtotalequityGreaterThan(String value) {
            addCriterion("BTotalEquity >", value, "btotalequity");
            return (Criteria) this;
        }

        public Criteria andBtotalequityGreaterThanOrEqualTo(String value) {
            addCriterion("BTotalEquity >=", value, "btotalequity");
            return (Criteria) this;
        }

        public Criteria andBtotalequityLessThan(String value) {
            addCriterion("BTotalEquity <", value, "btotalequity");
            return (Criteria) this;
        }

        public Criteria andBtotalequityLessThanOrEqualTo(String value) {
            addCriterion("BTotalEquity <=", value, "btotalequity");
            return (Criteria) this;
        }

        public Criteria andBtotalequityLike(String value) {
            addCriterion("BTotalEquity like", value, "btotalequity");
            return (Criteria) this;
        }

        public Criteria andBtotalequityNotLike(String value) {
            addCriterion("BTotalEquity not like", value, "btotalequity");
            return (Criteria) this;
        }

        public Criteria andBtotalequityIn(List<String> values) {
            addCriterion("BTotalEquity in", values, "btotalequity");
            return (Criteria) this;
        }

        public Criteria andBtotalequityNotIn(List<String> values) {
            addCriterion("BTotalEquity not in", values, "btotalequity");
            return (Criteria) this;
        }

        public Criteria andBtotalequityBetween(String value1, String value2) {
            addCriterion("BTotalEquity between", value1, value2, "btotalequity");
            return (Criteria) this;
        }

        public Criteria andBtotalequityNotBetween(String value1, String value2) {
            addCriterion("BTotalEquity not between", value1, value2, "btotalequity");
            return (Criteria) this;
        }

        public Criteria andBflowequityIsNull() {
            addCriterion("BFlowEquity is null");
            return (Criteria) this;
        }

        public Criteria andBflowequityIsNotNull() {
            addCriterion("BFlowEquity is not null");
            return (Criteria) this;
        }

        public Criteria andBflowequityEqualTo(String value) {
            addCriterion("BFlowEquity =", value, "bflowequity");
            return (Criteria) this;
        }

        public Criteria andBflowequityNotEqualTo(String value) {
            addCriterion("BFlowEquity <>", value, "bflowequity");
            return (Criteria) this;
        }

        public Criteria andBflowequityGreaterThan(String value) {
            addCriterion("BFlowEquity >", value, "bflowequity");
            return (Criteria) this;
        }

        public Criteria andBflowequityGreaterThanOrEqualTo(String value) {
            addCriterion("BFlowEquity >=", value, "bflowequity");
            return (Criteria) this;
        }

        public Criteria andBflowequityLessThan(String value) {
            addCriterion("BFlowEquity <", value, "bflowequity");
            return (Criteria) this;
        }

        public Criteria andBflowequityLessThanOrEqualTo(String value) {
            addCriterion("BFlowEquity <=", value, "bflowequity");
            return (Criteria) this;
        }

        public Criteria andBflowequityLike(String value) {
            addCriterion("BFlowEquity like", value, "bflowequity");
            return (Criteria) this;
        }

        public Criteria andBflowequityNotLike(String value) {
            addCriterion("BFlowEquity not like", value, "bflowequity");
            return (Criteria) this;
        }

        public Criteria andBflowequityIn(List<String> values) {
            addCriterion("BFlowEquity in", values, "bflowequity");
            return (Criteria) this;
        }

        public Criteria andBflowequityNotIn(List<String> values) {
            addCriterion("BFlowEquity not in", values, "bflowequity");
            return (Criteria) this;
        }

        public Criteria andBflowequityBetween(String value1, String value2) {
            addCriterion("BFlowEquity between", value1, value2, "bflowequity");
            return (Criteria) this;
        }

        public Criteria andBflowequityNotBetween(String value1, String value2) {
            addCriterion("BFlowEquity not between", value1, value2, "bflowequity");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("Area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("Area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(String value) {
            addCriterion("Area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(String value) {
            addCriterion("Area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(String value) {
            addCriterion("Area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(String value) {
            addCriterion("Area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(String value) {
            addCriterion("Area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(String value) {
            addCriterion("Area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLike(String value) {
            addCriterion("Area like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotLike(String value) {
            addCriterion("Area not like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<String> values) {
            addCriterion("Area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<String> values) {
            addCriterion("Area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(String value1, String value2) {
            addCriterion("Area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(String value1, String value2) {
            addCriterion("Area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("Province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("Province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("Province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("Province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("Province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("Province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("Province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("Province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("Province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("Province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("Province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("Province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("Province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("Province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("City is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("City is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("City =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("City <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("City >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("City >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("City <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("City <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("City like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("City not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("City in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("City not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("City between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("City not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andWebsiteIsNull() {
            addCriterion("Website is null");
            return (Criteria) this;
        }

        public Criteria andWebsiteIsNotNull() {
            addCriterion("Website is not null");
            return (Criteria) this;
        }

        public Criteria andWebsiteEqualTo(String value) {
            addCriterion("Website =", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotEqualTo(String value) {
            addCriterion("Website <>", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteGreaterThan(String value) {
            addCriterion("Website >", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteGreaterThanOrEqualTo(String value) {
            addCriterion("Website >=", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteLessThan(String value) {
            addCriterion("Website <", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteLessThanOrEqualTo(String value) {
            addCriterion("Website <=", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteLike(String value) {
            addCriterion("Website like", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotLike(String value) {
            addCriterion("Website not like", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteIn(List<String> values) {
            addCriterion("Website in", values, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotIn(List<String> values) {
            addCriterion("Website not in", values, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteBetween(String value1, String value2) {
            addCriterion("Website between", value1, value2, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotBetween(String value1, String value2) {
            addCriterion("Website not between", value1, value2, "website");
            return (Criteria) this;
        }

        public Criteria andIndustryIsNull() {
            addCriterion("Industry is null");
            return (Criteria) this;
        }

        public Criteria andIndustryIsNotNull() {
            addCriterion("Industry is not null");
            return (Criteria) this;
        }

        public Criteria andIndustryEqualTo(String value) {
            addCriterion("Industry =", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotEqualTo(String value) {
            addCriterion("Industry <>", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryGreaterThan(String value) {
            addCriterion("Industry >", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryGreaterThanOrEqualTo(String value) {
            addCriterion("Industry >=", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLessThan(String value) {
            addCriterion("Industry <", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLessThanOrEqualTo(String value) {
            addCriterion("Industry <=", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLike(String value) {
            addCriterion("Industry like", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotLike(String value) {
            addCriterion("Industry not like", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryIn(List<String> values) {
            addCriterion("Industry in", values, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotIn(List<String> values) {
            addCriterion("Industry not in", values, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryBetween(String value1, String value2) {
            addCriterion("Industry between", value1, value2, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotBetween(String value1, String value2) {
            addCriterion("Industry not between", value1, value2, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustry2IsNull() {
            addCriterion("industry2 is null");
            return (Criteria) this;
        }

        public Criteria andIndustry2IsNotNull() {
            addCriterion("industry2 is not null");
            return (Criteria) this;
        }

        public Criteria andIndustry2EqualTo(String value) {
            addCriterion("industry2 =", value, "industry2");
            return (Criteria) this;
        }

        public Criteria andIndustry2NotEqualTo(String value) {
            addCriterion("industry2 <>", value, "industry2");
            return (Criteria) this;
        }

        public Criteria andIndustry2GreaterThan(String value) {
            addCriterion("industry2 >", value, "industry2");
            return (Criteria) this;
        }

        public Criteria andIndustry2GreaterThanOrEqualTo(String value) {
            addCriterion("industry2 >=", value, "industry2");
            return (Criteria) this;
        }

        public Criteria andIndustry2LessThan(String value) {
            addCriterion("industry2 <", value, "industry2");
            return (Criteria) this;
        }

        public Criteria andIndustry2LessThanOrEqualTo(String value) {
            addCriterion("industry2 <=", value, "industry2");
            return (Criteria) this;
        }

        public Criteria andIndustry2Like(String value) {
            addCriterion("industry2 like", value, "industry2");
            return (Criteria) this;
        }

        public Criteria andIndustry2NotLike(String value) {
            addCriterion("industry2 not like", value, "industry2");
            return (Criteria) this;
        }

        public Criteria andIndustry2In(List<String> values) {
            addCriterion("industry2 in", values, "industry2");
            return (Criteria) this;
        }

        public Criteria andIndustry2NotIn(List<String> values) {
            addCriterion("industry2 not in", values, "industry2");
            return (Criteria) this;
        }

        public Criteria andIndustry2Between(String value1, String value2) {
            addCriterion("industry2 between", value1, value2, "industry2");
            return (Criteria) this;
        }

        public Criteria andIndustry2NotBetween(String value1, String value2) {
            addCriterion("industry2 not between", value1, value2, "industry2");
            return (Criteria) this;
        }

        public Criteria andIndustry3IsNull() {
            addCriterion("industry3 is null");
            return (Criteria) this;
        }

        public Criteria andIndustry3IsNotNull() {
            addCriterion("industry3 is not null");
            return (Criteria) this;
        }

        public Criteria andIndustry3EqualTo(String value) {
            addCriterion("industry3 =", value, "industry3");
            return (Criteria) this;
        }

        public Criteria andIndustry3NotEqualTo(String value) {
            addCriterion("industry3 <>", value, "industry3");
            return (Criteria) this;
        }

        public Criteria andIndustry3GreaterThan(String value) {
            addCriterion("industry3 >", value, "industry3");
            return (Criteria) this;
        }

        public Criteria andIndustry3GreaterThanOrEqualTo(String value) {
            addCriterion("industry3 >=", value, "industry3");
            return (Criteria) this;
        }

        public Criteria andIndustry3LessThan(String value) {
            addCriterion("industry3 <", value, "industry3");
            return (Criteria) this;
        }

        public Criteria andIndustry3LessThanOrEqualTo(String value) {
            addCriterion("industry3 <=", value, "industry3");
            return (Criteria) this;
        }

        public Criteria andIndustry3Like(String value) {
            addCriterion("industry3 like", value, "industry3");
            return (Criteria) this;
        }

        public Criteria andIndustry3NotLike(String value) {
            addCriterion("industry3 not like", value, "industry3");
            return (Criteria) this;
        }

        public Criteria andIndustry3In(List<String> values) {
            addCriterion("industry3 in", values, "industry3");
            return (Criteria) this;
        }

        public Criteria andIndustry3NotIn(List<String> values) {
            addCriterion("industry3 not in", values, "industry3");
            return (Criteria) this;
        }

        public Criteria andIndustry3Between(String value1, String value2) {
            addCriterion("industry3 between", value1, value2, "industry3");
            return (Criteria) this;
        }

        public Criteria andIndustry3NotBetween(String value1, String value2) {
            addCriterion("industry3 not between", value1, value2, "industry3");
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