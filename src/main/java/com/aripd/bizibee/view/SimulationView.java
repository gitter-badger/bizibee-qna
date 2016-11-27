package com.aripd.bizibee.view;

import com.aripd.bizibee.comparison.ComparisonDecisionSortOrderAsc;
import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.DecisionchoiceEntity;
import com.aripd.bizibee.entity.ResponseEntity;
import com.aripd.bizibee.entity.SkuEntity;
import com.aripd.bizibee.model.response.Response1Model;
import com.aripd.bizibee.model.response.Response2Model;
import com.aripd.bizibee.model.response.Response3Model;
import com.aripd.bizibee.model.response.Response4Model;
import com.aripd.bizibee.model.response.Response5Model;
import com.aripd.bizibee.model.response.Response7Model;
import com.aripd.bizibee.model.response.Response6Model;
import com.aripd.bizibee.model.response.ResponseConverter;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import com.aripd.bizibee.service.DecisionService;
import com.aripd.bizibee.service.DecisionchoiceService;
import com.aripd.bizibee.service.ResponseService;
import com.aripd.bizibee.service.SkuService;
import com.aripd.util.RequestUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

@Named
@ViewScoped
public class SimulationView implements Serializable {

    private MenuModel menuModel;

    @Inject
    private ResponseService responseService;
    private boolean disabled;

    @Inject
    private SkuService skuService;

    @Inject
    private DecisionchoiceService decisionchoiceService;

    @Inject
    private DecisionService decisionService;
    private DecisionEntity selectedRecord;
    private List<DecisionEntity> decisions;

    private Integer sequence;

    private Response1Model model1 = new Response1Model();
    private Response2Model model2 = new Response2Model();
    private Response3Model model3 = new Response3Model();
    private Response4Model model4 = new Response4Model();
    private List<Response5Model> model5 = new ArrayList<>();
    private List<Response6Model> model6 = new ArrayList<>();
    private List<Response7Model> model7 = new ArrayList<>();

    @Inject
    MessageUtil messageUtil;

    public SimulationView() {
        selectedRecord = new DecisionEntity();
        menuModel = new DefaultMenuModel();
    }

    @PostConstruct
    public void init() {
        decisions = decisionService.findAll();
        Collections.sort(decisions, new ComparisonDecisionSortOrderAsc());

        for (DecisionEntity decision : decisions) {
            menuModel.addElement(new DefaultMenuItem(decision.getName()));
        }

    }

    public void onLoad() {
        try {
            selectedRecord = decisions.get(sequence);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException ex) {
            sequence = 0;
            selectedRecord = decisions.get(sequence);
        }

        for (SkuEntity sku : selectedRecord.getSkus()) {
            switch (selectedRecord.getDecisionType()) {
                case SINGLE_CHOICE:
                    model1 = new Response1Model();
                    break;
                case MULTIPLE_CHOICE:
                    model2 = new Response2Model();
                    break;
                case SINGLE_SKU_LISTING:
                    model3 = new Response3Model();
                    break;
                case MULTIPLE_SKU_LISTING:
                    model4 = new Response4Model();
                    break;
                case RANGE_SKU_LISTING:
                    model5.clear();
                    model5.add(new Response5Model(sku));
                    break;
                case SINGLE_CHOICE_SKU_LISTING:
                    model6.clear();
                    model6.add(new Response6Model(sku));
                    break;
                case MULTIPLE_CHOICE_SKU_LISTING:
                    model7.clear();
                    model7.add(new Response7Model(sku));
                    break;
            }
        }

        ResponseEntity response = responseService.findOneByDecision(selectedRecord);
        if (response == null) {
            // cevaplanmamış
            disabled = false;
        } else {
            // cevaplanmış
            disabled = true;

            String outcome = response.getOutcome();
            DecisionEntity decision = response.getDecision();

            JsonObject jsonObject1;
            JsonArray jsonArray1;

            Long skuId;
            SkuEntity sku;

            Long decisionchoiceId;
            DecisionchoiceEntity decisionchoice;

            int value;

            switch (decision.getDecisionType()) {
                case SINGLE_CHOICE:
                    model1 = new Response1Model();
                    jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);

                    decisionchoiceId = jsonObject1.getJsonNumber("id").longValue();
                    decisionchoice = decisionchoiceService.find(decisionchoiceId);
                    model1.setDecisionchoice(decisionchoice);
                    break;
                case MULTIPLE_CHOICE:
                    model2 = new Response2Model();
                    List<DecisionchoiceEntity> decisionchoices = new ArrayList<>();
                    jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);
                    jsonArray1 = jsonObject1.getJsonArray("decisionchoices");
                    for (JsonValue jsonValue1 : jsonArray1) {
                        JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                        decisionchoiceId = jsonObject2.getJsonNumber("id").longValue();
                        decisionchoice = decisionchoiceService.find(decisionchoiceId);
                        decisionchoices.add(decisionchoice);
                    }
                    model2.setDecisionchoices(decisionchoices);
                    break;
                case SINGLE_SKU_LISTING:
                    model3 = new Response3Model();
                    jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);

                    skuId = jsonObject1.getJsonNumber("id").longValue();
                    sku = skuService.find(skuId);
                    model3.setSku(sku);
                    break;
                case MULTIPLE_SKU_LISTING:
                    model4 = new Response4Model();
                    List<SkuEntity> skus = new ArrayList<>();
                    jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);
                    jsonArray1 = jsonObject1.getJsonArray("skus");
                    for (JsonValue jsonValue1 : jsonArray1) {
                        JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                        skuId = jsonObject2.getJsonNumber("id").longValue();
                        sku = skuService.find(skuId);
                        skus.add(sku);
                    }
                    model4.setSkus(skus);
                    break;
                case RANGE_SKU_LISTING:
                    model5 = new ArrayList<>();
                    jsonArray1 = ResponseConverter.jsonArrayFromString(outcome);
                    for (JsonValue jsonValue1 : jsonArray1) {
                        JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                        skuId = jsonObject2.getJsonNumber("sku").longValue();
                        sku = skuService.find(skuId);

                        value = jsonObject2.getJsonNumber("value").intValue();

                        Response5Model m = new Response5Model(sku);
                        m.setValue(value);
                        model5.add(m);
                    }
                    break;
                case SINGLE_CHOICE_SKU_LISTING:
                    model6 = new ArrayList<>();
                    jsonArray1 = ResponseConverter.jsonArrayFromString(outcome);
                    for (JsonValue jsonValue1 : jsonArray1) {
                        JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                        skuId = jsonObject2.getJsonNumber("sku").longValue();
                        sku = skuService.find(skuId);

                        decisionchoiceId = jsonObject2.getJsonNumber("decisionchoice").longValue();
                        decisionchoice = decisionchoiceService.find(decisionchoiceId);

                        Response6Model m = new Response6Model(sku);
                        m.setDecisionchoice(decisionchoice);
                        model6.add(m);
                    }
                    break;
                case MULTIPLE_CHOICE_SKU_LISTING:
                    model7 = new ArrayList<>();
                    jsonArray1 = ResponseConverter.jsonArrayFromString(outcome);
                    for (JsonValue jsonValue1 : jsonArray1) {
                        JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                        skuId = jsonObject2.getJsonNumber("sku").longValue();
                        sku = skuService.find(skuId);

                        List<DecisionchoiceEntity> decisionchoices___ = new ArrayList<>();
                        JsonArray jsonArray2 = jsonObject2.getJsonArray("decisionchoices");
                        for (JsonValue jsonValue2 : jsonArray2) {
                            JsonObject jsonObject3 = ResponseConverter.jsonObjectFromString(jsonValue2.toString());
                            decisionchoiceId = jsonObject3.getJsonNumber("decisionchoice").longValue();
                            decisionchoice = decisionchoiceService.find(decisionchoiceId);
                            decisionchoices___.add(decisionchoice);
                        }

                        Response7Model m = new Response7Model(sku);
                        m.setDecisionchoices(decisionchoices___);
                        model7.add(m);
                    }
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
                // TODO prevent updating...
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

        try {
            sequence++;
            DecisionEntity dec = decisions.get(sequence);
            System.out.println("sequence: " + sequence);
            System.out.println("decisions.size(): " + decisions.size());

            String navigation = "/player/simulation?sequence=" + sequence + "&amp;faces-redirect=true";
            RequestUtil.doNavigate(navigation);
        } catch (ArrayIndexOutOfBoundsException ex) {
            String navigation = "/player/report?amp;faces-redirect=true";
            RequestUtil.doNavigate(navigation);
        }
    }

    public DecisionEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(DecisionEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<DecisionEntity> getDecisions() {
        return decisions;
    }

    public void setDecisions(List<DecisionEntity> decisions) {
        this.decisions = decisions;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
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

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

}
