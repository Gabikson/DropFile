$(function() {
    var avatarUpload = $('#uploadAvatar');
    var avatarPreview = $('#avatarPreview');
    var avatarMessage = $('#avatarMessage');
    $(avatarMessage).on('click', function() {
        $(avatarUpload).trigger('click');
    })
    $(avatarUpload).on(
        "change",
        function() {
            var files = !!this.files ? this.files : [];
            if (!files.length || !window.FileReader)
                return; // no file selected, or no FileReader support

            if (/^image/.test(files[0].type)) { // only image file
                var reader = new FileReader(); // instance of the FileReader
                reader.readAsDataURL(files[0]); // read the local file

                reader.onloadend = function() { // set image data as background of div
                    var result = this.result;
                    $(avatarPreview).animate(
                        {
                            opacity : '0'
                        },
                        "fast",
                        function() {
                            $(avatarPreview).css(
                                "background-image",
                                "url(" + result + ")");
                        });

                    $(avatarPreview).animate({
                        opacity : '1'
                    }, "fast");
                };
            }
        });
    $(avatarPreview).on({
        mouseenter: function() {
            $(avatarMessage).slideDown(100);
        },
        mouseleave: function() {
            $(avatarMessage).slideUp(100);
        }
    });
});