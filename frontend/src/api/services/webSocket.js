import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
    CheckIn(carMatNb, hour, mechanicName) {
        if (this.stompClient && this.stompClient.connected) {
            const msg = { carMatNb: carMatNb,
                hour: hour,
                mechanicName: mechanicName,
            };
            this.stompClient.send("/app/checkin", JSON.stringify(msg), {});
        }
    },
    connect() {
        let connected = false;
        this.socket = new SockJS("http://localhost:8088/gs-guide-websocket");
        this.stompClient = Stomp.over(this.socket);
        this.stompClient.connect(
            {"Access-Control-Allow-Origin": "*"},
            () => {
                connected = true;
            },
            error => {
                console.log(error);
                connected = false;
            }
        );
        return connected;
    },
    connectAndSubscribe(mechanicName, alertFunc) {
        this.socket = new SockJS("http://localhost:8088/gs-guide-websocket");
        this.stompClient = Stomp.over(this.socket);
        this.mechanicName = mechanicName;
        this.alertFunc = alertFunc;
        this.stompClient.connect(
            {"Access-Control-Allow-Origin": "*"},
            () => {
                this.connected = true;
                this.stompClient.subscribe("/mechanic/checkin", tick => {
                    let response = JSON.parse(tick.body);
                    if(response.mechanicName === this.mechanicName) {
                        this.alertFunc(response.message);
                    }
                }, {"Access-Control-Allow-Origin": "*"});
            },
            error => {
                console.log(error);
                this.connected = false;
            }
        );
    },
    disconnect() {
        let connected = false;
        if (this.stompClient) {
            this.stompClient.disconnect();
        }
        connected = false;
        return connected;
    },
}