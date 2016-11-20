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
import com.aripd.bizibee.service.SkuService;
import org.primefaces.model.UploadedFile;

@Named
@ViewScoped
public class SkuView implements Serializable {

    @Inject
    private SkuService skuService;
    private SkuEntity newRecord;
    private SkuEntity selectedRecord;
    private List<SkuEntity> selectedRecords;
    private LazyDataModel<SkuEntity> lazyModel;

    private UploadedFile file;

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
        if (file != null) {
            newRecord.setBytes(file.getContents());
        }
        skuService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        if (file != null) {
            selectedRecord.setBytes(file.getContents());
        }
        skuService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        skuService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        if (selectedRecords.isEmpty()) {
            messageUtil.addGlobalErrorFlashMessage("Please select at least one item");
        } else {
            skuService.deleteItems(selectedRecords);
            messageUtil.addGlobalInfoFlashMessage("Deleted");
        }
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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

}
