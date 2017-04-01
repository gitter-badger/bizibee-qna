package com.aripd.bizibee.view;

import com.aripd.bizibee.entity.AnswerEntity;
import com.aripd.bizibee.entity.Kind;
import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.ResponseEntity;
import com.aripd.bizibee.entity.SimulationEntity;
import com.aripd.bizibee.entity.UserEntity;
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
import com.aripd.bizibee.service.AnswerService;
import com.aripd.bizibee.service.QuestionService;
import java.util.Arrays;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LinearAxis;

@Named
@ViewScoped
public class ChartView implements Serializable {

    @Inject
    private UserService userService;
    private UserEntity user;
    private SimulationEntity simulation;

    @Inject
    private ResponseService responseService;

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

    public ChartView() {
    }

    @PostConstruct
    public void init() {
        user = userService.getCurrentUser();
        simulation = user.getSimulation();

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

    private double response2Revenue(ResponseEntity response) {
        JsonObject jsonObject1;
        JsonArray jsonArray1;

        Long answerId;
        AnswerEntity answer;

        int value;

        double usgLocal = 0;

        switch (response.getQuestion().getType()) {
            case SINGLE_CHOICE:
                jsonObject1 = ResponseConverter.jsonObjectFromString(response.getOutcome());

                try {
                    answerId = jsonObject1.getJsonNumber("id").longValue();
                    answer = answerService.find(answerId);
                    usgLocal = answer.getCoefUsg();
                } catch (NullPointerException ex) {
                }
                usgChange = usgLocal;
                sales += sales * usgChange;
                break;
            case MULTIPLE_CHOICE:
                jsonObject1 = ResponseConverter.jsonObjectFromString(response.getOutcome());
                jsonArray1 = jsonObject1.getJsonArray("answers");
                if (jsonArray1 != null && jsonArray1.size() > 0) {
                    for (JsonValue jsonValue1 : jsonArray1) {
                        JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                        answerId = jsonObject2.getJsonNumber("id").longValue();
                        answer = answerService.find(answerId);
                        usgLocal += answer.getCoefUsg();
                    }
                } else {
                    usgLocal += response.getQuestion().getCoefUsg();
                }

                usgChange = usgLocal;
                sales += sales * usgChange;
                break;
            case RANGE_CHOICE:
                jsonArray1 = ResponseConverter.jsonArrayFromString(response.getOutcome());
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

                    usgLocal += ((answer.getCoefIndexMax() - value) * (answer.getCoefUsgGainMax() - answer.getCoefUsgGainMin()) / (answer.getCoefIndexMax() - answer.getCoefIndexMin())) + answer.getCoefUsgGainMin();
                }

                usgChange = usgLocal;
                sales += sales * usgChange;
                break;
            case PLANOGRAM1:
            case PLANOGRAM2:
            case FILE_UPLOAD:
                break;
        }

        return sales;
    }

    private double response2Budget(ResponseEntity response) {
        JsonObject jsonObject1;
        JsonArray jsonArray1;

        Long answerId;
        AnswerEntity answer;

        int value;

        double budgetLocal = 0;

        switch (response.getQuestion().getType()) {
            case SINGLE_CHOICE:
                jsonObject1 = ResponseConverter.jsonObjectFromString(response.getOutcome());

                try {
                    answerId = jsonObject1.getJsonNumber("id").longValue();
                    answer = answerService.find(answerId);
                    budgetLocal = answer.getCoefBudget();
                } catch (NullPointerException ex) {
                }
                budget += budgetLocal;
                break;
            case MULTIPLE_CHOICE:
                jsonObject1 = ResponseConverter.jsonObjectFromString(response.getOutcome());
                jsonArray1 = jsonObject1.getJsonArray("answers");
                if (jsonArray1 != null && jsonArray1.size() > 0) {
                    for (JsonValue jsonValue1 : jsonArray1) {
                        JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                        answerId = jsonObject2.getJsonNumber("id").longValue();
                        answer = answerService.find(answerId);
                        budgetLocal += answer.getCoefBudget();
                    }
                } else {
                    budgetLocal += response.getQuestion().getCoefBudget();
                }
                budget += budgetLocal;
                break;
            case RANGE_CHOICE:
                jsonArray1 = ResponseConverter.jsonArrayFromString(response.getOutcome());
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

                    budgetLocal += answer.getCoefBudget();
                }
                budget += budgetLocal;
                break;
            case PLANOGRAM1:
            case PLANOGRAM2:
            case FILE_UPLOAD:
                break;
        }

        return budget;
    }

    private double response2USG(ResponseEntity response) {
        JsonObject jsonObject1;
        JsonArray jsonArray1;

        Long answerId;
        AnswerEntity answer;

        int value;

        double usgLocal = 0;

        switch (response.getQuestion().getType()) {
            case SINGLE_CHOICE:
                jsonObject1 = ResponseConverter.jsonObjectFromString(response.getOutcome());

                try {
                    answerId = jsonObject1.getJsonNumber("id").longValue();
                    answer = answerService.find(answerId);
                    usgLocal = answer.getCoefUsg();
                } catch (NullPointerException ex) {
                }
                break;
            case MULTIPLE_CHOICE:
                jsonObject1 = ResponseConverter.jsonObjectFromString(response.getOutcome());
                jsonArray1 = jsonObject1.getJsonArray("answers");
                if (jsonArray1 != null && jsonArray1.size() > 0) {
                    for (JsonValue jsonValue1 : jsonArray1) {
                        JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                        answerId = jsonObject2.getJsonNumber("id").longValue();
                        answer = answerService.find(answerId);
                        usgLocal += answer.getCoefUsg();
                    }
                } else {
                    usgLocal += response.getQuestion().getCoefUsg();
                }
                break;
            case RANGE_CHOICE:
                jsonArray1 = ResponseConverter.jsonArrayFromString(response.getOutcome());
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

                    usgLocal += ((answer.getCoefIndexMax() - value) * (answer.getCoefUsgGainMax() - answer.getCoefUsgGainMin()) / (answer.getCoefIndexMax() - answer.getCoefIndexMin())) + answer.getCoefUsgGainMin();
                }
                break;
            case PLANOGRAM1:
            case PLANOGRAM2:
            case FILE_UPLOAD:
                break;
        }

        return usgLocal;
    }

    private double response2GM(ResponseEntity response) {
        JsonObject jsonObject1;
        JsonArray jsonArray1;

        Long answerId;
        AnswerEntity answer;

        int value;

        double gmLocal = 0;

        switch (response.getQuestion().getType()) {
            case SINGLE_CHOICE:
                jsonObject1 = ResponseConverter.jsonObjectFromString(response.getOutcome());

                try {
                    answerId = jsonObject1.getJsonNumber("id").longValue();
                    answer = answerService.find(answerId);
                    gmLocal = answer.getCoefGm();
                } catch (NullPointerException ex) {
                }
                break;
            case MULTIPLE_CHOICE:
                jsonObject1 = ResponseConverter.jsonObjectFromString(response.getOutcome());
                jsonArray1 = jsonObject1.getJsonArray("answers");
                if (jsonArray1 != null && jsonArray1.size() > 0) {
                    for (JsonValue jsonValue1 : jsonArray1) {
                        JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                        answerId = jsonObject2.getJsonNumber("id").longValue();
                        answer = answerService.find(answerId);
                        gmLocal += answer.getCoefGm();
                    }
                } else {
                    gmLocal += response.getQuestion().getCoefGm();
                }
                break;
            case RANGE_CHOICE:
                jsonArray1 = ResponseConverter.jsonArrayFromString(response.getOutcome());
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

                    gmLocal += answer.getCoefGmGainMin() + ((value - answer.getCoefIndexMin()) * (answer.getCoefGmGainMax() - answer.getCoefGmGainMin()) / ((answer.getCoefIndexMax() + answer.getCoefIndexMin()) / 2 - (answer.getCoefIndexMin())));
                }
                break;
            case PLANOGRAM1:
            case PLANOGRAM2:
            case FILE_UPLOAD:
                break;
        }

        return gmLocal;
    }

    private double response2MS(ResponseEntity response) {
        JsonObject jsonObject1;
        JsonArray jsonArray1;

        Long answerId;
        AnswerEntity answer;

        int value;

        double msLocal = 0;

        switch (response.getQuestion().getType()) {
            case SINGLE_CHOICE:
                jsonObject1 = ResponseConverter.jsonObjectFromString(response.getOutcome());

                try {
                    answerId = jsonObject1.getJsonNumber("id").longValue();
                    answer = answerService.find(answerId);
                    msLocal = answer.getCoefMs();
                } catch (NullPointerException ex) {
                }
                ms += msLocal;
                break;
            case MULTIPLE_CHOICE:
                jsonObject1 = ResponseConverter.jsonObjectFromString(response.getOutcome());
                jsonArray1 = jsonObject1.getJsonArray("answers");
                if (jsonArray1 != null && jsonArray1.size() > 0) {
                    for (JsonValue jsonValue1 : jsonArray1) {
                        JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                        answerId = jsonObject2.getJsonNumber("id").longValue();
                        answer = answerService.find(answerId);
                        msLocal += answer.getCoefMs();
                    }
                } else {
                    msLocal += response.getQuestion().getCoefMs();
                }
                ms += msLocal;
                break;
            case RANGE_CHOICE:
                jsonArray1 = ResponseConverter.jsonArrayFromString(response.getOutcome());
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

                    if (value >= answer.getCoefIndexMin() && value < answer.getCoefMsBreakpointIndexMin()) {
                        msLocal += 0;
                    } else if (value >= answer.getCoefMsBreakpointIndexMin() && value < answer.getCoefMsBreakpointIndexMax()) {
                        msLocal += answer.getCoefMsGainMax() + ((value - answer.getCoefMsBreakpointIndexMin()) * (answer.getCoefMsGainMin() - answer.getCoefMsGainMax())) / (answer.getCoefMsBreakpointIndexMax() - answer.getCoefMsBreakpointIndexMin());
                    } else {
                        msLocal += answer.getCoefMsGainMin();
                    }
                }
                ms += msLocal;
                break;
            case PLANOGRAM1:
            case PLANOGRAM2:
            case FILE_UPLOAD:
                break;
        }

        return ms;
    }

    public LineChartModel getMultiAxisModel(UserEntity u) {
        List<ResponseEntity> responses;
        if (u != null) {
            responses = responseService.findByUser(u);
        } else {
            responses = responseService.findByUser(user);
        }

        LineChartModel model = new LineChartModel();

        BarChartSeries series1 = new BarChartSeries();
        series1.setLabel("Revenue");

        responses
                .stream()
                .filter(i -> i.getQuestion().getKind().equals(Kind.SIMULATION))
                .forEach(i -> {
                    series1.set(i.getQuestion().getName(), response2Revenue(i));
                });
        if (series1.getData().isEmpty()) {
//            series1.set("Initial Value", sales);
            questionService.findByKinds(Arrays.asList(Kind.SIMULATION))
                    .stream()
                    .forEach(i -> {
                        series1.set(i.getName(), null);
                    });
        }

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("USG");
        series2.setXaxis(AxisType.X2);
        series2.setYaxis(AxisType.Y2);

        responses
                .stream()
                .filter(i -> i.getQuestion().getKind().equals(Kind.SIMULATION))
                .forEach(i -> {
                    series2.set(i.getQuestion().getName(), response2USG(i) * 100);
                });
        if (series2.getData().isEmpty()) {
//            series2.set("Initial Value", usg * 100);
            questionService.findByKinds(Arrays.asList(Kind.SIMULATION))
                    .stream()
                    .forEach(i -> {
                        series2.set(i.getName(), null);
                    });
        }

        model.addSeries(series1);
        model.addSeries(series2);

        model.setTitle("Revenue & USG Chart");
        model.setExtender("customExtender");
        model.setSeriesColors("58BA27,FFCC33,F74A4A,F52F2F,A30303");
//        model.setLegendPosition("ne");
        model.setMouseoverHighlight(false);

        model.getAxes().put(AxisType.X, new CategoryAxis("Decisions"));
        model.getAxes().get(AxisType.X).setTickAngle(-50);
        model.getAxes().put(AxisType.X2, new CategoryAxis("Decisions"));
        model.getAxes().get(AxisType.X2).setTickAngle(-50);

        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Revenue");
//        yAxis.setMin(0);
//        yAxis.setMax(200);

        Axis y2Axis = new LinearAxis("USG (percentage)");
//        y2Axis.setMin(0);
//        y2Axis.setMax(200);

        model.getAxes().put(AxisType.Y2, y2Axis);

        return model;
    }

    public BarChartModel getBarModelGM(UserEntity u) {
        List<ResponseEntity> responses;
        if (u != null) {
            responses = responseService.findByUser(u);
        } else {
            responses = responseService.findByUser(user);
        }

        BarChartModel model = new BarChartModel();
        model.setTitle("GM Chart");
        model.setExtender("customExtender");
        model.setSeriesColors("58BA27,FFCC33,F74A4A,F52F2F,A30303");
        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setTickAngle(-50);
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("GM (percentage)");
        yAxis.setTickFormat("%.2f");

        ChartSeries series1 = new ChartSeries();

        responses
                .stream()
                .filter(i -> i.getQuestion().getKind().equals(Kind.SIMULATION))
                .forEach(i -> {
                    series1.set(i.getQuestion().getName(), response2GM(i) * 100);
                });
        if (series1.getData().isEmpty()) {
//        series1.set("Initial Value", gm);
            questionService.findByKinds(Arrays.asList(Kind.SIMULATION))
                    .stream()
                    .forEach(i -> {
                        series1.set(i.getName(), null);
                    });
        }

        model.addSeries(series1);

        return model;
    }

    public BarChartModel getBarModelMS(UserEntity u) {
        List<ResponseEntity> responses;
        if (u != null) {
            responses = responseService.findByUser(u);
        } else {
            responses = responseService.findByUser(user);
        }

        BarChartModel model = new BarChartModel();
        model.setTitle("MS Chart");
        model.setExtender("customExtender");
        model.setSeriesColors("58BA27,FFCC33,F74A4A,F52F2F,A30303");
        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setTickAngle(-50);
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("MS (percentage)");
        yAxis.setTickFormat("%.2f");

        ChartSeries series1 = new ChartSeries();

        responses
                .stream()
                .filter(i -> i.getQuestion().getKind().equals(Kind.SIMULATION))
                .forEach(i -> {
                    series1.set(i.getQuestion().getName(), response2MS(i) * 100);
                });
        if (series1.getData().isEmpty()) {
//        series1.set("Initial Value", ms);
            questionService.findByKinds(Arrays.asList(Kind.SIMULATION))
                    .stream()
                    .forEach(i -> {
                        series1.set(i.getName(), null);
                    });
        }

        model.addSeries(series1);

        return model;
    }

    public BarChartModel getBarModelBudget(UserEntity u) {
        List<ResponseEntity> responses;
        if (u != null) {
            responses = responseService.findByUser(u);
        } else {
            responses = responseService.findByUser(user);
        }

        BarChartModel model = new BarChartModel();
        model.setTitle("Budget Chart");
        model.setExtender("customExtender");
        model.setSeriesColors("58BA27,FFCC33,F74A4A,F52F2F,A30303");
        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setTickAngle(-50);
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Budget");
        yAxis.setTickFormat("%.2f");

        ChartSeries series1 = new ChartSeries();

        responses
                .stream()
                .filter(i -> i.getQuestion().getKind().equals(Kind.SIMULATION))
                .forEach(i -> {
                    series1.set(i.getQuestion().getName(), response2Budget(i));
                });
        if (series1.getData().isEmpty()) {
//        series1.set("Initial Value", budget);
            questionService.findByKinds(Arrays.asList(Kind.SIMULATION))
                    .stream()
                    .forEach(i -> {
                        series1.set(i.getName(), null);
                    });
        }

        model.addSeries(series1);

        return model;
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
