import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { claimDTO } from '../../../models/claim.dto';
import { ReclamationService } from '../../../service/reclamation.service';
import { StorageService } from '../../../service/storage.service';

@Component({
  selector: 'app-clamis',
  templateUrl: './clamis.component.html',
  styleUrls: ['./clamis.component.css'],
})
export class ClamisComponent {
  clamis: claimDTO[] = [];
  pageNumber = 1;
  pageSize = 5;
  totalPages: number = 0;

  totalClaims: number = 0;
  pendingClaims: number = 0;
  inProgressClaims: number = 0;
  resolvedClaims: number = 0;
  rejectedClaims: number = 0;

  constructor(
    private claimService: ReclamationService,
    private formBuilder: FormBuilder,
    private router: Router,
    private storage: StorageService
  ) {}

  ngOnInit(): void {
    this.getAll();
    this.loadClaimStatistics();
  }

  getAll() {
    const userId = this.storage.getUser().id;

    this.claimService
      .getAllByUser(this.pageNumber, this.pageSize, userId as number)
      .subscribe(
        (res: any) => {
          this.clamis = res.reclamations;
          this.totalPages = res.totalPages;
          this.pageNumber = res.pageNumber;
          this.pageSize = res.pageSize;
        },
        (err) => {
          console.error(
            '[HTTP ERROR] ClamisListComponent -> getAllClaimsList()',
            err
          );
        }
      );
  }
  loadClaimStatistics(): void {
    const userId = this.storage.getUser().id;

    this.claimService
      .getClaimStatisticsByUser(userId as number)
      .subscribe((stats) => {
        this.totalClaims = stats.totalClaims;
        this.pendingClaims = stats.pendingClaims;
        this.inProgressClaims = stats.approvedClaims;
        this.resolvedClaims = stats.completedClaims;
        this.rejectedClaims = stats.rejectedClaims;
      });
  }
  filterStatus(event: Event) {
    const userId = this.storage.getUser().id;

    const target = event.target as HTMLSelectElement;
    const status = target.value;
    if (status.trim() !== '') {
      this.resetPagination();
      this.claimService
        .filterByUserAndStatus(
          status,
          this.pageNumber,
          this.pageSize,
          userId as number
        )
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
    const userId = this.storage.getUser().id;

    const target = event.target as HTMLSelectElement;
    const type = target.value;
    if (type.trim() !== '') {
      this.resetPagination();
      this.claimService
        .filterByUserAndType(
          type,
          this.pageNumber,
          this.pageSize,
          userId as number
        )
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

  navigateToDetails(id: number) {
    this.router.navigate(['/api/user/claims', id]);
  }
  navigateToNewClaims() {
    this.router.navigate(['/api/user/claims/new']);
  }
}
