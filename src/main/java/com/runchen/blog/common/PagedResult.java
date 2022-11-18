package com.runchen.blog.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PagedResult<T> implements Serializable {

    protected static final long serialVersionUID = 1L;

    private int page;			// 当前页数
    private int total;			// 总页数
    private long records;		// 总记录数
    private List<T> rows;		// 每行显示的内容

    public PagedResult() {
        this.page = 1;
        this.total = 0;
        this.records = 0;
        this.rows = new ArrayList<>();
    }


    public PagedResult(List<T> rows) {
        this.page = 1;
        this.total = 0;
        this.records = 0;
        this.rows = rows;
    }

    public PagedResult(int page, int total, long records, List<T> rows) {
        this.page = page;
        this.total = total;
        this.records = records;
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public int getTotal() {
        return total;
    }

    public long getRecords() {
        return records;
    }

    public List<?> getRows() {
        return rows;
    }

    public PagedResult<T> setPage(int page) {
        this.page = page;
        return this;
    }

    public PagedResult<T> setTotal(int total) {
        this.total = total;
        return this;
    }

    public PagedResult<T> setRecords(long records) {
        this.records = records;
        return this;
    }

    public PagedResult<T> setRows(List<T> rows) {
        this.rows = rows;
        return this;
    }
}
