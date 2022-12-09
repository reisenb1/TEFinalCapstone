package com.techelevator.dao;

import com.techelevator.model.Tag;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class JdbcTagDaoTest extends BaseDaoTests {

    @Test
    public void getAllTags() {

        TagDao tagDao = new JdbcTagDao(new JdbcTemplate(dataSource));
        List<Tag> allTags = tagDao.getAllTags();
        Assert.assertEquals(2, allTags.size());

    }
}