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
import org.apache.log4j.Logger;
import com.aripd.bizibee.service.ResponseService;
import com.aripd.bizibee.service.SkuService;
import com.aripd.bizibee.service.UserService;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

@Named
@ViewScoped
public class ResultView implements Serializable {

    static final Logger LOG = Logger.getLogger(ResultView.class.getName());

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

    @Inject
    MessageUtil messageUtil;

    public ResultView() {
        selectedRecord = new ResponseEntity();
    }

    @PostConstruct
    public void init() {
        user = userService.getCurrentUser();
        simulation = user.getSimulation();

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
                break;
            case MULTIPLE_CHOICE:
                jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);
                jsonArray1 = jsonObject1.getJsonArray("decisionchoices");
                for (JsonValue jsonValue1 : jsonArray1) {
                    JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                    decisionchoiceId = jsonObject2.getJsonNumber("id").longValue();
                    decisionchoice = decisionchoiceService.find(decisionchoiceId);
                    score += decisionchoice.getGm();
                }
                break;
            case SINGLE_SKU_LISTING:
                jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);

                skuId = jsonObject1.getJsonNumber("id").longValue();
                sku = skuService.find(skuId);
                score += sku.getGm();
                break;
            case MULTIPLE_SKU_LISTING:
                jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);
                jsonArray1 = jsonObject1.getJsonArray("skus");
                for (JsonValue jsonValue1 : jsonArray1) {
                    JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                    skuId = jsonObject2.getJsonNumber("id").longValue();
                    sku = skuService.find(skuId);
                    score += sku.getGm();
                }
                break;
            case RANGE_SKU_LISTING:
                jsonArray1 = ResponseConverter.jsonArrayFromString(outcome);
                for (JsonValue jsonValue1 : jsonArray1) {
                    JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                    skuId = jsonObject2.getJsonNumber("sku").longValue();
                    sku = skuService.find(skuId);
                    score += sku.getGm();

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

                    decisionchoiceId = jsonObject2.getJsonNumber("decisionchoice").longValue();
                    decisionchoice = decisionchoiceService.find(decisionchoiceId);
                    score += decisionchoice.getGm();
                }
                break;
            case MULTIPLE_CHOICE_SKU_LISTING:
                jsonArray1 = ResponseConverter.jsonArrayFromString(outcome);
                for (JsonValue jsonValue1 : jsonArray1) {
                    JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                    skuId = jsonObject2.getJsonNumber("sku").longValue();
                    sku = skuService.find(skuId);
                    score += sku.getGm();

                    JsonArray jsonArray2 = jsonObject2.getJsonArray("decisionchoices");
                    for (JsonValue jsonValue2 : jsonArray2) {
                        JsonObject jsonObject3 = ResponseConverter.jsonObjectFromString(jsonValue2.toString());
                        decisionchoiceId = jsonObject3.getJsonNumber("decisionchoice").longValue();
                        decisionchoice = decisionchoiceService.find(decisionchoiceId);
                        score += decisionchoice.getGm();
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

}
