package com.techelevator.controller;


import com.techelevator.dao.StudySessionDao;
import com.techelevator.model.StudySession;
import com.techelevator.services.StudySessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class StudySessionController {


    private final StudySessionDao studySessionDao;
    private final StudySessionService studySessionService;

    public StudySessionController(StudySessionDao studySessionDao, StudySessionService studySessionService) {
        this.studySessionDao = studySessionDao;
        this.studySessionService = studySessionService;
    }


    @RequestMapping(path = "/{userId}/studySessions", method = RequestMethod.GET)
    public List<StudySession> getAllStudySessionsFromUser(@PathVariable int userId) {
        return studySessionDao.getAllMyStudySessions(userId);

    }

    @RequestMapping(path = "/studySessions/{studySessionId}", method = RequestMethod.GET)
    public StudySession getStudySession(@PathVariable int studySessionId) {
        return studySessionDao.getStudySession(studySessionId);

    };

    @RequestMapping(path = "/studySessions", method = RequestMethod.POST)
    public StudySession createStudySession(@RequestBody StudySession studySession) {
        studySession = studySessionService.computePercents(studySession);
        return studySessionDao.createStudySession(studySession);
    }

    @RequestMapping(path = "/studySessions/{studySessionId}", method = RequestMethod.PUT)
    public boolean updateStudySession(@RequestBody StudySession studySession, @PathVariable int studySessionId) {
        return studySessionDao.updateStudySession(studySession, studySessionId);
    }



}
