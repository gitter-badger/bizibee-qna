package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyResponseDataModel;
import com.aripd.bizibee.entity.ResponseEntity;
import com.aripd.bizibee.service.ResponseService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

@Named
@ViewScoped
public class ResponseController implements Serializable {

    @Inject
    private ResponseService responseService;
    private ResponseEntity selectedRecord;
    private List<ResponseEntity> selectedRecords;
    private LazyDataModel<ResponseEntity> lazyModel;

    @Inject
    MessageUtil messageUtil;

    public ResponseController() {
        selectedRecord = new ResponseEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyResponseDataModel(responseService);
    }

    public ResponseEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(ResponseEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<ResponseEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<ResponseEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public LazyDataModel<ResponseEntity> getLazyModel() {
        return lazyModel;
    }

}
