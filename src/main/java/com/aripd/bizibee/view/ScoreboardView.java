package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.DecisionchoiceEntity;
import com.aripd.bizibee.entity.ProductEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import com.aripd.bizibee.service.DecisionService;
import com.aripd.bizibee.service.ProductService;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.event.ActionEvent;

@Named
@ViewScoped
public class ScoreboardView implements Serializable {

    static final Logger LOG = Logger.getLogger(ScoreboardView.class.getName());

    @Inject
    private DecisionService decisionService;
    private List<DecisionEntity> decisions;

    private HashMap<DecisionEntity, DecisionchoiceEntity> map1 = new HashMap<>();
    private HashMap<DecisionEntity, ArrayList<DecisionchoiceEntity>> map2 = new HashMap<>();
    private HashMap<DecisionEntity, HashMap<ProductEntity, Integer>> map3 = new HashMap<>();
    private HashMap<DecisionEntity, HashMap<ProductEntity, DecisionchoiceEntity>> map4 = new HashMap<>();
    private HashMap<DecisionEntity, HashMap<ProductEntity, ArrayList<DecisionchoiceEntity>>> map5 = new HashMap<>();

    @Inject
    private ProductService productService;

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

        for (HashMap.Entry<DecisionEntity, HashMap<ProductEntity, Integer>> map : map3.entrySet()) {
            DecisionEntity key = map.getKey();
            HashMap<ProductEntity, Integer> value = map.getValue();
            LOG.info("map3 DecisionEntity: " + key);
            for (HashMap.Entry<ProductEntity, Integer> m : value.entrySet()) {
                LOG.info("map3 DecisionchoiceEntity ProductEntity: " + m.getKey());
                LOG.info("map3 DecisionchoiceEntity Integer: " + m.getValue());
            }
        }

        for (HashMap.Entry<DecisionEntity, HashMap<ProductEntity, DecisionchoiceEntity>> map : map4.entrySet()) {
            DecisionEntity key = map.getKey();
            HashMap<ProductEntity, DecisionchoiceEntity> value = map.getValue();
            LOG.info("map4 DecisionEntity: " + key);
            for (HashMap.Entry<ProductEntity, DecisionchoiceEntity> m : value.entrySet()) {
                LOG.info("map4 DecisionchoiceEntity ProductEntity: " + m.getKey());
                LOG.info("map4 DecisionchoiceEntity DecisionchoiceEntity: " + m.getValue());
            }
        }

        for (HashMap.Entry<DecisionEntity, HashMap<ProductEntity, ArrayList<DecisionchoiceEntity>>> map : map5.entrySet()) {
            DecisionEntity key = map.getKey();
            HashMap<ProductEntity, ArrayList<DecisionchoiceEntity>> value = map.getValue();
            LOG.info("map5 DecisionEntity: " + key);
            for (HashMap.Entry<ProductEntity, ArrayList<DecisionchoiceEntity>> m : value.entrySet()) {
                LOG.info("map5 DecisionchoiceEntity ProductEntity: " + m.getKey());
                ArrayList<DecisionchoiceEntity> v = m.getValue();
                for (DecisionchoiceEntity d : v) {
                    LOG.info("map5 DecisionchoiceEntity DecisionchoiceEntity: " + d);
                }
            }
        }

        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public List<ProductEntity> getProducts() {
        return productService.findAll();
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

    public HashMap<DecisionEntity, HashMap<ProductEntity, Integer>> getMap3() {
        return map3;
    }

    public void setMap3(HashMap<DecisionEntity, HashMap<ProductEntity, Integer>> map3) {
        this.map3 = map3;
    }

    public HashMap<DecisionEntity, HashMap<ProductEntity, DecisionchoiceEntity>> getMap4() {
        return map4;
    }

    public void setMap4(HashMap<DecisionEntity, HashMap<ProductEntity, DecisionchoiceEntity>> map4) {
        this.map4 = map4;
    }

    public HashMap<DecisionEntity, HashMap<ProductEntity, ArrayList<DecisionchoiceEntity>>> getMap5() {
        return map5;
    }

    public void setMap5(HashMap<DecisionEntity, HashMap<ProductEntity, ArrayList<DecisionchoiceEntity>>> map5) {
        this.map5 = map5;
    }

}
