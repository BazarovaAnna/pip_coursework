// Список созданых персонажей
var charactersData;

// Список игр персонажа
var gamesData;
// Список всех активных игр
var allGamesData;
// Флаг выбора режима
var isChosen = false;

window.onload = function (ev) {
    $("#gm-mode").mousedown(renderGmForm).mouseover(function () {
        $(".shadow-form").remove();
        $("#player-mode").prepend("<div class='shadow-form'></div>");
    }).mouseout(function () {
        if(!isChosen)
            $(".shadow-form").remove();
    });

    $("#player-mode").mousedown(renderPlayerForm).mouseover(function () {
        // Затемняем картинку игрок
        $(".shadow-form").remove();
        $("#gm-mode").prepend("<div class='shadow-form'></div>");
    }).mouseout(function () {
        if(!isChosen)
            $(".shadow-form").remove();
    });

    getDataFromServer('/user/allcharacters', geAllCharacterCallback);
    getDataFromServer('/lobby/usergames', getUserCreatedGameCallback);
    getDataFromServer('/lobby/allgames', getAllGameCallback);

    setDukatBorder(['#player-dukat', '#gm-dukat']);

    //Задание обработчиков ивентов для кнопок
    $('#btn-back-to-game-list').click(backToGameListEventHandler);
};

// Отрисовка формы игрока
function renderPlayerForm(event) {
    $("#gm-form").hide();
    $("#player-form").show(600);

    $("#gm-dukat").removeClass("active-dukat");
    $("#player-dukat").addClass("active-dukat");

    isChosen = true;

    moveToAnchor("#player-form", event);
}

// Отрисовка формы ведущего
function renderGmForm(event) {
    $("#player-form").hide();
    $("#player-form-2").hide();

    $("#gm-form").show(600);

    $("#player-dukat").removeClass("active-dukat");
    $("#gm-dukat").addClass("active-dukat");

    isChosen = true;

    moveToAnchor("#gm-form", event);
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

// Общая функция получения данных с сервера
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

// Полученеи данных о всех персонажах
function geAllCharacterCallback(data) {
    charactersData = $.parseJSON(JSON.stringify(data));
    createCharacterList();
}

// Создание списка персонажей
function createCharacterList() {
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

                fillCharacterPanal(event.data.item)
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

// Заполнение информации о персонаже
function fillCharacterPanal(character) {
    var sex = character.sex;
    $("#char-name").text(character.name)
    $("#char-condition").text(sex === 'm' ? character.condition : 'Жива')
    $("#char-level").text(character.level)
    $("#char-race").text(character.race.type)
    $("#char-class").text(character.userClass)
    $("#char-sex").text(character.sex === 'm' ? 'Мужчина' : 'Женщина')
    $("#char-money").text(character.persMoney)
    $("#char-maxWeight").text(character.maxWeight)

    $("#char-avatar").attr("src",character.race.imgPath)
}

// ПЕРЕМЕЩЕНИЕ МОНЕТКИ

// Ограничение перемещения монетки
function setDukatBorder(dukats){
    $('.dragElement').draggable({
        axis: "x", containment: "parent",
        stop:dragDukat
    });

    $.each(dukats, function(_, value){
        $('.dragElement').filter(value).draggable("option", "axis", "y");
    })
}

// Событие на завершение перемещения монетки
function dragDukat() {
    if(typeof gamesData[0] === 'undefined'){
        alert("Пусто")
    }

    // TODO Дописать логику перетаскивания монетки
}

// Получение списка созданных игр пользователем
function getUserCreatedGameCallback(data) {
    gamesData = $.parseJSON(JSON.stringify(data));
    createGameList("#gm-games-slider");
}

// Создание списка уже созданный игр для ГМа
function createGameList(tag) {
    $(tag).empty();
    var addNewGameBtn = "<div class='game-slide slide-gm-1 btn-create-new-game' id='slide-gm-1'><h1 class='label-under-glass'>Новая игра</h1><div class='innder-slide'></div></div>";

    $(tag).prepend(addNewGameBtn);

    $(".btn-create-new-game").click(renderCreatingGameForm);

    for(var i = 2; i <= gamesData.length; i++){
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

            $(".slide-gm-" + i).click({gameId:i-2, selector:"#gm-game-info"}, renderGameInfoForm);
        }
    }
}

// Получение списка созданных игр пользователем
function getAllGameCallback(data) {
    allGamesData = $.parseJSON(JSON.stringify(data));
    createAllActiveGameList("#player-games-slider");
}

// Создание спика доступныхы игр для игрока
function createAllActiveGameList(tag) {
    $(tag).empty();
    for(var i = 1; i <= allGamesData.length; i++){
        if(i - 1 < allGamesData.length){
            var slide = "<div class='game-slide slide-player-" + i + "'>" +
                "<h2 class='label-under-glass' style='top: -40;'>" + allGamesData[i-1].name+ "</h2>" +
                "<div class='innder-slide''>" +
                "<div style='display: inline;'>" +
                "<h3 class='label-under-glass' style='right: 1em'>Кол-во игроков: </h3>" +
                "<h3 class='label-under-glass' style='left: 6em; top: 1.5em'>" + allGamesData[i-1].personCount +"</h3>" +
                "</div>" +
                "</div></div>";

            $(tag).append(slide);

            $(".slide-player-" + i).click({gameId:i-1, selector:"#player-game-info"}, renderGameInfoForm);
        }
    }
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

// Очистка данных внутри формы создания игры и сокрытие страницы создания игры
function clearGameForm(){
    $("#game-form-header").empty();
    $("#new-game-form").trigger('reset').hide();
    $("#game-form-header").removeClass("fill-game-form-header");
}

// Создание новой игры
function createNewGame(event) {
    var form = $('#new-game-form')[0];

    var data =  new FormData(form);
    // Отправляем запрос
    $.ajax({
        url: '/lobby/newgame',
        type: "POST",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        beforeSend: function(request) {
            return request.setRequestHeader('X-CSRF-Token', $("input[name*='_csrf']").val());
        },
        success: function(data, textStatus, jqXHR) {
            clearGameForm();
            getDataFromServer('/lobby/usergames', getUserCreatedGameCallback);
        },
    });
}

// Отрисовка формы с информацией об игре
function renderGameInfoForm(event) {
    // TODO Дописать отображение информации
    $("#create-new-game-form").hide();

    $(event.data.selector).load("../../resources/html/gameinfo.html", function () {
        $("#game-form-header").empty().
        text("Информация об игре").
        addClass("fill-game-form-header");
        $("#game-name").text(gamesData[event.data.gameId].name);
        $("#game-count").text(gamesData[event.data.gameId].personCount);
        $("#game-descr").text(gamesData[event.data.gameId].description);

        $("#share-game-btn").click({gameId: event.data.gameId},shareGame);
    });
}

// Запрос на открытие игры для поиска
function shareGame(event){
    // Отправляем запрос
    $.ajax({
        url: '/lobby/sharegame',
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify({"gameName": gamesData[event.data.gameId].name}),
        processData: false,
        cache: false,
        beforeSend: function(request) {
            return request.setRequestHeader('X-CSRF-Token', $("input[name*='_csrf']").val());
        },
        success: function(data, textStatus, jqXHR) {
            shareGameEventHandler(data);
        },
    });
}

// Получение списка всех игр
function getAllCreatedGame() {

}

// Обработчик события при клике на кнопку выбора игры в лобби за ГМа
function shareGameEventHandler(data){
    var response = $.parseJSON(JSON.stringify(data));
    $("#btn-back-to-game-list").show();
    $("#share-game-btn").hide();
    $("#gm-games-slider").empty();
    $("#start-game-btn").show(400);
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

// Обработчик события при возврате к списку игр
function backToGameListEventHandler() {
    $("#btn-back-to-game-list").hide();
    createGameList("#gm-games-slider");
    $("#start-game-btn").hide();
    $("#share-game-btn").show();
}
