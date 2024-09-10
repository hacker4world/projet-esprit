import { Component } from '@angular/core';
import { ChatService } from '../../service/chat.service ';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { StorageService } from '../../service/storage.service';
import { UserDTO } from '../../models/user.dto';
@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  providers: [ChatService],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent {
  isMinimized!: boolean;
  owener(msg: string) {
    let username = msg.split(":")[0].trim();
    if (this.currentUser.userName == username) {
      return "me"
    }
    return "no"
  }
  toggleMinimize() {
    this.isMinimized = !this.isMinimized;
  }

  resetChat() {
    this.messages = [];
  }
  message!: string;
  sender!: string;
  recipient!: string;
  messages: string[] = [];
  currentUser!: UserDTO;

  constructor(private chatService: ChatService,
    private storage: StorageService
  ) {
    this.currentUser = this.storage.getUser();
  }
  setUsername() {
    this.chatService.setUsername(this.currentUser.userName ?? "");
  }

  sendMessage() {

    this.chatService.sendMessage(this.message, this.currentUser.userName ?? "", this.recipient);
    this.messages.push(`${this.currentUser.userName ?? ""}: ${this.message}`);
    this.message = '';
  }
}
