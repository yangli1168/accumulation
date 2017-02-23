package model;

public interface StudentMapper {
	public void add(Student student);
	public Student getone(int id);
	public Student get(int id);
	public void delete(int id);
	public void update1(Student student);
	public void update2(Student student);
}
