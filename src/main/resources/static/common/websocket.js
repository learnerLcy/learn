var websocket = null;
// 首先判断是否 支持 WebSocket
if('WebSocket' in window) {
    websocket=websocket==null?  new WebSocket("ws://localhost:8083/testHandler?userId=zhaoshouyun"):websocket;
} else if('MozWebSocket' in window) {
    websocket=websocket==null? new MozWebSocket("ws://localhost:8083/testHandler?userId=zhaoshouyun"):websocket;
} else {
    websocket=websocket==null? new SockJS("http://localhost:8083/socketJs/testHandler?userId=zhaoshouyun"):websocket;
}
// 打开连接时
websocket.onopen = function(evnt) {
    console.log("  websocket.onopen  ");
};

// 收到消息时
websocket.onmessage = function(evnt) {
    var xxtz_count = $(".xxtz").text();
    $(".xxtz").text(Number(xxtz_count)+1);
    $("body").append("<br>"+evnt.data);
};

websocket.onerror = function(evnt) {
    console.log("  websocket.onerror  ");
};

websocket.onclose = function(evnt) {
    console.log("  websocket.onclose  ");
};