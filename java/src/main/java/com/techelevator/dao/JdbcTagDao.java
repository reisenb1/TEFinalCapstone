package com.techelevator.dao;

import com.techelevator.model.Deck;
import com.techelevator.model.StudySession;
import com.techelevator.model.Tag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcTagDao implements TagDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTagDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tag> getAllTags() {

        String sql = "SELECT tag_name FROM tags;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        List<Tag> allTags = new ArrayList<>();
        if (rowSet.next()) {
            allTags.add(mapRowToTag((rowSet)));
            return allTags;
        } else {
            return allTags;
        }
    }


    private Tag mapRowToTag(SqlRowSet rowSet) {
        Tag tag = new Tag();
        tag.setTagName(rowSet.getString("tag_name"));
        return tag;
    }
}