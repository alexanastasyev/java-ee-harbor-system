package ru.rsreu.harbor.controller.command.moderator.users;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.StatusDao;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.Status;
import ru.rsreu.harbor.datalayer.model.User;

public class HandleUserBlockingLogicDbImpl implements HandleUserBlockingLogic {
    private final UserDao userDao;
    private final StatusDao statusDao;

    public HandleUserBlockingLogicDbImpl(UserDao userDao, StatusDao statusDao) {
        this.userDao = userDao;
        this.statusDao = statusDao;
    }

    @Override
    public void handleUserBlocking(String id) {
        User user = userDao.findById(Long.valueOf(id));
        Status newStatus = getNewStatus(user.getStatus());
        userDao.update(new User(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getRole(),
                newStatus
        ));
    }

    private Status getNewStatus(Status status) {
        String newStatusTitle;
        if (status.getTitle().equals(Resourcer.getString("db.status.active"))) {
            newStatusTitle = Resourcer.getString("db.status.blocked");
        } else {
            newStatusTitle = Resourcer.getString("db.status.active");
        }
        return statusDao.findByTitle(newStatusTitle);
    }
}
