package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.DecisionEntity;
import com.aripd.bizibee.entity.DecisionchoiceEntity;
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

    private Map<DecisionEntity, DecisionchoiceEntity> map = new HashMap<>();
    private DecisionchoiceEntity selectedDecisionchoice;
    private List<DecisionchoiceEntity> selectedDecisionchoices;

    private int index;

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
        LOG.info("map: " + map);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public List<DecisionEntity> getDecisions() {
        return decisions;
    }

    public DecisionchoiceEntity getSelectedDecisionchoice() {
        return selectedDecisionchoice;
    }

    public void setSelectedDecisionchoice(DecisionchoiceEntity selectedDecisionchoice) {
        this.selectedDecisionchoice = selectedDecisionchoice;
    }

    public List<DecisionchoiceEntity> getSelectedDecisionchoices() {
        return selectedDecisionchoices;
    }

    public void setSelectedDecisionchoices(List<DecisionchoiceEntity> selectedDecisionchoices) {
        this.selectedDecisionchoices = selectedDecisionchoices;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Map<DecisionEntity, DecisionchoiceEntity> getMap() {
        return map;
    }

    public void setMap(Map<DecisionEntity, DecisionchoiceEntity> map) {
        this.map = map;
    }

}
