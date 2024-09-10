import { Component, OnInit } from '@angular/core';
import { TestEvalutionService } from '../../../service/test-evalution.service';
import { SubjectTestService } from '../../../service/subject-test.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-admin-test-evalution-add-test',
  templateUrl: './admin-test-evalution-add-test.component.html',
  styleUrls: ['./admin-test-evalution-add-test.component.css']
})
export class AdminTestEvalutionAddTestComponent implements OnInit {
  studentInfo: any;
  availableTests: any[] = [];
  selectedTest: any;
  evaluationNote: number = 0;
  errorMessage: string = '';
  userId: number = 0;

  constructor(
    private testEvalutionService: TestEvalutionService,
    private subjectTestService: SubjectTestService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.userId = Number(this.route.snapshot.paramMap.get('id'));
    this.getAllByUser(this.userId);
  }

  getAllByUser(userId: number): void {
    console.log(`userid: ${userId}`)
    this.testEvalutionService.getAllByUser(userId).subscribe(
      (res: any) => {
        this.studentInfo = res;
        this.getAllSubjectTests();
      },
      (err) => {
        console.error("[HTTP ERROR] ClassroomDetailsComponent -> getStudentEvaluation()", err);
      }
    );
  }

  getAllSubjectTests(): void {
    this.testEvalutionService.getMissingEvaluationsByUser(this.userId).subscribe(
      (res: any) => {
        this.availableTests = res;
        console.log("available test")
        console.log(this.availableTests);
      },
      (err) => {
        console.error("[HTTP ERROR] ClassroomDetailsComponent -> getAllSubjectTests()", err);
      }
    )
  }

  addEvaluation(): void {
    if (this.selectedTest && this.evaluationNote >= 0 && this.evaluationNote <= 20) {
      const postRequest = {
        note: this.evaluationNote,
        subjectTestId: this.selectedTest.subjectTestId,
        userId: this.userId
      };

      this.testEvalutionService.post(postRequest).subscribe(
        () => {
          this.router.navigate(['/api/dashboard/admin/admin-test-evalutions/testByStudent', this.studentInfo.studentId]);
        },
        (err) => {
          this.errorMessage = "An error occurred while adding the evaluation. Please try again.";
          console.error("[HTTP ERROR] ClassroomDetailsComponent -> addEvaluation()", err);
        }
      );
    } else {
      this.errorMessage = "Please select a valid test and enter a note between 0 and 20.";
    }
  }
}
