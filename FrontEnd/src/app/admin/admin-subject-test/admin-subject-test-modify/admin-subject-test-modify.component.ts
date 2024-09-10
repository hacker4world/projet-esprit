import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SubjectTestService } from '../../../service/subject-test.service';

@Component({
  selector: 'app-admin-subject-test-modify',
  templateUrl: './admin-subject-test-modify.component.html',
  styleUrls: ['./admin-subject-test-modify.component.css']
})
export class AdminSubjectTestModifyComponent implements OnInit {
  subjectTestForm!: FormGroup;
  subjectTestId: number = 0;
  subjectId: number = 0;
  errorMessage: string = "";


  constructor(
    private fb: FormBuilder,
    private subjectTestService: SubjectTestService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.subjectTestId = Number(this.route.snapshot.paramMap.get('id'));
    this.initForm();
    this.loadSubjectTest();
  }

  initForm() {
    this.subjectTestForm = this.fb.group({
      coefficient: ['', Validators.required],
    });
  }

  loadSubjectTest() {
    this.subjectTestService.getById(this.subjectTestId).subscribe(
      (res: any) => {
        this.subjectId = res.subjectId;
        this.subjectTestForm.patchValue({
          coefficient: res.coefficient,
          subjectId: res.subjectId
        });
      },
      err => {
        this.errorMessage = 'Error loading subject test';
      }
    );
  }

  onSubmit() {
    if (this.subjectTestForm.value.coefficient >= 0 && this.subjectTestForm.value.coefficient <= 1) {
      if (this.subjectTestForm.valid) {
        const request: any = {
          id: this.subjectTestId,
          coefficient: this.subjectTestForm.value.coefficient,
        };
        this.subjectTestService.update(request).subscribe(
          res => {
            this.router.navigate(['/api/dashboard/admin/admin-subject/details/', this.subjectId]);
          },
          err => {
            this.errorMessage = 'Error updating subject test';
          }
        );
      }
    }
    else {
      this.errorMessage = 'Type a valid coefficient between 0 and 1';
    }
  }
}
