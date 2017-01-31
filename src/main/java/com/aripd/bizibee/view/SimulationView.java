package com.aripd.bizibee.view;

import com.aripd.bizibee.comparison.ComparisonQuestionSortOrderAsc;
import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.QuestionEntity;
import com.aripd.bizibee.entity.AnswerEntity;
import com.aripd.bizibee.entity.GroupEntity;
import com.aripd.bizibee.entity.ResponseEntity;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.model.response.Response1Model;
import com.aripd.bizibee.model.response.Response2Model;
import com.aripd.bizibee.model.response.Response5Model;
import com.aripd.bizibee.model.response.Response6Model;
import com.aripd.bizibee.model.response.ResponseConverter;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import com.aripd.bizibee.service.ResponseService;
import com.aripd.bizibee.service.UserService;
import com.aripd.util.RequestUtil;
import java.util.ArrayList;
import java.util.Collections;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import com.aripd.bizibee.service.AnswerService;
import com.aripd.bizibee.service.GroupService;
import com.aripd.bizibee.service.QuestionService;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class SimulationView implements Serializable {

    private static final Logger LOG = Logger.getLogger(SimulationView.class.getName());

    private final MenuModel menuModelByGroups;
    private final MenuModel menuModelByQuestions;

    @Inject
    private UserService userService;
    private UserEntity user;

    @Inject
    private ResponseService responseService;
    private boolean disabled;

    @Inject
    private GroupService groupService;
    private List<GroupEntity> groups;

    @Inject
    private AnswerService answerService;

    @Inject
    private QuestionService questionService;
    private QuestionEntity selectedRecord;
    private QuestionEntity nextRecord;
    private List<QuestionEntity> questions;

    private String uuid;
    private Integer sequence;

    private Response1Model model1;
    private Response2Model model2;
    private List<Response5Model> model5;
    private Response6Model model6;

    @Inject
    MessageUtil messageUtil;

    public SimulationView() {
        selectedRecord = new QuestionEntity();
        menuModelByQuestions = new DefaultMenuModel();
        menuModelByGroups = new DefaultMenuModel();
    }

    @PostConstruct
    public void init() {
        user = userService.getCurrentUser();

        groups = groupService.findAll();
        questions = questionService.findAll();
        Collections.sort(questions, new ComparisonQuestionSortOrderAsc());

        for (GroupEntity group : groups) {
            menuModelByGroups.addElement(new DefaultMenuItem(group.getName()));
        }
        for (QuestionEntity question : questions) {
            menuModelByQuestions.addElement(new DefaultMenuItem(question.getName()));
        }
    }

    public void onLoad() {
        if (uuid == null) {
            sequence = 0;
            selectedRecord = questions.get(sequence);
        } else {
            selectedRecord = questionService.findOneByUuid(uuid);
            sequence = questions.indexOf(selectedRecord);
        }

        nextRecord = this.getNext(selectedRecord);

        model1 = new Response1Model();
        model2 = new Response2Model();
        model5 = new ArrayList<>();
        model6 = new Response6Model();

        for (AnswerEntity answer : selectedRecord.getAnswers()) {
            switch (selectedRecord.getType()) {
                case SINGLE_CHOICE:
                    break;
                case MULTIPLE_CHOICE:
                    break;
                case RANGE_CHOICE:
                case PLANOGRAM1:
                case PLANOGRAM2:
                    model5.add(new Response5Model(answer));
                    break;
                case FILE_UPLOAD:
                    break;
            }
        }

        ResponseEntity response = responseService.findOneByUserAndQuestion(user, selectedRecord);
        if (response == null) {
            // cevaplanmamış
            disabled = false;
        } else {
            // cevaplanmış
            disabled = true;

            String outcome = response.getOutcome();
            byte[] bytes = response.getBytes();
            QuestionEntity question = response.getQuestion();

            JsonObject jsonObject1;
            JsonArray jsonArray1;

            Long answerId;
            AnswerEntity answer;

            int value;

            switch (question.getType()) {
                case SINGLE_CHOICE:
                    model1 = new Response1Model();
                    jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);

                    try {
                        answerId = jsonObject1.getJsonNumber("id").longValue();
                        answer = answerService.find(answerId);
                        model1.setAnswer(answer);
                    } catch (NullPointerException ex) {
                        model1.setAnswer(null);
                    }

                    break;
                case MULTIPLE_CHOICE:
                    model2 = new Response2Model();
                    List<AnswerEntity> answers = new ArrayList<>();
                    jsonObject1 = ResponseConverter.jsonObjectFromString(outcome);
                    jsonArray1 = jsonObject1.getJsonArray("answers");
                    for (JsonValue jsonValue1 : jsonArray1) {
                        JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                        answerId = jsonObject2.getJsonNumber("id").longValue();
                        answer = answerService.find(answerId);
                        answers.add(answer);
                    }
                    model2.setAnswers(answers);
                    break;
                case RANGE_CHOICE:
                case PLANOGRAM1:
                case PLANOGRAM2:
                    model5 = new ArrayList<>();
                    jsonArray1 = ResponseConverter.jsonArrayFromString(outcome);
                    for (JsonValue jsonValue1 : jsonArray1) {
                        JsonObject jsonObject2 = ResponseConverter.jsonObjectFromString(jsonValue1.toString());

                        answerId = jsonObject2.getJsonNumber("answer").longValue();
                        answer = answerService.find(answerId);

                        Response5Model m = new Response5Model(answer);

                        try {
                            value = jsonObject2.getJsonNumber("value").intValue();
                            m.setValue(value);
                        } catch (NullPointerException | ClassCastException ex) {
                            // TODO bunun yerine default olarak answer.getCoefIndexMin() girilebilir
                            value = answer.getCoefIndexMin();
                        }

                        model5.add(m);
                    }
                    break;
                case FILE_UPLOAD:
                    model6 = new Response6Model();
                    model6.setBytes(bytes);
                    break;
            }
        }

    }

    public void doUpdate(ActionEvent actionEvent) {
        ResponseEntity entity = responseService.findOneByUserAndQuestion(user, selectedRecord);
        if (entity != null) {
            messageUtil.addGlobalErrorFlashMessage("You cannot submit more than once");
//            throw new FacesException("You cannot submit more than once");
        } else {

            ResponseEntity response = new ResponseEntity();
            response.setUser(user);
            response.setQuestion(selectedRecord);

            switch (selectedRecord.getType()) {
                case SINGLE_CHOICE:
                    response.setOutcome(model1.toString());
                    responseService.create(response);
                    break;
                case MULTIPLE_CHOICE:
                    response.setOutcome(model2.toString());
                    responseService.create(response);
                    break;
                case RANGE_CHOICE:
                    response.setOutcome(model5.toString());
                    responseService.create(response);
                    break;
                case PLANOGRAM1:
                case PLANOGRAM2:
                    List<Integer> values = new ArrayList<>();
                    model5.forEach((model) -> {
                        values.add(model.getValue());
                    });
                    Set<Integer> uniques = new HashSet<>(values);
                    boolean hasEmptyElement = values.contains(null);

                    if (hasEmptyElement) {
                        messageUtil.addGlobalErrorFlashMessage("You can not leave it blank");
                    } else if (values.size() != uniques.size()) {
                        messageUtil.addGlobalErrorFlashMessage("You can not enter the same slot number");
                    } else if (values.size() == uniques.size() && !hasEmptyElement) {
                        response.setOutcome(model5.toString());
                        responseService.create(response);
                    }

                    break;
                case FILE_UPLOAD:
                    response.setBytes(model6.getFile().getContents());
                    responseService.create(response);
                    break;
            }

        }

        String navigation = "/player/simulation?uuid=" + selectedRecord.getUuid() + "&amp;faces-redirect=true";
        RequestUtil.doNavigate(navigation);
    }

    public AnswerEntity slotToAnswer(int slot) {
        try {
            return model5.stream().filter(m -> m.getValue().equals(slot)).findFirst().orElse(null).getAnswer();
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public String goNext() {
        try {
            QuestionEntity question = getNext(selectedRecord);
            return "/player/simulation?uuid=" + question.getUuid() + "&amp;faces-redirect=true";
        } catch (NullPointerException ex) {
            return "/player/report?faces-redirect=true";
        }
    }

    public String goPrevious() {
        try {
            QuestionEntity question = getPrevious(selectedRecord);
            return "/player/simulation?uuid=" + question.getUuid() + "&amp;faces-redirect=true";
        } catch (NullPointerException ex) {
            return "/player/report?faces-redirect=true";
        }
    }

    private QuestionEntity getNext(QuestionEntity question) {
        int idx = questions.indexOf(question);
        if (idx < 0 || idx + 1 == questions.size()) {
            return null;
        }
        return questions.get(idx + 1);
    }

    private QuestionEntity getPrevious(QuestionEntity question) {
        int idx = questions.indexOf(question);
        if (idx <= 0) {
            return null;
        }
        return questions.get(idx - 1);
    }

    public QuestionEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(QuestionEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public QuestionEntity getNextRecord() {
        return nextRecord;
    }

    public void setNextRecord(QuestionEntity nextRecord) {
        this.nextRecord = nextRecord;
    }

    public List<GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupEntity> groups) {
        this.groups = groups;
    }

    public List<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public MenuModel getMenuModelByGroups() {
        return menuModelByGroups;
    }

    public MenuModel getMenuModelByQuestions() {
        return menuModelByQuestions;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public Response1Model getModel1() {
        return model1;
    }

    public void setModel1(Response1Model model1) {
        this.model1 = model1;
    }

    public Response2Model getModel2() {
        return model2;
    }

    public void setModel2(Response2Model model2) {
        this.model2 = model2;
    }

    public List<Response5Model> getModel5() {
        return model5;
    }

    public void setModel5(List<Response5Model> model5) {
        this.model5 = model5;
    }

    public Response6Model getModel6() {
        return model6;
    }

    public void setModel6(Response6Model model6) {
        this.model6 = model6;
    }

}
