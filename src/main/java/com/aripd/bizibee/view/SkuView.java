package com.aripd.bizibee.view;

import com.aripd.bizibee.entity.BrandEntity;
import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazySkuDataModel;
import com.aripd.bizibee.entity.SkuEntity;
import com.aripd.bizibee.service.BrandService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.apache.log4j.Logger;
import com.aripd.bizibee.service.SkuService;

@Named
@ViewScoped
public class SkuView implements Serializable {

    static final Logger LOG = Logger.getLogger(SkuView.class.getName());

    @Inject
    private SkuService skuService;
    private SkuEntity newRecord;
    private SkuEntity selectedRecord;
    private List<SkuEntity> selectedRecords;
    private LazyDataModel<SkuEntity> lazyModel;

    @Inject
    private BrandService brandService;

    @Inject
    MessageUtil messageUtil;

    public SkuView() {
        newRecord = new SkuEntity();
        selectedRecord = new SkuEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazySkuDataModel(skuService);
    }

    public List<BrandEntity> getBrands() {
        return brandService.findAll();
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        skuService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        skuService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        skuService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        skuService.deleteItems(selectedRecords);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public SkuEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(SkuEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<SkuEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<SkuEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public SkuEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(SkuEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<SkuEntity> getLazyModel() {
        return lazyModel;
    }

}
