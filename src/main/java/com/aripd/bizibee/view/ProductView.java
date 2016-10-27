package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyProductDataModel;
import com.aripd.bizibee.entity.ProductEntity;
import com.aripd.bizibee.service.ProductService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.apache.log4j.Logger;

@Named
@ViewScoped
public class ProductView implements Serializable {

    static final Logger LOG = Logger.getLogger(ProductView.class.getName());

    @Inject
    private ProductService productService;
    private ProductEntity newRecord;
    private ProductEntity selectedRecord;
    private List<ProductEntity> selectedRecords;
    private LazyDataModel<ProductEntity> lazyModel;

    @Inject
    MessageUtil messageUtil;

    public ProductView() {
        newRecord = new ProductEntity();
        selectedRecord = new ProductEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyProductDataModel(productService);
    }

    public List<ProductEntity> fetchAllRecords() {
        return productService.findAll();
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        productService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        productService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        productService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        productService.deleteItems(selectedRecords);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public ProductEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(ProductEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<ProductEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<ProductEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public ProductEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(ProductEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<ProductEntity> getLazyModel() {
        return lazyModel;
    }

}
