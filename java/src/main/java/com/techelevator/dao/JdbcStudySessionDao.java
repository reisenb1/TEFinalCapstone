package com.techelevator.dao;

import com.techelevator.model.StudySession;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcStudySessionDao implements StudySessionDao{

    @Override
    public List<StudySession> getAllMyStudySessions(int userId) {
        return null;
    }

    @Override
    public StudySession getStudySession(int studySessionId) {
        return null;
    }

    @Override
    public StudySession createStudySession(StudySession StudySession) {
        return null;
    }

    @Override
    public boolean updateStudySession(StudySession StudySession) {
        return false;
    }
}
