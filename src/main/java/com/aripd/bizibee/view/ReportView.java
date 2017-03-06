package com.aripd.bizibee.view;

import com.aripd.bizibee.entity.QuestionEntity;
import com.aripd.bizibee.entity.AnswerEntity;
import com.aripd.bizibee.entity.Kind;
import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.ResponseEntity;
import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.model.data.LazyUserDataModelBySimulation;
import com.aripd.bizibee.model.response.ResponseConverter;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.aripd.bizibee.service.ResponseService;
import com.aripd.bizibee.service.UserService;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import org.primefaces.model.LazyDataModel;
import com.aripd.bizibee.service.AnswerService;
import com.aripd.bizibee.service.QuestionService;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@Named
@ViewScoped
public class ReportView implements Serializable {

    @Inject
    private UserService userService;
    private LazyDataModel<UserEntity> lazyModelPlayer;
    private UserEntity user;
    private SimulationEntity simulation;

    private Long id;
    private UserEntity selectedPlayer;

    @Inject
    private ResponseService responseService;
    private ResponseEntity selectedRecord;

    @Inject
    private QuestionService questionService;

    @Inject
    private AnswerService answerService;

    double score = 0;
    double scoreChange = 0;
    double sales = 0;
    double budget = 0;
    double budgetChange = 0;

    double usgWeighted = 0;
    double usg = 0;
    double usgChange = 0;
    double gmWeighted = 0;
    double gm = 0;
    double gmChange = 0;
    double msWeighted = 0;
    double ms = 0;
    double msChange = 0;

    @Inject
    MessageUtil messageUtil;

    public ReportView() {
        selectedRecord = new ResponseEntity();
    }

    @PostConstruct
    public void init() {
        user = userService.getCurrentUser();
        simulation = user.getSimulation();
        lazyModelPlayer = new LazyUserDataModelBySimulation(userService, simulation);

        score = simulation.getScoreStart();
        scoreChange = 0;
        sales = simulation.getSalesStart();
        budget = simulation.getBudgetStart();
        budgetChange = 0;

        gmWeighted = simulation.getGmWeighted();
        gm = simulation.getGmStart();
        gmChange = 0;
        msWeighted = simulation.getMsWeighted();
        ms = simulation.getMsStart();
        msChange = 0;
        usgWeighted = simulation.getUsgWeighted();
        usg = 0;
        usgChange = 0;
    }

    public void onLoad() {
        if (id == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedPlayer = userService.find(id);

        if (selectedPlayer == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Unknown record.");
            return;
        }

    }

    public String calculateScore(ResponseEntity response) {
        String outcome = response.getOutcome();
        QuestionEntity question = response.getQuestion();

        JsonObject jsonObject1;
        JsonArray jsonArray1;

        Long answerId;
        AnswerEntity answer;

        int value;

        double scoreLocal = 0;
        double budgetLocal = 0;
        double gmLocal = 0;
        double msLocal = 0;
        double usgLocal = 0;

        switch (question.getType()) {
            case SINGLE_CHOICE:
                jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);

                try {
                    answerId = jsonObject1.getJsonNumber("id").longValue();
                    answer = answerService.find(answerId);
                    score += answer.getCoefScore();
                    scoreLocal = answer.getCoefScore();
                    budget += answer.getCoefBudget();
                    budgetLocal = answer.getCoefBudget();
                    usg += answer.getCoefUsg();
                    usgLocal = answer.getCoefUsg();
                    gm += answer.getCoefGm();
                    gmLocal = answer.getCoefGm();
                    ms += answer.getCoefMs();
                    msLocal = answer.getCoefMs();
                } catch (NullPointerException ex) {
                }

                scoreChange = scoreLocal;
                budgetChange = budgetLocal;
                usgChange = usgLocal;
                gmChange = gmLocal;
                msChange = msLocal;
                sales += sales * usgChange;
                break;
            case MULTIPLE_CHOICE:
                jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);
                jsonArray1 = jsonObject1.getJsonArray("answers");
                if (jsonArray1 != null && jsonArray1.size() > 0) {
                    for (JsonValue jsonValue1 : jsonArray1) {
                        JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                        answerId = jsonObject2.getJsonNumber("id").longValue();
                        answer = answerService.find(answerId);
                        score += answer.getCoefScore();
                        scoreLocal += answer.getCoefScore();
                        budget += answer.getCoefBudget();
                        budgetLocal += answer.getCoefBudget();
                        usg += answer.getCoefUsg();
                        usgLocal += answer.getCoefUsg();
                        gm += answer.getCoefGm();
                        gmLocal += answer.getCoefGm();
                        ms += answer.getCoefMs();
                        msLocal += answer.getCoefMs();
                    }
                } else {
                    score += question.getCoefScore();
                    scoreLocal += question.getCoefScore();
                    budget += question.getCoefBudget();
                    budgetLocal += question.getCoefBudget();
                    usg += question.getCoefUsg();
                    usgLocal += question.getCoefUsg();
                    gm += question.getCoefGm();
                    gmLocal += question.getCoefGm();
                    ms += question.getCoefMs();
                    msLocal += question.getCoefMs();
                }

                scoreChange = scoreLocal;
                budgetChange = budgetLocal;
                usgChange = usgLocal;
                gmChange = gmLocal;
                msChange = msLocal;
                sales += sales * usgChange;
                break;
            case RANGE_CHOICE:
                jsonArray1 = ResponseConverter.jsonArrayFromString(outcome);
                for (JsonValue jsonValue1 : jsonArray1) {
                    JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                    answerId = jsonObject2.getJsonNumber("answer").longValue();
                    answer = answerService.find(answerId);

                    try {
                        value = jsonObject2.getJsonNumber("value").intValue();
                    } catch (NullPointerException | ClassCastException ex) {
                        // TODO bunun yerine default olarak answer.getIndexMin() girilebilir
                        value = answer.getCoefIndexMin();
                    }

                    score += answer.getCoefScore();
                    scoreLocal += answer.getCoefScore();
                    budget += answer.getCoefBudget();
                    budgetLocal += answer.getCoefBudget();
                    usg += ((answer.getCoefIndexMax() - value) * (answer.getCoefUsgGainMax() - answer.getCoefUsgGainMin()) / (answer.getCoefIndexMax() - answer.getCoefIndexMin())) + answer.getCoefUsgGainMin();
                    usgLocal += ((answer.getCoefIndexMax() - value) * (answer.getCoefUsgGainMax() - answer.getCoefUsgGainMin()) / (answer.getCoefIndexMax() - answer.getCoefIndexMin())) + answer.getCoefUsgGainMin();
                    gm += answer.getCoefGmGainMin() + ((value - answer.getCoefIndexMin()) * (answer.getCoefGmGainMax() - answer.getCoefGmGainMin()) / ((answer.getCoefIndexMax() + answer.getCoefIndexMin()) / 2 - (answer.getCoefIndexMin())));
                    gmLocal += answer.getCoefGmGainMin() + ((value - answer.getCoefIndexMin()) * (answer.getCoefGmGainMax() - answer.getCoefGmGainMin()) / ((answer.getCoefIndexMax() + answer.getCoefIndexMin()) / 2 - (answer.getCoefIndexMin())));
                    if (value >= answer.getCoefIndexMin() && value < answer.getCoefMsBreakpointIndexMin()) {
                        ms += 0;
                        msLocal += 0;
                    } else if (value >= answer.getCoefMsBreakpointIndexMin() && value < answer.getCoefMsBreakpointIndexMax()) {
                        ms += answer.getCoefMsGainMax() + ((value - answer.getCoefMsBreakpointIndexMin()) * (answer.getCoefMsGainMin() - answer.getCoefMsGainMax())) / (answer.getCoefMsBreakpointIndexMax() - answer.getCoefMsBreakpointIndexMin());
                        msLocal += answer.getCoefMsGainMax() + ((value - answer.getCoefMsBreakpointIndexMin()) * (answer.getCoefMsGainMin() - answer.getCoefMsGainMax())) / (answer.getCoefMsBreakpointIndexMax() - answer.getCoefMsBreakpointIndexMin());
                    } else {
                        ms += answer.getCoefMsGainMin();
                        msLocal += answer.getCoefMsGainMin();
                    }
                }

                scoreChange = scoreLocal;
                budgetChange = budgetLocal;
                usgChange = usgLocal;
                gmChange = gmLocal;
                msChange = msLocal;
                sales += sales * usgChange;
                break;
            case PLANOGRAM1:
            case PLANOGRAM2:
            case FILE_UPLOAD:
                break;
        }

        /**
         * Market Share cannot be greater than 100%
         */
        if (ms > 1) {
            ms = 1;
        }

        return outcome;
    }

    public List<QuestionEntity> getQuestions() {
        return questionService.findAll();
    }

    public List<ResponseEntity> getResponses(UserEntity u) {
        if (u != null) {
            return responseService.findByUser(u);
        } else {
            return responseService.findByUser(user);
        }
    }

    public BarChartModel getBarModel1(UserEntity u) {
        List<ResponseEntity> responses;
        if (u != null) {
            responses = responseService.findByUser(u);
        } else {
            responses = responseService.findByUser(user);
        }

        BarChartModel model = new BarChartModel();
        model.setTitle("Revenue Chart");
//        model.setLegendPosition("ne");
//        Axis xAxis = model.getAxis(AxisType.X);
//        xAxis.setLabel("Gender");
//        Axis yAxis = model.getAxis(AxisType.Y);
//        yAxis.setLabel("Births");
//        yAxis.setMin(0);
//        yAxis.setMax(200);

        ChartSeries series1 = new ChartSeries();
//        series1.setLabel("Revenue");
        series1.set("Initial", sales);
//        series1.set("2004", 120);
//        series1.set("2005", 100);
//        series1.set("2006", 44);
//        series1.set("2007", 150);
//        series1.set("2008", 25);

        responses
                .stream()
                .filter(i -> i.getQuestion().getKind().equals(Kind.SIMULATION))
                .forEach(i -> {
                    series1.set(i.getQuestion().getName(), sales + (i.getId() * 100));
                });

        model.addSeries(series1);

        return model;
    }

    public LineChartModel getLineModel1(UserEntity u) {
        List<ResponseEntity> responses;
        if (u != null) {
            responses = responseService.findByUser(u);
        } else {
            responses = responseService.findByUser(user);
        }

        LineChartModel model = new LineChartModel();
        model.setTitle("Revenue Chart");
//        model.setLegendPosition("e");
//        Axis yAxis = model.getAxis(AxisType.Y);
//        yAxis.setMin(0);
//        yAxis.setMax(10);

        LineChartSeries series1 = new LineChartSeries();
//        series1.setLabel("Revenue");
        series1.set(0, sales);
//        series1.set(1, 2);
//        series1.set(2, 1);
//        series1.set(3, 3);
//        series1.set(4, 6);
//        series1.set(5, 8);

        responses
                .stream()
                .filter(i -> i.getQuestion().getKind().equals(Kind.SIMULATION))
                .forEach(i -> {
                    series1.set(i.getId(), sales + (i.getId() * 100));
                });

        model.addSeries(series1);

        return model;
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

    public double getGmChange() {
        return gmChange;
    }

    public void setGmChange(double gmChange) {
        this.gmChange = gmChange;
    }

    public double getMsChange() {
        return msChange;
    }

    public void setMsChange(double msChange) {
        this.msChange = msChange;
    }

    public double getUsgChange() {
        return usgChange;
    }

    public void setUsgChange(double usgChange) {
        this.usgChange = usgChange;
    }

    public double getBudgetChange() {
        return budgetChange;
    }

    public void setBudgetChange(double budgetChange) {
        this.budgetChange = budgetChange;
    }

    public LazyDataModel<UserEntity> getLazyModelPlayer() {
        return lazyModelPlayer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(UserEntity selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScoreChange() {
        return scoreChange;
    }

    public void setScoreChange(double scoreChange) {
        this.scoreChange = scoreChange;
    }

    public double getUsgWeighted() {
        return usgWeighted;
    }

    public void setUsgWeighted(double usgWeighted) {
        this.usgWeighted = usgWeighted;
    }

    public double getGmWeighted() {
        return gmWeighted;
    }

    public void setGmWeighted(double gmWeighted) {
        this.gmWeighted = gmWeighted;
    }

    public double getMsWeighted() {
        return msWeighted;
    }

    public void setMsWeighted(double msWeighted) {
        this.msWeighted = msWeighted;
    }

}
