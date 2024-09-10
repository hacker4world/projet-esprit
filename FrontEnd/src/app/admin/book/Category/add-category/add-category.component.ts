import { Component, OnInit } from '@angular/core';
import { CategoryDTO } from '../../../../models/category.dto';
import { CategoryBookService } from '../../../../service/category-book.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-category',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './add-category.component.html',
  styleUrl: './add-category.component.css'
})
export class AddCategoryComponent implements OnInit {
  category : CategoryDTO;
  isEditMode: boolean = false;
  constructor(private categoryService : CategoryBookService, private router: Router){
    this.category = { id:0, categoryName: '', description: '' };
  }

    ngOnInit(): void {
      
    }
    saveCategory():void {
     
        this.categoryService.addCategory(this.category).subscribe(()=> {
          this.router.navigate(['/categories']);
        });
      
    }
}
