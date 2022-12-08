package com.techelevator.dao;

import com.techelevator.model.Deck;
import com.techelevator.model.StudySession;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class JdbcStudySessionDaoTest extends BaseDaoTests {

    @Test
    public void getAllMyStudySessions() {

        StudySessionDao studySessionDao = new JdbcStudySessionDao(new JdbcTemplate(dataSource));
        List<StudySession> studySessions = studySessionDao.getAllMyStudySessions(1);
        Assert.assertEquals(10, studySessions.get(0).getNumberOfCards());

    }

    @Test
    public void getStudySession() {
        StudySessionDao studySessionDao = new JdbcStudySessionDao(new JdbcTemplate(dataSource));
        StudySession studySession = studySessionDao.getStudySession(1);
        Assert.assertEquals(10, studySession.getNumberOfCards());
    }

    @Test
    public void createStudySession() {
        StudySessionDao studySessionDao = new JdbcStudySessionDao(new JdbcTemplate(dataSource));
        StudySession studySession = new StudySession();
        studySession.setUserId(1);
        studySession.setDeckId(1);
        studySession.setNumberOfCards(10);
        studySession.setNumberCorrect(6);
        studySession.setNumberGreen(5);
        studySession.setNumberYellow(5);
        studySession.setNumberRed(0);
        studySession.setConfidencePercent(50);
        studySession.setCorrectPercent(60);
        StudySession result = studySessionDao.createStudySession(studySession);
        Assert.assertEquals(1, result.getUserId());
        Assert.assertEquals(1, result.getDeckId());
        Assert.assertEquals(10, result.getNumberOfCards());
        Assert.assertEquals(5, result.getNumberYellow());
    }

    @Test
    public void updateStudySession() {
        StudySessionDao studySessionDao = new JdbcStudySessionDao(new JdbcTemplate(dataSource));
        StudySession studySession = studySessionDao.getStudySession(1);
        studySession.setNumberRed(9);
        studySessionDao.updateStudySession(studySession, 1);
        StudySession updatedStudySession = studySessionDao.getStudySession(1);
        Assert.assertEquals(9, updatedStudySession.getNumberRed());
        Assert.assertTrue(true);

    }
}