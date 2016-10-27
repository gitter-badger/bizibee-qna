package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.DecisionEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import com.aripd.bizibee.service.DecisionService;
import java.util.ArrayList;

@Named
@ViewScoped
public class ScoreboardView implements Serializable {

    static final Logger LOG = Logger.getLogger(ScoreboardView.class.getName());

    @Inject
    private DecisionService decisionService;
    private List<DecisionEntity> decisions;

    @Inject
    MessageUtil messageUtil;

    public ScoreboardView() {
        decisions = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        decisions = decisionService.findAll();
    }

    public List<DecisionEntity> getDecisions() {
        return decisions;
    }

}
