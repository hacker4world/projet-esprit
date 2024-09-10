import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DocumentService } from '../../../service/document.service';
import { StorageService } from '../../../service/storage.service';

@Component({
  selector: 'app-new-documents',
  templateUrl: './new-documents.component.html',
  styleUrls: ['./new-documents.component.css'],
})
export class NewDocumentsComponent implements OnInit {
  documentForm: FormGroup;
  types: string[] = [
    'diploma',
    'transcript',
    'studentCard',
    'attendanceCertificate',
  ];

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  constructor(
    private fb: FormBuilder,
    private documentService: DocumentService,
    private storage: StorageService,
    private router: Router
  ) {
    this.documentForm = this.fb.group({
      type: ['', Validators.required],
      description: ['', Validators.required],
    });
  }

  async onSubmit(): Promise<void> {
    const userId = this.storage.getUser().id;
    if (this.documentForm.valid) {
      const body = {
        comment: this.documentForm.get('description')?.value,
        type: this.documentForm.get('type')?.value,
      };

      await this.documentService.create(body, userId as number).subscribe(
        (response) => {
          this.router.navigate(['/api/user/documents']);
          console.log('documents created successfully!', response);
        },
        (error) => {
          console.log('Error creating documents', error);
        }
      );
    }
  }
  navigateToList() {
    this.router.navigate(['/api/user/documents']);
  }
}
