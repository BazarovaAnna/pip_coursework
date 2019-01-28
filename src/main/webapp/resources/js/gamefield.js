let coordinates = [[0, 0], [0, 0], [0, 0], [0, 0], [0, 0]];
//x, y and user id on map
let uid1;
let uid2;
let uid3;
let uid4;
let uid5;

function init(id1, id2, id3, id4, id5) {
    uid1 = id1;
    uid2 = id2;
    uid3 = id3;
    uid4 = id4;
    uid5 = id5;
}

function sendMessage() {
    let chat = document.getElementById("chat-messages");
    //в комментариях написан псевдокод, который будет работать в нормальной версии
    //а пока что тут тестовая заглушка
    //let messageInp = document.getElementById("chat-write");
    //let user = getUser();
    // ajaxSendMessage(user, message);
    //let newP = document.createElement("p");
    //newP.innerHTML = "[ " + user + " ] : " + message;
    //chat.appendChild(newP);
    //let objDiv = document.getElementById("content-discussion");
    //objDiv.scrollTop = objDiv.scrollHeight;
    let message = "KEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEKKEK";
    let user = "Зигарыг";
    let newP = document.createElement("p");
    newP.innerHTML = "[ " + user + " ] : " + message;
    chat.appendChild(newP);
    let objDiv = document.getElementById("content-discussion");
    objDiv.scrollTop = objDiv.scrollHeight;
}

function fieldClick(x, y) {
    //if(!gm)
    coordinates[0] = [x, y];
    drawSpot();
    //ajaxRequest(username, x, y);
    //чтобы другие юзеры видели перемещение
}

function drawSpot() {
    for (let i=0; i < 5; i++) {
        let curDiv = document.getElementById(coordinates[i][0].toString() + coordinates[i][1].toString());
        curDiv.appendChild(document.getElementById("spot" + (i + 1).toString()));
    }
}