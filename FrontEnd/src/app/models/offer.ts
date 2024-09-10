import { UserDTO } from "./user.dto";

export interface Offer {
    id: number;
    titre: string;
    description: string;
    isStillAvailable: boolean;
    hasUserApplied: boolean;
    dateMaj: Date;
    datePublication : Date
    utilisateurId :number
    utilisateurNom : string
    typeOffre : string
    user : UserDTO
  }