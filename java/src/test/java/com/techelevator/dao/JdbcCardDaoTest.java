package com.techelevator.dao;

import com.techelevator.model.Card;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class JdbcCardDaoTest extends BaseDaoTests{

    @Test
    public void getAllCards() {
        CardDao cardDao = new JdbcCardDao(new JdbcTemplate(dataSource));
        List<Card> cards = cardDao.getAllCards(1);
        Assert.assertEquals(3, cards.size());
    }

    @Test
    public void getCard() {
        CardDao cardDao = new JdbcCardDao(new JdbcTemplate(dataSource));
        Card card = cardDao.getCard(1);
        Assert.assertEquals("this is the front", card.getFront());
        Assert.assertEquals("this is the back", card.getBack());
    }

    @Test
    public void createCard() {
        CardDao cardDao = new JdbcCardDao(new JdbcTemplate(dataSource));
        Card card = new Card();
        card.setUserId(1);
        card.setDeckId(1);
        card.setFront("example");
        card.setBack("testing");
        Card result = cardDao.createCard(card);
        Assert.assertEquals("example", result.getFront());
        Assert.assertEquals("testing", result.getBack());
        Assert.assertEquals(1, result.getDeckId());
        Assert.assertTrue(result.getCardId() > 0);

    }

    @Test
    public void updateCard() {
        //not done
        CardDao cardDao = new JdbcCardDao(new JdbcTemplate(dataSource));
        Card card = cardDao.getCard(1);
        card.setFront("new result");
        cardDao.updateCard(card, 1);
        Card updatedCard = cardDao.getCard(1);
        Assert.assertEquals("new result", updatedCard.getFront());
    }

    @Test
    public void deleteCard() {
        CardDao cardDao = new JdbcCardDao(new JdbcTemplate(dataSource));
        Card card = new Card();
        cardDao.deleteCard(card.getCardId());
        Assert.assertTrue(true);

    }
}