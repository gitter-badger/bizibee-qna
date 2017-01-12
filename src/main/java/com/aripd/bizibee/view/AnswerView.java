package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.entity.AnswerEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import org.primefaces.model.UploadedFile;
import com.aripd.bizibee.service.AnswerService;
import com.aripd.util.RequestUtil;

@Named
@ViewScoped
public class AnswerView implements Serializable {

    @Inject
    private AnswerService answerService;
    private AnswerEntity newRecord;
    private AnswerEntity selectedRecord;
    private List<AnswerEntity> selectedRecords;

    private Long id;

    private UploadedFile file;

    @Inject
    MessageUtil messageUtil;

    public AnswerView() {
        newRecord = new AnswerEntity();
        selectedRecord = new AnswerEntity();
        selectedRecords = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
    }

    public void onLoad() {
        if (id == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedRecord = answerService.find(id);

        if (selectedRecord == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Unknown record.");
            return;
        }

    }

    public void doUploadImage(ActionEvent actionEvent) {
        if (file != null && file.getSize() > 0) {
            selectedRecord.setBytes(file.getContents());
        }
        answerService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Uploaded");

        String navigation = "/ruler/simulation/question/answer/image?id=" + selectedRecord.getId() + "&amp;faces-redirect=true";
        RequestUtil.doNavigate(navigation);
    }

    public void doResetImage(ActionEvent actionEvent) {
        selectedRecord.setBytes(null);
        answerService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Resetted");

        String navigation = "/ruler/simulation/question/answer/image?id=" + selectedRecord.getId() + "&amp;faces-redirect=true";
        RequestUtil.doNavigate(navigation);
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        answerService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        answerService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        answerService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");

        String navigation = "/ruler/simulation/question/answer/index?id=" + selectedRecord.getQuestion().getId() + "&amp;faces-redirect=true";
        RequestUtil.doNavigate(navigation);
    }

    public AnswerEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(AnswerEntity newRecord) {
        this.newRecord = newRecord;
    }

    public AnswerEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(AnswerEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<AnswerEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<AnswerEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
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

}
