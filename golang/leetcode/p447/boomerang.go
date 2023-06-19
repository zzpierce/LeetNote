package main

import "fmt"

func main() {

	fmt.Println(numberOfBoomerangs([][]int{
		{0, 0}, {1, 0}, {2, 0},
	}))

	fmt.Println(numberOfBoomerangs([][]int{
		{1, 1}, {2, 2}, {3, 3},
	}))
}

func numberOfBoomerangs(points [][]int) int {
	l := len(points)
	ret := 0
	for i := 0; i < l; i++ {
		m := map[int]int{}
		for j := 0; j < l; j++ {
			if j != i {
				d := dis(points[i][0], points[i][1], points[j][0], points[j][1])
				if m[d] == 0 {
					m[d] = 1
				} else {
					ret += m[d] * 2
					m[d]++
				}
			}
		}
	}
	return ret
}

func dis(x, y, x1, y1 int) int {
	return (x1-x)*(x1-x) + (y1-y)*(y1-y)
}
