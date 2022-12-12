package com.techelevator.dao;

import com.techelevator.model.StudySession;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcStudySessionDao implements StudySessionDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcStudySessionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<StudySession> getAllMyStudySessions(int userId) {
        List<StudySession> allStudySessions = new ArrayList<>();
        String sql = "SELECT * FROM study_session WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()) {
            allStudySessions.add(mapRowToStudySession(results));
        }
        return allStudySessions;
    }

    @Override
    public StudySession getStudySession(int studySessionId) {
        String sql = "SELECT * FROM study_session WHERE study_session_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, studySessionId);
        if (rowSet.next()) {
            StudySession studySession = mapRowToStudySession(rowSet);
            return studySession;
        } else {
            return null;
        }
    }

    @Override
    public StudySession createStudySession(StudySession studySession) {
        String sql = "INSERT INTO study_session(user_id, deck_id, number_of_cards, number_correct," +
                "number_green, number_yellow, number_red, confidence_percent, correct_percent) \n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING study_session_id;";
        Integer studySessionId = jdbcTemplate.queryForObject(sql, Integer.class, studySession.getUserId(),
                studySession.getDeckId(), studySession.getNumberOfCards(), studySession.getNumberCorrect(),
                studySession.getNumberGreen(), studySession.getNumberYellow(), studySession.getNumberRed(),
                studySession.getConfidencePercent(), studySession.getCorrectPercent());
        studySession.setStudySessionId(studySessionId);

        return studySession;
    }

    @Override
    public boolean updateStudySession(StudySession studySession, int studySessionId) {
        String sql = "UPDATE study_session SET user_id = ?, deck_id = ?, number_of_cards = ?, number_correct = ?, \n" +
                                "number_green = ?, number_yellow = ?, number_red = ?, confidence_percent = ?, correct_percent =? \n" +
                "WHERE study_session_id = ?;";
        int count = jdbcTemplate.update(sql,studySession.getUserId(),
                studySession.getDeckId(), studySession.getNumberOfCards(), studySession.getNumberCorrect(),
                studySession.getNumberGreen(), studySession.getNumberYellow(), studySession.getNumberRed(),
                studySession.getConfidencePercent(), studySession.getCorrectPercent(), studySessionId);
        return count == 1;
    }

    private StudySession mapRowToStudySession(SqlRowSet rowSet) {
        StudySession studySession = new StudySession();
        studySession.setStudySessionId(rowSet.getInt("study_session_id"));
        studySession.setUserId(rowSet.getInt("user_id"));
        studySession.setDeckId(rowSet.getInt("deck_id"));
        studySession.setNumberOfCards(rowSet.getInt("number_of_cards"));
        studySession.setNumberCorrect(rowSet.getInt("number_correct"));
        studySession.setNumberGreen(rowSet.getInt("number_green"));
        studySession.setNumberYellow(rowSet.getInt("number_yellow"));
        studySession.setNumberRed(rowSet.getInt("number_red"));
        studySession.setConfidencePercent(rowSet.getInt("confidence_percent"));
        studySession.setCorrectPercent(rowSet.getInt("correct_percent"));

        return studySession;
    }

}
