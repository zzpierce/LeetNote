SELECT
	f.dname AS Department,
	ename AS Employee,
	Salary
FROM
	(
SELECT
	d.NAME AS dname,
	e1.NAME AS ename,
	e1.Salary,
	( SELECT count(distinct e2.salary) FROM Employee e2 WHERE e2.Salary > e1.Salary AND e2.DepartmentId = e1.DepartmentId ) AS c
FROM
	Employee e1
	INNER JOIN Department d ON e1.DepartmentId = d.Id
ORDER BY
	d.Id,
	e1.Salary DESC
	) f
WHERE
	c < 3;