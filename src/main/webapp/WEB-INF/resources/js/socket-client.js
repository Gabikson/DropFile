function connect() {
    var sock = new SockJS('/socket');
    sock.onopen = function() {
        console.log('open');
    };
    sock.onmessage = function(e) {
        console.log('message', e.data);
    };
    sock.onclose = function() {
        console.log('close');
    };
    sock.send('test');
}


$('#btn').on('click', function (e) {
    alert('qwerty');
connect();
});
