package com.aripd.bizibee.entity;

import com.aripd.util.RequestUtil;
import com.aripd.util.SHA512;
import java.net.URL;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.LocaleUtils;
import org.hibernate.validator.constraints.Email;

@Entity
public class UserEntity extends AbstractEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserGroup userGroup;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus userStatus;

    @Email
    @NotNull
    @Column(unique = true, nullable = false)
    private String email;
    @NotNull
    @Column(nullable = false)
    private String password;
    @NotNull
    @Column(nullable = false)
    private String locale;
    private String timezone;

    @NotNull
    @Column(nullable = false)
    private String firstname;
    @NotNull
    @Column(nullable = false)
    private String lastname;

    private String theme;

    private String imapProtocol;
    private String imapHost;
    private String imapPort;
    private String imapUserName;
    private String imapPassword;

    private String smtpProtocol;
    private String smtpHost;
    private String smtpPort;
    private String smtpUserName;
    private String smtpPassword;

    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private CompanyEntity company;

    public UserEntity() {
    }

    @Transient
    public String getLocaleString() {
        Locale l = LocaleUtils.toLocale(locale);
        return l.getDisplayName(new Locale(l.getLanguage(), l.getCountry()));
    }

    @Transient
    public String getToken() {
        return SHA512.hashText(password + email);
    }

    @Transient
    public String getFullname() {
        return String.format("%s %s", firstname, lastname);
    }

    @Transient
    public URL getValidationUrl() {
        String file = String.format("/api/v2/users/validate/?token=%s", getToken());
        return RequestUtil.getFullAddress(file);
    }

    @Transient
    public URL getNewPasswordRequestUrl() {
        String file = String.format("/api/v2/users/newPasswordRequest/?token=%s", getToken());
        return RequestUtil.getFullAddress(file);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getImapProtocol() {
        return imapProtocol;
    }

    public void setImapProtocol(String imapProtocol) {
        this.imapProtocol = imapProtocol;
    }

    public String getImapHost() {
        return imapHost;
    }

    public void setImapHost(String imapHost) {
        this.imapHost = imapHost;
    }

    public String getImapPort() {
        return imapPort;
    }

    public void setImapPort(String imapPort) {
        this.imapPort = imapPort;
    }

    public String getImapUserName() {
        return imapUserName;
    }

    public void setImapUserName(String imapUserName) {
        this.imapUserName = imapUserName;
    }

    public String getImapPassword() {
        return imapPassword;
    }

    public void setImapPassword(String imapPassword) {
        this.imapPassword = imapPassword;
    }

    public String getSmtpProtocol() {
        return smtpProtocol;
    }

    public void setSmtpProtocol(String smtpProtocol) {
        this.smtpProtocol = smtpProtocol;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getSmtpUserName() {
        return smtpUserName;
    }

    public void setSmtpUserName(String smtpUserName) {
        this.smtpUserName = smtpUserName;
    }

    public String getSmtpPassword() {
        return smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

}
