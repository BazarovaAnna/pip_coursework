// Список созданых персонажей
var charactersData;

// Список игр персонажа
var gamesData;

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

    setDukatBorder(['#player-dukat', '#gm-dukat']);
};

// Отрисовка формы игрока
function renderPlayerForm(event) {
    $("#gm-form").hide();
    $("#player-form").show(600);

    $("#gm-dukat").removeClass("active-dukat");
    $("#player-dukat").addClass("active-dukat");

    isChosen = true;

    moveToAnchor("#player-form");
}

// Отрисовка формы ведущего
function renderGmForm(event) {
    $("#player-form").hide();
    $("#player-form-2").hide();

    $("#gm-form").show(600);

    $("#player-dukat").removeClass("active-dukat");
    $("#gm-dukat").addClass("active-dukat");

    isChosen = true;

    moveToAnchor("#gm-form");
}

// Перемещение на якорь
function moveToAnchor(anchor) {
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
                moveToAnchor("#player-form-2");
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

    var addNewGameBtn = "<div class='game-slide slide-1 btn-create-new-game' id='slide-1'><h1 class='label-under-glass'>Новая игра</h1><div class='innder-slide'></div></div>";

    $(tag).prepend(addNewGameBtn);

    $(".btn-create-new-game").click(renderCreatingGameForm);

    for(var i = 2; i <= 3; i++){
        var slide = "<div class='game-slide slide-" + i + "'>" +
            "<div class='innder-slide'></div></div>";

        $(tag).append(slide);

        $(".slide-" + i).click({i:i}, renderGameInfoForm);
    }
}

// Отрисовка формы для создания новой игры
function renderCreatingGameForm() {
    $("#game-info").hide();
    $("#create-new-game-form").show(600);

    $("#game-form-header").empty()
    $("#game-form-header").text("Новая игра");
}

// Отрисовка формы с информацией об игре
function renderGameInfoForm(event) {
    // TODO Дописать отображение информации
    alert(event.data.i)
}

// Получение списка всех игр
function getAllCreatedGame() {

}