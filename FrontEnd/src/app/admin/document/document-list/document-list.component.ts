import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DocumentDTO, DocumentUpdateDTO } from '../../../models/document.dto';
import { DocumentService } from '../../../service/document.service';

@Component({
  selector: 'app-document-list',
  templateUrl: './document-list.component.html',
  styleUrl: './document-list.component.css',
})
export class DocumentListComponent implements OnInit {
  @ViewChild('exampleModalCenter') exampleModalCenter!: ElementRef;
  @ViewChild('exampleModalLong') exampleModalLong!: ElementRef;
  @ViewChild('exampleModalLong2') exampleModalLong2!: ElementRef;
  @ViewChild('exampleModalLong3') exampleModalLong3!: ElementRef;
  totalDocuments: number = 0;
  pendingDocuments: number = 0;
  inProgressDocument: number = 0;
  resolvedDocuments: number = 0;
  rejectedDocuments: number = 0;
  documenets: DocumentDTO[] = [];
  selectedDocument: DocumentDTO | null = null;
  updateForm: FormGroup;
  pageNumber = 1;
  pageSize = 5;
  totalPages: number = 0;

  documenetsA: DocumentDTO[] = [];

  pageNumberA = 1;
  pageSizeA = 5;
  totalPagesA: number = 0;

  constructor(
    private documentService: DocumentService,
    private formBuilder: FormBuilder
  ) {
    this.updateForm = this.formBuilder.group({
      comment: ['', Validators.required],
    });
  }
  ngOnInit(): void {
    this.getAll();
    this.loadDocumentStatistics();
    this.getAllArchivied();
  }

  getAll() {
    this.documentService.getAll(this.pageNumber, this.pageSize).subscribe(
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
  getAllArchivied() {
    this.documentService
      .getAllArchivied(this.pageNumberA, this.pageSizeA)
      .subscribe(
        (res: any) => {
          this.documenetsA = res.documents;
          this.totalPagesA = res.totalPages;
          this.pageNumberA = res.pageNumber;
          this.pageSizeA = res.pageSize;
        },
        (err) => {
          console.error(
            '[HTTP ERROR] AdminDocumentListComponent -> getAllDocumentList()',
            err
          );
        }
      );
  }

  loadDocumentStatistics(): void {
    this.documentService.getStatistics().subscribe((stats) => {
      this.totalDocuments = stats.totalDocuments;
      this.pendingDocuments = stats.pendingDocuments;
      this.inProgressDocument = stats.approvedDocuments;
      this.resolvedDocuments = stats.completedDocuments;
      this.rejectedDocuments = stats.rejectedDocuments;
    });
  }
  updateStatus(): void {
    try {
      this.documentService
        .updateStatus({
          documentId: this.selectedDocument?.id as number,
          status: 'Completed',
        })
        .subscribe(
          (res) => {
            console.log(res, 'success');
            this.closeModal(this.exampleModalLong);
            this.ngOnInit();
          },
          (err) => {
            console.error(
              '[HTTP ERROR] AdminDocumentListComponent -> updateStatus()',
              err
            );
          }
        );
    } catch (err) {
      console.log(err);
    }
  }

  updateStatusRejected(id: number): void {
    this.documentService.updateStatus({
      documentId: id,
      status: 'Rejected',
      comment: 'Rejected',
    });
  }

  async onSubmit(): Promise<void> {
    if (this.updateForm.valid) {
      const commentControl = this.updateForm.get('comment');
      if (commentControl) {
        const comment = commentControl.value;
        const body: DocumentUpdateDTO = {
          status: 'Rejected',
          comment: comment,
          documentId: this.selectedDocument?.id as number,
        };
        try {
          this.documentService.updateStatus(body).subscribe(
            (res) => {
              this.closeModal(this.exampleModalCenter);
              this.ngOnInit();
            },
            (err) => {
              console.error(
                '[HTTP ERROR] AdminDocumentListComponent -> onSubmit()',
                err
              );
            }
          );
          this.getAll();
        } catch (error) {
          console.error('API call error', error);
        }
      }
    }
  }
  onSelectDocument(document: DocumentDTO): void {
    this.selectedDocument = document;
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
    const target = event.target as HTMLSelectElement;
    const status = target.value;
    if (status.trim() !== '') {
      this.resetPagination();
      this.documentService
        .filterByStatus(status, this.pageNumber, this.pageSize)
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
    const target = event.target as HTMLSelectElement;
    const type = target.value;
    if (type.trim() !== '') {
      this.resetPagination();
      this.documentService
        .filterByType(type, this.pageNumber, this.pageSize)
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

  closeModal(modal: ElementRef) {
    const modalElement = modal.nativeElement as HTMLElement;
    //@ts-expect-error
    const modalInstance = bootstrap.Modal.getInstance(modalElement);
    modalInstance.hide();
  }

  updateStatusArchivied(): void {
    try {
      this.documentService
        .updateStatus({
          documentId: this.selectedDocument?.id as number,
          status: 'Archived',
        })
        .subscribe(
          (res) => {
            console.log(res, 'success');
            this.closeModal(this.exampleModalLong2);
            this.ngOnInit();
          },
          (err) => {
            console.error(
              '[HTTP ERROR] AdminDocumentListComponent -> updateStatusArchivied()',
              err
            );
          }
        );
    } catch (err) {
      console.log(err);
    }
  }

  resetPaginationA() {
    this.pageNumberA = 1;
  }

  previousPageA() {
    if (this.pageNumberA > 1) {
      this.pageNumberA--;
      this.getAllArchivied();
    }
  }

  nextPageA() {
    if (this.pageNumberA < this.totalPagesA) {
      this.pageNumberA++;
      this.getAllArchivied();
    }
  }

  goToPageA(page: number) {
    this.pageNumberA = page;
    this.getAllArchivied();
  }

  totalPagesArrayA() {
    return Array(this.totalPagesA)
      .fill(0)
      .map((x, i) => i + 1);
  }
  delete() {
    this.documentService.delete(this.selectedDocument?.id as number).subscribe(
      (res) => {
        console.log('Deleted');
        this.closeModal(this.exampleModalLong3);
        this.ngOnInit();
      },
      (err) => {
        console.error('Error deleting document', err);
      }
    );
  }
}
