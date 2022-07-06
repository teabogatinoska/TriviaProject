'use strict'

let stompClient = null

const join = () => {
    const socket = new SockJS('/websocket')
    stompClient = Stomp.over(socket)
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame)
        stompClient.subscribe('/quizzes/open', function (response) {
            console.log("Response: " + response.body)
            onQuestionReceived(JSON.parse(response.body))
        });
    });
}

const openQuiz = () => {
    stompClient.send("/app/open", {}, JSON.stringify());

}

function setAnswers(payload) {

    var answers = document.getElementsByClassName("answerValue");

    for (var i = 0; i < answers.length; i++) {
        answers[i].id = payload.answers[i].id;
        answers[i].answer = payload.answers[i].answer;
    }

}

const onQuestionReceived = (payload) => {

    document.getElementById("hiddenQuestion").style.display = "block";
    document.getElementsByClassName("question").item(0).id = payload.id
    document.getElementsByClassName("question").item(0).id = payload.id
    document.getElementsByClassName("question").item(0).innerHTML = payload.question;
    document.getElementById("answer1").innerHTML = payload.answers[0].answer;
    document.getElementById("answer2").innerHTML = payload.answers[1].answer;
    document.getElementById("answer3").innerHTML = payload.answers[2].answer;

    setAnswers(payload);
}

const sendAnswer = () => {

    var selectedAnswer;
    var selectedAnswerId;
    var answers = document.getElementsByClassName("answerValue");

    var questionId = document.getElementsByClassName("question").item(0).id;
    console.log('Question Id: ' + questionId)

    for (var i = 0; i < answers.length; i++) {

        if (answers[i].checked) {
            selectedAnswer = answers[i].answer;
            console.log('Selected answer: ' + selectedAnswer)
            selectedAnswerId = answers[i].id;
            console.log("Selected answer id: " + selectedAnswerId)
            console.log('Question Id: ' + questionId)
        }
    }
    stompClient.send("/app/send/answer", {}, JSON.stringify({

        questionId: questionId,
        selectedAnswerId: selectedAnswerId
    }))
}