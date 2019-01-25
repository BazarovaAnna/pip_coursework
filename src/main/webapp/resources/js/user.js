// Загрузка файла в поле input file
$('.photobox__label').click(function( event ){
    event.stopPropagation(); // Остановка происходящего
    event.preventDefault();  // Полная остановка происходящего

    $('#file-uploader').click();
});

// Загрузка файла на сервер
function handleFiles() {
    // Создадим данные формы и добавим в них данные файлов из files
    var form = $('#fileUploadForm')[0];

    var data = new FormData(form);

    // Отправляем запрос
    $.ajax({
        url: '/uploadfile',
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
            $("#user-avatar").attr("src", data);
        },
    });
}