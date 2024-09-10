import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


import { CategoryBookService } from '../../../../service/category-book.service';
import { CategoryDTO } from '../../../../models/category.dto';

@Component({
  selector: 'app-category',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {
  categories: CategoryDTO[] = [];
  categoryForm!: FormGroup;
  selectedCategory: CategoryDTO | null = null;
  isModalOpen = false;  // Add this line
  constructor(
    private fb: FormBuilder,
    private categoryService: CategoryBookService
  ) {
    
  }

  ngOnInit(): void {
    this.categoryForm = this.fb.group({
      id: [null],
      categoryName: ['', Validators.required],
      description: ['', Validators.required],
      
    });
    this.loadCategories();
  }

  // Load all categories from the service
  loadCategories() {
    this.categoryService.getAllCategory().subscribe(categories => {
      this.categories = categories;
    });
  }

  // Open modal to add a new category
  // Open modal to add a new category
  openAddModal() {
    this.selectedCategory = null;
    this.categoryForm.reset();
    this.isModalOpen = true;  // Add this line
  }

  // Open modal to edit an existing category
  openEditModal(category: CategoryDTO) {
    this.selectedCategory = category;
    this.categoryForm.patchValue(category);
    this.isModalOpen = true;  // Add this line
  }
// Close the modal
closeModal() {
  this.isModalOpen = false;  // Add this line
}
  // Save category (add or update)
  saveCategory() {
    if (this.categoryForm.invalid) {
      return;
    }

    const category: CategoryDTO = this.categoryForm.value;
    console.log('Form Data:', category); 
    if (this.selectedCategory) {
      this.categoryService.updateCategory(category.id, category).subscribe(() => {
        this.loadCategories();
        this.closeModal();  // Add this line
      });
    } else {
      // Send HTTP request to backend
  this.categoryService.addCategory(category).subscribe((response) => {
    console.log('Response from backend:', response);  // Check the response from backend
    this.categories.push(response); // Assuming the backend returns the newly added category
    this.closeModal();
  });
    }
  }

  // Delete a category
  deleteCategory(category: CategoryDTO) {
    if (confirm(`Are you sure you want to delete the category "${category.categoryName}"?`)) {
      this.categoryService.deleteCategory(category.id).subscribe(() => {
        this.loadCategories();
      });
    }
  }
}
