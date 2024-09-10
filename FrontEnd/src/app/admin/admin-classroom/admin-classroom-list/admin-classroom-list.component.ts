import { Component, OnInit } from '@angular/core';
import { ClassroomService } from '../../../service/classroom.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-classroom-list',
  templateUrl: './admin-classroom-list.component.html',
  styleUrls: ['./admin-classroom-list.component.css'],
})
export class AdminClassroomListComponent implements OnInit {
  pageNumber = 1;
  pageSize = 5;
  totalPages: number = 0;
  classrooms: any[] = [];
  searchTerm: string = '';

  constructor(private classroomService: ClassroomService, private router: Router) { }

  ngOnInit(): void {
    this.getAllClassroomList();
  }

  getAllClassroomList() {
    this.classroomService.getAllClassrooms(this.pageNumber, this.pageSize).subscribe(
      (res: any) => {
        this.classrooms = res.classrooms;
        this.totalPages = res.totalPages;
        this.pageNumber = res.pageNumber;
        this.pageSize = res.pageSize;
      },
      err => {
        console.error("[HTTP ERROR] AdminClassroomListComponent -> getAllClassroomList()", err);
      }
    );
  }

  searchClassrooms() {
    if (this.searchTerm.trim() !== '') {
      this.resetPagination();
      this.classroomService.searchClassrooms(this.searchTerm, this.pageNumber, this.pageSize).subscribe(
        (res: any) => {
          this.classrooms = res.classrooms;
          this.totalPages = res.totalPages;
          this.pageNumber = res.pageNumber;
          this.pageSize = res.pageSize;
        },
        err => {
          console.error("[HTTP ERROR] AdminClassroomListComponent -> searchClassrooms()", err);
        }
      );
    } else {
      this.resetPagination();
      this.getAllClassroomList();  // If search term is empty, reload the paginated list
    }
  }

  resetPagination() {
    this.pageNumber = 1;
  }

  previousPage() {
    if (this.pageNumber > 1) {
      this.pageNumber--;
      this.getAllClassroomList();
    }
  }

  nextPage() {
    if (this.pageNumber < this.totalPages) {
      this.pageNumber++;
      this.getAllClassroomList();
    }
  }

  goToPage(page: number) {
    this.pageNumber = page;
    this.getAllClassroomList();
  }

  totalPagesArray() {
    return Array(this.totalPages).fill(0).map((x, i) => i + 1);
  }

  navigateToDetails(id: number) {
    this.router.navigate(['/api/dashboard/admin/admin-classroom/details/', id]);
  }
  goToAddNew(){
    this.router.navigate(['/api/dashboard/admin/admin-classroom/create']);
  }
}
