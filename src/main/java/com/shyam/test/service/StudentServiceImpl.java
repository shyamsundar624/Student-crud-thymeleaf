package com.shyam.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyam.test.entity.Student;
import com.shyam.test.exception.StudentNotFoundException;
import com.shyam.test.repo.StudentRepository;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentRepository repo;

	@Override
	public Integer saveStudent(Student student) {
		// TODO Auto-generated method stub
		double per = (student.getMarks() * 100) / 500;
		student.setPercentage(per);
		return repo.save(student).getId();
	}

	@Override
	public void updateStudent(Student student) {
		// TODO Auto-generated method stub
		double per = (student.getMarks() * 100) / 500;
		student.setPercentage(per);
		repo.save(student);
	}

	@Override
	public void deleteStudent(Integer id) {
		// TODO Auto-generated method stub
		// repo.delete(getOneStudent(id));
		Optional<Student> student = repo.findById(id);
		if (student.isPresent()) {
			repo.delete(student.get());
		} else {
			throw new StudentNotFoundException("STUDENT '" + id + "' NOT FOUND");
		}

	}

	@Override
	public Student getOneStudent(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
