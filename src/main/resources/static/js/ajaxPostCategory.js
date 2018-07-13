$("#newCategory").submit(function (e) {
    e.preventDefault();
    ajaxPost();
});

function ajaxPost() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    var formData = {
        title : $("#cTitle").val()
    };

    $.ajax({
        beforeSend: function( xhr ){
            xhr.setRequestHeader(header, token);
        },
        url : '/api/category',
        type : 'post',
        contentType: 'application/json',
        dataType : 'json',
        data : JSON.stringify(formData),
        success : function ( result ) {
            $(".del").remove();
            $("#cTitle").removeClass("is-invalid");

            $('#categorySelect')
                .append('<option value="' + result.id + '">' + result.title + '</option>');
        },
        error: function ( errors ) {
            var errorsList = errors.responseJSON.categoryTitleError;

            $(".del").remove();
            $("#cTitle").addClass("is-invalid");

            $.each(errorsList, function (index, err) {
                $("#cInput").append('<div class="del invalid-feedback text-center">' + err + '</div>')
            });
        }
    });

    resetData();
}

function resetData() {
    $("#cTitle").val("");
}
