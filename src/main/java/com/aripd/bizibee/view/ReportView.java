package com.aripd.bizibee.view;

import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.DecisionchoiceEntity;
import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.ResponseEntity;
import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.entity.SkuEntity;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.model.response.ResponseConverter;
import com.aripd.bizibee.service.DecisionchoiceService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.aripd.bizibee.service.ResponseService;
import com.aripd.bizibee.service.SkuService;
import com.aripd.bizibee.service.UserService;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

@Named
@ViewScoped
public class ReportView implements Serializable {

    @Inject
    private UserService userService;
    private UserEntity user;
    private SimulationEntity simulation;

    @Inject
    private ResponseService responseService;
    private ResponseEntity selectedRecord;

    @Inject
    private DecisionchoiceService decisionchoiceService;

    @Inject
    private SkuService skuService;

    double sales = 0;
    double budget = 0;
    double gm = 0;
    double ms = 0;
    double usg = 0;

    @Inject
    MessageUtil messageUtil;

    public ReportView() {
        selectedRecord = new ResponseEntity();
    }

    @PostConstruct
    public void init() {
        user = userService.getCurrentUser();
        simulation = user.getSimulation();

        sales = simulation.getSalesStart();
        budget = simulation.getBudgetStart();
        gm = simulation.getGmStart();
        ms = simulation.getMsStart();
        usg = 0;
    }

    public double calculateScore(ResponseEntity response) {
        String outcome = response.getOutcome();
        DecisionEntity decision = response.getDecision();

        JsonObject jsonObject1;
        JsonArray jsonArray1;
        double score = 0;

        Long skuId;
        SkuEntity sku;

        Long decisionchoiceId;
        DecisionchoiceEntity decisionchoice;

        int value;

        switch (decision.getDecisionType()) {
            case SINGLE_CHOICE:
                jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);

                decisionchoiceId = jsonObject1.getJsonNumber("id").longValue();
                decisionchoice = decisionchoiceService.find(decisionchoiceId);
                score += decisionchoice.getGm();
                budget += decisionchoice.getBudget();
                gm += decisionchoice.getGm();
                ms += decisionchoice.getMs();
                usg += decisionchoice.getUsg();
                sales += sales * usg;
                break;
            case MULTIPLE_CHOICE:
                jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);
                jsonArray1 = jsonObject1.getJsonArray("decisionchoices");
                for (JsonValue jsonValue1 : jsonArray1) {
                    JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                    decisionchoiceId = jsonObject2.getJsonNumber("id").longValue();
                    decisionchoice = decisionchoiceService.find(decisionchoiceId);
                    score += decisionchoice.getGm();
                    budget += decisionchoice.getBudget();
                    gm += decisionchoice.getGm();
                    ms += decisionchoice.getMs();
                    usg += decisionchoice.getUsg();
                    sales += sales * usg;
                }
                break;
            case SINGLE_SKU_LISTING:
                jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);

                skuId = jsonObject1.getJsonNumber("id").longValue();
                sku = skuService.find(skuId);
                score += sku.getGm();
                budget += sku.getBudget();
                gm += sku.getGm();
                ms += sku.getMs();
                usg += sku.getUsg();
                sales += sales * usg;
                break;
            case MULTIPLE_SKU_LISTING:
                jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);
                jsonArray1 = jsonObject1.getJsonArray("skus");
                for (JsonValue jsonValue1 : jsonArray1) {
                    JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                    skuId = jsonObject2.getJsonNumber("id").longValue();
                    sku = skuService.find(skuId);
                    score += sku.getGm();
                    budget += sku.getBudget();
                    gm += sku.getGm();
                    ms += sku.getMs();
                    usg += sku.getUsg();
                    sales += sales * usg;
                }
                break;
            case RANGE_SKU_LISTING:
                jsonArray1 = ResponseConverter.jsonArrayFromString(outcome);
                for (JsonValue jsonValue1 : jsonArray1) {
                    JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                    skuId = jsonObject2.getJsonNumber("sku").longValue();
                    sku = skuService.find(skuId);
                    score += sku.getGm();
                    budget += sku.getBudget();
                    gm += sku.getGm();
                    ms += sku.getMs();
                    usg += sku.getUsg();
                    sales += sales * usg;

                    value = jsonObject2.getJsonNumber("value").intValue();
                }
                break;
            case SINGLE_CHOICE_SKU_LISTING:
                jsonArray1 = ResponseConverter.jsonArrayFromString(outcome);
                for (JsonValue jsonValue1 : jsonArray1) {
                    JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                    skuId = jsonObject2.getJsonNumber("sku").longValue();
                    sku = skuService.find(skuId);
                    score += sku.getGm();
                    budget += sku.getBudget();
                    gm += sku.getGm();
                    ms += sku.getMs();
                    usg += sku.getUsg();
                    sales += sales * usg;

                    decisionchoiceId = jsonObject2.getJsonNumber("decisionchoice").longValue();
                    decisionchoice = decisionchoiceService.find(decisionchoiceId);
                    score += decisionchoice.getGm();
                    budget += decisionchoice.getBudget();
                    gm += decisionchoice.getGm();
                    ms += decisionchoice.getMs();
                    usg += decisionchoice.getUsg();
                    sales += sales * usg;
                }
                break;
            case MULTIPLE_CHOICE_SKU_LISTING:
                jsonArray1 = ResponseConverter.jsonArrayFromString(outcome);
                for (JsonValue jsonValue1 : jsonArray1) {
                    JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                    skuId = jsonObject2.getJsonNumber("sku").longValue();
                    sku = skuService.find(skuId);
                    score += sku.getGm();
                    budget += sku.getBudget();
                    gm += sku.getGm();
                    ms += sku.getMs();
                    usg += sku.getUsg();
                    sales += sales * usg;

                    JsonArray jsonArray2 = jsonObject2.getJsonArray("decisionchoices");
                    for (JsonValue jsonValue2 : jsonArray2) {
                        JsonObject jsonObject3 = ResponseConverter.jsonObjectFromString(jsonValue2.toString());
                        decisionchoiceId = jsonObject3.getJsonNumber("decisionchoice").longValue();
                        decisionchoice = decisionchoiceService.find(decisionchoiceId);
                        score += decisionchoice.getGm();
                        budget += decisionchoice.getBudget();
                        gm += decisionchoice.getGm();
                        ms += decisionchoice.getMs();
                        usg += decisionchoice.getUsg();
                        sales += sales * usg;
                    }
                }
                break;
        }
        return score;
    }

    public List<ResponseEntity> getResponses() {
        return responseService.findAll();
    }

    public ResponseEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(ResponseEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public SimulationEntity getSimulation() {
        return simulation;
    }

    public void setSimulation(SimulationEntity simulation) {
        this.simulation = simulation;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getGm() {
        return gm;
    }

    public void setGm(double gm) {
        this.gm = gm;
    }

    public double getMs() {
        return ms;
    }

    public void setMs(double ms) {
        this.ms = ms;
    }

    public double getUsg() {
        return usg;
    }

    public void setUsg(double usg) {
        this.usg = usg;
    }

}
