package com.aripd.bizibee.view;

import com.aripd.bizibee.comparison.ComparisonDecisionSortOrderAsc;
import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.DecisionchoiceEntity;
import com.aripd.bizibee.entity.ResponseEntity;
import com.aripd.bizibee.entity.SkuEntity;
import com.aripd.bizibee.entity.UserEntity;
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
import com.aripd.bizibee.service.UserService;
import com.aripd.util.RequestUtil;
import java.util.ArrayList;
import java.util.Collections;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

@Named
@ViewScoped
public class SimulationView implements Serializable {

    private final MenuModel menuModel;

    @Inject
    private UserService userService;
    private UserEntity user;

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

    private String uuid;
    private Integer sequence;

    private Response1Model model1;
    private Response2Model model2;
    private Response3Model model3;
    private Response4Model model4;
    private List<Response5Model> model5;
    private List<Response6Model> model6;
    private List<Response7Model> model7;

    @Inject
    MessageUtil messageUtil;

    public SimulationView() {
        selectedRecord = new DecisionEntity();
        menuModel = new DefaultMenuModel();
    }

    @PostConstruct
    public void init() {
        user = userService.getCurrentUser();

        decisions = decisionService.findAll();
        Collections.sort(decisions, new ComparisonDecisionSortOrderAsc());

        for (DecisionEntity decision : decisions) {
            menuModel.addElement(new DefaultMenuItem(decision.getName()));
        }
    }

    public void onLoad() {
        if (uuid == null) {
            sequence = 0;
            selectedRecord = decisions.get(sequence);
        } else {
            selectedRecord = decisionService.findOneByUuid(uuid);
            sequence = decisions.indexOf(selectedRecord);
        }

        model1 = new Response1Model();
        model2 = new Response2Model();
        model3 = new Response3Model();
        model4 = new Response4Model();
        model5 = new ArrayList<>();
        model6 = new ArrayList<>();
        model7 = new ArrayList<>();

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
                case INPUT_SKU_LISTING:
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

        ResponseEntity response = responseService.findOneByUserAndDecision(user, selectedRecord);
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

                    try {
                        decisionchoiceId = jsonObject1.getJsonNumber("id").longValue();
                        decisionchoice = decisionchoiceService.find(decisionchoiceId);
                        model1.setDecisionchoice(decisionchoice);
                    } catch (NullPointerException ex) {
                        model1.setDecisionchoice(null);
                    }

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

                    try {
                        skuId = jsonObject1.getJsonNumber("id").longValue();
                        sku = skuService.find(skuId);
                        model3.setSku(sku);
                    } catch (NullPointerException ex) {
                        model3.setSku(null);
                    }

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
                case INPUT_SKU_LISTING:
                    model5 = new ArrayList<>();
                    jsonArray1 = ResponseConverter.jsonArrayFromString(outcome);
                    for (JsonValue jsonValue1 : jsonArray1) {
                        JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                        skuId = jsonObject2.getJsonNumber("sku").longValue();
                        sku = skuService.find(skuId);

                        Response5Model m = new Response5Model(sku);

                        try {
                            value = jsonObject2.getJsonNumber("value").intValue();
                            m.setValue(value);
                        } catch (NullPointerException | ClassCastException ex) {
                            // TODO bunun yerine default olarak sku.getIndexMin() girilebilir
                            value = sku.getIndexMin();
                        }

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

                        Response6Model m = new Response6Model(sku);

                        try {
                            decisionchoiceId = jsonObject2.getJsonNumber("decisionchoice").longValue();
                            decisionchoice = decisionchoiceService.find(decisionchoiceId);
                            m.setDecisionchoice(decisionchoice);
                        } catch (NullPointerException | ClassCastException ex) {
                            m.setDecisionchoice(null);
                        }

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
        ResponseEntity entity = responseService.findOneByUserAndDecision(user, selectedRecord);
        if (entity != null) {
            messageUtil.addGlobalErrorFlashMessage("You cannot submit more than once");
//            throw new FacesException("You cannot submit more than once");
        } else {

            ResponseEntity response = new ResponseEntity();
            response.setUser(user);
            response.setDecision(selectedRecord);

            switch (selectedRecord.getDecisionType()) {
                case SINGLE_CHOICE:
                    response.setOutcome(model1.toString());
                    break;
                case MULTIPLE_CHOICE:
                    response.setOutcome(model2.toString());
                    break;
                case SINGLE_SKU_LISTING:
                    response.setOutcome(model3.toString());
                    break;
                case MULTIPLE_SKU_LISTING:
                    response.setOutcome(model4.toString());
                    break;
                case RANGE_SKU_LISTING:
                case INPUT_SKU_LISTING:
                    response.setOutcome(model5.toString());
                    break;
                case SINGLE_CHOICE_SKU_LISTING:
                    response.setOutcome(model6.toString());
                    break;
                case MULTIPLE_CHOICE_SKU_LISTING:
                    response.setOutcome(model7.toString());
                    break;
            }

            responseService.create(response);
            messageUtil.addGlobalInfoFlashMessage("Updated");
        }

        String navigation = "/player/simulation?uuid=" + selectedRecord.getUuid() + "&amp;faces-redirect=true";
        RequestUtil.doNavigate(navigation);
    }

    public String goNext() {
        try {
            DecisionEntity decision = getNext(selectedRecord);
            return "/player/simulation?uuid=" + decision.getUuid() + "&amp;faces-redirect=true";
        } catch (NullPointerException ex) {
            return "/player/report?faces-redirect=true";
        }
    }

    public String goPrevious() {
        try {
            DecisionEntity decision = getPrevious(selectedRecord);
            return "/player/simulation?uuid=" + decision.getUuid() + "&amp;faces-redirect=true";
        } catch (NullPointerException ex) {
            return "/player/report?faces-redirect=true";
        }
    }

    private DecisionEntity getNext(DecisionEntity decision) {
        int idx = decisions.indexOf(decision);
        if (idx < 0 || idx + 1 == decisions.size()) {
            return null;
        }
        return decisions.get(idx + 1);
    }

    private DecisionEntity getPrevious(DecisionEntity decision) {
        int idx = decisions.indexOf(decision);
        if (idx <= 0) {
            return null;
        }
        return decisions.get(idx - 1);
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
