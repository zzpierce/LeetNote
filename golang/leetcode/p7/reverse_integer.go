package main

import (
	"fmt"
	"strconv"
)

func reverse(x int) int {
	s := []int{}
	for x != 0 {
		s = append(s, x%10)
		x /= 10
	}
	base := 0
	for _, i := range s {
		base = base*10 + i
	}
	if base >= (1<<31) || base < (-1<<31) {
		return 0
	}
	return base
}

func main() {
	fmt.Println(reverse(1534236469))
	fmt.Println(reverse(-431))
	fmt.Println(1<<31 - 1)
	fmt.Println(-1 << 31)
	fmt.Println(strconv.IntSize)
}
