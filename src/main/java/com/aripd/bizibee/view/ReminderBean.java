package com.aripd.bizibee.view;

import com.aripd.bizibee.service.UserService;
import com.aripd.bizibee.entity.UserEntity;
import com.aripd.util.mail.MailUtil;
import com.aripd.util.MessageUtil;
import com.aripd.util.locale.LocaleProvider;
import com.aripd.util.mail.IMAPMessageSendResponseModel;
import java.io.Serializable;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.validator.constraints.Email;

@Named
@ViewScoped
public class ReminderBean implements Serializable {

    @Email
    private String email;

    @Inject
    private UserService userService;

    @Inject
    MessageUtil messageUtil;

    @Inject
    MailUtil mailUtil;

    public ReminderBean() {
    }

    public void doSubmit(ActionEvent actionEvent) {
        UserEntity user = userService.findOneByEmail(email);
        if (user == null) {
            messageUtil.addGlobalErrorFlashMessage("User is not exist");
        } else {
            String subject = messageUtil.getMailMessage(LocaleProvider.getLocale(), "npr.subject");
            String msg = messageUtil.getMailMessage(LocaleProvider.getLocale(), "npr.msg", new Object[]{
                user.getEmail(),
                user.getNewPasswordRequestUrl()
            });
            IMAPMessageSendResponseModel model = mailUtil.sendEmail(user.getEmail(), subject, msg);
            if (model.isSuccess()) {
                messageUtil.addGlobalInfoFlashMessage("Request confirmation message is sent to {0}", new Object[]{user.getEmail()});
            } else {
                messageUtil.addGlobalCustomFlashMessage(model.getMessage());
            }
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
