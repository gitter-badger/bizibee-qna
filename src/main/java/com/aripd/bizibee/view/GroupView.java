package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyGroupDataModel;
import com.aripd.bizibee.entity.GroupEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import com.aripd.bizibee.service.GroupService;
import java.util.ArrayList;

@Named
@ViewScoped
public class GroupView implements Serializable {

    @Inject
    private GroupService groupService;
    private GroupEntity newRecord;
    private GroupEntity selectedRecord;
    private List<GroupEntity> selectedRecords;
    private LazyDataModel<GroupEntity> lazyModel;

    @Inject
    MessageUtil messageUtil;

    public GroupView() {
        newRecord = new GroupEntity();
        selectedRecord = new GroupEntity();
        selectedRecords = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyGroupDataModel(groupService);
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        groupService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        groupService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        groupService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public GroupEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(GroupEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<GroupEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<GroupEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public GroupEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(GroupEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<GroupEntity> getLazyModel() {
        return lazyModel;
    }

}
