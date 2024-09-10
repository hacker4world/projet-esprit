import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReclamationService } from '../../../service/reclamation.service';
import { StorageService } from '../../../service/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-claims',
  templateUrl: './new-claims.component.html',
  styleUrls: ['./new-claims.component.css'],
})
export class NewClaimsComponent implements OnInit {
  reclamationForm: FormGroup;
  types: string[] = ['absence', 'note', 'others'];
  selectedFiles: File[] = [];
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  constructor(
    private fb: FormBuilder,
    private reclamationService: ReclamationService,
    private storage: StorageService,
    private router: Router
  ) {
    this.reclamationForm = this.fb.group({
      subject: ['', Validators.required],
      // emails: [''],
      type: ['', Validators.required],
      description: ['', Validators.required],
      attachments: [''],
    });
  }
  onFileSelected(event: any): void {
    const files = event.target.files;
    for (let i = 0; i < files.length; i++) {
      this.selectedFiles.push(files[i]);
    }
  }

  onSubmit(): void {
    const userId = this.storage.getUser().id;

    if (this.reclamationForm.valid) {
      const formData = new FormData();
      formData.append('subject', this.reclamationForm.get('subject')?.value);
      // formData.append('emails', this.reclamationForm.get('emails')?.value);
      formData.append('type', this.reclamationForm.get('type')?.value);
      formData.append('c', this.reclamationForm.get('description')?.value);

      const body = {
        subject: this.reclamationForm.get('subject')?.value,
        description: this.reclamationForm.get('description')?.value,
        type: this.reclamationForm.get('type')?.value,
        // attachments: formData.get('attachments'),
      };
      // for (let file of this.selectedFiles) {

      //   formData.append('attachments', file, file.name);
      // }
      this.reclamationService.create(body, userId as number).subscribe(
        (response) => {
          console.log('Reclamation created successfully!', response);
          this.router.navigate(['/api/user/claims']);
        },
        (error) => {
          console.error('Error creating reclamation', error);
        }
      );
    }
  }

  navigateToList() {
    this.router.navigate(['/api/user/claims']);
  }
}
