package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyDecisionDataModel;
import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.DecisionType;
import com.aripd.bizibee.entity.DecisionchoiceEntity;
import com.aripd.bizibee.entity.SkuEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import com.aripd.bizibee.service.DecisionService;
import com.aripd.bizibee.service.DecisionchoiceService;
import com.aripd.bizibee.service.SkuService;
import java.util.Arrays;
import org.primefaces.model.UploadedFile;

@Named
@ViewScoped
public class DecisionView implements Serializable {

    @Inject
    private DecisionService decisionService;
    private DecisionEntity newDecision;
    private DecisionEntity selectedDecision;
    private List<DecisionEntity> selectedDecisions;
    private LazyDataModel<DecisionEntity> lazyModel;

    private Long id;

    private UploadedFile file;

    @Inject
    private DecisionchoiceService decisionchoiceService;
    private DecisionchoiceEntity newDecisionchoice;
    private DecisionchoiceEntity selectedDecisionchoice;

    @Inject
    private SkuService skuService;

    @Inject
    MessageUtil messageUtil;

    public DecisionView() {
        newDecision = new DecisionEntity();
        selectedDecision = new DecisionEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyDecisionDataModel(decisionService);
    }

    public void onLoad() {
        if (id == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedDecision = decisionService.find(id);

        if (selectedDecision == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Unknown record.");
            return;
        }

    }

    public List<DecisionType> getDecisionTypes() {
        return Arrays.asList(DecisionType.values());
    }

    public List<DecisionEntity> getDecisions() {
        return decisionService.findAll();
    }

    public List<DecisionchoiceEntity> getDecisionchoices() {
        return decisionchoiceService.findByDecision(selectedDecision);
    }

    public List<SkuEntity> getSkus() {
        return skuService.findAll();
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        if (file != null && file.getSize() > 0) {
            newDecision.setBytes(file.getContents());
        }
        decisionService.create(newDecision);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        if (file != null && file.getSize() > 0) {
            selectedDecision.setBytes(file.getContents());
        }
        decisionService.update(selectedDecision);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        decisionService.delete(selectedDecision);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        decisionService.deleteItems(selectedDecisions);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public DecisionEntity getSelectedDecision() {
        return selectedDecision;
    }

    public void setSelectedDecision(DecisionEntity selectedDecision) {
        this.selectedDecision = selectedDecision;
    }

    public List<DecisionEntity> getSelectedDecisions() {
        return selectedDecisions;
    }

    public void setSelectedDecisions(List<DecisionEntity> selectedDecisions) {
        this.selectedDecisions = selectedDecisions;
    }

    public DecisionEntity getNewDecision() {
        return newDecision;
    }

    public void setNewDecision(DecisionEntity newDecision) {
        this.newDecision = newDecision;
    }

    public LazyDataModel<DecisionEntity> getLazyModel() {
        return lazyModel;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DecisionchoiceEntity getNewDecisionchoice() {
        return newDecisionchoice;
    }

    public void setNewDecisionchoice(DecisionchoiceEntity newDecisionchoice) {
        this.newDecisionchoice = newDecisionchoice;
    }

    public DecisionchoiceEntity getSelectedDecisionchoice() {
        return selectedDecisionchoice;
    }

    public void setSelectedDecisionchoice(DecisionchoiceEntity selectedDecisionchoice) {
        this.selectedDecisionchoice = selectedDecisionchoice;
    }

}
