     token = $("meta[name='_csrf']").attr("content");
     header = $("meta[name='_csrf_header']").attr("content");

    $('#signinbtn').on('click',function(){
        $.ajax({
            url:'signin',
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            data: {username: $('#username').val(),password: $('#password').val(),'_csrf':token},
            beforeSend:function(xhr){
                xhr.setRequestHeader('_csrf_header', header);
            },
            success: function(data){
                var dataJSON = $.parseJSON(data);
                if(dataJSON.login){
                    document.location.href="/";
                } else{
                    var msg = $('#loginErrorMsg');
                    msg.text(dataJSON.errorMessage);
                    msg.addClass('alert-danger');
                }

            },
            error: function(data){
                alert('Error');
            }
        })
    });

    $('#signoutbtn').on('click',function(){
        $.ajax({
            url:'signout',
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            data: {'_csrf':token},
            beforeSend:function(xhr){
                xhr.setRequestHeader('_csrf_header', header);
            },
            success: function(data){
                var dataJSON = $.parseJSON(data);
                if(dataJSON.logout){
                    document.location.href="/login";
                } else{
                    alert('logout error');
                }
            },
            error: function(data){
                alert('Error');
            }
        })
    });