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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.faces.component.UIOutput;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

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

    public void onChange1(AjaxBehaviorEvent event) {
        DecisionchoiceEntity entity = (DecisionchoiceEntity) ((UIOutput) event.getSource()).getValue();
        LOG.info(entity);
        messageUtil.addGlobalCustomFlashMessage("Changed");
    }

    private DecisionchoiceEntity selectedItem;
    private boolean selectedItemRemoved;

    public void selectedItemsChanged(ValueChangeEvent event) {
        List<DecisionchoiceEntity> oldValue = (List<DecisionchoiceEntity>) event.getOldValue();
        List<DecisionchoiceEntity> newValue = (List<DecisionchoiceEntity>) event.getNewValue();
        LOG.info("oldValue: " + oldValue);
        LOG.info("newValue: " + newValue);

        if (oldValue == null) {
            oldValue = Collections.emptyList();
        }

        if (oldValue.size() > newValue.size()) {
            oldValue = new ArrayList<>(oldValue);
            oldValue.removeAll(newValue);
            selectedItem = oldValue.iterator().next();
            selectedItemRemoved = true;
        } else {
            newValue = new ArrayList<>(newValue);
            newValue.removeAll(oldValue);
            selectedItem = newValue.iterator().next();
            selectedItemRemoved = false;
        }
    }

    public void itemSelected(AjaxBehaviorEvent event) {
        LOG.info("Selected item: " + selectedItem);
        LOG.info("Selected item removed? " + selectedItemRemoved);
    }

    public void doUpdate(ActionEvent actionEvent) {
        LOG.info("map1: " + map1);
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
