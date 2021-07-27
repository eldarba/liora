package app.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentRestController {

	private List<Student> students = new ArrayList<>();

	// CRUD methods are mapped to HTTP methods
	// POST for create
	// GET for read
	// PUT for update
	// DELETE for delete

//	@RequestMapping(method = RequestMethod.POST, path = "/add")
	@PostMapping("/add")
	public void addStudent(@RequestBody Student student) {
		this.students.add(student);
	}

	@GetMapping("/get")
	public Student getStudent(@RequestParam int id) {
		for (Student student : students) {
			if (student.getId() == id) {
				return student;
			}
		}
		return null;
	}

	@PutMapping("/update")
	public boolean update(@RequestBody Student student) {
		for (Student curr : students) {
			if (curr.getId() == student.getId()) {
				curr.setAge(student.getAge());
				curr.setName(student.getName());
				curr.setEmail(student.getEmail());
				return true;
			}
		}
		return false;
	}

	@DeleteMapping("/delete")
	public String delete(int id) {
		Iterator<Student> it = this.students.iterator();
		while (it.hasNext()) {
			Student curr = it.next();
			if (curr.getId() == id) {
				it.remove();
				return "Deleted: " + curr;
			}
		}
		return "Delete Failed. id " + id + " not found";
	}

}
