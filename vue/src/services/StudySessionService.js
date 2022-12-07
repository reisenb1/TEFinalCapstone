import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:9000"
});


export default {
    getStudySessions(userId) {
        return http.get(`/${userId}/studySessions`);
    },

    getStudySessionById(studySessionId) {
        return http.get(`/studySessions/${studySessionId}`)
    },

    addStudySession(studySession) {
        return http.post(`/studySessions`, studySession)
    },

    updateStudySession(studySession) {
        return http.put(`/studySessions/${studySession.studySessionId}`, studySession);
    },

}