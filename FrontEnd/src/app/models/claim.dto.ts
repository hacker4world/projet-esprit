import { UserDTO } from './user.dto';
export interface claimDTO {
  id: number;
  type: string;
  createdAt: string;
  updatedAt: string;
  status: string;
  user: UserDTO;
  subject: string;
}
export interface ClaimUpdateDTO {
  status: string;
  claimId: number;
}
