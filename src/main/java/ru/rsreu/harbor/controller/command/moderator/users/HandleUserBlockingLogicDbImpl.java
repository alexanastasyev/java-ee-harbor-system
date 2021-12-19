package ru.rsreu.harbor.controller.command.moderator.users;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.validation.HandleUserBlockingOrCreateReportValidator;
import ru.rsreu.harbor.controller.validation.HandleUserBlockingOrCreateReportValidatorDbImpl;
import ru.rsreu.harbor.datalayer.dao.StatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.Status;
import ru.rsreu.harbor.datalayer.model.User;

public class HandleUserBlockingLogicDbImpl implements HandleUserBlockingLogic {
    private final UserDao userDao;
    private final StatusDao statusDao;
    private final HandleUserBlockingOrCreateReportValidator handleUserBlockingOrCreateReportValidator;

    public HandleUserBlockingLogicDbImpl(UserDao userDao, StatusDao statusDao) {
        this.userDao = userDao;
        this.statusDao = statusDao;
        this.handleUserBlockingOrCreateReportValidator =
                new HandleUserBlockingOrCreateReportValidatorDbImpl(this.userDao);
    }

    @Override
    public void handleUserBlocking(String idParameter) {
        if (this.handleUserBlockingOrCreateReportValidator.isValidBlockingOrReportCreating(idParameter)) {
            User blockingUser = userDao.findById(Long.valueOf(idParameter)).orElseThrow(IllegalArgumentException::new);
            Status newStatus = getNewStatus(blockingUser.getStatus());
            userDao.update(new User(
                    blockingUser.getId(),
                    blockingUser.getLogin(),
                    blockingUser.getPassword(),
                    blockingUser.getRole(),
                    newStatus,
                    false));
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Status getNewStatus(Status status) {
        String newStatusTitle;
        if (status.getTitle().equals(Resourcer.getString("db.status.active"))) {
            newStatusTitle = Resourcer.getString("db.status.blocked");
        } else {
            newStatusTitle = Resourcer.getString("db.status.active");
        }
        return statusDao.findByTitle(newStatusTitle).orElseThrow(IllegalArgumentException::new);
    }
}
