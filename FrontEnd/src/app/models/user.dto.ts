export interface UserDTO {
    id?: number;
    firstName: string;
    lastName: string;
    createdDate: string;
    updatedDate: string;
    email:string;
    role: string;
    profilePicURI?: string;
    resumeURI?: string;
    userName?:string;
    userOption?:string;
  }
  