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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.faces.event.ActionEvent;

@Named
@ViewScoped
public class ScoreboardView implements Serializable {

    static final Logger LOG = Logger.getLogger(ScoreboardView.class.getName());

    @Inject
    private DecisionService decisionService;
    private List<DecisionEntity> decisions;

    private Map<DecisionEntity, DecisionchoiceEntity> map1 = new HashMap<>();
    private Map<DecisionEntity, List<DecisionchoiceEntity>> map2 = new HashMap<>();
    private Map<DecisionEntity, Map<ProductEntity, Integer>> map3 = new HashMap<>();
    private Map<DecisionEntity, Map<ProductEntity, DecisionchoiceEntity>> map4 = new HashMap<>();
    private Map<DecisionEntity, Map<ProductEntity, List<DecisionchoiceEntity>>> map5 = new HashMap<>();

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

        for (Map.Entry<DecisionEntity, DecisionchoiceEntity> map : map1.entrySet()) {
            DecisionEntity key = map.getKey();
            DecisionchoiceEntity value = map.getValue();
            LOG.info("map1 DecisionEntity: " + key);
            LOG.info("map1 DecisionchoiceEntity: " + value);
        }

        for (Map.Entry<DecisionEntity, List<DecisionchoiceEntity>> map : map2.entrySet()) {
            DecisionEntity key = map.getKey();
            List<DecisionchoiceEntity> value = map.getValue();
            LOG.info("map2 DecisionEntity: " + key);
            for (DecisionchoiceEntity e : value) {
                LOG.info("map2 DecisionchoiceEntity: " + e);
            }
        }

        for (Map.Entry<DecisionEntity, Map<ProductEntity, Integer>> map : map3.entrySet()) {
            DecisionEntity key = map.getKey();
            Map<ProductEntity, Integer> value = map.getValue();
            LOG.info("map3 DecisionEntity: " + key);
            for (Map.Entry<ProductEntity, Integer> m : value.entrySet()) {
                LOG.info("map3 DecisionchoiceEntity ProductEntity: " + m.getKey());
                LOG.info("map3 DecisionchoiceEntity Integer: " + m.getValue());
            }
        }

        for (Map.Entry<DecisionEntity, Map<ProductEntity, DecisionchoiceEntity>> map : map4.entrySet()) {
            DecisionEntity key = map.getKey();
            Map<ProductEntity, DecisionchoiceEntity> value = map.getValue();
            LOG.info("map4 DecisionEntity: " + key);
            for (Map.Entry<ProductEntity, DecisionchoiceEntity> m : value.entrySet()) {
                LOG.info("map4 DecisionchoiceEntity ProductEntity: " + m.getKey());
                LOG.info("map4 DecisionchoiceEntity DecisionchoiceEntity: " + m.getValue());
            }
        }

        for (Map.Entry<DecisionEntity, Map<ProductEntity, List<DecisionchoiceEntity>>> map : map5.entrySet()) {
            DecisionEntity key = map.getKey();
            Map<ProductEntity, List<DecisionchoiceEntity>> value = map.getValue();
            LOG.info("map5 DecisionEntity: " + key);
            for (Map.Entry<ProductEntity, List<DecisionchoiceEntity>> m : value.entrySet()) {
                LOG.info("map5 DecisionchoiceEntity ProductEntity: " + m.getKey());
                List<DecisionchoiceEntity> v = m.getValue();
                for (DecisionchoiceEntity d : v) {
                    LOG.info("map5 DecisionchoiceEntity DecisionchoiceEntity: " + d);
                }
            }
        }

        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public List<DecisionEntity> getDecisions() {
        return decisions;
    }

    public Map<DecisionEntity, DecisionchoiceEntity> getMap1() {
        return map1;
    }

    public void setMap1(Map<DecisionEntity, DecisionchoiceEntity> map1) {
        this.map1 = map1;
    }

    public Map<DecisionEntity, List<DecisionchoiceEntity>> getMap2() {
        return map2;
    }

    public void setMap2(Map<DecisionEntity, List<DecisionchoiceEntity>> map2) {
        this.map2 = map2;
    }

    public Map<DecisionEntity, Map<ProductEntity, Integer>> getMap3() {
        return map3;
    }

    public void setMap3(Map<DecisionEntity, Map<ProductEntity, Integer>> map3) {
        this.map3 = map3;
    }

    public Map<DecisionEntity, Map<ProductEntity, DecisionchoiceEntity>> getMap4() {
        return map4;
    }

    public void setMap4(Map<DecisionEntity, Map<ProductEntity, DecisionchoiceEntity>> map4) {
        this.map4 = map4;
    }

    public Map<DecisionEntity, Map<ProductEntity, List<DecisionchoiceEntity>>> getMap5() {
        return map5;
    }

    public void setMap5(Map<DecisionEntity, Map<ProductEntity, List<DecisionchoiceEntity>>> map5) {
        this.map5 = map5;
    }

}
