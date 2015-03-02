package be.gerard.core.service.merger;

import be.gerard.common.merger.MergeContext;
import be.gerard.common.merger.Merger;
import be.gerard.core.interface_v1.model.User;
import be.gerard.core.service.model.UserRecord;
import org.springframework.stereotype.Component;

/**
 * UserMerger
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Component
public class UserMerger implements Merger<UserRecord, User, MergeContext> {

    @Override
    public void merge(UserRecord userRecord, User user, MergeContext context) {
        userRecord.setUsername(user.getUsername());
        userRecord.setFirstname(user.getFirstname());
        userRecord.setLastname(user.getLastname());
        userRecord.setBirthDate(user.getBirthDate());
    }

}
