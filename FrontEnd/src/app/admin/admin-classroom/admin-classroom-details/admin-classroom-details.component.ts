import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { forkJoin } from 'rxjs';
import { ClassroomService } from '../../../service/classroom.service';
import { SubjectService } from '../../../service/subject.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-admin-classroom-details',
  templateUrl: './admin-classroom-details.component.html',
  styleUrls: ['./admin-classroom-details.component.css'],
})
export class AdminClassroomDetailsComponent implements OnInit {
  classroom: any;
  subjects: any[] = [];
  users: any[] = [];

  subjectsPageNumber = 1;
  subjectsPageSize = 5;
  subjectsTotalPages: number = 0;

  usersPageNumber = 1;
  usersPageSize = 5;
  usersTotalPages: number = 0;

  constructor(
    private route: ActivatedRoute,
    private classroomService: ClassroomService,
    private subjectService: SubjectService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const classroomIdStr = this.route.snapshot.paramMap.get('id');
    if (classroomIdStr !== null) {
      const classroomId = +classroomIdStr;


      forkJoin({
        classroom: this.classroomService.getClassroomById(classroomId),
        subjects: this.subjectService.getAllSubjectsByClassroom(classroomId, this.subjectsPageNumber, this.subjectsPageSize),
        users: this.classroomService.getUsersByClassroom(classroomId, this.usersPageNumber, this.usersPageSize),
      }).subscribe(
        res => {
          this.classroom = res.classroom;
          this.subjects = res.subjects.subjects;
          this.subjectsTotalPages = res.subjects.totalPages;
          this.users = res.users.users;
          this.usersTotalPages = res.users.totalPages;
        },
        err => {
          console.error("[HTTP ERROR] ClassroomDetailsComponent -> ngOnInit()", err);
        }
      );
    } else {
      console.error("Classroom ID is null");
    }

  }

  goToModify() {
    this.router.navigate(['/api/dashboard/admin/admin-classroom/modify/', this.route.snapshot.paramMap.get('id')]);
  }

  goAssignNewStudent() {
    this.router.navigate(['/api/dashboard/admin/admin-classroom/assignstudent/', this.route.snapshot.paramMap.get('id')]);
  }

  goToStudentTestEvaluations(sutdentId: number) {
    this.router.navigate(['/api/dashboard/admin/admin-test-evalutions/testByStudent', sutdentId]);
  }

  goToCreateSubject() {
    this.router.navigate(['/api/dashboard/admin/admin-subject/create', this.route.snapshot.paramMap.get('id')]);
  }


  goToSubjectDetails(subjectId: number) {
    this.router.navigate(['/api/dashboard/admin/admin-subject/details', subjectId]);
  }

  getSubjectsByClassroom(classroomId: number): void {
    this.subjectService.getAllSubjectsByClassroom(classroomId, this.subjectsPageNumber, this.subjectsPageSize).subscribe(
      (res: any) => {
        this.subjects = res.subjects;
        this.subjectsTotalPages = res.totalPages;
      },
      err => {
        console.error("[HTTP ERROR] ClassroomDetailsComponent -> getSubjectsByClassroom()", err);
      }
    );
  }

  getUsersByClassroom(classroomId: number): void {
    this.classroomService.getUsersByClassroom(classroomId, this.usersPageNumber, this.usersPageSize).subscribe(
      (res: any) => {
        console.log("users:");
        console.log(res);
        this.users = res.users;
        this.usersPageNumber = res.pageNumber;
        this.usersTotalPages = res.totalPages;
      },
      err => {
        console.error("[HTTP ERROR] ClassroomDetailsComponent -> getUsersByClassroom()", err);
      }
    );
  }

  resetSubjectsPagination() {
    this.subjectsPageNumber = 1;
    const classroomIdStr = this.route.snapshot.paramMap.get('id');
    if (classroomIdStr !== null) {
      const classroomId = +classroomIdStr;
      this.getSubjectsByClassroom(classroomId);
    }
  }

  previousSubjectsPage() {
    if (this.subjectsPageNumber > 1) {
      this.subjectsPageNumber--;
      const classroomIdStr = this.route.snapshot.paramMap.get('id');
      if (classroomIdStr !== null) {
        const classroomId = +classroomIdStr;
        this.getSubjectsByClassroom(classroomId);
      }
    }
  }

  nextSubjectsPage() {
    if (this.subjectsPageNumber < this.subjectsTotalPages) {
      this.subjectsPageNumber++;
      const classroomIdStr = this.route.snapshot.paramMap.get('id');
      if (classroomIdStr !== null) {
        const classroomId = +classroomIdStr;
        this.getSubjectsByClassroom(classroomId);
      }
    }
  }

  goToSubjectsPage(page: number) {
    this.subjectsPageNumber = page;
    const classroomIdStr = this.route.snapshot.paramMap.get('id');
    if (classroomIdStr !== null) {
      const classroomId = +classroomIdStr;
      this.getSubjectsByClassroom(classroomId);
    }
  }

  resetUsersPagination() {
    this.usersPageNumber = 1;
    const classroomIdStr = this.route.snapshot.paramMap.get('id');
    if (classroomIdStr !== null) {
      const classroomId = +classroomIdStr;
      this.getUsersByClassroom(classroomId);
    }
  }

  previousUsersPage() {
    if (this.usersPageNumber > 1) {
      this.usersPageNumber--;
      const classroomIdStr = this.route.snapshot.paramMap.get('id');
      if (classroomIdStr !== null) {
        const classroomId = +classroomIdStr;
        this.getUsersByClassroom(classroomId);
      }
    }
  }

  nextUsersPage() {
    if (this.usersPageNumber < this.usersTotalPages) {
      this.usersPageNumber++;
      const classroomIdStr = this.route.snapshot.paramMap.get('id');
      if (classroomIdStr !== null) {
        const classroomId = +classroomIdStr;
        this.getUsersByClassroom(classroomId);
      }
    }
  }

  goToUsersPage(page: number) {
    this.usersPageNumber = page;
    const classroomIdStr = this.route.snapshot.paramMap.get('id');
    if (classroomIdStr !== null) {
      const classroomId = +classroomIdStr;
      this.getUsersByClassroom(classroomId);
    }
  }

  subjectsTotalPagesArray() {
    return Array(this.subjectsTotalPages).fill(0).map((x, i) => i + 1);
  }

  usersTotalPagesArray() {
    return Array(this.usersTotalPages).fill(0).map((x, i) => i + 1);
  }


  removeStudent(studentId: number): void {
    const classroomIdStr = this.route.snapshot.paramMap.get('id');
    if (classroomIdStr !== null) {
      const classroomId = +classroomIdStr;
      this.classroomService.removeStudentFromClassroom(studentId).subscribe(
        () => {
          this.getUsersByClassroom(classroomId);
        },
        err => {
          console.error("[HTTP ERROR] AdminClassroomDetailsComponent -> removeStudent()", err);
        }
      );
    }

  }
}
