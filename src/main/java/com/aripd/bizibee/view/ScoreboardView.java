package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.DecisionchoiceEntity;
import com.aripd.bizibee.entity.SkuEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import com.aripd.bizibee.service.DecisionService;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.event.ActionEvent;
import com.aripd.bizibee.service.SkuService;

@Named
@ViewScoped
public class ScoreboardView implements Serializable {

    static final Logger LOG = Logger.getLogger(ScoreboardView.class.getName());

    @Inject
    private DecisionService decisionService;
    private List<DecisionEntity> decisions;

    private HashMap<DecisionEntity, DecisionchoiceEntity> map1 = new HashMap<>();
    private HashMap<DecisionEntity, ArrayList<DecisionchoiceEntity>> map2 = new HashMap<>();
    private HashMap<DecisionEntity, SkuEntity> map3 = new HashMap<>();
    private HashMap<DecisionEntity, ArrayList<SkuEntity>> map4 = new HashMap<>();
    private HashMap<DecisionEntity, HashMap<SkuEntity, Integer>> map5 = new HashMap<>();
    private HashMap<DecisionEntity, HashMap<SkuEntity, DecisionchoiceEntity>> map6 = new HashMap<>();
    private HashMap<DecisionEntity, HashMap<SkuEntity, ArrayList<DecisionchoiceEntity>>> map7 = new HashMap<>();

    @Inject
    private SkuService skuService;

    @Inject
    MessageUtil messageUtil;

    public ScoreboardView() {
        decisions = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        decisions = decisionService.findAll();
    }

    public void doUpdate(ActionEvent actionEvent) {
        LOG.info("map1: " + map1);
        LOG.info("map2: " + map2);
        LOG.info("map3: " + map3);
        LOG.info("map4: " + map4);
        LOG.info("map5: " + map5);
        LOG.info("map6: " + map6);
        LOG.info("map7: " + map7);

        for (HashMap.Entry<DecisionEntity, DecisionchoiceEntity> map : map1.entrySet()) {
            DecisionEntity key = map.getKey();
            DecisionchoiceEntity value = map.getValue();
            LOG.info("map1 DecisionEntity: " + key);
            LOG.info("map1 DecisionchoiceEntity: " + value);
        }

        for (HashMap.Entry<DecisionEntity, ArrayList<DecisionchoiceEntity>> map : map2.entrySet()) {
            DecisionEntity key = map.getKey();
            ArrayList<DecisionchoiceEntity> value = map.getValue();
            LOG.info("map2 DecisionEntity: " + key);
            for (DecisionchoiceEntity e : value) {
                LOG.info("map2 DecisionchoiceEntity: " + e);
            }
        }

        for (HashMap.Entry<DecisionEntity, SkuEntity> map : map3.entrySet()) {
            DecisionEntity key = map.getKey();
            SkuEntity value = map.getValue();
            LOG.info("map1 DecisionEntity: " + key);
            LOG.info("map1 SkuEntity: " + value);
        }

        for (HashMap.Entry<DecisionEntity, ArrayList<SkuEntity>> map : map4.entrySet()) {
            DecisionEntity key = map.getKey();
            ArrayList<SkuEntity> value = map.getValue();
            LOG.info("map2 DecisionEntity: " + key);
            for (SkuEntity e : value) {
                LOG.info("map2 SkuEntity: " + e);
            }
        }

        for (HashMap.Entry<DecisionEntity, HashMap<SkuEntity, Integer>> map : map5.entrySet()) {
            DecisionEntity key = map.getKey();
            HashMap<SkuEntity, Integer> value = map.getValue();
            LOG.info("map3 DecisionEntity: " + key);
            for (HashMap.Entry<SkuEntity, Integer> m : value.entrySet()) {
                LOG.info("map3 DecisionchoiceEntity SkuEntity: " + m.getKey());
                LOG.info("map3 DecisionchoiceEntity Integer: " + m.getValue());
            }
        }

        for (HashMap.Entry<DecisionEntity, HashMap<SkuEntity, DecisionchoiceEntity>> map : map6.entrySet()) {
            DecisionEntity key = map.getKey();
            HashMap<SkuEntity, DecisionchoiceEntity> value = map.getValue();
            LOG.info("map4 DecisionEntity: " + key);
            for (HashMap.Entry<SkuEntity, DecisionchoiceEntity> m : value.entrySet()) {
                LOG.info("map4 DecisionchoiceEntity SkuEntity: " + m.getKey());
                LOG.info("map4 DecisionchoiceEntity DecisionchoiceEntity: " + m.getValue());
            }
        }

        for (HashMap.Entry<DecisionEntity, HashMap<SkuEntity, ArrayList<DecisionchoiceEntity>>> map : map7.entrySet()) {
            DecisionEntity key = map.getKey();
            HashMap<SkuEntity, ArrayList<DecisionchoiceEntity>> value = map.getValue();
            LOG.info("map5 DecisionEntity: " + key);
            for (HashMap.Entry<SkuEntity, ArrayList<DecisionchoiceEntity>> m : value.entrySet()) {
                LOG.info("map5 DecisionchoiceEntity SkuEntity: " + m.getKey());
                ArrayList<DecisionchoiceEntity> v = m.getValue();
                for (DecisionchoiceEntity d : v) {
                    LOG.info("map5 DecisionchoiceEntity DecisionchoiceEntity: " + d);
                }
            }
        }

        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public List<SkuEntity> getSkus() {
        return skuService.findAll();
    }

    public List<DecisionEntity> getDecisions() {
        return decisions;
    }

    public HashMap<DecisionEntity, DecisionchoiceEntity> getMap1() {
        return map1;
    }

    public void setMap1(HashMap<DecisionEntity, DecisionchoiceEntity> map1) {
        this.map1 = map1;
    }

    public HashMap<DecisionEntity, ArrayList<DecisionchoiceEntity>> getMap2() {
        return map2;
    }

    public void setMap2(HashMap<DecisionEntity, ArrayList<DecisionchoiceEntity>> map2) {
        this.map2 = map2;
    }

    public HashMap<DecisionEntity, SkuEntity> getMap3() {
        return map3;
    }

    public void setMap3(HashMap<DecisionEntity, SkuEntity> map3) {
        this.map3 = map3;
    }

    public HashMap<DecisionEntity, ArrayList<SkuEntity>> getMap4() {
        return map4;
    }

    public void setMap4(HashMap<DecisionEntity, ArrayList<SkuEntity>> map4) {
        this.map4 = map4;
    }

    public HashMap<DecisionEntity, HashMap<SkuEntity, Integer>> getMap5() {
        return map5;
    }

    public void setMap5(HashMap<DecisionEntity, HashMap<SkuEntity, Integer>> map5) {
        this.map5 = map5;
    }

    public HashMap<DecisionEntity, HashMap<SkuEntity, DecisionchoiceEntity>> getMap6() {
        return map6;
    }

    public void setMap6(HashMap<DecisionEntity, HashMap<SkuEntity, DecisionchoiceEntity>> map6) {
        this.map6 = map6;
    }

    public HashMap<DecisionEntity, HashMap<SkuEntity, ArrayList<DecisionchoiceEntity>>> getMap7() {
        return map7;
    }

    public void setMap7(HashMap<DecisionEntity, HashMap<SkuEntity, ArrayList<DecisionchoiceEntity>>> map7) {
        this.map7 = map7;
    }

}
