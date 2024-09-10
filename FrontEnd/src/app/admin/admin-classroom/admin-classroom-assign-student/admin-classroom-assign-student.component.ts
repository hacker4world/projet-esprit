import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ClassroomService } from '../../../service/classroom.service';

@Component({
  selector: 'app-admin-classroom-assign-student',
  templateUrl: './admin-classroom-assign-student.component.html',
  styleUrls: ['./admin-classroom-assign-student.component.css']
})
export class AdminClassroomAssignStudentComponent implements OnInit {
  assignForm!: FormGroup;
  students: any[] = []; // Replace with your actual student type
  errorMessage: string = "";
  classroomId!: number;

  constructor(
    private fb: FormBuilder,
    private classroomService: ClassroomService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.classroomId = +this.route.snapshot.paramMap.get('id')!;
    this.assignForm = this.fb.group({
      studentId: ['', Validators.required]
    });

    this.loadUnassignedStudents();
  }

  loadUnassignedStudents(): void {
    this.classroomService.getStudentsWithoutClassroom().subscribe(
      (students) => this.students = students,
      (err) => console.log('[HTTP ERROR] AdminClassroomAssignStudentComponent -> loadUnassignedStudents()', err)
    );
  }

  onSubmit(): void {
    if (this.assignForm.valid) {
      const { studentId } = this.assignForm.value;

      this.classroomService.assignUserToClassroom(studentId, this.classroomId).subscribe(
        () => {
          this.router.navigate(['/api/dashboard/admin/admin-classroom/details/', this.route.snapshot.paramMap.get('id')]);
        },
        (err) => {
          this.errorMessage = 'Error assigning student to classroom. Please try again.';
          console.log("[HTTP ERROR] AdminClassroomAssignStudentComponent -> onSubmit()", err);
        }
      );
    }
  }
}
