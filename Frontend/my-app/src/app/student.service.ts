import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Student {
  id?: string;
  firstName: string;
  lastName: string;
  dateOfBirth: string;
  email: string;
  phoneNumber: number;
  address: string;
  enrollmentDate: string;
}

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  private apiUrl = 'http://localhost:8085/student';
 // private apiUrl = '/api/student';
  constructor(private http: HttpClient) {}

  // Get all students
  getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.apiUrl);
  }

  // Get student by ID
  getStudentById(id: string): Observable<Student> {
    return this.http.get<Student>(`${this.apiUrl}/${id}`);
  }

  // Create student
  addStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(this.apiUrl, student);
  }

  // Update student
  updateStudent(id: string, student: Student): Observable<Student> {
    return this.http.put<Student>(`${this.apiUrl}/${id}`, student);
  }

  // Delete student
  deleteStudent(id: string): Observable<string> {
    return this.http.delete(`${this.apiUrl}/${id}`, {
      responseType: 'text',
    });
  }
}
