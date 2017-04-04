package com.aripd.bizibee.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class SimulationEntity extends AbstractEntity {

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;

    @NotNull
    @Column(nullable = false)
    private String code;

    @NotNull
    @Column(nullable = false)
    private String name;

    @Lob
    private byte[] bytesCompany;
    @Lob
    private byte[] bytesSimulation;

    private String headline;
    @Column(columnDefinition = "TEXT")
    private String description;

    private String thanksDialogHeader;
    @Column(columnDefinition = "TEXT")
    private String thanksDialogContent;
    private String thanksLinkHref;
    private String thanksLinkText;

    private double scoreStart = 0;
    private double salesStart = 0;
    private double budgetStart = 0;
    private double gmStart = 0;
    private double msStart = 0;

    private double gmWeighted = 0;
    private double msWeighted = 0;
    private double usgWeighted = 0;

    private String backgroundColorBar = "0388e5";//150958
    private String backgroundColorBudget = "f9c851";//fbb040
    private String backgroundColorDecisions = "f9c851";//fbb040
    private String backgroundColorGM = "20d077";//1c75bc
    private String backgroundColorGuide = "20d077";//1c75bc
    private String backgroundColorMS = "ef6262";//f58220
    private String backgroundColorReport = "007be5";//8a1f63
    private String backgroundColorRevenue = "007be5";//8a1f63
    private String backgroundColorUSG = "007be5";//f58220

    private String colorBudget = "ffffff";//ffffff
    private String colorDecisions = "b58c2b";//a85f00
    private String colorGM = "ffffff";//ffffff
    private String colorGuide = "038d4a";//00458f
    private String colorMS = "ffffff";//ffffff
    private String colorReport = "00448f";//630045
    private String colorRevenue = "ffffff";//ffffff
    private String colorUSG = "ffffff";//ffffff

    @OneToMany(mappedBy = "simulation", orphanRemoval = true)
    private List<UserEntity> users;

    public SimulationEntity() {
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBytesCompany() {
        return bytesCompany;
    }

    public void setBytesCompany(byte[] bytesCompany) {
        this.bytesCompany = bytesCompany;
    }

    public byte[] getBytesSimulation() {
        return bytesSimulation;
    }

    public void setBytesSimulation(byte[] bytesSimulation) {
        this.bytesSimulation = bytesSimulation;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThanksDialogHeader() {
        return thanksDialogHeader;
    }

    public void setThanksDialogHeader(String thanksDialogHeader) {
        this.thanksDialogHeader = thanksDialogHeader;
    }

    public String getThanksDialogContent() {
        return thanksDialogContent;
    }

    public void setThanksDialogContent(String thanksDialogContent) {
        this.thanksDialogContent = thanksDialogContent;
    }

    public String getThanksLinkHref() {
        return thanksLinkHref;
    }

    public void setThanksLinkHref(String thanksLinkHref) {
        this.thanksLinkHref = thanksLinkHref;
    }

    public String getThanksLinkText() {
        return thanksLinkText;
    }

    public void setThanksLinkText(String thanksLinkText) {
        this.thanksLinkText = thanksLinkText;
    }

    public double getScoreStart() {
        return scoreStart;
    }

    public void setScoreStart(double scoreStart) {
        this.scoreStart = scoreStart;
    }

    public double getSalesStart() {
        return salesStart;
    }

    public void setSalesStart(double salesStart) {
        this.salesStart = salesStart;
    }

    public double getBudgetStart() {
        return budgetStart;
    }

    public void setBudgetStart(double budgetStart) {
        this.budgetStart = budgetStart;
    }

    public double getGmStart() {
        return gmStart;
    }

    public void setGmStart(double gmStart) {
        this.gmStart = gmStart;
    }

    public double getMsStart() {
        return msStart;
    }

    public void setMsStart(double msStart) {
        this.msStart = msStart;
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

    public double getUsgWeighted() {
        return usgWeighted;
    }

    public void setUsgWeighted(double usgWeighted) {
        this.usgWeighted = usgWeighted;
    }

    public String getBackgroundColorBar() {
        return backgroundColorBar;
    }

    public void setBackgroundColorBar(String backgroundColorBar) {
        this.backgroundColorBar = backgroundColorBar;
    }

    public String getBackgroundColorBudget() {
        return backgroundColorBudget;
    }

    public void setBackgroundColorBudget(String backgroundColorBudget) {
        this.backgroundColorBudget = backgroundColorBudget;
    }

    public String getBackgroundColorDecisions() {
        return backgroundColorDecisions;
    }

    public void setBackgroundColorDecisions(String backgroundColorDecisions) {
        this.backgroundColorDecisions = backgroundColorDecisions;
    }

    public String getBackgroundColorGM() {
        return backgroundColorGM;
    }

    public void setBackgroundColorGM(String backgroundColorGM) {
        this.backgroundColorGM = backgroundColorGM;
    }

    public String getBackgroundColorGuide() {
        return backgroundColorGuide;
    }

    public void setBackgroundColorGuide(String backgroundColorGuide) {
        this.backgroundColorGuide = backgroundColorGuide;
    }

    public String getBackgroundColorMS() {
        return backgroundColorMS;
    }

    public void setBackgroundColorMS(String backgroundColorMS) {
        this.backgroundColorMS = backgroundColorMS;
    }

    public String getBackgroundColorReport() {
        return backgroundColorReport;
    }

    public void setBackgroundColorReport(String backgroundColorReport) {
        this.backgroundColorReport = backgroundColorReport;
    }

    public String getBackgroundColorRevenue() {
        return backgroundColorRevenue;
    }

    public void setBackgroundColorRevenue(String backgroundColorRevenue) {
        this.backgroundColorRevenue = backgroundColorRevenue;
    }

    public String getBackgroundColorUSG() {
        return backgroundColorUSG;
    }

    public void setBackgroundColorUSG(String backgroundColorUSG) {
        this.backgroundColorUSG = backgroundColorUSG;
    }

    public String getColorBudget() {
        return colorBudget;
    }

    public void setColorBudget(String colorBudget) {
        this.colorBudget = colorBudget;
    }

    public String getColorDecisions() {
        return colorDecisions;
    }

    public void setColorDecisions(String colorDecisions) {
        this.colorDecisions = colorDecisions;
    }

    public String getColorGM() {
        return colorGM;
    }

    public void setColorGM(String colorGM) {
        this.colorGM = colorGM;
    }

    public String getColorGuide() {
        return colorGuide;
    }

    public void setColorGuide(String colorGuide) {
        this.colorGuide = colorGuide;
    }

    public String getColorMS() {
        return colorMS;
    }

    public void setColorMS(String colorMS) {
        this.colorMS = colorMS;
    }

    public String getColorReport() {
        return colorReport;
    }

    public void setColorReport(String colorReport) {
        this.colorReport = colorReport;
    }

    public String getColorRevenue() {
        return colorRevenue;
    }

    public void setColorRevenue(String colorRevenue) {
        this.colorRevenue = colorRevenue;
    }

    public String getColorUSG() {
        return colorUSG;
    }

    public void setColorUSG(String colorUSG) {
        this.colorUSG = colorUSG;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

}
