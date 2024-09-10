import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { DocumentService } from '../../../service/document.service';
import { ReclamationService } from '../../../service/reclamation.service';
import { claimDTO, ClaimUpdateDTO } from '../../../models/claim.dto';
import { forkJoin } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { DocumentUpdateDTO } from '../../../models/document.dto';
import { StorageService } from '../../../service/storage.service';

@Component({
  selector: 'app-admin-reclamation-details',
  templateUrl: './admin-reclamation-details.component.html',
  styleUrls: ['./admin-reclamation-details.component.css'],
})
export class AdminReclamationDetailsComponent implements OnInit {
  @ViewChild('exampleModalLong3') exampleModalLong3!: ElementRef;
  @ViewChild('exampleModalLong') exampleModalLong!: ElementRef;
  @ViewChild('exampleModalLong2') exampleModalLong2!: ElementRef;

  claim: any;
  sendForm: FormGroup;
  claimId: any;
  loading: boolean = true;
  constructor(
    private claimService: ReclamationService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private storage: StorageService
  ) {
    this.sendForm = this.formBuilder.group({
      message: ['', Validators.required],
    });
  }

  async ngOnInit(): Promise<void> {
    const claimIdStr = this.route.snapshot.paramMap.get('id');
    if (claimIdStr !== null) {
      this.claimId = +claimIdStr;
      await this.getClaimById(this.claimId);
    } else {
      console.error('Claim ID is null');
      this.loading = false;
    }
  }
  async getClaimById(id: number) {
    this.loading = true;
    await this.claimService.getById(id).subscribe(
      (claim) => {
        this.claim = claim;
        this.loading = false;
      },
      (err) => {
        console.error(
          '[HTTP ERROR] AdminReclamationDetailsComponent -> getClaimById()',
          err
        );
        this.loading = false;
      }
    );
  }
  resetForm() {
    this.sendForm = this.formBuilder.group({
      message: ['', Validators.required],
    });
  }

  async onSubmit(): Promise<void> {
    const user = this.storage.getUser().id;

    if (this.sendForm.valid) {
      const commentControl = this.sendForm.get('message');
      if (commentControl) {
        const comment = commentControl.value;
        const body = {
          comment: comment,
          claimId: this.claimId as number,
          userId: user,
        };

        try {
          this.claimService.update(body).subscribe(
            (res) => {
              console.log(res, 'success');
              this.ngOnInit();
              this.resetForm();
            },
            (err) => {
              console.error(
                '[HTTP ERROR] AdminDocumentListComponent -> onSubmit()',
                err
              );
            }
          );
        } catch (error) {
          console.error('API call error', error);
        }
      }
    }
  }

  updateStatusClosed(): void {
    try {
      const body: ClaimUpdateDTO = {
        claimId: this.claimId,
        status: 'Rejected',
      };
      this.claimService.updateStatus(body).subscribe(
        (res) => {
          console.log(res, 'success');
          this.ngOnInit();
          this.closeModal(this.exampleModalLong2);
        },
        (err) => {
          console.error(
            '[HTTP ERROR] AdminClaimsDetailsComponent -> updateStatus()',
            err
          );
        }
      );
    } catch (err) {
      console.log(err);
    }
  }
  updateStatusCompleted(): void {
    try {
      const body: ClaimUpdateDTO = {
        claimId: this.claimId,
        status: 'Completed',
      };
      this.claimService.updateStatus(body).subscribe(
        (res) => {
          console.log(res, 'success');
          this.ngOnInit();
          this.closeModal(this.exampleModalLong);
        },
        (err) => {
          console.error(
            '[HTTP ERROR] AdminClaimsDetailsComponent -> updateStatus()',
            err
          );
        }
      );
    } catch (err) {
      console.log(err);
    }
  }

  updateStatusSupported(): void {
    try {
      const body: ClaimUpdateDTO = {
        claimId: this.claimId,
        status: 'Approved',
      };
      this.claimService.updateStatus(body).subscribe(
        (res) => {
          console.log(res, 'success');
          this.ngOnInit();
          this.closeModal(this.exampleModalLong3);
        },
        (err) => {
          console.error(
            '[HTTP ERROR] AdminClaimsDetailsComponent -> updateStatus()',
            err
          );
        }
      );
    } catch (err) {
      console.log(err);
    }
  }

  closeModal(modal: ElementRef) {
    const modalElement = modal.nativeElement as HTMLElement;
    //@ts-expect-error
    const modalInstance = bootstrap.Modal.getInstance(modalElement);
    modalInstance.hide();
  }
}
