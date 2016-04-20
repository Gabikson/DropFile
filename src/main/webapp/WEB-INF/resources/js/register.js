token = $("meta[name='_csrf']").attr("content");
header = $("meta[name='_csrf_header']").attr("content");

$('#registerme').on('click',function(){
    $.ajax({
        url: 'registerme',
        type: 'POST',
        enctype: 'multipart/form-data',
        data: {
            'username' : $('#username').val(),
            'password' : $('#password').val(),
            'email'    : $('#email').val(),
            'avatar'   : $('#uploadAvatar')[0].files[0],
            '_csrf'    : token
        },
        beforeSend:function(xhr){
            xhr.setRequestHeader('_csrf_header', header);
        },
        success: function(data){
            alert(data);
        },
        error: function(data){
            alert('Error');
        }
    })
});
