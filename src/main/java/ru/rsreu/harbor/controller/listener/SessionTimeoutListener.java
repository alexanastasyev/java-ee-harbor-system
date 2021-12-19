package ru.rsreu.harbor.controller.listener;

import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.datalayer.dao.UserDao;
import ru.rsreu.harbor.datalayer.model.User;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionTimeoutListener implements HttpSessionListener {
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        UserDao userDao = ((UserDao) se.getSession().getServletContext().getAttribute(
                Resourcer.getString("servlet.context.attribute.name.user.dao")));
        Object login = se.getSession().getAttribute(Resourcer.getString("session.attribute.name.user"));
        if (login != null) {
            User user = userDao.findByLogin(login.toString()).orElseThrow(IllegalArgumentException::new);
            userDao.update(new User(
                    user.getId(),
                    user.getLogin(),
                    user.getPassword(),
                    user.getRole(),
                    user.getStatus(),
                    false
            ));
        }
    }
}
