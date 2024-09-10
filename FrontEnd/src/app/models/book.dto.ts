export class BookDTO {
    id!: number;
    title!: string;
    author!: string;
    description!: string;
    state!: boolean;
    nbPage!:number;
    imageUrl!: string;
    price!: number;
    categoryId!: number;
    isReturned!:boolean;
    isApproved!:boolean;
    requestReturn!: boolean;
    endDate!:string;
    remainingDays?: number;
    recommended!:boolean;
}