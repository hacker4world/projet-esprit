import { Offer } from "./offer";
import { UserDTO } from "./user.dto";


export interface Application {
    id: number;
    applicationDate : string;
    offerId: number;
    userId: number;
    is_viewed: boolean;
    offerDetails ?: Offer;
    userDetails ?: UserDTO

  }