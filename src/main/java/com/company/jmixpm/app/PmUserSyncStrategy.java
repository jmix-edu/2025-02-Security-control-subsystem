package com.company.jmixpm.app;

import com.company.jmixpm.entity.User;
import io.jmix.ldap.userdetails.AbstractLdapUserDetailsSynchronizationStrategy;
import org.springframework.ldap.core.DirContextOperations;

import org.springframework.stereotype.Component;

@Component
public class PmUserSyncStrategy extends AbstractLdapUserDetailsSynchronizationStrategy<User> {

    @Override
    protected Class<User> getUserClass() {
        return User.class;
    }

    @Override
    protected void mapUserDetailsAttributes(User user, DirContextOperations ctx) {
        user.setFirstName(ctx.getStringAttribute("givenName"));
        user.setLastName(ctx.getStringAttribute("sn"));
        user.setEmail(ctx.getStringAttribute("mail"));
    }
}