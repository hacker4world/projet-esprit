import { Component, OnInit } from '@angular/core';
import { SubjectService } from '../../../service/subject.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-admin-subject-details',
  templateUrl: './admin-subject-details.component.html',
  styleUrls: ['./admin-subject-details.component.css']
})
export class AdminSubjectDetailsComponent implements OnInit {
  subjectDetails: any;
  subjectId: number = 0;
  showAddTestButton: boolean = true;

  constructor(
    private subjectService: SubjectService,
    private router: Router,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.subjectId = Number(this.route.snapshot.paramMap.get('id'));
    this.getSubject();
  }

  getSubject() {
    this.subjectService.getSubjectById(this.subjectId).subscribe(
      res => {
        this.subjectDetails = res;
        this.checkTestTypes();
      },
      err => {
        console.error("[HTTP ERROR] AdminSubjectDetailsComponent -> ngOnInit()", err);
      }
    );
  }

  checkTestTypes() {
    const testTypes = this.subjectDetails.subjectTests.map((test: { testType: any; }) => test.testType);
    this.showAddTestButton = !(testTypes.includes('CC') && testTypes.includes('TP') && testTypes.includes('EXAM'));
  }

  modifySubjectTest(subjectTestId: number) {
    this.router.navigate(['/api/dashboard/admin/admin-subject-test/modify/', subjectTestId]);
  }

  addTest() {
    this.router.navigate(['/api/dashboard/admin/admin-subject-test/add/', this.subjectId]);
  }
}
