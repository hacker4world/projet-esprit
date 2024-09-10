import { UserDTO } from './user.dto';
export interface DocumentDTO {
  id: number;
  type: string;
  createdAt: string;
  updatedAt: string;
  status: string;
  user: UserDTO;
  comment?: string;
}
export interface DocumentUpdateDTO {
  status: string;
  comment?: string;
  documentId: number;
}
