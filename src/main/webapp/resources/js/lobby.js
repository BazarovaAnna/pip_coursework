// СПИСОК ПЕРЕМЕННЫХ

// Клиент для работы с сокетами
var stompClient = null;

// Список созданых персонажей
var charactersData;

// Список игр персонажа
var gamesData;

// Список всех активных игр
var allGamesData;

// Флаг выбора режима
var isChosen = false;

// Флаг выбранной роли для игры
var isGM = false;

// Флаг подключения к игре
var isConnectionGame = false;

// Выбранная для поиска игра ГМом
var currentGmGame;

// Выбранный персонаж
var currentCharacter;

// Подписка на переход к игровому полю
var subscriptionOnRedirectToGameField;


window.onload = function (ev) {
    createConnectionToGameWebSocketEndPoint();

    $("#gm-mode").mousedown(renderGmForm).mouseover(function () {
        $(".shadow-form").remove();
        $("#player-mode").prepend("<div class='shadow-form'></div>");
    }).mouseout(function () {
        if(!isChosen)
            $(".shadow-form").remove();
        hideGame();
        isGM = true;
    });

    $("#player-mode").mousedown(renderPlayerForm).mouseover(function () {
        // Затемняем картинку игрока
        $(".shadow-form").remove();
        $("#gm-mode").prepend("<div class='shadow-form'></div>");
    }).mouseout(function () {
        if(!isChosen)
            $(".shadow-form").remove();
        reversePlayerForm();

        getDataFromServer('/lobby/allgames', getAllGameCallback);
        hideGame();
        isGM = false;
    });

    getDataFromServer('/user/allcharacters', createCharacterList);
    getDataFromServer('/lobby/usergames', getUserCreatedGameCallback);
    getDataFromServer('/lobby/allgames', getAllGameCallback);
};

// РАБОТА С СЕРВЕРОМ POST/GET ЗАПРОСЫ

// Общая функция отправки get запроса на сервер
function getDataFromServer(url, callback) {
    $.ajax({
        url: url,
        type: "GET",
        processData: false,
        contentType: false,
        cache: false,
        beforeSend: function(request) {
            return request.setRequestHeader('X-CSRF-Token', $("input[name*='_csrf']").val());
        },
        success: function(data, textStatus, jqXHR) {
            callback(data);
        },
    });
}

// Общая функция отправки post запроса на сервер
function postDataToServer(url, data, onSuccess, contentType) {
    $.ajax({
        url: url,
        type: 'POST',
        contentType: contentType,
        data: data,
        processData: false,
        cache: false,
        beforeSend: function(request) {
            return request.setRequestHeader('X-CSRF-Token', $("input[name*='_csrf']").val());
        },
        success: function(data, textStatus, jqXHR) {
            onSuccess(data);
        },
    });
}

// ОБРАБОТКА ДАННЫХ, ПОЛУЧЕННЫХ С СЕРВЕРА

// Создание списка персонажей
function createCharacterList(data) {
    charactersData = $.parseJSON(JSON.stringify(data));

    $("#character-list").empty();

    for(var i = 1; i < 5; i++){
        if(charactersData[i-1] != null){
            var character = "<div class='photobox photobox character-" + i +"' style='margin: 2.5%;'>" +
                "<div class='photobox__previewbox'>" +
                "<img class='character-preview img-thumbnail' src='"+ charactersData[i-1].race.imgPath +"'>" +
                "<span class='photobox__label'>"+charactersData[i-1].name + "</span>" +
                "<span class='level-label'>Level: "+charactersData[i-1].level + "</span>" +
                "</div></div>";

            $("#character-list").prepend(character);

            $(".character-" + i).mousedown({item: charactersData[i-1]},function (event) {
                // Открываем вторую часть меню персонажа
                $("#player-form-2").show(600);
                currentCharacter = event.data.item;
                fillCharacterPanal();
                moveToAnchor("#player-form-2", event);
            });
        }
        else{
            if(charactersData[i-1] === 0){
                $("#character-list").prepend(
                    '<h2 class="character-list-header">У вас еще нет персонажей!</h2>');
            }
        }
    }
}

// Обработка списка созданных игр пользователем
function getUserCreatedGameCallback(data) {
    gamesData = $.parseJSON(JSON.stringify(data));
    createGMGameList();
}

// Создание списка созданный игр для ГМа
function createGMGameList() {
    var tag = "#gm-games-slider";

    $(tag).empty();
    var addNewGameBtn = "<div class='game-slide slide-gm-1 btn-create-new-game' id='slide-gm-1'><h1 class='label-under-glass'>Новая игра</h1><div class='innder-slide'></div></div>";

    $(tag).prepend(addNewGameBtn);
    $(".btn-create-new-game").click(renderCreatingGameForm);

    for(var i = 2; i <= gamesData.length+1; i++){
        if(i - 2 < gamesData.length){
            var slide = "<div class='game-slide slide-gm-" + i + "'>" +
                "<h2 class='label-under-glass' style='top: -40;'>" + gamesData[i-2].name+ "</h2>" +
                "<div class='innder-slide''>" +
                "<div style='display: inline;'>" +
                "<h3 class='label-under-glass' style='right: 1em'>Кол-во игроков: </h3>" +
                "<h3 class='label-under-glass' style='left: 6em; top: 1.5em'>" + gamesData[i-2].personCount +"</h3>" +
                "</div>" +
                "</div></div>";

            $(tag).append(slide);

            $(".slide-gm-" + i).click(
                {
                    gameId:i-2,
                    gameCollection: gamesData,
                    selector:"#gm-game-info",
                    callback:gmGameInfoFormCallBack,
                    backfunction: hideGame,
                    executefunction: shareGame,
                    startfunction: startGameGM
                }, renderGameInfoForm);
        }
    }
}

// Обработка всех активных игр
function getAllGameCallback(data) {
    allGamesData = $.parseJSON(JSON.stringify(data));
    createUserGameList();
}

// Создание спика доступныхы игр для игрока
function createUserGameList() {
    var tag = "#player-games-slider";
    $(tag).empty();
    for(var i = 1; i <= allGamesData.length; i++){
        if(i - 1 < allGamesData.length){
            var slide = "<div class='game-slide slide-player-" + i + "'>" +
                "<h2 class='label-under-glass' style='top: -40;'>" + allGamesData[i-1].name+ "</h2>" +
                "<div class='innder-slide''>" +
                "<div style='display: inline;'>" +
                "<h3 class='label-under-glass' style='right: 1emplayer-games-slider'>Кол-во игроков: </h3>" +
                "<h3 class='label-under-glass' style='left: 7em; top: 1.5em'>" + allGamesData[i-1].personCount +"</h3>" +
                "</div>" +
                "</div></div>";

            $(tag).append(slide);

            $(".slide-player-" + i).click(
                {
                    gameId:i-1,
                    gameCollection: allGamesData,
                    selector:"#player-game-info",
                    callback:playerGameInfoFormCallBack,
                    backfunction: backToPlayerEventHandler,
                    executefunction: connectionPlayerEventHandler,
                    startfunction: startGamePlayer
                }, renderGameInfoForm);
        }
    }
}

// Создание новой игры
function createNewGame() {
    var form = $('#new-game-form')[0];

    postDataToServer(
        '/lobby/newgame',
        new FormData(form),
        function () {
            clearGameForm();
            getDataFromServer('/lobby/usergames', getUserCreatedGameCallback);
        },
        false);
}

// ОТРИСОВКА КОМПОНЕНТОВ

// Отрисовка формы с информацией об игре
function renderGameInfoForm(event) {
    currentGmGame = event.
        data.
        gameCollection[event.data.gameId];
    event.data.callback();
    $(event.data.selector).load("../../resources/html/gameinfo.html", function () {
        $("#game-form-header").empty().
        text("Информация об игре").
        addClass("fill-game-form-header");
        $("#game-name").text(currentGmGame.name);
        $("#game-master").text(currentGmGame.gameMaseterName);
        $("#game-count").text(currentGmGame.personCount);
        $("#game-descr").text(currentGmGame.description);

        $("#share-game-btn").click({gameId: event.data.gameId}, event.data.executefunction);

        $("#start-game-btn").click(event.data.startfunction);
        //Задание обработчиков ивентов для кнопок
        $('#btn-back-to-game-list').click(event.data.backfunction);
    });
}

// РАБОТА С REST-API

// ОТКРЫТИЕ И СОКРЫТИЕ ИГР ДЛЯ ПОИСКА

// Начало новой игры (Переход на страницу с игровым полем)
function startGameGM() {
    isStartGame();
}

// Подтверждение готовности игроком (Индикатор того, что игрок готов перейти на страницу с игровым полем)
function startGamePlayer() {
    isReadyToGame();
}

// Открытие игры для поиска
function shareGame(event){
    postDataToServer('/lobby/sharegame',
        JSON.stringify({"gameName": gamesData[event.data.gameId].name}),
            shareGameEventHandler,
            'application/json');

    isConnectionGame = true;

    subscribeToStartGame();
}

// Сокрытие игры для поиска
function hideGame(){
    hideGamePostRequest();

    isConnectionGame = false;

    if(subscriptionOnRedirectToGameField != null){
        subscriptionOnRedirectToGameField.unsubscribe();
    }
}

// Запрос на скрытие игры от других игроков
function hideGamePostRequest() {
    if(currentGmGame != null){
        postDataToServer('/lobby/hidegame',
            JSON.stringify({"gameName": currentGmGame.name}),
            hideGameEventHandler,
            'application/json');
    }
}

// ОТРИСОВКА КОМПОНЕНТОВ ЛОББИ

// Отрисовка формы игрока
function renderPlayerForm(event) {
    $("#gm-form").hide();
    $("#player-form").show(600);

    isChosen = true;

    moveToAnchor("#player-form", event);
}

// Отрисовка формы ведущего
function renderGmForm(event) {
    $("#player-form").hide();
    $("#player-form-2").hide();

    $("#gm-form").show(600);

    isChosen = true;

    moveToAnchor("#gm-form", event);
}

// Заполнение информации о персонаже
function fillCharacterPanal() {
    reversePlayerForm();

    var sex = currentCharacter.sex;
    $("#char-name").text(currentCharacter.name)
    $("#char-condition").text(sex === 'm' ? currentCharacter.condition : 'Жива')
    $("#char-level").text(currentCharacter.level)
    $("#char-race").text(currentCharacter.race.type)
    $("#char-class").text(currentCharacter.userClass)
    $("#char-sex").text(currentCharacter.sex === 'm' ? 'Мужчина' : 'Женщина')
    $("#char-money").text(currentCharacter.persMoney)
    $("#char-maxWeight").text(currentCharacter.maxWeight)

    $("#char-avatar").attr("src",currentCharacter.race.imgPath)
}

// Перемещение на якорь
function moveToAnchor(anchor, event) {
    var top = $(anchor).offset().top
    $('html, body').stop().animate({
        scrollTop: top + 100
    }, 777);

    event.preventDefault();
    return false;
}

// Откатываем форму игрока до первоначальных значений
function reversePlayerForm() {
    backToPlayerEventHandler();
    $("#player-game-info").empty();
    $("#player-character-info").show();
}

// Скрытие формы создания игры после клика на игру
function gmGameInfoFormCallBack() {
    $("#create-new-game-form").hide();
}

// Обработчик событий при клике на кнопку выбрать для игрока
function connectionPlayerEventHandler(event){
    $("#btn-back-to-game-list").show();
    $("#share-game-btn").hide();
    $("#player-games-slider").empty();
    $("#start-game-btn").show(400);

    connectToGame();

    subscribeToStartGame();
}

// Скрытие формы описания персонажа после клика на игру
function playerGameInfoFormCallBack() {
    $("#player-character-info").hide();
}

// Отрисовка формы для создания новой игры
function renderCreatingGameForm() {
    $("#gm-game-info").empty();
    $("#create-new-game-form").show(600);

    $("#game-form-header").empty().
        text("Новая игра").
        addClass("fill-game-form-header");

    // Очистка данных внутри формы создания игры
    $("#new-game-form").trigger('reset');

    $("#submit-btn").click(createNewGame);
}

// Отрисовка списка персонажей для определенной роли
function renderCharacterList(characters, tag) {
    $(tag).empty();
    for(var i = 0; i < characters.length; i++){
        $(tag).append(formCharacterSlide(characters[i], i));
    }
}

// Формирование слайда с персонажем
function formCharacterSlide(character, index) {
    var slide = "<div class='game-slide slide-player-" + index + "'>" +
        "<h2 class='label-under-glass' style='top: -40;'>" + character.characterName + "</h2>" +
        "<div class='innder-slide''>" +
        "<div style='display: inline;'>" +
        "<h3 class='label-under-glass' style='right: 1emplayer-games-slider'>Расса: </h3>" +
        "<h3 class='label-under-glass' style='left: 7em; top: 1.5em'>" + character.race.type +"</h3>" +
        "</div>" +
        "</div></div>";

    return slide;
}

// ОБРАБОТЧИКИ СОБЫТИЙ

// Обработчик события при клике на кнопку выбора игры в лобби за ГМа
function shareGameEventHandler(){
    $("#btn-back-to-game-list").show();
    $("#share-game-btn").hide();
    $("#gm-games-slider").empty();
    $("#start-game-btn").show(400);
}

// Обработчик события при скрытии выбранной игры при игре за ГМа
function hideGameEventHandler(){
    $("#btn-back-to-game-list").hide();
    createGMGameList();
    $("#start-game-btn").hide();
    $("#share-game-btn").show();
}

// Обработчик события на возврат к списку игр для Игрока
function backToPlayerEventHandler() {
    $("#btn-back-to-game-list").hide();
    createUserGameList();

    if(isConnectionGame){
        disconnectFromGame();
    }

    $("#start-game-btn").hide();
    $("#share-game-btn").show();

    if(subscriptionOnRedirectToGameField != null){
        subscriptionOnRedirectToGameField.unsubscribe();
    }
}

// ДОПОЛНИЕЛЬНЫЙ ФУНКЦИОНАЛ

// Очистка данных внутри формы создания игры и сокрытие страницы создания игры
function clearGameForm(){
    $("#game-form-header").empty();
    $("#new-game-form").trigger('reset').hide();
    $("#game-form-header").removeClass("fill-game-form-header");
}

// Выбор количества персонажей в игре
$("#personCount").on("input", function() {
    var userListHtml = "";
    for(var i = 0; i < this.value; i++){
        userListHtml += "<i class='fa fa-user' aria-hidden='true'></i>";
    }

    this.setAttribute('value', this.value);
    $("#player-count-list").
        empty().
        prepend(userListHtml);
});

// РАБОТА С WEB-SOCKETS

// Создание точки входа для подключеия к лобби и подписка на событие присоединения к лобби
function createConnectionToGameWebSocketEndPoint() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe("/topic/joining", function (msg) {
            if(isConnectionGame){
                var message = JSON.parse(msg.body);

                if(!message.message){
                    var charactersInGroup = message;

                    if(charactersInGroup.length > 0 &&
                        charactersInGroup[0].gameId === currentGmGame.gameId){

                        if(isGM){
                            renderCharacterList(charactersInGroup, "#gm-games-slider");
                        }
                        else{
                            renderCharacterList(charactersInGroup, "#player-games-slider");
                        }
                    }
                }
                else {
                    if(!isGM){
                        alert(message.message);
                        backToPlayerEventHandler();
                    }
                }
            }
        });
    });
}

// Подписка на событие перехода на страницу с игрой
function subscribeToStartGame() {
    subscriptionOnRedirectToGameField = stompClient.subscribe("/topic/redirecting", function (msg) {
        var message = JSON.parse(msg.body);
        if(message.ready){
            if(isGM){
                hideGamePostRequest();
            }

            document.location.href = "/gamefield?session_id=" + message.sessionId;
        }
    });
}

// Сигнал готовности игрока
function isReadyToGame() {
    generalPlayerReadyMethod(true, currentCharacter.name);
}

// Сигнал начала игры ГМом
function isStartGame() {
    generalPlayerReadyMethod(true, null);
}

// Общий метод готовности игрока к игре
function generalPlayerReadyMethod(isReady, characterName) {
    stompClient.send("/app/lobby/ready",
        {},
        JSON.stringify({
            "gameId": currentGmGame.gameId,
            "characterName": characterName,
            "ready": isReady,
            "start": !characterName
        }));
}

// Подключение к группе
function connectToGame() {
    isConnectionGame = true;
    generalGameConnection(isConnectionGame);
}

// Отключение от группы
function disconnectFromGame() {
    isConnectionGame = false;
    generalGameConnection(isConnectionGame);
}

// Общий метод для активации/деактивации игры
function generalGameConnection(isConnectionGame) {
    stompClient.send("/app/lobby/connect",
        {},
        JSON.stringify({
            "gameId": currentGmGame.gameId,
            "characterName": currentCharacter.name,
            "connection": isConnectionGame
        }));
}
