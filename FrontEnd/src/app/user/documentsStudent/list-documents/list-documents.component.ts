import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { DocumentDTO } from '../../../models/document.dto';
import { DocumentService } from '../../../service/document.service';
import { StorageService } from '../../../service/storage.service';

@Component({
  selector: 'app-list-documents',
  templateUrl: './list-documents.component.html',
  styleUrls: ['./list-documents.component.css'],
})
export class ListDocumentsComponent {
  @ViewChild('exampleModalCenter') exampleModalCenter!: ElementRef;
  documenets: DocumentDTO[] = [];
  selectedDocument: DocumentDTO | null = null;
  pageNumber = 1;
  pageSize = 5;
  totalPages: number = 0;
  totalDocuments: number = 0;
  pendingDocuments: number = 0;
  inProgressDocument: number = 0;
  resolvedDocuments: number = 0;
  rejectedDocuments: number = 0;

  constructor(
    private documentService: DocumentService,
    private storage: StorageService,
    private router: Router
  ) {}
  ngOnInit(): void {
    this.getAll();
    this.loadDocumentStatistics();
  }

  getAll() {
    const userId = this.storage.getUser().id;
    this.documentService
      .getAllByUser(this.pageNumber, this.pageSize, userId as number)
      .subscribe(
        (res: any) => {
          this.documenets = res.documents;
          this.totalPages = res.totalPages;
          this.pageNumber = res.pageNumber;
          this.pageSize = res.pageSize;
        },
        (err) => {
          console.error(
            '[HTTP ERROR] AdminDocumentListComponent -> getAllDocumentList()',
            err
          );
        }
      );
  }
  resetPagination() {
    this.pageNumber = 1;
  }

  previousPage() {
    if (this.pageNumber > 1) {
      this.pageNumber--;
      this.getAll();
    }
  }

  nextPage() {
    if (this.pageNumber < this.totalPages) {
      this.pageNumber++;
      this.getAll();
    }
  }

  goToPage(page: number) {
    this.pageNumber = page;
    this.getAll();
  }

  totalPagesArray() {
    return Array(this.totalPages)
      .fill(0)
      .map((x, i) => i + 1);
  }

  filterStatus(event: Event) {
    const userId = this.storage.getUser().id;

    const target = event.target as HTMLSelectElement;
    const status = target.value;
    if (status.trim() !== '') {
      this.resetPagination();
      this.documentService
        .filterByUserAndStatus(
          status,
          this.pageNumber,
          this.pageSize,
          userId as number
        )
        .subscribe(
          (res: any) => {
            this.documenets = res.documents;
            this.totalPages = res.totalPages;
            this.pageNumber = res.pageNumber;
            this.pageSize = res.pageSize;
          },
          (err) => {
            console.error(
              '[HTTP ERROR] AdminReclamationListComponent -> filterStatus()',
              err
            );
          }
        );
    } else {
      this.resetPagination();
      this.getAll();
    }
  }

  filterType(event: Event) {
    const userId = this.storage.getUser().id;
    const target = event.target as HTMLSelectElement;
    const type = target.value;
    if (type.trim() !== '') {
      this.resetPagination();
      this.documentService
        .filterByUserAndType(
          type,
          this.pageNumber,
          this.pageSize,
          userId as number
        )
        .subscribe(
          (res: any) => {
            this.documenets = res.documents;
            this.totalPages = res.totalPages;
            this.pageNumber = res.pageNumber;
            this.pageSize = res.pageSize;
          },
          (err) => {
            console.error(
              '[HTTP ERROR] AdminReclamationListComponent -> filterType()',
              err
            );
          }
        );
    } else {
      this.resetPagination();
      this.getAll();
    }
  }
  navigateToNewDocument() {
    this.router.navigate(['/api/user/documents/new']);
  }
  loadDocumentStatistics(): void {
    const userId = this.storage.getUser().id;

    this.documentService
      .getStatisticsByUser(userId as number)
      .subscribe((stats) => {
        this.totalDocuments = stats.totalDocuments;
        this.pendingDocuments = stats.pendingDocuments;
        this.inProgressDocument = stats.approvedDocuments;
        this.resolvedDocuments = stats.completedDocuments;
        this.rejectedDocuments = stats.rejectedDocuments;
      });
  }

  onSelectDocument(document: DocumentDTO): void {
    this.selectedDocument = document;
  }
}
