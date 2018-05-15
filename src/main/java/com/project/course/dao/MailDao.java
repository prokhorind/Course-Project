package com.project.course.dao;

import com.project.course.entity.User;
import com.project.course.exception.MailException;

/**
 * Created by kleba on 09.05.2018.
 */
public interface MailDao {
    void send(User user) throws MailException;
}
