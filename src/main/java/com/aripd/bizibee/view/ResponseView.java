package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyDecisionDataModel;
import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.DecisionchoiceEntity;
import com.aripd.bizibee.entity.SkuEntity;
import com.aripd.bizibee.model.response.Response1Model;
import com.aripd.bizibee.model.response.Response2Model;
import com.aripd.bizibee.model.response.Response3Model;
import com.aripd.bizibee.model.response.Response4Model;
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

    private MenuModel menuModel;

    @Inject
    private ResponseService responseService;

    @Inject
    private DecisionService decisionService;
    private DecisionEntity newRecord;
    private DecisionEntity selectedRecord;
    private List<DecisionEntity> selectedRecords;
    private LazyDataModel<DecisionEntity> lazyModel;

    private Long id;

    private Response1Model model1 = new Response1Model();
    private Response2Model model2 = new Response2Model();
    private Response3Model model3 = new Response3Model();
    private Response4Model model4 = new Response4Model();
    private List<Response5Model> model5 = new ArrayList<>();
    private List<Response6Model> model6 = new ArrayList<>();
    private List<Response7Model> model7 = new ArrayList<>();

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
            item.setOutcome("/member/simulation");
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
                case SINGLE_CHOICE:
                    break;
                case MULTIPLE_CHOICE:
                    break;
                case SINGLE_SKU_LISTING:
                    break;
                case MULTIPLE_SKU_LISTING:
                    break;
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
                System.out.println("model1: " + model1);
                if (model1 != null) {
                    System.out.println("Decisionchoice: " + model1.getDecisionchoice().getName());
                }
                responseService.updateOrCreate(selectedRecord, model1.toString());
                break;
            case MULTIPLE_CHOICE:
                System.out.println("model2: " + model2);
                model2.getDecisionchoices().forEach(c -> {
                    System.out.println("Decisionchoice: " + c.getName());
                });
                responseService.updateOrCreate(selectedRecord, model2.toString());
                break;
            case SINGLE_SKU_LISTING:
                System.out.println("model3: " + model3);
                if (model3 != null) {
                    System.out.println("Sku: " + model3.getSku().getName());
                }
                responseService.updateOrCreate(selectedRecord, model3.toString());
                break;
            case MULTIPLE_SKU_LISTING:
                System.out.println("model4: " + model4);
                model4.getSkus().forEach(c -> {
                    System.out.println("Sku: " + c.getName());
                });
                responseService.updateOrCreate(selectedRecord, model4.toString());
                break;
            case RANGE_SKU_LISTING:
                System.out.println("model5: " + model5);
                model5.forEach(c -> {
                    System.out.println("Sku: " + c.getSku().getName());
                    if (c.getValue() != null) {
                        System.out.println("Value: " + c.getValue());
                    }
                });
                responseService.updateOrCreate(selectedRecord, model5.toString());
                break;
            case SINGLE_CHOICE_SKU_LISTING:
                System.out.println("model6: " + model6);
                model6.forEach(c -> {
                    System.out.println("Sku: " + c.getSku().getName());
                    if (c.getDecisionchoice() != null) {
                        System.out.println("Decisionchoice: " + c.getDecisionchoice().getName());
                    }
                });
                responseService.updateOrCreate(selectedRecord, model6.toString());
                break;
            case MULTIPLE_CHOICE_SKU_LISTING:
                System.out.println("model7: " + model7);
                model7.forEach(c -> {
                    System.out.println("Sku: " + c.getSku().getName());
                    for (DecisionchoiceEntity decisionchoice : c.getDecisionchoices()) {
                        System.out.println("Decisionchoice: " + decisionchoice.getName());
                    }
                });
                responseService.updateOrCreate(selectedRecord, model7.toString());
                break;
        }

        messageUtil.addGlobalInfoFlashMessage("Updated");

        /**
         * TODO +1 hesaplamasının mantığı yanlış, acil düzelt.
         */
        String navigation = "/member/simulation?id=" + (selectedRecord.getId() + 1) + "&amp;faces-redirect=true";
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

    public Response1Model getModel1() {
        return model1;
    }

    public void setModel1(Response1Model model1) {
        this.model1 = model1;
    }

    public Response2Model getModel2() {
        return model2;
    }

    public void setModel2(Response2Model model2) {
        this.model2 = model2;
    }

    public Response3Model getModel3() {
        return model3;
    }

    public void setModel3(Response3Model model3) {
        this.model3 = model3;
    }

    public Response4Model getModel4() {
        return model4;
    }

    public void setModel4(Response4Model model4) {
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
