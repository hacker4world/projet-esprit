import { Component, OnInit } from '@angular/core';
import { TestEvalutionService } from '../../../service/test-evalution.service';
import { StorageService } from '../../../service/storage.service';
import { ProcessedEvaluation } from '../../../models/processed-evaluation';
import { ActivatedRoute, Router } from '@angular/router';
import { SubjectTestService } from '../../../service/subject-test.service';

@Component({
  selector: 'app-test-res',

  templateUrl: './test-res.component.html',
  styleUrl: './test-res.component.css'
})
export class TestResComponent implements OnInit {
  studentInfo: any;
  currentUser: any;
  processedEvaluations: ProcessedEvaluation[] = [];
  missingTests: boolean = false;
  allSubjectTests: any[] = [];

  constructor(
    private testEvalutionService: TestEvalutionService,
    private storage: StorageService,
    private subjectTestService: SubjectTestService,
    private router: Router,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.currentUser=this.storage.getUser();
    this.getAllByUser(this.currentUser.id);
  }

  getAllByUser(userId: number): void {
    this.testEvalutionService.getAllByUser(userId).subscribe(
      (res: any) => {
        this.studentInfo = res;
        this.processData();
        this.getAllSubjectTests(this.studentInfo.studentClassRoomId);
      },
      (err) => {
        console.error("[HTTP ERROR] ClassroomDetailsComponent -> getStudentEvaluation()", err);
      }
    );
  }

  processData(): void {
    const groupedEvaluations = this.studentInfo.testEvalutionGradeAvgList.reduce((acc: any, curr: any) => {
      let subject = acc.find((e: any) => e.subjectId === curr.subjectId);
      if (!subject) {
        subject = {
          subjectId: curr.subjectId,
          subjectName: curr.subjectName,
          CC: null,
          TP: null,
          EXAM: null,
        };
        acc.push(subject);
      }
      subject[curr.subjectTestType] = curr.note;
      return acc;
    }, []);

    this.processedEvaluations = groupedEvaluations;
  }


  getAllSubjectTests(classRoomId: number): void {
    this.subjectTestService.getAllByClassRoom(classRoomId).subscribe(
      (res: any) => {
        this.allSubjectTests = res;
        console.log("all tests")
        console.log(res);
        this.checkMissingTests();
      },
      (err) => {
        console.error("[HTTP ERROR] ClassroomDetailsComponent -> getAllSubjectTests()", err);
      }
    );
  }

  checkMissingTests(): void {
    const existingTests = new Set(this.studentInfo.testEvalutionGradeAvgList.map((test: any) => `${test.subjectId}-${test.subjectTestType}`));
    this.missingTests = this.allSubjectTests.some((test: any) => !existingTests.has(`${test.subjectId}-${test.testType}`));

  }

  goToFinalResult(): void {
    this.router.navigate(['api/user/manag/finalReuslt', this.studentInfo.studentId]);
  }


}

