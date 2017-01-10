package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyQuestionDataModel;
import com.aripd.bizibee.entity.QuestionEntity;
import com.aripd.bizibee.entity.Type;
import com.aripd.bizibee.entity.AnswerEntity;
import com.aripd.bizibee.entity.GuideEntity;
import com.aripd.bizibee.entity.Kind;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import java.util.ArrayList;
import java.util.Arrays;
import org.primefaces.model.UploadedFile;
import com.aripd.bizibee.service.AnswerService;
import com.aripd.bizibee.service.GuideService;
import com.aripd.bizibee.service.QuestionService;

@Named
@ViewScoped
public class QuestionView implements Serializable {

    @Inject
    private QuestionService questionService;
    private QuestionEntity newQuestion;
    private QuestionEntity selectedQuestion;
    private List<QuestionEntity> selectedQuestions;
    private LazyDataModel<QuestionEntity> lazyModel;

    private Long id;

    private UploadedFile file;

    @Inject
    private AnswerService answerService;
    private AnswerEntity newAnswer;
    private AnswerEntity selectedAnswer;

    @Inject
    private GuideService guideService;

    @Inject
    MessageUtil messageUtil;

    public QuestionView() {
        newQuestion = new QuestionEntity();
        selectedQuestion = new QuestionEntity();
        selectedQuestions = new ArrayList<>();
        newAnswer = new AnswerEntity();
        selectedAnswer = new AnswerEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyQuestionDataModel(questionService);
    }

    public void onLoad() {
        if (id == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedQuestion = questionService.find(id);

        if (selectedQuestion == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Unknown record.");
            return;
        }

    }

    public List<Kind> getKinds() {
        return Arrays.asList(Kind.values());
    }

    public List<Type> getTypes() {
        return Arrays.asList(Type.values());
    }

    public List<GuideEntity> getGuides() {
        return guideService.findAll();
    }

    public List<QuestionEntity> getQuestions() {
        return questionService.findAll();
    }

    public List<AnswerEntity> getAnswers() {
        return answerService.findByQuestion(selectedQuestion);
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        if (file != null && file.getSize() > 0) {
            newQuestion.setBytes(file.getContents());
        }
        questionService.create(newQuestion);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        if (file != null && file.getSize() > 0) {
            selectedQuestion.setBytes(file.getContents());
        }
        questionService.update(selectedQuestion);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        questionService.delete(selectedQuestion);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doCreateAnswer(ActionEvent actionEvent) {
        if (file != null && file.getSize() > 0) {
            newAnswer.setBytes(file.getContents());
        }
        newAnswer.setQuestion(selectedQuestion);
        answerService.create(newAnswer);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateAnswer(ActionEvent actionEvent) {
        if (file != null && file.getSize() > 0) {
            selectedAnswer.setBytes(file.getContents());
        }
        answerService.update(selectedAnswer);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteAnswer(ActionEvent actionEvent) {
        answerService.delete(selectedAnswer);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public QuestionEntity getSelectedQuestion() {
        return selectedQuestion;
    }

    public void setSelectedQuestion(QuestionEntity selectedQuestion) {
        this.selectedQuestion = selectedQuestion;
    }

    public List<QuestionEntity> getSelectedQuestions() {
        return selectedQuestions;
    }

    public void setSelectedQuestions(List<QuestionEntity> selectedQuestions) {
        this.selectedQuestions = selectedQuestions;
    }

    public QuestionEntity getNewQuestion() {
        return newQuestion;
    }

    public void setNewQuestion(QuestionEntity newQuestion) {
        this.newQuestion = newQuestion;
    }

    public LazyDataModel<QuestionEntity> getLazyModel() {
        return lazyModel;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnswerEntity getNewAnswer() {
        return newAnswer;
    }

    public void setNewAnswer(AnswerEntity newAnswer) {
        this.newAnswer = newAnswer;
    }

    public AnswerEntity getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(AnswerEntity selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

}
