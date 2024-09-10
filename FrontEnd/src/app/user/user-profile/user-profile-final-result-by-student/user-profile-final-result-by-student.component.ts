import { Component, OnInit  } from '@angular/core';
import { TestEvalutionService } from '../../../service/test-evalution.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-profile-final-result-by-student',
  templateUrl: './user-profile-final-result-by-student.component.html',
  styleUrl: './user-profile-final-result-by-student.component.css'
})
export class UserProfileFinalResultByStudentComponent implements OnInit  {
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
        console.error("[HTTP ERROR] UserProfileFinalResultByStudentComponent -> getStudentEvaluation()", err);
      }
    );
  }
}
