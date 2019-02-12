// Список созданых персонажей
var charactersData;


window.onload = function (ev) {
    $("#gamemode").mouseover(function () {
        renderGameForm();
    })

    $("#gamemmode").mouseover(function () {
        // Затемняем картинку ведущий
        renderGmForm();
    })

    getAllCharacter();

    setDukatBorder();
};

// Отрисовка формы игрока
function renderGmForm() {
    // Затемняем картинку игрок
    $(".shadow-form").remove();
    $("#gamemode").prepend("<div class='shadow-form'></div>");

    $("#player-form").hide();
    $("#player-form-2").hide();
    $("#gm-form").show(600);

}

// Отрисовка формы ведущего
function renderGameForm() {
    $(".shadow-form").remove();
    $("#gamemmode").prepend("<div class='shadow-form'></div>");

    var playerForm = $("#player-form");
    var $gmForm =   $("#gm-form");

    $gmForm.hide();
    playerForm.show(600);
}


// Полученеи данных о все персонажах
function getAllCharacter() {
    $.ajax({
        url: '/user/allcharacters',
        type: "GET",
        processData: false,
        contentType: false,
        cache: false,
        beforeSend: function(request) {
            return request.setRequestHeader('X-CSRF-Token', $("input[name*='_csrf']").val());
        },
        success: function(data, textStatus, jqXHR) {
            charactersData = $.parseJSON(JSON.stringify(data));
            createCharacterList();
        },
    });
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

            $(".character-" + i).mouseover({item: charactersData[i-1]},function (event) {
                // Открываем вторую часть меню персонажа
                $("#player-form-2").show(600);

                fillCharacterPanal(event.data.item)
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
function setDukatBorder(){
    $('.dragElement').draggable({
        axis: "x", containment: "parent",
        stop:stopDukat
    }).filter('#dukat').draggable("option", "axis", "y");
}

// Событие на завершение перемещения монетки
function stopDukat() {
    // TODO Дописать логику перетаскивания монетки
    alert('Логика перетаскивания')
}