import { Component, OnInit  } from '@angular/core';
import { TestEvalutionService } from '../../../service/test-evalution.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ProcessedEvaluation } from '../../../models/processed-evaluation';
import { SubjectTestService } from '../../../service/subject-test.service';

@Component({
  selector: 'app-admin-test-evalution-by-student',
  templateUrl: './admin-test-evalution-by-student.component.html',
  styleUrls: ['./admin-test-evalution-by-student.component.css']
})
export class AdminTestEvalutionByStudentComponent implements OnInit {
  studentInfo: any;
  processedEvaluations: ProcessedEvaluation[] = [];
  allSubjectTests: any[] = [];
  missingTests: boolean = false;

  constructor(
    private testEvalutionService: TestEvalutionService,
    private subjectTestService: SubjectTestService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit(): void {
    const userId = Number(this.route.snapshot.paramMap.get('id'));
    this.getAllByUser(userId);
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

  checkMissingTests(): void {
    const existingTests = new Set(this.studentInfo.testEvalutionGradeAvgList.map((test: any) => `${test.subjectId}-${test.subjectTestType}`));
    this.missingTests = this.allSubjectTests.some((test: any) => !existingTests.has(`${test.subjectId}-${test.testType}`));

  }

  modifyEvaluation(evaluation: any, type: string): void {
    const matchingItem = this.studentInfo.testEvalutionGradeAvgList.find((item: any) =>
      item.subjectId === evaluation.subjectId && item.subjectTestType === type
    );

    if (matchingItem) {
      let newNote: string | null;
      let parsedNote: number | null;

      do {
        newNote = prompt(`Enter new note for ${evaluation.subjectName} (${type}) [0-20]:`, matchingItem.note);
        if (newNote !== null) {
          parsedNote = parseFloat(newNote);
          if (isNaN(parsedNote) || parsedNote < 0 || parsedNote > 20) {
            alert("Please enter a valid note between 0 and 20.");
          } else {
            matchingItem.note = parsedNote;
            evaluation[type as keyof ProcessedEvaluation] = parsedNote;

            // Create request payload
            const updateRequest = {
              id: matchingItem.testEvalutionId,
              note: parsedNote,
              subjectTestId: matchingItem.subjectTestId
            };

            // Call the service to update the backend
            this.testEvalutionService.put(updateRequest).subscribe(
              (res) => {
                console.log("Test evaluation updated successfully.", res);
              },
              (err) => {
                console.error("[HTTP ERROR] ClassroomDetailsComponent -> modifyEvaluation()", err);
              }
            );

            break;
          }
        } else {
          parsedNote = null;
          break;
        }
      } while (true);
    } else {
      console.error("[ERROR] ClassroomDetailsComponent -> modifyEvaluation() ---> No matching item found for modification.");
    }
  }

  goToFinalResult(): void {
    this.router.navigate(['/api/dashboard/admin/admin-test-evalutions/finalResultByStudent/', this.studentInfo.studentId]);
  }

  AddNewTestEvaltion() {
    this.router.navigate(['/api/dashboard/admin/admin-test-evalutions/addtest/', this.studentInfo.studentId]);
  }

}
