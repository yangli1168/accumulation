package model;

public interface CourseMapper {
	public void add(Course course);
	public Course getone(int id);
	public Course get(int id);
	public Course gets(int id);
	public void delete(int id);
	public void update1(Course course);
	public void update2(Course course);

}
