import { Component, OnInit } from '@angular/core';
import { SubjectService } from '../../../service/subject.service';
import { SubjectTestService } from '../../../service/subject-test.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-admin-subject-test-add',
  templateUrl: './admin-subject-test-add.component.html',
  styleUrls: ['./admin-subject-test-add.component.css']
})
export class AdminSubjectTestAddComponent implements OnInit {
  subjectDetails: any;
  subjectId: number = 0;
  availableTests: string[] = [];
  selectedTest: string = '';
  evaluationNote: number = 0;
  errorMessage: string = '';

  constructor(
    private subjectService: SubjectService,
    private subjectTestService: SubjectTestService,
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
        this.setAvailableTests();
      },
      err => {
        console.error("[HTTP ERROR] AddSubjectTestComponent -> ngOnInit()", err);
      }
    );
  }

  setAvailableTests() {
    const existingTests = this.subjectDetails.subjectTests.map((test: { testType: any; }) => test.testType);
    const allTests = ['CC', 'TP', 'EXAM'];
    this.availableTests = allTests.filter(test => !existingTests.includes(test));
  }

  addEvaluation() {
    if (this.selectedTest && this.evaluationNote >= 0 && this.evaluationNote <= 20) {
      const request: any = {
        testType: this.selectedTest,
        coefficient: this.evaluationNote,
        subjectId: this.subjectId
      };
      this.subjectTestService.createSubjectTest(request).subscribe(
        res => {
          this.router.navigate(['/api/dashboard/admin/admin-subject/details', this.subjectId]);
        },
        err => {
          this.errorMessage = 'Error adding test evaluation';
          console.error("[HTTP ERROR] AddSubjectTestComponent -> addEvaluation()", err);
        }
      );
    } else {
      this.errorMessage = 'Please select a valid test type and note';
    }
  }
}
