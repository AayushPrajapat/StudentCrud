import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Student, StudentService } from './student.service';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css'],
})
export class StudentComponent implements OnInit {
  students: Student[] = [];

  student: Student = {
    firstName: '',
    lastName: '',
    dateOfBirth: '',
    email: '',
    phoneNumber: 0,
    address: '',
    enrollmentDate: '',
  };

  editingId: string | null = null;

  serverErrors: { [key: string]: string } = {};

  constructor(
    private studentService: StudentService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.loadStudents();
  }

  // Get all students
  loadStudents(): void {
    this.studentService.getStudents().subscribe({
      next: (data) => {
        this.students = data;
      },

      error: (error) => {
        console.error('Error loading students:', error);
        alert('Unable to load students.');
      },
    });
  }

  // Add or Update student
  saveStudent(): void {
    // Clear old errors
    this.serverErrors = {};

    if (this.editingId) {
      // Update
      this.studentService
        .updateStudent(this.editingId, this.student)
        .subscribe({
          next: () => {
            alert('Student updated successfully!');

            this.loadStudents();

            this.resetForm();
          },

          error: (error) => {
            console.error('Update validation error:', error);

            this.handleServerErrors(error);
          },
        });
    } else {
      // Add
      this.studentService.addStudent(this.student).subscribe({
        next: () => {
          alert('Student added successfully!');

          this.loadStudents();

          this.resetForm();
        },

        error: (error) => {
          console.error('Create validation error:', error);

          this.handleServerErrors(error);
        },
      });
    }
  }

  // Backend validation errors
  handleServerErrors(error: any): void {
    console.log('Full HTTP error:', error);
    console.log('Backend response:', error?.error);

    if (
      error?.error &&
      typeof error.error === 'object' &&
      !Array.isArray(error.error)
    ) {
      this.serverErrors = error.error;
    } else {
      alert('Something went wrong. Please try again.');
    }
  }

  // Edit student
  editStudent(student: Student): void {
    this.editingId = student.id || null;

    this.serverErrors = {};

    this.student = {
      id: student.id,
      firstName: student.firstName,
      lastName: student.lastName,
      dateOfBirth: student.dateOfBirth,
      email: student.email,
      phoneNumber: student.phoneNumber,
      address: student.address,
      enrollmentDate: student.enrollmentDate,
    };
  }

  // Delete student
  deleteStudent(id: string | undefined): void {
    if (!id) {
      return;
    }

    const confirmDelete = confirm(
      'Are you sure you want to delete this student?',
    );

    if (!confirmDelete) {
      return;
    }

    this.studentService.deleteStudent(id).subscribe({
      next: (response) => {
        console.log('Delete response:', response);

        alert('Student deleted successfully!');

        this.loadStudents();
      },

      error: (error) => {
        console.error('Error deleting student:', error);

        alert('Unable to delete student.');
      },
    });
  }

  // Cancel update
  cancelEdit(): void {
    this.resetForm();
  }

  logout(): void {
    this.router.navigate(['/signup']);
  }

  // Reset form
  resetForm(): void {
    this.editingId = null;

    this.serverErrors = {};

    this.student = {
      firstName: '',
      lastName: '',
      dateOfBirth: '',
      email: '',
      phoneNumber: 0,
      address: '',
      enrollmentDate: '',
    };
  }
}
