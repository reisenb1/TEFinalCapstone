package com.techelevator.services;

import com.techelevator.model.StudySession;
import org.springframework.stereotype.Component;


@Component
public class StudySessionService {


    public StudySessionService(){

    }


    public StudySession computePercents (StudySession session){

        double confidencePercent = 0;
        double correctPercent = 0;
        int numGreen = session.getNumberGreen();
        int numYellow = session.getNumberYellow();
        double numCards = session.getNumberOfCards();
        int numCorrect = session.getNumberCorrect();

        confidencePercent = ((numGreen + 0.5*numYellow)/numCards)*100;
        correctPercent = (numCorrect/numCards)*100;

        session.setConfidencePercent(confidencePercent);
        session.setCorrectPercent(correctPercent);

        return session;

    }

}
