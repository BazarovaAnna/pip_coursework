window.onload = function () {
    laodRace();
    $("#race-dropdown").on("change",function () {
        $("#character-avatar").attr('src', "../../resources/default/img/man.png");
    });
    $("#submit-btn").on("click",function (event) {
        addNewCharacter();
    });
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

// Загрузка файла в поле input file
$('.photobox__label').click(function( event ){
    loadImgByRace();
});

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
        },
    });
}