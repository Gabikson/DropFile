token = $("meta[name='_csrf']").attr("content");
header = $("meta[name='_csrf_header']").attr("content");

$('#registerme').on('click',function(){
    var form = new FormData();
    form.append('username', $('#username').val());
    form.append('password', $('#password').val());
    form.append('email', $('#email').val());
    form.append('avatar', $('#uploadAvatar')[0].files[0]);

    $.ajax({
        url: 'registerme',
        type: 'POST',
        cache:false,
        processData:false,
        contentType: false,
        data: form,
        beforeSend:function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success: function(data){
            $('#success_window').modal();
        },
        error: function(data){
            alert('Error');
        }
    })
});

