package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyDecisionDataModel;
import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.DecisionchoiceEntity;
import com.aripd.bizibee.entity.ResponseEntity;
import com.aripd.bizibee.entity.SkuEntity;
import com.aripd.bizibee.model.response.Response5Model;
import com.aripd.bizibee.model.response.Response7Model;
import com.aripd.bizibee.model.response.Response6Model;
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
import com.aripd.bizibee.service.ResponseService;
import com.aripd.util.RequestUtil;
import java.util.ArrayList;
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

    private DecisionchoiceEntity model1;
    private List<DecisionchoiceEntity> model2 = new ArrayList<>();
    private SkuEntity model3;
    private List<SkuEntity> model4 = new ArrayList<>();
    private List<Response5Model> model5 = new ArrayList<>();
    private List<Response6Model> model6 = new ArrayList<>();
    private List<Response7Model> model7 = new ArrayList<>();

    @Inject
    private ResponseService responseService;

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

        for (SkuEntity sku : selectedRecord.getSkus()) {
            switch (selectedRecord.getDecisionType()) {
                case RANGE_SKU_LISTING:
                    model5.add(new Response5Model(sku));
                    break;
                case SINGLE_CHOICE_SKU_LISTING:
                    model6.add(new Response6Model(sku));
                    break;
                case MULTIPLE_CHOICE_SKU_LISTING:
                    model7.add(new Response7Model(sku));
                    break;
            }
        }

    }

    public void doUpdate(ActionEvent actionEvent) {

        switch (selectedRecord.getDecisionType()) {
            case SINGLE_CHOICE:
                LOG.info("model1: " + model1);
                responseService.create(new ResponseEntity(selectedRecord, model1.toString()));
                break;
            case MULTIPLE_CHOICE:
                LOG.info("model2: " + model2);
                responseService.create(new ResponseEntity(selectedRecord, model2.toString()));
                break;
            case SINGLE_SKU_LISTING:
                LOG.info("model3: " + model3);
                LOG.info("Sku: " + model3.getName());
                responseService.create(new ResponseEntity(selectedRecord, model3.toString()));
                break;
            case MULTIPLE_SKU_LISTING:
                LOG.info("model4: " + model4);
                model4.forEach(c -> {
                    LOG.info("Sku: " + c.getName());
                });
                responseService.create(new ResponseEntity(selectedRecord, model4.toString()));
                break;
            case RANGE_SKU_LISTING:
                LOG.info("model5: " + model5);
                model5.forEach(c -> {
                    LOG.info("Sku: " + c.getSku().getName());
                    if (c.getValue() != null) {
                        LOG.info("Value: " + c.getValue());
                    }
                });
                responseService.create(new ResponseEntity(selectedRecord, model5.toString()));
                break;
            case SINGLE_CHOICE_SKU_LISTING:
                LOG.info("model6: " + model6);
                model6.forEach(c -> {
                    LOG.info("Sku: " + c.getSku().getName());
                    if (c.getDecisionchoice() != null) {
                        LOG.info("Decisionchoice: " + c.getDecisionchoice().getName());
                    }
                });
                break;
            case MULTIPLE_CHOICE_SKU_LISTING:
                LOG.info("model7: " + model7);
                model7.forEach(c -> {
                    LOG.info("Sku: " + c.getSku().getName());
                    for (DecisionchoiceEntity decisionchoice : c.getDecisionchoices()) {
                        LOG.info("Decisionchoice: " + decisionchoice.getName());
                    }
                });
                break;
        }

        messageUtil.addGlobalInfoFlashMessage("Updated");

        /**
         * TODO +1 hesaplamasının mantığı yanlış, acil düzelt.
         */
        String navigation = "/member/response?id=" + (selectedRecord.getId() + 1) + "&amp;faces-redirect=true";
        RequestUtil.doNavigate(navigation);
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

    public SkuEntity getModel3() {
        return model3;
    }

    public void setModel3(SkuEntity model3) {
        this.model3 = model3;
    }

    public List<SkuEntity> getModel4() {
        return model4;
    }

    public void setModel4(List<SkuEntity> model4) {
        this.model4 = model4;
    }

    public List<Response5Model> getModel5() {
        return model5;
    }

    public void setModel5(List<Response5Model> model5) {
        this.model5 = model5;
    }

    public List<Response6Model> getModel6() {
        return model6;
    }

    public void setModel6(List<Response6Model> model6) {
        this.model6 = model6;
    }

    public List<Response7Model> getModel7() {
        return model7;
    }

    public void setModel7(List<Response7Model> model7) {
        this.model7 = model7;
    }

}
