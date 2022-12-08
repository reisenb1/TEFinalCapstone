package com.techelevator.controller;

import com.techelevator.dao.DeckDao;
import com.techelevator.dao.TagDao;
import com.techelevator.model.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TagController {

    private TagDao tagDao;

    public TagController(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @RequestMapping(path = "/tags", method = RequestMethod.GET)
    public List<Tag> getAllTags () {
        return tagDao.getAllTags();
    }
}
