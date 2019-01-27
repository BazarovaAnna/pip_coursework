// Список созданых персонажей
var charactersData;

// Цвета для слайдеров
var sliderColors =  ["m--blend-green","m--blend-red","m--blend-blue"];

// Рандомное значение между min и max
function getRandomInt(min, max)
{
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

window.onload = function () {
    laodRace();
    getAllCharacter();
    $("#race-dropdown").on("change",function () {
        $("#character-avatar").attr('src', "../../resources/default/img/man.png");
    });

    $("#submit-btn").on("click",function (event) {
        addNewCharacter();
    });

    // Загрузка файла в поле input file
    $('#character-avatar-box').click(function( event ){
        loadImgByRace();
    });

    setTimeout(function () {

        $.getScript('../../resources/js/herodashboard.js', function() {

        });
    }, 500);
};

// Метод получения списка рас с сервера
function laodRace() {
    $.ajax({
        url: '/newcharacter/allrace',
        type: "POST",
        data: null,
        processData: false,
        contentType: false,
        cache: false,
        beforeSend: function(request) {
            return request.setRequestHeader('X-CSRF-Token', $("input[name*='_csrf']").val());
        },
        success: function(data, textStatus, jqXHR) {
            var option = '';

            data.forEach(function (item) {
                option += '<option value="'+item+'">' + item + '</option>';
            });

            $("#race-dropdown").append(option);
        },
    });
}

// Получение изображений для рас с сервера
function loadImgByRace(){

    var data = new FormData();

    data.append("race", $("#race-dropdown").val());

    // Отправляем запрос
    $.ajax({
        url: '/newcharacter/race',
        type: "POST",
        enctype: 'multipart/form-data',
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        beforeSend: function(request) {
            return request.setRequestHeader('X-CSRF-Token', $("input[name*='_csrf']").val());
        },
        success: function(data, textStatus, jqXHR) {
            // Отрисовка изображения
            drawRaceImg(data);
        }
    });
}

// Отрисовка изображения
function drawRaceImg(data) {
    var imgs = "<ul class='ur'></ul>";
    $("#modal-window").prepend(imgs);

    $("ul.ur").on("click", function () {
        $("#modal-window").hide(800);
        setTimeout($("ul.ur").remove(), 1200);
    });

    data.forEach(function (item, i)
    {
        var img = '<div class="photobox photobox" id="avatar'+ i + '">' +
                  '<div class="photobox__previewbox">' +
                  '<img src="' + item + '" class="photobox__preview img-thumbnail" alt="Preview">' +
                  '<span class="photobox__label">Выбрать аватар</span>' +
                  '</div>' +
                  '</div>';
        $("ul.ur").prepend(img);
        // Задание параметра для скрытого поля
        $("#avatar"+i).click({item: item}, function setImg(event) {
            $("#character-img").val(event.data.item);
            $("#character-avatar").attr('src', event.data.item);
        });
    });

    $("#modal-window").addClass("modal-window-active");
    $("body").addClass("body-window-active");
    $("#modal-window").show(600);

}

// Создание нового персонажа
function addNewCharacter() {
    $(".alert.alert-msg").remove()


    var form = $('#new-character-form')[0];

    var data = new FormData(form);

    // Отправляем запрос
    $.ajax({
        url: '/newcharacter/add'  ,
        type: "POST",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        beforeSend: function(request) {
            return request.setRequestHeader('X-CSRF-Token', $("input[name*='_csrf']").val());
        },
        success: function(data, textStatus, jqXHR) {
            $("#msg-box").prepend(
              '<div class="alert alert-msg">' +
                '<h4 id="error-msg">'+ data +'</h4>' +
              '</div>'
            );
            getAllCharacter();
            setTimeout(function () {

                $.getScript('../../resources/js/herodashboard.js', function() {

                });
            }, 500);
        },
    });
}

// Полученеи данных о все персонажах
function getAllCharacter() {
    $.ajax({
        url: '/newcharacter/allcharacters',
        type: "GET",
        processData: false,
        contentType: false,
        cache: false,
        beforeSend: function(request) {
            return request.setRequestHeader('X-CSRF-Token', $("input[name*='_csrf']").val());
        },
        success: function(data, textStatus, jqXHR) {
            charactersData = $.parseJSON(JSON.stringify(data));
            createCarousel();
        },
    });
}

// Создание списка персонажей
function  createCarousel() {
    $("#character-slider").empty();
    var slides = "<div class='fnc-slider__slides'>";

    for(var i = 1; i < 5; i++){
        if(charactersData[i-1] != null){
            slides += createSlide(charactersData[i-1], i, sliderColors[getRandomInt(0,2)]);

        }
        else{
            slides += createEmptySlide(i)
        }
    }

    slides += "</div>";

    $("#character-slider").prepend(slides);
    $("#character-slider").prepend(createNavbar())
    addBtnAction()
}

// Добавление обработчиков событий на кнопки
function  addBtnAction() {
    for (var i = 1; i < 5; i++){
        $(".info-btn-" + i).click({item: charactersData[i-1]}, function (event) {
            createInfoMenu(event);
        })
    }

    $(".new-character-btn").click(function () {
       createNewCharacterMenu();
    });
}

// Создание слайда
function createSlide(item, i, color) {

    var slide = '<!-- Слайдер номер ' + i + '  -->' +
        '<div class="fnc-slide fnc-slide-'+ i + ' ' + color + (i === 1 ? ' m--active-slide">' : '">') +
        '<div class="fnc-slide__inner" style="background-image: url(' + item.race.imgPath + ')">' +
            '<div class="fnc-slide__mask">' +
                '<div class="fnc-slide__mask-inner" style="background-image: url(' + item.race.imgPath + ')"></div>' +
            '</div>' +
        '<div class="fnc-slide__content">' +
            '<h2 class="fnc-slide__heading">' +
                '<div class="fnc-slide__heading-line">' +
                '<span>' + item.name + '</span>' +
            '</h2>' +
            '<button type="button" class="fnc-slide__action-btn info-btn-'+ i + '">' +
                'Подробнее' +
                '<span data-text="Подробнее">Подробнее</span>' +
            '</button></div></div></div>';
    return slide;
}

// Слайд персонажа, который еще не создан
function createEmptySlide(i) {
    var slide = '<!-- Слайдер номер ' + i + '  -->' +
        '<div class="fnc-slide m--blend-dark fnc-slide-' + i + ' ' + (i === 0 ? ' m--active-slide">' : '">') +
        '<div class="fnc-slide__inner" style="background-image: url(../../resources/default/img/race/еще_не_создан.jpg)">' +
        '<div class="fnc-slide__mask">' +
        '<div class="fnc-slide__mask-inner" style="background-image: url(../../resources/default/img/race/еще_не_создан.jpg)"></div>' +
        '</div>' +
        '<div class="fnc-slide__content">' +
        '<h3 class="fnc-slide__heading">' +
        '<div class="fnc-slide__heading-line">' +
        '<span>Персонаж не создан</span>' +
        '</h3>' +
        '<button type="button" class="fnc-slide__action-btn new-character-btn">' +
        'Создать' +
        '<span data-text="Создать">Создать</span>' +
        '</button></div></div></div>';
    return slide;
}

// Создание навигации по списку персонажей Навигационная панель
function createNavbar() {
    var navbar = '<nav class="fnc-nav">' +
            '<div class="fnc-nav__bgs">' +
                '<div class="fnc-nav__bg m--navbg-green m--active-nav-bg"></div>' +
                '<div class="fnc-nav__bg m--navbg-dark"></div>' +
                '<div class="fnc-nav__bg m--navbg-red"></div>' +
                '<div class="fnc-nav__bg m--navbg-blue"></div>' +
            '</div>' +
            '<div class="fnc-nav__controls">';


    for(var i = 1; i < 5; i++){
        if(charactersData[i-1] != null){
            navbar += '<button class="fnc-nav__control fnc-nav__control-' + i +'">' +
                '' + charactersData[i-1].name +
                '<span class="fnc-nav__control-progress"></span>' +
                '</button>';
        }
        else {
            navbar += '<button class="fnc-nav__control fnc-nav__control- ' + i +'">' +
                'Создать' +
                '<span class="fnc-nav__control-progress"></span>' +
            '</button>';
        }
    }

    navbar += '</div></nav>';

    return navbar;
}

// Отрисовка меню информации о персонаже
function createInfoMenu(event) {
    hiddenAllMenu();
    $("#info-character").show();
    // TODO остановка прокрутки изображений $slider.querySelector(".m--previous-slide").classList.remove("m--active-slide");
    $("#avatar").attr("src", event.data.item.race.imgPath);
    $("#char-name").text(event.data.item.name)
    $("#char-class").text(event.data.item.userClass)
    $("#char-race").text(event.data.item.race.type)
    $("#char-sex").text(event.data.item.sex === 'm' ? "Мужчина" : "Женщина");
}

// Отрисовка меню создания нового персонажа
function createNewCharacterMenu() {
    hiddenAllMenu();
    $("#create-character").show();
}

// Скрытие меню
function hiddenAllMenu() {
    $("#create-character").hide();
    $("#info-character").hide();

}