package com.techelevator.controller;

import com.techelevator.model.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class TagController {

    @RequestMapping(path = "/tags", method = RequestMethod.GET)
    public List<Tag> getAllTags () {

        return null;
    }
}
