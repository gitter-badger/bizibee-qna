package com.aripd.bizibee.model.data;

import com.aripd.bizibee.entity.AbstractEntity;
import com.aripd.bizibee.service.CrudService;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

public abstract class LazyAbstractDataModel<T extends AbstractEntity> extends LazyDataModel<T> implements Serializable {

    private final CrudService crudService;
    private List<T> datasource;
    private int pageSize;
    private int rowIndex;
    private int rowCount;

    public LazyAbstractDataModel(CrudService crudService) {
        this.crudService = crudService;
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        datasource = crudService.getResultList(first, pageSize, sortField, sortOrder, filters);
        setRowCount(crudService.count(filters));
        return datasource;
    }

    @Override
    public List<T> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        datasource = crudService.getResultList(first, pageSize, multiSortMeta, filters);
        setRowCount(crudService.count(filters));
        return datasource;
    }

    @Override
    public boolean isRowAvailable() {
        if (datasource == null) {
            return false;
        }
        int index = rowIndex % pageSize;
        return index >= 0 && index < datasource.size();
    }

    @Override
    public Object getRowKey(T e) {
        return e.getId().toString();
    }

    @Override
    public T getRowData() {
        if (datasource == null) {
            return null;
        }
        int index = rowIndex % pageSize;
        if (index > datasource.size()) {
            return null;
        }
        return datasource.get(index);
    }

    @Override
    public T getRowData(String rowKey) {
        if (datasource == null) {
            return null;
        }
        for (T e : datasource) {
            if (e.getId().toString().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getRowIndex() {
        return this.rowIndex;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    @Override
    public int getRowCount() {
        return this.rowCount;
    }

}
