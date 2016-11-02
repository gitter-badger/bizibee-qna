package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyDecisionDataModel;
import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.DecisionchoiceEntity;
import com.aripd.bizibee.entity.ProductEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.apache.log4j.Logger;
import com.aripd.bizibee.service.DecisionService;
import com.aripd.bizibee.service.DecisionchoiceService;
import com.aripd.util.RequestUtil;
import java.util.ArrayList;
import java.util.HashMap;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

@Named
@ViewScoped
public class ResponseView implements Serializable {

    static final Logger LOG = Logger.getLogger(ResponseView.class.getName());

    private MenuModel menuModel;

    @Inject
    private DecisionService decisionService;
    private DecisionEntity newRecord;
    private DecisionEntity selectedRecord;
    private List<DecisionEntity> selectedRecords;
    private LazyDataModel<DecisionEntity> lazyModel;

    private Long id;

    @Inject
    private DecisionchoiceService decisionchoiceService;
    private DecisionchoiceEntity model1;
    private List<DecisionchoiceEntity> model2 = new ArrayList<>();
    private HashMap<ProductEntity, Integer> model3 = new HashMap<>();
    private HashMap<ProductEntity, DecisionchoiceEntity> model4 = new HashMap<>();
    private HashMap<ProductEntity, ArrayList<DecisionchoiceEntity>> model5 = new HashMap<>();

    @Inject
    MessageUtil messageUtil;

    public ResponseView() {
        newRecord = new DecisionEntity();
        selectedRecord = new DecisionEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyDecisionDataModel(decisionService);

        menuModel = new DefaultMenuModel();
        for (DecisionEntity decision : decisionService.findAll()) {
            DefaultMenuItem item = new DefaultMenuItem();
            item.setValue(decision.getName());
            item.setOutcome("/member/response");
            item.setParam("id", decision.getId());
            menuModel.addElement(item);
        }
    }

    public void onLoad() {
        if (id == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedRecord = decisionService.find(id);

        if (selectedRecord == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Unknown record.");
            return;
        }

    }

    public void doUpdate(ActionEvent actionEvent) {
        LOG.info("model1: " + model1);
        LOG.info("model2: " + model2);
        LOG.info("model3: " + model3);
        LOG.info("model4: " + model4);
        LOG.info("model5: " + model5);
        messageUtil.addGlobalInfoFlashMessage("Updated");

        /**
         * TODO +1 hesaplamasının mantığı yanlış, acil düzelt.
         */
        String navigation = "/member/response?id=" + (selectedRecord.getId() + 1) + "&amp;faces-redirect=true";
        RequestUtil.doNavigate(navigation);
    }

    public List<DecisionchoiceEntity> fetchDecisionchoices(DecisionEntity decision) {
        return decisionchoiceService.findByDecision(decision);
    }

    public List<DecisionEntity> fetchAllRecords() {
        return decisionService.findAll();
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        decisionService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        decisionService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        decisionService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        decisionService.deleteItems(selectedRecords);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public DecisionEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(DecisionEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<DecisionEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<DecisionEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public DecisionEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(DecisionEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<DecisionEntity> getLazyModel() {
        return lazyModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public DecisionchoiceEntity getModel1() {
        return model1;
    }

    public void setModel1(DecisionchoiceEntity model1) {
        this.model1 = model1;
    }

    public List<DecisionchoiceEntity> getModel2() {
        return model2;
    }

    public void setModel2(List<DecisionchoiceEntity> model2) {
        this.model2 = model2;
    }

    public HashMap<ProductEntity, Integer> getModel3() {
        return model3;
    }

    public void setModel3(HashMap<ProductEntity, Integer> model3) {
        this.model3 = model3;
    }

    public HashMap<ProductEntity, DecisionchoiceEntity> getModel4() {
        return model4;
    }

    public void setModel4(HashMap<ProductEntity, DecisionchoiceEntity> model4) {
        this.model4 = model4;
    }

    public HashMap<ProductEntity, ArrayList<DecisionchoiceEntity>> getModel5() {
        return model5;
    }

    public void setModel5(HashMap<ProductEntity, ArrayList<DecisionchoiceEntity>> model5) {
        this.model5 = model5;
    }

}
