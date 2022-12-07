package com.techelevator.controller;


import com.techelevator.dao.StudySessionDao;
import com.techelevator.services.StudySessionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class StudySessionController {


    private final StudySessionDao studySessionDao;
    private final StudySessionService studySessionService;

    public StudySessionController(StudySessionDao studySessionDao, StudySessionService studySessionService) {
        this.studySessionDao = studySessionDao;
        this.studySessionService = studySessionService;
    }

}
