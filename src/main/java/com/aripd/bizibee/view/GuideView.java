package com.aripd.bizibee.view;

import com.aripd.util.MessageUtil;
import com.aripd.bizibee.model.data.LazyGuideDataModel;
import com.aripd.bizibee.entity.GuideEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import com.aripd.bizibee.service.GuideService;
import java.util.ArrayList;

@Named
@ViewScoped
public class GuideView implements Serializable {

    @Inject
    private GuideService guideService;
    private GuideEntity newGuide;
    private GuideEntity selectedGuide;
    private List<GuideEntity> selectedGuides;
    private LazyDataModel<GuideEntity> lazyModel;

    private Long id;

    @Inject
    MessageUtil messageUtil;

    public GuideView() {
        newGuide = new GuideEntity();
        selectedGuide = new GuideEntity();
        selectedGuides = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyGuideDataModel(guideService);
    }

    public void onLoad() {
        if (id == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedGuide = guideService.find(id);

        if (selectedGuide == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Unknown record.");
            return;
        }

    }

    public List<GuideEntity> getGuides() {
        return guideService.findAll();
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        guideService.create(newGuide);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        guideService.update(selectedGuide);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        guideService.delete(selectedGuide);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public GuideEntity getSelectedGuide() {
        return selectedGuide;
    }

    public void setSelectedGuide(GuideEntity selectedGuide) {
        this.selectedGuide = selectedGuide;
    }

    public List<GuideEntity> getSelectedGuides() {
        return selectedGuides;
    }

    public void setSelectedGuides(List<GuideEntity> selectedGuides) {
        this.selectedGuides = selectedGuides;
    }

    public GuideEntity getNewGuide() {
        return newGuide;
    }

    public void setNewGuide(GuideEntity newGuide) {
        this.newGuide = newGuide;
    }

    public LazyDataModel<GuideEntity> getLazyModel() {
        return lazyModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
