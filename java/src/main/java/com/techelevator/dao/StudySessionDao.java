package com.techelevator.dao;


import com.techelevator.model.StudySession;

import java.util.List;

public interface StudySessionDao {

    List<StudySession> getAllMyStudySessions(int userId);

    StudySession getStudySession(int studySessionId);

    StudySession createStudySession(StudySession studySession);

    boolean updateStudySession(StudySession studySession, int studySessionId);

}
