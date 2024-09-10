import { Component, OnInit  } from '@angular/core';
import { TestEvalutionService } from '../../../service/test-evalution.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-admin-test-evalution-final-result',
  templateUrl: './admin-test-evalution-final-result.component.html',
  styleUrls: ['./admin-test-evalution-final-result.component.css']
})
export class AdminTestEvalutionFinalResultComponent implements OnInit {
  studentInfo: any;

  constructor(
    private testEvalutionService: TestEvalutionService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getStudentEvaluation(Number(this.route.snapshot.paramMap.get('id')));
  }

  getStudentEvaluation(userId: number): void {
    this.testEvalutionService.calculateAvgGrade(userId).subscribe(
      (res: any) => {
        this.studentInfo = res;
      },
      (err) => {
        console.error("[HTTP ERROR] ClassroomDetailsComponent -> getStudentEvaluation()", err);
      }
    );
  }

}
