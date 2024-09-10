import { Injectable } from '@angular/core';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private stompClient: Client;
  private username!: string;

  constructor() {
    this.stompClient = new Client({
      webSocketFactory: () => new SockJS('http://localhost:7646/api/ws'), // Ensure this URL matches your backend configuration
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000
    });

    this.stompClient.onConnect = (frame) => {
      console.log('Connected: ' + frame);
      if (this.username) {
        this.stompClient.subscribe(`/user/${this.username}/queue/messages`, (message) => {
          console.log('Message received: ' + message.body);
        });
      }
    };

    this.stompClient.onStompError = (frame) => {
      console.error('Broker reported error: ' + frame.headers['message']);
      console.error('Additional details: ' + frame.body);
    };

    this.stompClient.activate();
  }

  setUsername(username: string) {
    this.username = username;
  }

  sendMessage(message: string, sender: string, recipient: string) {
    const chatMessage = {
      content: message,
      sender: sender,
      recipient: recipient
    };
    this.stompClient.publish({ destination: '/app/chat.sendMessage', body: JSON.stringify(chatMessage) });
  }
}
