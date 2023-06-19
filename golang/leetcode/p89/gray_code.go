package main

import "fmt"

func main() {
	fmt.Println(grayCode(3))
	fmt.Println(grayCode(0))
}

func grayCode(n int) []int {
	r := []int{0}
	k := 1
	for n > 0 {
		n--
		l := len(r)
		for i := l - 1; i >= 0; i-- {
			r = append(r, k+r[i])
		}
		k *= 2
	}
	return r
}
