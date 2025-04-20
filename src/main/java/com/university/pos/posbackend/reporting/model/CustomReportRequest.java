package com.university.pos.posbackend.reporting.model;

import java.util.List;

public class CustomReportRequest {
    private List<String> fields;
    private String entity;
    private List<FilterCriteria> filters;
    private List<String> groupBy;
    private String sortBy;
    private String sortDirection; // ASC or DESC

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public List<FilterCriteria> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterCriteria> filters) {
        this.filters = filters;
    }

    public List<String> getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(List<String> groupBy) {
        this.groupBy = groupBy;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    @Override
    public String toString() {
        return "CustomReportRequest{" +
                "fields=" + fields +
                ", entity='" + entity + '\'' +
                ", filters=" + filters +
                ", groupBy=" + groupBy +
                ", sortBy='" + sortBy + '\'' +
                ", sortDirection='" + sortDirection + '\'' +
                '}';
    }

    public static class FilterCriteria {
        private String field;
        private String operator; // e.g., "=", ">", "<", "LIKE"
        private Object value;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "FilterCriteria{" +
                    "field='" + field + '\'' +
                    ", operator='" + operator + '\'' +
                    ", value=" + value +
                    '}';
        }
    }
}