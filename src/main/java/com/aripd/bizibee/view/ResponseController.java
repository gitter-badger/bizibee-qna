package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.QuestionEntity;
import com.aripd.bizibee.entity.AnswerEntity;
import com.aripd.bizibee.entity.ResponseEntity;
import com.aripd.bizibee.model.response.Response1Model;
import com.aripd.bizibee.model.response.Response2Model;
import com.aripd.bizibee.model.response.Response5Model;
import com.aripd.bizibee.model.response.Response6Model;
import com.aripd.bizibee.model.response.ResponseConverter;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.aripd.bizibee.service.ResponseService;
import java.util.ArrayList;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import com.aripd.bizibee.service.AnswerService;

@Named
@ViewScoped
public class ResponseController implements Serializable {

    @Inject
    private ResponseService responseService;
    private ResponseEntity selectedRecord;

    private Long questionId;
    private Long userId;

    private boolean disabled;

    @Inject
    private AnswerService answerService;

    private Response1Model model1;
    private Response2Model model2;
    private List<Response5Model> model5;
    private Response6Model model6;

    @Inject
    MessageUtil messageUtil;

    public ResponseController() {
        model1 = new Response1Model();
        model2 = new Response2Model();
        model5 = new ArrayList<>();
        model6 = new Response6Model();
    }

    @PostConstruct
    public void init() {
    }

    public void onLoad() {
        if (questionId == null || userId == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedRecord = responseService.findOneByUserIdAndQuestionId(userId, questionId);

        if (selectedRecord == null) {
            // cevaplanmamış
            disabled = false;
        } else {
            // cevaplanmış
            disabled = true;

            String outcome = selectedRecord.getOutcome();
            byte[] bytes = selectedRecord.getBytes();
            QuestionEntity question = selectedRecord.getQuestion();

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

    public AnswerEntity slotToAnswer(int slot) {
        try {
            return model5
                    .stream()
                    .filter(m -> m.getValue().equals(slot))
                    .findFirst()
                    .orElse(null)
                    .getAnswer();
        } catch (NullPointerException ex) {
            return null;
        }
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

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public ResponseEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(ResponseEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

}
