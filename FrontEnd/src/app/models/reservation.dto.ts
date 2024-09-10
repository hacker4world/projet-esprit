import { BookDTO } from "./book.dto";

export class ReservationBookDTO {
    id?: number;
    reason ?: string;
    startDate?: string; // Using string to keep it simple, or use Date if you handle date conversions
    endDate?: string;   // Using string to keep it simple, or use Date if you handle date conversions
    approved?: boolean;
    returned?: boolean;
    requestReturn?:boolean;
    userId!: number;
    bookId?: number;
    book?: BookDTO; // Including the book details
  }