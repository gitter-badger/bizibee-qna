package com.aripd.bizibee.entity;

import com.aripd.bizibee.model.response.ResponseConverter;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.MultitenantType;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumns;

@Entity
@Multitenant(value = MultitenantType.SINGLE_TABLE)
@TenantDiscriminatorColumns({
    @TenantDiscriminatorColumn(name = "SIMULATION_ID", contextProperty = "eclipselink.tenant-id")
    ,
    @TenantDiscriminatorColumn(name = "USER_ID", contextProperty = "eclipselink.tenant-user-id")
})
@Cacheable(false)
public class ResponseEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "SIMULATION_ID", insertable = false, updatable = false)
    private SimulationEntity simulation;

    @ManyToOne
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private UserEntity user;

    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private DecisionEntity decision;

    /**
     * TODO jsonObjectStr olarak ismini değiştir
     */
    @Column(columnDefinition = "TEXT")
    private String outcome;

    public ResponseEntity() {
    }

    public ResponseEntity(DecisionEntity decision, String outcome) {
        this.decision = decision;
        this.outcome = outcome;
    }

    @Transient
    public double getScore() {
        JsonObject jsonObject;
        JsonArray jsonArray;
        double score = 0;
        switch (decision.getDecisionType()) {
            case SINGLE_CHOICE:
                jsonObject = ResponseConverter.jsonObjectFromString(outcome);
                Long decisionchoiceId = jsonObject.getJsonNumber("id").longValue();
                String decisionchoiceName = jsonObject.getString("name");
                double decisionchoiceGm = jsonObject.getJsonNumber("gm").doubleValue();
                System.out.println("decisionchoiceId: " + decisionchoiceId);
                System.out.println("decisionchoiceName: " + decisionchoiceName);
                System.out.println("decisionchoiceGm: " + decisionchoiceGm);
                score += decisionchoiceGm;
                break;
            case MULTIPLE_CHOICE:
                jsonObject = ResponseConverter.jsonObjectFromString(outcome);
                JsonArray decisionchoicesArray = jsonObject.getJsonArray("decisionchoices");
                for (JsonValue jsonValue : decisionchoicesArray) {
                    System.out.println("jsonValue: " + jsonValue);
//                    JsonObject json = ResponseConverter.jsonObjectFromString(jsonValue.toString());
//                    System.out.println("id: " + json.get("id"));
//                    System.out.println("name: " + json.get("name"));
//                    System.out.println("gm: " + json.get("gm"));
//                    score += Double.valueOf(json.get("gm").toString());
                }
                //score += decision.getGm();
                break;
            case SINGLE_SKU_LISTING:
                jsonObject = ResponseConverter.jsonObjectFromString(outcome);
                Long skuId = jsonObject.getJsonNumber("id").longValue();
                String skuName = jsonObject.getString("name");
                double skuGm = jsonObject.getJsonNumber("gm").doubleValue();
                System.out.println("skuId: " + skuId);
                System.out.println("skuName: " + skuName);
                System.out.println("skuGm: " + skuGm);
                score += skuGm;
                break;
            case MULTIPLE_SKU_LISTING:
                jsonObject = ResponseConverter.jsonObjectFromString(outcome);
                JsonArray skusArray = jsonObject.getJsonArray("skus");
                for (JsonValue jsonValue : skusArray) {
                    System.out.println("jsonValue: " + jsonValue);
//                    JsonObject json = ResponseConverter.jsonObjectFromString(jsonValue.toString());
//                    System.out.println("id: " + json.get("id"));
//                    System.out.println("name: " + json.get("name"));
//                    System.out.println("gm: " + json.get("gm"));
//                    score += Double.valueOf(json.get("gm").toString());
                }
                //score += decision.getGm();
                break;
            case RANGE_SKU_LISTING:
                jsonArray = ResponseConverter.jsonArrayFromString(outcome);
                for (JsonValue jsonValue : jsonArray) {
                    System.out.println("jsonValue: " + jsonValue);
                }
//                score += decision.getGm();
                break;
            case SINGLE_CHOICE_SKU_LISTING:
                jsonArray = ResponseConverter.jsonArrayFromString(outcome);
                for (JsonValue jsonValue : jsonArray) {
                    System.out.println("jsonValue: " + jsonValue);
                }
//                score += decision.getGm();
                break;
            case MULTIPLE_CHOICE_SKU_LISTING:
                jsonArray = ResponseConverter.jsonArrayFromString(outcome);
                for (JsonValue jsonValue : jsonArray) {
                    System.out.println("jsonValue: " + jsonValue);
                }
//                score += decision.getGm();
                break;
        }
        return score;
    }

    public SimulationEntity getSimulation() {
        return simulation;
    }

    public void setSimulation(SimulationEntity simulation) {
        this.simulation = simulation;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public DecisionEntity getDecision() {
        return decision;
    }

    public void setDecision(DecisionEntity decision) {
        this.decision = decision;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

}
