package org.ichat.backend.service;

import org.ichat.backend.exeception.AccountException;
import org.ichat.backend.model.Jobseeker;
import org.ichat.backend.model.User;

import java.util.List;

public interface IJobseekerService{
    List<Jobseeker> findAll();
    Jobseeker findBy(String email);
    Jobseeker findBy(Long jobseeker_id);

    void deleteBy(Long userID);
    Jobseeker update(Long oldUserID, Jobseeker newUser) throws AccountException;
    Jobseeker add(Jobseeker jobseeker);
}
