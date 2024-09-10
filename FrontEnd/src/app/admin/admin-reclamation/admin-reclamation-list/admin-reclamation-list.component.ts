import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { claimDTO } from '../../../models/claim.dto';
import { ReclamationService } from '../../../service/reclamation.service';
import { DocumentDTO } from '../../../models/document.dto';

@Component({
  selector: 'app-admin-reclamation-list',
  templateUrl: './admin-reclamation-list.component.html',
  styleUrls: ['./admin-reclamation-list.component.css'],
})
export class AdminReclamationListComponent {
  @ViewChild('exampleModalLong2') exampleModalLong2!: ElementRef;
  @ViewChild('exampleModalLong3') exampleModalLong3!: ElementRef;

  clamis: claimDTO[] = [];
  pageNumber = 1;
  pageSize = 5;
  totalPages: number = 0;

  totalClaims: number = 0;
  pendingClaims: number = 0;
  inProgressClaims: number = 0;
  resolvedClaims: number = 0;
  rejectedClaims: number = 0;

  documenetsA: claimDTO[] = [];
  selectedClaims: claimDTO | null = null;
  pageNumberA = 1;
  pageSizeA = 5;
  totalPagesA: number = 0;
  constructor(
    private claimService: ReclamationService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getAll();
    this.loadClaimStatistics();
    this.getAllArchivied();
  }
  onSelectClaim(claim: claimDTO): void {
    this.selectedClaims = claim;
  }
  getAll() {
    this.claimService.getAll(this.pageNumber, this.pageSize).subscribe(
      (res: any) => {
        this.clamis = res.reclamations;
        this.totalPages = res.totalPages;
        this.pageNumber = res.pageNumber;
        this.pageSize = res.pageSize;
      },
      (err) => {
        console.error(
          '[HTTP ERROR] AdminReclamationListComponent -> getAllClaimsList()',
          err
        );
      }
    );
  }
  getAllArchivied() {
    this.claimService
      .getAllArchivied(this.pageNumberA, this.pageSizeA)
      .subscribe(
        (res: any) => {
          this.documenetsA = res.reclamations;
          this.totalPagesA = res.totalPages;
          this.pageNumberA = res.pageNumber;
          this.pageSizeA = res.pageSize;
        },
        (err) => {
          console.error(
            '[HTTP ERROR] AdminClaimsListComponent -> getAllDocumentList()',
            err
          );
        }
      );
  }
  loadClaimStatistics(): void {
    this.claimService.getClaimStatistics().subscribe((stats) => {
      this.totalClaims = stats.totalClaims;
      this.pendingClaims = stats.pendingClaims;
      this.inProgressClaims = stats.approvedClaims;
      this.resolvedClaims = stats.completedClaims;
      this.rejectedClaims = stats.rejectedClaims;
    });
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
      this.claimService
        .filterByStatus(status, this.pageNumber, this.pageSize)
        .subscribe(
          (res: any) => {
            this.clamis = res.reclamations;
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
      this.claimService
        .filterByType(type, this.pageNumber, this.pageSize)
        .subscribe(
          (res: any) => {
            this.clamis = res.reclamations;
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
  navigateToDetails(id: number) {
    this.router.navigate(['/api/dashboard/admin/admin-reclamation/', id]);
  }
  closeModal(modal: ElementRef) {
    const modalElement = modal.nativeElement as HTMLElement;
    //@ts-expect-error
    const modalInstance = bootstrap.Modal.getInstance(modalElement);
    modalInstance.hide();
  }
  updateStatusArchivied(): void {
    try {
      this.claimService
        .updateStatus({
          claimId: this.selectedClaims?.id as number,
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
    this.claimService.delete(this.selectedClaims?.id as number).subscribe(
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
