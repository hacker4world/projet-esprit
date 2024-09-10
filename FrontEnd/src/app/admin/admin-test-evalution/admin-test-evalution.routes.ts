import { Routes } from "@angular/router";
import { AdminTestEvalutionByStudentComponent } from "./admin-test-evalution-by-student/admin-test-evalution-by-student.component";
import { AdminTestEvalutionFinalResultComponent } from "./admin-test-evalution-final-result/admin-test-evalution-final-result.component";
import { AdminTestEvalutionAddTestComponent } from "./admin-test-evalution-add-test/admin-test-evalution-add-test.component";

export const routes: Routes = [

  {
    path: 'testByStudent/:id',
    component: AdminTestEvalutionByStudentComponent
  },
  {
    path: 'finalResultByStudent/:id',
    component: AdminTestEvalutionFinalResultComponent
  },
  {
    path: 'addtest/:id',
    component: AdminTestEvalutionAddTestComponent
  },

];
