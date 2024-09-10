import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../service/aut.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-regester',
  templateUrl: './regester.component.html',
  styleUrl: './regester.component.css',
})
export class RegesterComponent {
  registerForm: FormGroup;
  isSubmitted = false;
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private readonly router: Router
  ) {
    this.registerForm = this.fb.group(
      {
        firstName: ['', [Validators.required, Validators.minLength(3)]],
        lastName: ['', [Validators.required, Validators.minLength(3)]],
        email: ['', [Validators.required]],
        password: ['', [Validators.required, Validators.minLength(6)]],
        confirmPassword: ['', Validators.required],
      },
      { validator: this.passwordMatchValidator }
    );
  }
  passwordMatchValidator(form: FormGroup) {
    const password = form.get('password');
    const confirmPassword = form.get('confirmPassword');

    if (
      password &&
      confirmPassword &&
      password.value !== confirmPassword.value
    ) {
      confirmPassword.setErrors({ mismatch: true });
    } else {
      confirmPassword?.setErrors(null);
    }

    return null;
  }

  ngOnInit(): void {}
  get f() {
    return this.registerForm.controls;
  }
  onSubmit(): void {
    this.isSubmitted = true;
    if (this.registerForm.invalid) {
      this.errorMessage = 'Tous les champs sont obligatoires';

      return;
    }

    const { firstName, lastName, role, password, email } =
      this.registerForm.value;

    this.authService
      .register(firstName, lastName, role, password, email)
      .subscribe({
        next: (data) => {
          this.router.navigate(['./api/auth/login']);
        },
        error: (err) => {
          this.errorMessage = err.error.message;
        },
      });
  }
}
