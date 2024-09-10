import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ClassroomService } from '../../../service/classroom.service';
import { SubjectService } from '../../../service/subject.service';

@Component({
  selector: 'app-admin-subject-create',
  templateUrl: './admin-subject-create.component.html',
  styleUrls: ['./admin-subject-create.component.css']
})
export class AdminSubjectCreateComponent implements OnInit {
  subjectForm!: FormGroup;
  classroomId!: number;
  errorMessage: string = "";

  constructor(
    private fb: FormBuilder,
    private subjectService: SubjectService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.classroomId = Number(this.route.snapshot.paramMap.get('id'));
    this.subjectForm = this.fb.group({
      name: ['', Validators.required],
      totalHours: ['', [Validators.required, Validators.min(0)]],
      coefficient: ['', [Validators.required, Validators.min(0)]]
    });
  }

  onSubmit(): void {
    if (this.subjectForm.valid) {
      this.subjectForm.value.classRoomId = this.classroomId;
      const subjectData = {
        ...this.subjectForm.value,
      };
      this.subjectService.post(subjectData).subscribe(
        (res) => {
          this.router.navigate(['/api/dashboard/admin/admin-classroom/details/', this.classroomId]);
        },
        (err) => {
          this.errorMessage = 'Error creating subject. Please try again.';
          console.log("[HTTP ERROR] AdminSubjectCreateComponent -> onSubmit()", err);
        }
      );
    }
  }
}
