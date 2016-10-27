package com.aripd.bizibee.flow.register;

import com.aripd.bizibee.entity.UserGroup;
import com.aripd.bizibee.entity.UserStatus;
import com.aripd.util.locale.LocaleProvider;
import java.security.SecureRandom;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @see Builder Pattern
 *
 * @author cem
 */
public class RegisterModel {

    private final UserGroup userGroup;
    private final UserStatus userStatus;
    private final String email;
    private final String password;
    private final String locale;
    private final String firstname;
    private final String lastname;
    private final String phone;

    private final String company;
    private final Long credit;

    public RegisterModel(UserGroup userGroup, UserStatus userStatus, String email, String password, String locale, String firstname, String lastname, String phone, String company, Long credit) {
        this.userGroup = userGroup;
        this.userStatus = userStatus;
        this.email = email;
        this.password = password;
        this.locale = locale;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.company = company;
        this.credit = credit;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getLocale() {
        return locale;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public String getCompany() {
        return company;
    }

    public Long getCredit() {
        return credit;
    }

    public static class Builder {

        private UserGroup userGroup;
        private UserStatus userStatus;
        private String email;
        private String password = RandomStringUtils.random(6, 0, 0, true, true, null, new SecureRandom());
        private String locale = LocaleProvider.getLocale().toString();

        private String firstname = "";
        private String lastname = "";
        private String phone = "";

        private String company = "";
        private Long credit = 100L;

        public Builder setGroup(UserGroup userGroup) {
            this.userGroup = userGroup;
            return this;
        }

        public Builder setStatus(UserStatus userStatus) {
            this.userStatus = userStatus;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setLocale(String locale) {
            this.locale = locale;
            return this;
        }

        public Builder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setCompany(String company) {
            this.company = company;
            return this;
        }

        public Builder setCredit(Long credit) {
            this.credit = credit;
            return this;
        }

        public RegisterModel build() {
            return new RegisterModel(userGroup, userStatus, email, password, locale, firstname, lastname, phone, company, credit);
        }
    }

}
