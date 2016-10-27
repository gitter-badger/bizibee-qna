package com.aripd.bizibee.flow.register;

import com.aripd.bizibee.entity.UserEntity;
import com.aripd.bizibee.entity.UserGroup;
import com.aripd.bizibee.entity.UserStatus;
import com.aripd.bizibee.service.UserService;
import com.aripd.util.mail.MailUtil;
import com.aripd.util.MessageUtil;
import com.aripd.util.locale.LocaleProvider;
import com.aripd.util.mail.IMAPMessageSendResponseModel;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import javax.faces.event.ActionEvent;
import javax.faces.flow.FlowScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.validator.constraints.Email;

@Named
//@FlowScoped("register")
@ViewScoped
public class RegisterBean implements Serializable {

    static final Logger LOG = Logger.getLogger(RegisterBean.class.getName());

    @Email
    private String email;

    private String firstname;
    private String lastname;
    private String phone;

    private String company;

    private String line1;
    private String line2;
    private String zipcode;
    private String county;

    @Inject
    private UserService userService;

    @Inject
    MessageUtil messageUtil;

    @Inject
    MailUtil mailUtil;

    public RegisterBean() {
        LOG.info(String.format("%s has been created...", this.getClass().getSimpleName()));
    }

    @PostConstruct
    public void init() {
    }

    public String getReturnValue() {
        return "/index";
    }

    public void doSubmitMember(ActionEvent actionEvent) {
        UserEntity user = userService.findOneByEmail(email);
        if (user != null) {
            messageUtil.addGlobalInfoFlashMessage("User is already registered");
        } else {
            RegisterModel registerModel = new RegisterModel.Builder()
                    .setGroup(UserGroup.Members)
                    .setStatus(UserStatus.Unconfirmed)
                    .setEmail(email)
                    .setFirstname(firstname)
                    .setLastname(lastname)
                    .setPhone(phone)
                    .build();

            user = userService.register(registerModel);

            String subject = messageUtil.getMailMessage(LocaleProvider.getLocale(), "confirmation.subject");
            String msg = messageUtil.getMailMessage(LocaleProvider.getLocale(), "confirmation.msg", new Object[]{
                user.getEmail(),
                user.getEmail(),
                user.getPassword(),
                user.getValidationUrl()
            });
            IMAPMessageSendResponseModel model = mailUtil.sendEmail(user.getEmail(), subject, msg);
            if (model.isSuccess()) {
                messageUtil.addGlobalInfoFlashMessage("Your confirmation message is sent to {0}", new Object[]{user.getEmail()});
            } else {
                messageUtil.addGlobalCustomFlashMessage(model.getMessage());
            }
        }
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
