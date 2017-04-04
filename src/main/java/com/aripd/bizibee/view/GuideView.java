package com.aripd.bizibee.view;

import com.aripd.bizibee.comparison.ComparisonGuideSortOrderAsc;
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
import com.aripd.util.RequestUtil;
import java.util.ArrayList;
import java.util.Collections;
import org.primefaces.model.UploadedFile;

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

    private UploadedFile file;

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
        List<GuideEntity> guides = guideService.findAll();
        Collections.sort(guides, new ComparisonGuideSortOrderAsc());
        return guides;
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        guideService.create(newGuide);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        if (file != null && file.getSize() > 0) {
            selectedGuide.setBytes(file.getContents());
        }
        guideService.update(selectedGuide);
        messageUtil.addGlobalInfoFlashMessage("Updated");

        String navigation = "/ruler/simulation/guide/form?id=" + selectedGuide.getId() + "&amp;faces-redirect=true";
        RequestUtil.doNavigate(navigation);
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        guideService.delete(selectedGuide);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doResetImage(ActionEvent actionEvent) {
        selectedGuide.setBytes(null);
        guideService.update(selectedGuide);
        messageUtil.addGlobalInfoFlashMessage("Resetted");

        String navigation = "/ruler/simulation/guide/form?id=" + selectedGuide.getId() + "&amp;faces-redirect=true";
        RequestUtil.doNavigate(navigation);
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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

}
