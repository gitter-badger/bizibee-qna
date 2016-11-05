package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyBrandDataModel;
import com.aripd.bizibee.entity.BrandEntity;
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

@Named
@ViewScoped
public class BrandView implements Serializable {

    static final Logger LOG = Logger.getLogger(BrandView.class.getName());

    @Inject
    private BrandService brandService;
    private BrandEntity newRecord;
    private BrandEntity selectedRecord;
    private List<BrandEntity> selectedRecords;
    private LazyDataModel<BrandEntity> lazyModel;

    @Inject
    MessageUtil messageUtil;

    public BrandView() {
        newRecord = new BrandEntity();
        selectedRecord = new BrandEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyBrandDataModel(brandService);
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        brandService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        brandService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        brandService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        brandService.deleteItems(selectedRecords);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public BrandEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(BrandEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<BrandEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<BrandEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public BrandEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(BrandEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<BrandEntity> getLazyModel() {
        return lazyModel;
    }

}
